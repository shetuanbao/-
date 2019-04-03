package com.bn.user_myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bn.util.FontManager;
import com.example.chat.R;


public class user_lianxiwomen_weiboActivity extends Activity{
	TextView fanhui=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.geren_lianxiwomen_weibo);
		FontManager.changeFonts(FontManager.getContentView(this), this);
		fanhui=(TextView)findViewById(R.id.weibotool_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
