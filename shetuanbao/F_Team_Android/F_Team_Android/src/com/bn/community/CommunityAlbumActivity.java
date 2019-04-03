package com.bn.community;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.chat.R;

public class CommunityAlbumActivity extends Activity {
	Bitmap bb;
	Bitmap bb2;
	ImageView ttb = null;
	String re_id = null;
	String re_kouhao = null;
	String re_name = null;
	ImageView imageView2;
	byte bbb[] = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiangce);
		Intent intent = getIntent();
		re_name = intent.getStringExtra("name");
		re_id = intent.getStringExtra("id");
		re_kouhao = intent.getStringExtra("kouhao");

//		bb = intent.getParcelableExtra("picture");
		bb2 = intent.getParcelableExtra("xiangce");
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		bb.compress(Bitmap.CompressFormat.PNG, 100, baos);
//		bbb = baos.toByteArray();
		imageView2 = (ImageView) findViewById(R.id.xiangce);
		imageView2.setImageBitmap(bb2);
		// t4=(LinearLayout)findViewById(R.id.shetuan_button_4);
		imageView2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(CommunityAlbumActivity.this, CommunityDetailActivity.class);
				it.putExtra("name", re_name);
				it.putExtra("id", re_id);
				it.putExtra("kouhao", re_kouhao);
				//it.putExtra("picture", bbb);
				finish();
				startActivity(it);
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)      //��д���ؼ�
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent it = new Intent(CommunityAlbumActivity.this, CommunityDetailActivity.class);
			it.putExtra("name", re_name);
			it.putExtra("id", re_id);
			it.putExtra("kouhao", re_kouhao);
			//it.putExtra("picture", bbb);
			finish();
			startActivity(it);
		}
		return false;
	}
}
