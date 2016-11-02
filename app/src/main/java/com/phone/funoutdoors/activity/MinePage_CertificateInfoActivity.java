package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.Constant;
import com.phone.funoutdoors.utils.PopupWindowDialog;
import com.phone.funoutdoors.utils.PopupWindowScreen;
import com.phone.funoutdoors.view.IDCartView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinePage_CertificateInfoActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.toolbar_layout)
    LinearLayout toolbarLayout;
    @BindView(R.id.commit_bnt)
    Button commitBnt;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.card_id)
    EditText cardId;
    @BindView(R.id.cart_font_image)
    IDCartView cartFontImage;
    @BindView(R.id.cart_back_image)
    IDCartView cartBackImage;
    @BindView(R.id.people_card)
    IDCartView peopleCard;
    private ImageView imageView = null;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_page_certificate_info);
        ButterKnife.bind(this);
        initToolbar();
    }

    @OnClick({R.id.commit_bnt, R.id.cart_font_image, R.id.cart_back_image, R.id.people_card})
    public void onClick(View view) {
        PopupWindowScreen popupWindowScreen = PopupWindowScreen.getInstance();
        View v = null;
        switch (view.getId()) {
            case R.id.commit_bnt:
                commitData();
                break;
            case R.id.cart_font_image:
                imageView = cartFontImage.getBgImage();
                v = popupWindowScreen.showDialog(this, this);handleCamera(v);
                handlePhotoAbum(v);
                handleCanncel(v, popupWindowScreen);

                break;
            case R.id.cart_back_image:
                imageView = cartBackImage.getBgImage();
                v = popupWindowScreen.showDialog(this, this);
                handleCamera(v);
                handlePhotoAbum(v);
                handleCanncel(v, popupWindowScreen);
                break;
            case R.id.people_card:
                imageView = peopleCard.getBgImage();
                v = popupWindowScreen.showDialog(this, this);
                handleCamera(v);
                handlePhotoAbum(v);
                handleCanncel(v, popupWindowScreen);
                break;
        }

    }

    /**
     * 提交数据
     */
    private void commitData() {
        PopupWindowDialog windowDialog = PopupWindowDialog.getInstance();
        String username = name.getText().toString();
        if (TextUtils.isEmpty(username)) {
            windowDialog.showPopupWindowDialog("请输入姓名", Constant.TEXTVIEW_AND_BUTTON, this);
        } else {
            String id = cardId.getText().toString();
            if (TextUtils.isEmpty(id)) {
                windowDialog.showPopupWindowDialog("请输入正确的身份证号码！", Constant.TEXTVIEW_AND_BUTTON, this);
            } else {

            }
        }
    }

    /**
     * 设置toolBar
     */
    private void initToolbar() {
        line.setVisibility(View.GONE);
        homePageBannerToolbarTitle.setText("实名认证");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 点击拍照
     *
     * @param view
     */
    private void handleCamera(View view) {
        TextView camera = (TextView) view.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    imagePath = makeFiles();
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imagePath)));
                    startActivityForResult(intent, 100);
                } else {
                    Toast.makeText(MinePage_CertificateInfoActivity.this, "sd卡不可用", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 点击相机
     *
     * @param view
     */
    private void handlePhotoAbum(View view) {
        TextView photoAbum = (TextView) view.findViewById(R.id.photo_abum);
        photoAbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(albumIntent, 101);
            }
        });
    }

    /**
     * 点击取消按钮操作
     *
     * @param view
     * @param popupWindowScreen
     */
    private void handleCanncel(View view, final PopupWindowScreen popupWindowScreen) {
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowScreen.hiddenDialog();
            }
        });
    }


    /**
     * 创建图片存放文件
     */
    private String makeFiles() {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(root, "1613");
        if (!file.exists()) {
            file.mkdirs();
        }
        File imagFile = new File(file, System.currentTimeMillis() + ".jpg");
        String imgePath = imagFile.getAbsolutePath();
        return imgePath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bitmap map = resetBitmap(imagePath, 0, 0);
                imageView.setImageBitmap(map);
            } else {
                Toast.makeText(MinePage_CertificateInfoActivity.this, "结果码不匹配", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MinePage_CertificateInfoActivity.this, "响应码不匹配", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 图片二次采样
     *
     * @param imgePath 图片路径
     * @param width    要求图片的宽
     * @param height   要求图片的高
     * @return 返回缩小之后的图片
     */
    private Bitmap resetBitmap(String imgePath, int width, int height) {
        //获得一个options对象
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置只解码图片边缘，避免内存溢出
        options.inJustDecodeBounds = true;
        //解码原图片,返回的一个空对象
        BitmapFactory.decodeFile(imgePath, options);
        //设置缩小倍数，
        options.inSampleSize = 4;
        //设置图片的每个颜色基数在内存所占的字节数
        options.inPreferredConfig = Bitmap.Config.RGB_565;//使用推荐的
        //设置第二次解码范围
        options.inJustDecodeBounds = false;
        //解码缩小后的图片并放回
        return BitmapFactory.decodeFile(imgePath, options);
    }
}
