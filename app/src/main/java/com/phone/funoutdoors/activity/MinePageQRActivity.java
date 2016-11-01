package com.phone.funoutdoors.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.db.UserDBManager;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MinePageQRActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.mine_icon)
    CircleImageView mineIcon;
    @BindView(R.id.mine_nick)
    TextView mineNick;
    @BindView(R.id.mine_qr)
    ImageView mineQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_qr);
        ButterKnife.bind(this);
        long userId = getSharedPreferences("config", MODE_PRIVATE).getLong("userId", 0);
        initToolBar();
        getMineInfo(userId);
    }

    /**
     * @param userId
     */
    private void getMineInfo(long userId) {
        List<User> byUser = UserDBManager.getInstance(this).findByUser(userId);
        User user = byUser.get(0);
        if (user != null) {
            setUserInfo(user);
        }
    }

    /**
     * 填充我的信息
     *
     * @param user
     */
    private void setUserInfo(User user) {
        if (!TextUtils.isEmpty(user.getNickName())) {
            mineNick.setText(user.getNickName());
        }
        if (TextUtils.isEmpty(user.getHeadIcon())) {
            mineIcon.setImageResource(R.mipmap.avtor_default);
        } else {
            Glide.with(this).load(user.getHeadIcon()).into(mineIcon);
        }
        //生成我的二维码
        StringBuffer sb = new StringBuffer();
        sb.append(user.getPhone()).append(user.getBirthday()).append(user.getDescription()).append(user.getBirthday())
                .append(user.getGender()).append(user.getNickName())
                .append(user.getUser_id());
        Resources r = getResources();
        InputStream inputStream = r.openRawResource(R.raw.app_icon);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(inputStream);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap bitmap1 = generateQRCode(sb.toString(), 500, bitmap);
        mineQr.setImageBitmap(bitmap1);
    }

    /**
     * 设置toolbar
     */
    private void initToolBar() {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText("我的二维码");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.mine_qr)
    public void onClick() {
    }

    /**
     * 生成中心带图片的二维码
     *
     * @param text：需要生成二维码的文字信息
     * @param widthAndHeight：生成二维码的宽和高
     * @param mBitmap：二维码中心的图片
     * @return 返回中心带图片的二维码的bitmap
     */
    public Bitmap generateQRCode(String text, int widthAndHeight, Bitmap mBitmap) {
        //中心图的宽高
        int IMAGE_HALFWIDTH = 50;
        Matrix m = new Matrix();
        float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
        float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
        m.setScale(sx, sy);//设置缩放信息
        //将logo图片按martix设置的信息缩放
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                mBitmap.getWidth(), mBitmap.getHeight(), m, false);


        //imageView.setBackgroundDrawable(new BitmapDrawable(mBitmap));
        Hashtable<EncodeHintType, Object> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(text.toString(), BarcodeFormat.QR_CODE,
                            widthAndHeight, widthAndHeight);
            int[] pixles = new int[widthAndHeight * widthAndHeight];
            int halfW = widthAndHeight / 2;
            int halfH = widthAndHeight / 2;
            //控制行数
            for (int i = 0; i < widthAndHeight; i++) {
                //控制列数
                for (int j = 0; j < widthAndHeight; j++) {
                    if (i > halfW - IMAGE_HALFWIDTH && i < halfW + IMAGE_HALFWIDTH
                            && j > halfH - IMAGE_HALFWIDTH
                            && j < halfH + IMAGE_HALFWIDTH) {
                        //该位置用于存放图片信息
                        //记录图片每个像素信息
                        pixles[i * widthAndHeight + j] = mBitmap.getPixel
                                (j - halfH + IMAGE_HALFWIDTH,
                                        i - halfW + IMAGE_HALFWIDTH);
                    } else {
                        if (bitMatrix.get(i, j)) {//如果有黑块点，记录信息
                            pixles[i * widthAndHeight + j] = Color.BLACK;//记录黑块信息
                        }
                    }
                }
            }
            Bitmap bmp1 = Bitmap.createBitmap(widthAndHeight, widthAndHeight, Bitmap.Config.ARGB_8888);
            bmp1.setPixels(pixles, 0, widthAndHeight, 0, 0, widthAndHeight, widthAndHeight);
            return bmp1;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
