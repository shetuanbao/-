package com.bn.manage;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class user_guanli extends Activity{
	public EditText  kouling;
	LinearLayout username=null;
	TextView ok=null;
	public TextView fanhui=null;
	public String thiskouling=null;
	public List<String[]> datakouling=null;
	public String all[][]=null;
	public String password[]=null;
	public static String s="";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.geren_guanli);
		FontManager.changeFonts(FontManager.getContentView(this), this);
		username = (LinearLayout) findViewById(R.id.guanli_username);
		kouling=(EditText )findViewById(R.id.guanli_koulingshuru);
		kouling.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
		public void onFocusChange(View arg0, boolean hasFocus) {

				if (hasFocus) {
					username.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					username.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		
		fanhui=(TextView)findViewById(R.id.guanli_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		ok=(TextView)findViewById(R.id.guanli_queding);
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				s=kouling.getText().toString();
				guanliThread();
				
			}
		});
		
	}
	public void guanliThread() {
		
		new Thread() {
			@Override
			public void run() {
				
				thiskouling=kouling.getText().toString();
				
				if(!thiskouling.equals(""))
				{
					datakouling=NetInfoUtil.getguanliyuankouling();
					all=new String[datakouling.size()][datakouling.get(0).length];
					password=new String[all.length];
					for(int i=0;i<datakouling.size();i++)
					  {
						  for(int j=0;j<datakouling.get(i).length;j++)
						  {
							  all[i][j]=datakouling.get(i)[j];
							  password[i]=all[i][0];
							  
						  }
					  }
					for(int i=0;i<password.length;i++)
					{
						
						if(!password[i].equals(thiskouling)&&i==password.length-1)
						{
							
							initMoreInfo(1);
							
						}
						else if(password[i].equals(thiskouling)) 
						{
							Looper.prepare();  
							Toast.makeText(user_guanli.this, "登陆成功", Toast.LENGTH_SHORT).show();
							
							Intent it = new Intent(user_guanli.this,user_guanligongneng.class);
							it.putExtra("password", thiskouling);
							startActivity(it);
							
							Looper.loop();
							break;
						}
					}
					
				}else
				{
					initMoreInfo(2);
				}
			}
		}.start();
	}
	@SuppressLint("HandlerLeak")
	Handler hd = new Handler() 
	{
		@SuppressWarnings("deprecation")
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				
				case 1:
					showDialog(1);
					break;
				case 2:
					showDialog(2);
					break;
			}
		}
	};
	public void initMoreInfo(int i) {
		Message msg = new Message();
		msg.what = i;
		hd.sendMessage(msg);
	}
	
	// �Ի������
			@Override
			public Dialog onCreateDialog(int id) {
				Dialog dialog = null;
				switch (id) {
					case 1:
						Builder b1 = new AlertDialog.Builder(this);
						b1.setMessage("输入错误！");// ������Ϣ
						b1.setPositiveButton// Ϊ�Ի������ð�ť
								("确定", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										kouling.setText("");
										
									}
								});
						dialog = b1.create();
						break;
					case 2:
						Builder b2 = new AlertDialog.Builder(this);
						b2.setMessage("请输入口令");// ������Ϣ
						b2.setPositiveButton// Ϊ�Ի������ð�ť
								("确定", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										
									}
								});
						dialog = b2.create();
						break;
					
				}
				return dialog;
			}
			

}
