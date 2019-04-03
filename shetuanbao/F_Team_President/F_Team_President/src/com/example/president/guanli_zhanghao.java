package com.example.president;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class guanli_zhanghao extends Activity{
	TextView geren;
	TextView shetuan;
	TextView fanhui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guanli_zhanghao);
		fanhui=(TextView)findViewById(R.id.zhanghao_fanhui);
		fanhui.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		geren=(TextView)findViewById(R.id.geren_zhanghao);
		geren.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//pd.show();
				Intent it=new Intent(guanli_zhanghao.this,gerenActivity.class);
				startActivity(it);
				//mHandler.sendEmptyMessage(0);
			}
		});
		shetuan=(TextView)findViewById(R.id.zhanghao_shetuan);
		shetuan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//pd.show();
				Intent it=new Intent(guanli_zhanghao.this,shetuanzhanghao.class);
				startActivity(it);
				//mHandler.sendEmptyMessage(0);
			}
		});
	}
}
