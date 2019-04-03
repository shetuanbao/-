package com.example.president;

import com.example.util.NetInfoUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class shetuanmanger extends Activity{
	TextView zengjiashetuan;
	String maxid="";
	String maxid2="";
	TextView shanchushetuan;
	TextView gaixiushetuan;
	TextView fanhui;
	TextView gaibian;
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.president_shetuan);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		fanhui=(TextView)findViewById(R.id.zhuxi_textview_1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		zengjiashetuan=(TextView)findViewById(R.id.zengjiashetuan);
		zengjiashetuan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(shetuanmanger.this,zengjiashetuan.class);
				it.putExtra("maxid", maxid);
				finish();
				startActivity(it);
			}
		});
		shanchushetuan=(TextView)findViewById(R.id.shanchushetuan);
		shanchushetuan.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent it=new Intent(shetuanmanger.this,shanchushetuan.class);
					startActivity(it);
				}
			});
		gaixiushetuan=(TextView)findViewById(R.id.xiugaishetuan);
		gaixiushetuan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(shetuanmanger.this,xiugaishetuanzhu.class);
				startActivity(it);
			}
		});
		gaibian=(TextView)findViewById(R.id.jiechushetuan);
		gaibian.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(shetuanmanger.this,fengjinshetuan.class);
				startActivity(it);
			}
		});
		thread_getmax gg=new thread_getmax();
		gg.start();
		try{
			gg.join();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	Handler mHandler=new Handler()
	{
		 @Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case 0:pd.dismiss();			
			break;
			case 1:pd.show();
			
			break;
			}
		}
	};

	private class thread_getmax extends Thread{
		@Override
		public void run(){
			maxid=NetInfoUtil.GetShetuanMaxid();
		}
	}
}
