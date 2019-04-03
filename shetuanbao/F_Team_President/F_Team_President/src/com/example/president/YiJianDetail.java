package com.example.president;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class YiJianDetail extends Activity{
	EditText name;
	EditText time;
	EditText detail;
	EditText lianxi;
	TextView fanhui;
	String name1,time1,lianxi1,detail1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yijiandetail);
		name=(EditText)findViewById(R.id.yijian_11);
		time=(EditText)findViewById(R.id.yijian_tijiao_3);
		lianxi=(EditText)findViewById(R.id.yijian_lianxi_3);
		detail=(EditText)findViewById(R.id.yijian_13);
		fanhui=(TextView)findViewById(R.id.yijian_text1_2);
		Intent intent = getIntent();
		name1 = intent.getStringExtra("name");
		time1 = intent.getStringExtra("time");
		detail1 = intent.getStringExtra("detail");
		lianxi1 = intent.getStringExtra("lianxi");
		name.setText(name1);
		time.setText(time1);
		lianxi.setText(lianxi1);
		detail.setText(detail1);
        fanhui.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
