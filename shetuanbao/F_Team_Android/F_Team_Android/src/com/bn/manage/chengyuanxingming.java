package com.bn.manage;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.NetInfoUtil;
import com.example.chat.R;
public class chengyuanxingming extends Activity{
	LinearLayout username=null;
	String name=null;
	String id="";
	String s="";
	private ImageView Qr=null;
	AutoCompleteTextView yonghuming=null;
	TextView ok=null;
	TextView fanhui=null;
	String idy[]=null;
	boolean pd=false;
	List<String[]> namey=new ArrayList<String[]>();
	List<String[]> namey2=new ArrayList<String[]>();
	List<String[]> namey3=new ArrayList<String[]>();
	String all[][]=null;
	String all_2[][]=null;
	String namecunzai[]=null;
	String namebucunzai[]=null;
	boolean pd2=false;
	boolean pd3=false;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chengyuanxingming);
		fanhui=(TextView)findViewById(R.id.xingming_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
		    public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Qr=(ImageView)findViewById(R.id.saomiao);
	    Qr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCameraIntent = new Intent(chengyuanxingming.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
				username = (LinearLayout) findViewById(R.id.chengyuanrname);
				yonghuming=(AutoCompleteTextView)findViewById(R.id.chengyuanname_edit);
				yonghuming.setOnFocusChangeListener(new OnFocusChangeListener() {
					@Override
				public void onFocusChange(View arg0, boolean hasFocus) {
						if (hasFocus) {
							username.setBackgroundResource(R.drawable.roundshapepress);
						} else {
							username.setBackgroundResource(R.drawable.roundshape);
						}
					}
				});
				ok=(TextView)findViewById(R.id.chengyuan_Button_1);
				ok.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						s=yonghuming.getText().toString();
						huoquid(s);
						yonghuming.setText("");
					}
				});
	}
	public void tianjiathread()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				String st=user_guanli.s;
				String m=NetInfoUtil.getguanliid(st);
				st=s+"#"+m;
				NetInfoUtil.tianjiachenghyuan(st);
				Intent it=new Intent(chengyuanxingming.this,guanlichengyuan.class);
				finish();
				startActivity(it);
			}
		}.start();
	}
	public void tianjiacunzaithread()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				String st=user_guanli.s;
				String m=NetInfoUtil.getguanliid(st);
				st=s+"#"+m;
				NetInfoUtil.tianjiacunzaichengyuan(st);
				Intent it=new Intent(chengyuanxingming.this,guanlichengyuan.class);
				finish();
				startActivity(it);
			}
		}.start();
	}
	public void huoquid(final String ss)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				idy=NetInfoUtil.getalluserid();
				String s=user_guanli.s;
				namey=NetInfoUtil.getcunzaishetuanchengyuan(s);
				all=new String[namey.size()][namey.get(0).length];
				namecunzai=new String[all.length];
				for(int i=0;i<namey.size();i++){
					for(int j=0;j<namey.get(i).length;j++){
						all[i][j]=namey.get(i)[j];
						namecunzai[i]=all[i][0];
					}
				}
				namey2=NetInfoUtil.getshachushetuanchengyuan(s);
				all_2=new String[namey2.size()][namey2.get(0).length];
				namebucunzai=new String[all_2.length];
				for(int i=0;i<namey2.size();i++){
					for(int j=0;j<namey2.get(i).length;j++){
						all_2[i][j]=namey2.get(i)[j];
						namebucunzai[i]=all_2[i][0];
					}
				}
				for(int i=0;i<idy.length;i++){
					if(ss.equals(idy[i])){
						pd=true;
					}
				}
				for(int i=0;i<namecunzai.length;i++){
					if(ss.equals(namecunzai[i])){
						pd2=true;
					}
				}
				for(int i=0;i<namebucunzai.length;i++){
					if(ss.equals(namebucunzai[i])){
						pd3=true;
					}
				}
				if(!ss.equals(""))
				{
					if(pd2==true){
						pd2=false;
						Looper.prepare(); 
						Toast.makeText(chengyuanxingming.this, "账号已添加，不可添加", Toast.LENGTH_SHORT).show();
						Looper.loop();
					}
					else if(pd3==true){
						pd3=false;
						tianjiacunzaithread();
						Looper.prepare();  
						Toast.makeText(chengyuanxingming.this, "添加成功", Toast.LENGTH_SHORT).show();
						Looper.loop();
						
					}
					else if(pd==false)
					{
						Looper.prepare(); 
						Toast.makeText(chengyuanxingming.this, "账号不存在，不可添加", Toast.LENGTH_SHORT).show();
						Looper.loop();
					}
					else 
					{
						tianjiathread();
						Looper.prepare();  
						Toast.makeText(chengyuanxingming.this, "添加成功", Toast.LENGTH_SHORT).show();
						Looper.loop();
					}
				}
				else 
				{
					Looper.prepare(); 
					Toast.makeText(chengyuanxingming.this, "请输入账号", Toast.LENGTH_SHORT).show();
					Looper.loop();
				}
			}
		}.start();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			
			yonghuming.setText(scanResult);
		}
	}
}
