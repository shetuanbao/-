package com.bn.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.Constant;
import com.bn.util.Exit;
import com.bn.util.NetInfoUtil;
import com.example.Team_Android.MainFrame;
import com.example.chat.R;

public class LoginActivity extends Activity implements  OnClickListener
{
	
    private ImageView mImgBackgroud=null;
	public static SharedPreferences sp;
	private Button sign=null;
	private TextView register=null;
	private EditText username=null;
	private EditText password=null;
	private String zh_pw[]=null;
	private  String count="";
	private String name="";
	private String userpassword="";
	Runnable runnable=null;
	private  int SUCCESS = 0;
	private  int FAIL =1 ;  
	private String userInfo;
	public static ProgressDialog pd;
	String zhuangtai;
	String zhuangtai2=null;
	String userNameValue=null;
	String passwordValue=null;
	String ss=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
			
		super.onCreate(savedInstanceState);	
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.loginactivity);
		Exit.getInstance().addActivities(this);
		mImgBackgroud = (ImageView) findViewById(R.id.de_img_backgroud);
		username=(EditText)findViewById(R.id.app_username_et);
		password=(EditText)findViewById(R.id.app_password_et);	
		register=(TextView)findViewById(R.id.de_login_register);
		sign=(Button)findViewById(R.id.app_sign_in_bt);	        
	    sp=this.getSharedPreferences("userInfo",MODE_WORLD_READABLE);
	    sign.setOnClickListener(this);
	    register.setOnClickListener(this);	
	    pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		String zhuangtai=null;
		//thread_get th=new thread_get();
//		th.start();
//		try{
//			th.join();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		//if(zhuangtai.equals("1")){
			 if(sp.getBoolean("CHECK", false))
			   {		
				   username.setText(sp.getString("USER_NAME", ""));
				   password.setText(sp.getString("PASSWORD", ""));
				   Constant.userName=sp.getString("USER_NAME","");//ID
				   Constant.userPassword=sp.getString("PASSWORD", "");
				   Constant.userToken=sp.getString("Token", "");
				   ss=Constant.userName;
				    thread_get2 th=new thread_get2();
					th.start();
					try{
						th.join();
					}catch(Exception e){
						e.printStackTrace();
					}
				   if(zhuangtai2.equals("1")){
					   Intent in=new Intent(LoginActivity.this,MainFrame.class);
					   startActivity(in);
				   }
				   else if(zhuangtai2.equals("0")){
					   Toast.makeText(LoginActivity.this, "账号被封，不能登录!!", Toast.LENGTH_SHORT).show();
				   }
				   			   
			   }
		//}else if(zhuangtai.equals("0")){
			//Toast.makeText(LoginActivity.this, "账号被封，不能登录!!", Toast.LENGTH_SHORT).show();
		//}
	    
	}
	Handler mHandler=new Handler()
	{
		 @Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case 0:Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();			
			break;
			case 1:Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
			break;
			case 3:pd.show();
			
			break;
			}
		}
	};

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.de_login_register:
			
			Intent ii=new Intent(LoginActivity.this,registerActivity.class);
			startActivity(ii);
			break;
		case R.id.app_sign_in_bt:
			 userNameValue=username.getText().toString();
			 passwordValue=password.getText().toString();
			 if(userNameValue.equals("")||passwordValue.equals(""))
			 {
				 Toast.makeText(LoginActivity.this,"账号或密码不能为空", Toast.LENGTH_SHORT).show();
			 }
			 else
			 {	    thread_get th=new thread_get();
					th.start();
					try{
						th.join();
					}catch(Exception e){
						e.printStackTrace();
					}
				 if(zhuangtai.equals("1")){
					 login(userNameValue,passwordValue);
				 }
				 else {
					 Toast.makeText(LoginActivity.this, "账号被封，不能登录!!", Toast.LENGTH_SHORT).show();
				 }
			 }
			 break;			
		}				
	}
	void login(String userNameValue, String passwordValue)
	{
		zh_pw=new String[2];
		zh_pw[0]=userNameValue;
		zh_pw[1]=passwordValue;
		name=userNameValue;
		userpassword=passwordValue;
	    mHandler.sendEmptyMessage(3);
	    pd.show();
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{		
				count=NetInfoUtil.login(zh_pw);
				if(count.equals("1"))
				{	
					  String sno=NetInfoUtil.getusername(name);
					  String url=NetInfoUtil.getuseronephoto(name);
					 
		              Editor editor = sp.edit();  
		              editor.putString("USER_NAME", name);  
		              editor.putString("PASSWORD",userpassword); 
		              editor.putString("SNO",sno);
		              editor.putString("Token", name+"#"+sno+"#"+"file:///"+Environment.getExternalStorageDirectory().toString()+"/download_test/"+url);
		              editor.putBoolean("CHECK", true);
		              editor.putBoolean("zhuangtai", true);
		              editor.commit();
		              Constant.userName=sp.getString("USER_NAME","");
					  Constant.userPassword=sp.getString("PASSWORD", "");	
					  Constant.userToken=(sp.getString("Token", ""));
		              mHandler.sendEmptyMessage(SUCCESS);
		              Intent ii=new Intent(LoginActivity.this,MainFrame.class);
		  			  startActivity(ii);
		  			  try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  			  pd.dismiss();
				}else
				{
					 mHandler.sendEmptyMessage(FAIL);
					 pd.dismiss();
				}
				
			}
			
			
		}).start();
		
	}
	private class thread_get extends Thread{
		@Override
		public void run(){
			zhuangtai=NetInfoUtil.getuserstatic(userNameValue);
		}
	}
	private class thread_get2 extends Thread{
		@Override
		public void run(){
			zhuangtai2=NetInfoUtil.getuserstatic(ss);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)      //��д���ؼ�
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Exit.exitActivity();
		}
		return true;
	}
}
