package com.bn.user_myself;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.Constant;
import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.bn.util.RoundImageView;
import com.example.chat.R;
public class user_Qr extends  Activity
{
	private ImageView qr=null;
	RoundImageView touxiang;
    TextView name;
	TextView fanhui;
	String image=null;
	Bitmap touxiang2=null;
	byte all_image[]=null;
	List<String[]> name2=new ArrayList<String[]>();
	TextView baocun;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_qr);
		fanhui=(TextView)findViewById(R.id.erweima_fanhui);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		name=(TextView)findViewById(R.id.erweima_text);
		touxiang=(RoundImageView)findViewById(R.id.QR_1);
		qr=(ImageView)findViewById(R.id.QR);
		Intent intent=getIntent();
		final Bitmap bitmap=intent.getParcelableExtra("Bitmap");
		qr.setImageBitmap(bitmap);
		thread_get gg=new thread_get();
		gg.start();
		try{
			gg.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		name.setText(name2.get(0)[0]);
		touxiang.setImageBitmap(touxiang2);
		baocun=(TextView)findViewById(R.id.baocun);
		baocun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				all_image = stream.toByteArray();
				F_GetBitmap.setInSDBitmap(all_image, name2.get(0)[0]+".png");
				Toast.makeText(user_Qr.this, "保存成功!!",
						Toast.LENGTH_LONG).show();
			}
		});
		FontManager.initTypeFace(this);
		FontManager.changeFonts(FontManager.getContentView(this), this);
	}
	private class thread_get extends Thread{
		@Override
		public void run()
		{
			name2=NetInfoUtil.getusernamebuid(Constant.userName);
			image=NetInfoUtil.getuserpicture(Constant.userName);
			touxiang2 = F_GetBitmap.getSDBitmap(image);// �õ�����BitMap���͵�ͼƬ���
			if (F_GetBitmap.bitmap != null && !F_GetBitmap.bitmap.isRecycled()) 
			     {F_GetBitmap.bitmap = null;}
		}
	}
}
