package com.bn.user_myself;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.NetInfoUtil;
import com.example.Team_Android.MainMyselfActivity;
import com.example.chat.R;

public class user_xiugaimima extends Activity
{
	TextView fanhui=null;
	TextView tittle=null;
	TextView notify1=null;
	TextView notify2=null;
	TextView notify3=null;
	EditText mima1=null;
	EditText mima2=null;
	EditText mima3=null;
	String message;
	List<String[]> ls=new ArrayList<String[]>();
	static String password;
	static String phone;
	Button ok;
	private ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.geren_xiugaimima);
		
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("�����У����Ժ�...");
		ok=(Button)findViewById(R.id.update_main_ok);
		mima1=(EditText)findViewById(R.id.update_main_password1_edit);
		mima2=(EditText)findViewById(R.id.update_main_password2_edit);
		mima3=(EditText)findViewById(R.id.update_main_password3_edit);
		notify1=(TextView)findViewById(R.id.update_main_text1);
		notify2=(TextView)findViewById(R.id.update_main_text2);
		notify3=(TextView)findViewById(R.id.update_main_text3);
		tittle=(TextView)findViewById(R.id.updatetool_text2);
		fanhui=(TextView)findViewById(R.id.updatetool_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				finish();
			}
		});
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(message.equals("�޸�����"))
				{
					if(mima1.getText().toString().trim().equals("")||mima2.getText().toString().trim().equals("")||mima3.getText().toString().trim().equals(""))
					{
						Toast.makeText(user_xiugaimima.this, "������Ϣ����Ϊ��", Toast.LENGTH_LONG).show();
					}
					else if(!mima1.getText().toString().trim().equals("")&&!mima2.getText().toString().trim().equals("")&&!mima3.getText().toString().trim().equals(""))
					{
						if(mima1.getText().toString().trim().equals(mima2.getText().toString().trim()))
						{
							Toast.makeText(user_xiugaimima.this, "�¾����벻����ͬ", Toast.LENGTH_LONG).show();
						}
						else if(!mima1.getText().toString().trim().equals(password))
						{
							Toast.makeText(user_xiugaimima.this, "��������������", Toast.LENGTH_LONG).show();
							mima1.setText("");
							mima2.setText("");
							mima3.setText("");
						}
						else if(!mima3.getText().toString().trim().equals(mima2.getText().toString().trim()))
						{
							Toast.makeText(user_xiugaimima.this, "�����������벻һ��", Toast.LENGTH_LONG).show();
							mima1.setText("");
							mima2.setText("");
							mima3.setText("");
						}
						else
						{
							updatepassword th_password=new updatepassword();
							th_password.start();
							try{
								th_password.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							finish();
							Toast.makeText(user_xiugaimima.this, "�޸ĳɹ�", Toast.LENGTH_LONG).show();
						}
					}
				}
				else if (message.equals("����Ԥ���绰"))
				{
					if(mima1.getText().toString().trim().equals("")||mima2.getText().toString().trim().equals(""))
					{
						Toast.makeText(user_xiugaimima.this, "������Ϣ����Ϊ��", Toast.LENGTH_LONG).show();
					}
					else if(!mima1.getText().toString().trim().equals("")&&!mima2.getText().toString().trim().equals(""))
					{
						if(mima1.getText().toString().trim().equals(mima2.getText().toString().trim()))
						{
							Toast.makeText(user_xiugaimima.this, "�¾ɵ绰�Ų�����ͬ", Toast.LENGTH_LONG).show();
						}
						else if(!mima1.getText().toString().trim().equals(phone))
						{
							Toast.makeText(user_xiugaimima.this, "�ɵ绰���������", Toast.LENGTH_LONG).show();
							mima1.setText("");
							mima2.setText("");
							mima3.setText("");
						}
						else
						{
							updatepassword th_password=new updatepassword();
							th_password.start();
							try{
								th_password.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							finish();
							Toast.makeText(user_xiugaimima.this, "�޸ĳɹ�", Toast.LENGTH_LONG).show();
						}
					}
				}
			}
		});
		Intent intent =getIntent();
		message=intent.getStringExtra("mes");
		tittle.setText(message);
		if(message.equals("�޸�����"))
		{
			notify1.setText("�����룺");
			notify2.setText("�����룺");
			notify3.setVisibility(View.VISIBLE);
			mima3.setVisibility(View.VISIBLE);
			notify3.setText("ȷ�����룺");
			mima1.setHint("6-12���ַ������ִ�Сд");
			mima2.setHint("6-12���ַ������ִ�Сд");
		}
		else if(message.equals("����Ԥ���绰"))
		{
			notify1.setText("�ɵ绰�ţ�");
			notify2.setText("�µ绰�ţ�");
			notify3.setVisibility(View.GONE);
			mima3.setVisibility(View.GONE);
			mima1.setHint("���������ĵ绰����");
			mima2.setHint("���������ĵ绰����");
		}
		new passwordAsyncTask().execute();
	}
	class  updatepassword extends Thread
	{
		@Override
		public void run()
		{
			NetInfoUtil.updateandusermessage(message+"<#>"+MainMyselfActivity.information.getText().toString().trim().substring(3,MainMyselfActivity.information.getText().toString().trim().length())+"<#>"+mima2.getText().toString().trim());
		}
	}
	class getuserpassword extends Thread
	{
		@Override
		public void run()
		{
			ls=NetInfoUtil.getpasswordandphone(MainMyselfActivity.information.getText().toString().trim());
			if(!ls.get(0)[0].equals(""))
			{
				password=ls.get(0)[0];
				phone=ls.get(0)[1];
			}
		}
	}
	private class passwordAsyncTask extends AsyncTask<Void, Integer, Void>
	{

		@Override
		protected void onPreExecute()
		{
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params)
		{
			getuserpassword th_getpassword=new getuserpassword();
			th_getpassword.start();
			try{
				th_getpassword.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values)
		{
			pd.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Void result)
		{
			// �������ݼ�����ɺ��UI����
			pd.dismiss();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}
}
