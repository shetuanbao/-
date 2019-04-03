package com.bn.manage;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class chakanxiangceActivity extends Activity{
	private String xiangcename=null;
	Bitmap bt=null;
	ImageView im;
	TextView fanhui;
	TextView shanchu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guanli_chakanxiangce);
		Intent intent = getIntent();
		xiangcename = intent.getStringExtra("name");
		bt = intent.getParcelableExtra("xiangce");
		im=(ImageView)findViewById(R.id.xiangcexiugai5);
		fanhui=(TextView)findViewById(R.id.xiangce_juti_fanhui_5);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		shanchu=(TextView)findViewById(R.id.xiangce_shachu);
		shanchu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				thread_delete dt=new thread_delete();
				dt.start();
				try{
					dt.join();
				}catch(Exception e){
					e.printStackTrace();
				}
			
				MainActivity.data.remove(MainActivity.x);
				MainActivity.base.notifyDataSetChanged();
				finish();
				//startActivity(it);
			}
		});
		im.setImageBitmap(bt);
	}
	private class thread_delete extends Thread{
		@Override
		public void run(){
			NetInfoUtil.deletexiangce(xiangcename);
		}
	}
}
