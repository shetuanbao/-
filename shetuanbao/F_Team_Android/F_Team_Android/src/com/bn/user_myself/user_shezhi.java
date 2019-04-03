package com.bn.user_myself;

import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bn.login.LoginActivity;
import com.bn.util.Constant;
import com.bn.util.FontManager;
import com.example.chat.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class user_shezhi extends Activity{
	TextView fanhui=null;
	TextView gerenziliao=null;
	Button zhuxiao=null;
	private TextView my_Qr=null;
	private  static final int QR_WIDTH=300;
	private static final int QR_HEIGHT=300;
	String name=null;
	//public static RoundImageView touxiang=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shezhi_activity);
		Intent intent =new Intent();
		name=intent.getStringExtra("name");
		//touxiang=(RoundImageView)findViewById(R.id.)
		FontManager.changeFonts(FontManager.getContentView(this), this);
		fanhui=(TextView)findViewById(R.id.emailtoolshezhi_text1);
		fanhui.setTypeface(FontManager.tf);
		fanhui.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		gerenziliao=(TextView)findViewById(R.id.set_message);
		gerenziliao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
					Intent it=new Intent(user_shezhi.this,user_gerenziliao.class);
					startActivity(it);
				
			}
		});
		zhuxiao=(Button)findViewById(R.id.zhuxiao);
        zhuxiao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent logoutIntent = new Intent(user_shezhi.this, LoginActivity.class);

				logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

				startActivity(logoutIntent);
				LoginActivity.sp.edit().clear().commit();
			}
		});
        my_Qr=(TextView)findViewById(R.id.my_erweima);
        my_Qr.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bitmap bitmap=createBitmap(Constant.userName);
				Intent intent=new Intent(user_shezhi.this,user_Qr.class);
				intent.putExtra("name", name);
				intent.putExtra("Bitmap",bitmap);
				startActivity(intent);
				
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}
	private Bitmap createBitmap(String text) {  
        Bitmap bitmap = null;  
        try {  
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,  
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);  
  
            // QRCodeWriter writer = new QRCodeWriter();  
            // // 把输入的文本转为二维码  
            // BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE,  
            // QR_WIDTH, QR_HEIGHT);  
  
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];  
            for (int y = 0; y < QR_HEIGHT; y++) {  
                for (int x = 0; x < QR_WIDTH; x++) {  
                    if (bitMatrix.get(x, y)) {  
                        pixels[y * QR_WIDTH + x] = 0xff000000;  
                    } else {  
                        pixels[y * QR_WIDTH + x] = 0xffffffff;  
                    }  
  
                }  
            }  
            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,  
                    Bitmap.Config.ARGB_8888);  
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);  
  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
        return bitmap;  
    }  
  
}
