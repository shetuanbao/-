package com.bn.user_myself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bn.util.FontManager;
import com.example.chat.R;


public class user_lianxiwomen extends Activity{

    TextView fanhui=null;
    TextView lianxiwomenqq=null;
    TextView lianxiwomenweixin=null;
    TextView lianxiwomenweibo=null;
    TextView lianxiwomendianhua=null;
    TextView lianxiwomenyouxiang=null;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lianxi);
		FontManager.changeFonts(FontManager.getContentView(this), this);
		fanhui=(TextView)findViewById(R.id.emailtool_text1);
		fanhui.setTypeface(FontManager.tf);
	    fanhui.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	    
	    lianxiwomenqq=(TextView)findViewById(R.id.lianxi_qq);
	    lianxiwomenqq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(user_lianxiwomen.this,user_lianxiwomen_qqActivity.class);
				//����Activity
				startActivity(it);
			}
		});
	    
	    lianxiwomenweixin=(TextView)findViewById(R.id.lianxi_weixin);
	    lianxiwomenweixin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(user_lianxiwomen.this,user_lianxiwomen_weixinActivity.class);
				//����Activity
				startActivity(it);
			}
		});
	    
	    lianxiwomenweibo=(TextView)findViewById(R.id.lianxi_guanfangweibo);
	    lianxiwomenweibo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(user_lianxiwomen.this,user_lianxiwomen_weiboActivity.class);
				//����Activity
				startActivity(it);
			}
		});
	    
	    lianxiwomendianhua=(TextView)findViewById(R.id.lianxi_dianhua);
	    lianxiwomendianhua.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(user_lianxiwomen.this,user_lianxiwomen_dianhuaActivity.class);
				//����Activity
				startActivity(it);
			}
		});
	    
	    lianxiwomenyouxiang=(TextView)findViewById(R.id.lianxi_lianxiyouxiang);
	    lianxiwomenyouxiang.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(user_lianxiwomen.this,user_lianxiwomen_youxiangActivity.class);
				//����Activity
				startActivity(it);
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
}
