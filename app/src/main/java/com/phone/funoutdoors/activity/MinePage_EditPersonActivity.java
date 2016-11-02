package com.phone.funoutdoors.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.db.UserDBManager;
import com.phone.funoutdoors.utils.PopupWindowScreen;
import com.phone.funoutdoors.view.PersonInfoItem;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MinePage_EditPersonActivity extends AppCompatActivity {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.person_head)
    PersonInfoItem personHead;
    @BindView(R.id.person_nickname)
    PersonInfoItem personNickname;
    @BindView(R.id.person_sex)
    PersonInfoItem personSex;
    @BindView(R.id.person_birth)
    PersonInfoItem personBirth;
    @BindView(R.id.person_qr)
    PersonInfoItem personQr;
    @BindView(R.id.person_city)
    PersonInfoItem personCity;
    @BindView(R.id.person_description)
    PersonInfoItem personDescription;
    private String imagePath;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_page_edit_person_info);
        ButterKnife.bind(this);
        initToolBar();
        queryUser();
    }

    /**
     * 设置toolBar
     */
    private void initToolBar() {
        line.setBackgroundColor(Color.parseColor("#8e8f90"));
        homePageBannerToolbarTitle.setText("个人资料");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.person_head, R.id.person_nickname, R.id.person_sex, R.id.person_birth, R.id.person_qr, R.id.person_city, R.id.person_description})
    public void onClick(View view) {
        PopupWindowScreen instance = PopupWindowScreen.getInstance();
        switch (view.getId()) {
            case R.id.person_head:
                instance.showDialog(this, this);
                break;
            case R.id.person_nickname:
                break;
            case R.id.person_sex:
                Log.e("TAG", "性别");
                break;
            case R.id.person_birth:
                Log.e("TAG", "生日");
                break;
            case R.id.person_qr:
                Intent intent = new Intent(this, MinePageQRActivity.class);
                startActivity(intent);
                break;
            case R.id.person_city:
                Intent intent1 = new Intent(this, CityActivity.class);
                startActivityForResult(intent1, 100);
                break;
            case R.id.person_description:
                Log.e("TAG", "签名");
                break;
        }
        overridePendingTransition(R.anim.activity_open, 0);
    }

    /**
     * 查询用户
     */
    private void queryUser() {
        long id = getSharedPreferences("config", Context.MODE_PRIVATE).getLong("userId", 0);
        List<User> user = UserDBManager.getInstance(this).findByUser(id);
        this.user = user.get(0);
        setPersonHead();
        setNickname();
        setPersonSex();
        setPersonBirthday();
        setPersonDescription();
        setPersonQr();
        setCity();
    }

    /**
     * 设置我的城市
     */
    private void setCity() {
        String city = user.getCity();
        if (!TextUtils.isEmpty(city)) {
            TextView v = new TextView(this);
            v.setPadding(2, 2, 2, 2);
            v.setGravity(Gravity.CENTER);
            v.setText(city);
            personCity.addView(v);
        }
    }

    /**
     * 设置二维码
     */
    private void setPersonQr() {
        ImageView v = new ImageView(this);
        v.setPadding(5, 5, 5, 5);
        v.setImageResource(R.mipmap.dimensionalcode);
        personQr.addView(v);
    }

    /**
     * 设置签名
     */
    private void setPersonDescription() {
        String description = user.getDescription();
        if (!TextUtils.isEmpty(description)) {
            TextView v = new TextView(this);
            v.setPadding(2, 2, 2, 2);
            v.setGravity(Gravity.CENTER);
            v.setText(description);
            personDescription.addView(v);
        }
    }

    /**
     * 设置生日
     */
    private void setPersonBirthday() {
        String birth = user.getBirthday();
        if (!TextUtils.isEmpty(birth)) {
            TextView v = new TextView(this);
            v.setPadding(2, 2, 2, 2);
            v.setGravity(Gravity.CENTER);
            v.setText(birth);
            personBirth.addView(v);
        }

        personBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //创建一个日期弹出框
                DatePickerDialog dialog = new DatePickerDialog(MinePage_EditPersonActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(year).append("-").append(monthOfYear).append("-").append(dayOfMonth);
                        user.setBirthday(sb.toString());
                        UserDBManager.getInstance(MinePage_EditPersonActivity.this).update(user);
                        recreate();
                    }
                }, year, month, day);
                dialog.show();
            }
        });
    }

    /**
     * 设置性别
     */
    private void setPersonSex() {
        int gender = user.getGender();
        String sex = "";
        if (gender == 0) {
            sex = "男";
        } else {
            sex = "女";
        }
        TextView v = new TextView(this);
        v.setPadding(2, 2, 2, 2);
        v.setGravity(Gravity.CENTER);
        v.setText(sex);
        personSex.addView(v);
    }

    /**
     * 设置昵称
     */
    private void setNickname() {
        String nickname = user.getNickName();
        if (!TextUtils.isEmpty(nickname)) {
            TextView v = new TextView(this);
            v.setPadding(2, 2, 2, 2);
            v.setGravity(Gravity.CENTER);
            v.setText(nickname);
            personNickname.addView(v);
        }
    }

    /**
     * 设置头像
     */
    private void setPersonHead() {
        CircleImageView v = new CircleImageView(this);
        v.setBackgroundResource(R.drawable.circle_pic_bind);
        v.setPadding(2, 2, 2, 2);
        if (TextUtils.isEmpty(user.getHeadIcon())) {
            v.setImageResource(R.mipmap.avtor_default);
        } else {
            Glide.with(this).load(user.getHeadIcon()).into(v);
        }
        personHead.addView(v);
        personHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindowScreen instance = PopupWindowScreen.getInstance();
                View view = instance.showDialog(MinePage_EditPersonActivity.this, MinePage_EditPersonActivity.this);
                handleCamera(view, instance);
                handlePhotoAbum(view, instance);
                handleCanncel(view, instance);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 201 && resultCode == RESULT_OK && data != null) {
            //打开相册返回的结果
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            user.setHeadIcon(picturePath);
        } else if (requestCode == 100) {
            //城市列表返回的结果
            String city = data.getStringExtra("city");
            user.setCity(city);
        } else if (requestCode == 200) {
            //打开拍照返回的结果
            if (resultCode == RESULT_OK) {
                user.setHeadIcon(imagePath);
            }
        }
        UserDBManager.getInstance(this).update(user);
        recreate();
    }

    /**
     * 点击拍照
     *
     * @param view
     */
    private void handleCamera(View view, final PopupWindowScreen popupWindowScreen) {
        TextView camera = (TextView) view.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    imagePath = makeFiles();
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imagePath)));
                    startActivityForResult(intent, 200);
                    popupWindowScreen.hiddenDialog();
                } else {
                    Toast.makeText(MinePage_EditPersonActivity.this, "sd卡不可用", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 点击相机
     *
     * @param view
     */
    private void handlePhotoAbum(View view, final PopupWindowScreen popupWindowScreen) {
        TextView photoAbum = (TextView) view.findViewById(R.id.photo_abum);
        photoAbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(albumIntent, 201);
                popupWindowScreen.hiddenDialog();
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
        options.inSampleSize = 5;
        //设置图片的每个颜色基数在内存所占的字节数
        options.inPreferredConfig = Bitmap.Config.RGB_565;//使用推荐的
        //设置第二次解码范围
        options.inJustDecodeBounds = false;
        //解码缩小后的图片并放回
        return BitmapFactory.decodeFile(imgePath, options);
    }
}
