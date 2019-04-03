package com.bn.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bn.util.NetInfoUtil;
import com.example.Team_Android.MainFrame;
import com.example.chat.R;

public class user_guanligongneng extends Activity{

	TextView guanlichengyuan=null;
	TextView guanlihuodong=null;
	TextView fanhui=null;
	TextView guanlishetuan=null;
	TextView guanlixiangce=null;
	String password=null;
	String id=null;
	TextView huodongrenyuan=null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_guanli_guanligongneng);
		Intent intent = getIntent();
		password = intent.getStringExtra("password");
		fanhui=(TextView)findViewById(R.id.guanliyuan_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(user_guanligongneng.this,MainFrame.class);
				startActivity(it);
			}
		});
		/*guanlishetuan=(TextView)findViewById(R.id.guanli_tianjiashetuan);
		guanlishetuan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(user_guanligongneng.this,guanlishetuan.class);
				startActivity(it);
			}
		});*/
		guanlichengyuan=(TextView)findViewById(R.id.guanli_tianjiachengyuan);
		guanlichengyuan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(user_guanligongneng.this,guanlichengyuan.class);
				startActivity(it);
			}
		});
		guanlihuodong=(TextView)findViewById(R.id.guanli_tianjiahuodong);
		guanlihuodong.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(user_guanligongneng.this,huodonglibiao.class);
				startActivity(it);
			}
		});
		guanlixiangce=(TextView)findViewById(R.id.shetuanxiangce_manger);
		guanlixiangce.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(user_guanligongneng.this,huodongxiangce.class);
				it.putExtra("id", id);
				startActivity(it);
			}
		});
		huodongrenyuan=(TextView)findViewById(R.id.huodong_renyuan);
        huodongrenyuan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(user_guanligongneng.this,huodongrenyusn.class);
				startActivity(it);
			}
		});
		thread th=new thread();
		th.start();
		try{
			th.join();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private class thread extends Thread{
		@Override
		public void run(){
			id=NetInfoUtil.getcommunityid(password);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)      //��д���ؼ�
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent it=new Intent(user_guanligongneng.this,MainFrame.class);
			startActivity(it);
		}
		return true;
	}

}
