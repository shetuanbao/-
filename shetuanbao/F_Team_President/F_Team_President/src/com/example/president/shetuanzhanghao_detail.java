package com.example.president;

import java.util.ArrayList;
import java.util.List;

import com.example.util.NetInfoUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class shetuanzhanghao_detail extends Activity{
	String name=null;
	String password=null;
	EditText name2;
	EditText password2;
	TextView baocun;
	String mes=null;
	String p=null;
	List<String[]> idy=new ArrayList<String[]>();
	String all[][]=null;
	String id[]=null;
	TextView fanhui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shetuan_zhanghao_detail);
		fanhui=(TextView)findViewById(R.id.shetuanzhanghao_fanhui_2);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Intent intent=getIntent();
		name=intent.getStringExtra("name");
		password=intent.getStringExtra("password");
		name2=(EditText)findViewById(R.id.shetuan_zhanghao_name_3);
		name2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(shetuanzhanghao_detail.this, "社团名称不可在此处修改!!",
						Toast.LENGTH_LONG).show();
			}
		});
		password2=(EditText)findViewById(R.id.sheutuanzhanghao_mima_3);
		name2.setHint(name);
		password2.setText(password);
		baocun=(TextView)findViewById(R.id.shetuan_zhanghao_baocun);
		baocun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				thread_get gg=new thread_get();
				gg.start();
				try{
					gg.join();
				}catch(Exception e){
					e.printStackTrace();
				}
				Toast.makeText(shetuanzhanghao_detail.this, "保存成功!!",
						Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}
	private class thread_get extends Thread{
		@Override
		public void run(){
			idy=NetInfoUtil.getshetuanidbyname(name);
			if (!password2.getText().toString().equals("")) {
				p = password2.getText().toString();
			} else {
				p = password2.getHint().toString();
			}
			mes = p;
			mes=mes+"#"+idy.get(0)[0];
			NetInfoUtil.updataguanlimima(mes);
		}
	}
}
