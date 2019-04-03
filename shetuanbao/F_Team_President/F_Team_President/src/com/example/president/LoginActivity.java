package com.example.president;
import com.example.util.NetInfoUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LoginActivity extends Activity implements OnClickListener{
	private Button sign=null;
	private EditText username=null;
	private EditText password=null;
	private String zh_pw[]=null;
	private String count="";
	private String name="";
	private String userpassword="";
	private int SUCCESS = 0;
	private int FAIL = 1 ;  
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);	
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_login);
		username=(EditText)findViewById(R.id.login_edtId);
		password=(EditText)findViewById(R.id.login_edtPwd);

		sign=(Button)findViewById(R.id.login_btnLogin);	
		sign.setOnClickListener(this);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
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
		//TODO Auto-generated method stub
		switch(v.getId())
		{
		 case R.id.login_btnLogin:
			 String userNameValue=username.getText().toString();
			 String passwordValue=password.getText().toString();
			 if(userNameValue.equals("")||passwordValue.equals(""))
			 {
				 Toast.makeText(LoginActivity.this,"账号或密码不能为空", Toast.LENGTH_SHORT).show();
			 }
			 else
			 {	
				 login(userNameValue,passwordValue);
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
		              mHandler.sendEmptyMessage(SUCCESS);
		              Intent ii=new Intent(LoginActivity.this,MainActivity.class);
		              finish();
		  			  startActivity(ii);
		  			  pd.dismiss();
				}else
				{
					  mHandler.sendEmptyMessage(FAIL);  
					  pd.dismiss();
				}
			}
		}).start();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)      //返回键监听
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			finish();
		}
		return true;
	}
}
