package com.example.Team_Android;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bn.manage.user_guanli;
import com.bn.user_myself.user_lianxiwomen;
import com.bn.user_myself.user_shezhi;
import com.bn.user_myself.user_yijianfankui;
import com.bn.user_myself.user_yonghubangzhu;
import com.bn.util.Constant;
import com.bn.util.Exit;
import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.bn.util.RoundImageView;
import com.example.chat.R;

public class MainMyselfActivity extends Activity{
	private LinearLayout myself_background=null;
	private TextView shezhi=null;
	private TextView lianxi=null;
	private TextView yijianfankui=null;
	private TextView yonghubangzhu=null;
	private TextView gerenzhongxin=null;
    public static TextView information=null;
    public static RoundImageView photo=null;
	public static Button denglu=null;
	public static Button zhuce=null;
	private String image=null;
	private String name=null;
	private Bitmap imageData;
	private byte[] all_image;
	private TextView guanli=null;
	private String zhuangtai=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Exit.getInstance().addActivities(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myself_activity);
		Exit.getInstance().addActivities(this);		
		information=(TextView)findViewById(R.id.mine_userphoto_text);
		photo=(RoundImageView)findViewById(R.id.mine_userphoto_imagezhu);
		thread_getuserpicture th=new thread_getuserpicture();
		th.start();
		try{
			th.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		photo.setImageBitmap(imageData);
		System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+zhuangtai);
		if(zhuangtai.equals("1")){
			information.setText(name+"|ID:"+Constant.userName);
		}else if(zhuangtai.equals("0")){
			information.setText(name+"|ID:"+Constant.userName+"|账号被封禁");
		}
		
		shezhi=(TextView)findViewById(R.id.mine_shezhi);
		shezhi.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainMyselfActivity.this,user_shezhi.class);
				it.putExtra("name", name);
				startActivity(it);
			}
		});
		lianxi=(TextView)findViewById(R.id.mine_lianxiwomen);
		lianxi.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainMyselfActivity.this,user_lianxiwomen.class);
				//����Activity
				startActivity(it);
			}
		});
		yonghubangzhu=(TextView)findViewById(R.id.mine_yonghubangzhu);
		yonghubangzhu.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) { 
				Intent it = new Intent(MainMyselfActivity.this,user_yonghubangzhu.class);
				//����Activity
				startActivity(it);
			}
		});
		yijianfankui=(TextView)findViewById(R.id.mine_yijianfankui);
		yijianfankui.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainMyselfActivity.this,user_yijianfankui.class);
				startActivity(it);
			}
		});
		guanli=(TextView)findViewById(R.id.mine_guanli);
		guanli.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MainMyselfActivity.this,user_guanli.class);
				startActivity(it);
			}
		 });
		 FontManager.initTypeFace(this);
		 FontManager.changeFonts(FontManager.getContentView(this),this);
	}
	private class thread_getuserpicture extends Thread{
		@Override
		public void run(){
			image=NetInfoUtil.getuseronephoto(Constant.userName);
			name=NetInfoUtil.getusername(Constant.userName);
			zhuangtai=NetInfoUtil.getuserstatic(Constant.userName);
			 if(F_GetBitmap.isEmpty(image))
			  {
				  all_image=NetInfoUtil.getPicture(image);
				      F_GetBitmap.setInSDBitmap(all_image, image);
					  InputStream input = null;   
					  BitmapFactory.Options options = new BitmapFactory.Options();  
					  options.inSampleSize = 2;  
					  input = new ByteArrayInputStream(all_image);  
					  @SuppressWarnings({ "rawtypes", "unchecked" })
					  SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(  
					                input, null, options));  
					  imageData = (Bitmap) softRef.get();
			 }
			  else{
				     imageData=F_GetBitmap.getSDBitmap(image);//拿到的是BitMap类型的图片数据
					 if(F_GetBitmap.bitmap!=null && !F_GetBitmap.bitmap.isRecycled())
					 {
						 F_GetBitmap.bitmap = null;
					 }
			  }
		}
	}
	@Override
	@SuppressWarnings("deprecation")
	public boolean onKeyDown(int keyCode, KeyEvent event)      //��д���ؼ�
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			AlertDialog isExit=new AlertDialog.Builder(this).create();
			isExit.setTitle("系统提示");
			isExit.setMessage("确定退出吗？");
			isExit.setButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Exit.exitActivity();
						}
					}
			);
			isExit.setButton2("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}
			);
			isExit.show();
		}
		return true;
	}
//	private Bitmap getDarker(Drawable d){
//
//		BitmapDrawable drawable =(BitmapDrawable)d;
//
//		Bitmap origin = drawable.getBitmap();
//
//		int width = origin.getWidth();
//
//		int height = origin.getHeight();
//
//		Bitmap background =Bitmap.createBitmap(width, height, Config.ARGB_8888);
//
//		Canvas canvas=new Canvas(background);
//
//		canvas.drawBitmap(origin, 0, 0, new Paint());
//
//		Paint p1=new Paint();
//
//		p1.setAlpha(75);
//
//		canvas.drawRect(0, 0, width, height, p1);
//
//		return background;
//
//		}

}
