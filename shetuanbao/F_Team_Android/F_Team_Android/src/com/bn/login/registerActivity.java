package com.bn.login;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bn.util.Exit;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;
@SuppressLint("HandlerLeak")
public class registerActivity extends Activity {
	public static String registerusername;
	public static String registerpassword;
	public String userId;
	private String thisusername;
	private String datausername=" ";
	private String thispassword;
	private String thispassword1;
	private String thisname;
	private String thise_mail;
	private String thisphone;
	private String thispen;
	private String thissex="男";
	Button ok = null;
	TextView fanhui = null;
	LinearLayout usernamel = null;
	EditText yonghuming = null;
	LinearLayout passwordl = null;
	EditText mima = null;
	LinearLayout passwordl1 = null;
	EditText mima1 = null;
	LinearLayout namel = null;
	EditText mingzi = null;
	LinearLayout e_mail = null;
	EditText youxiang = null;
	LinearLayout phone = null;
	EditText dianhua = null;
	private Spinner sex=null;
	private ArrayAdapter<String> sexAdapter = null;    
	private String[]  sexArray={"男","女"};
	private  EditText  sexEd=null;
	private EditText pen=null;
	private EditText xueyuan=null;
	private EditText major=null;
	private String thisxueyuan="";
	private String thismajor="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.de_ac_register);
		Exit.getInstance().addActivities(this);
		pen=(EditText)findViewById(R.id.enrollmain_pen_edit);
		usernamel = (LinearLayout) findViewById(R.id.enrollmain_username);
		yonghuming = (EditText) findViewById(R.id.enrollmain_username_edit);
		xueyuan=(EditText)findViewById(R.id.enrollmain_xueyuan_edit);
		major=(EditText)findViewById(R.id.enrollmain_major_edit);
		sex=(Spinner)findViewById(R.id.sex);
		sex.setPrompt("请选择性别：" ); 
		setSpinner();
		yonghuming.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					usernamel.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					usernamel.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		passwordl = (LinearLayout) findViewById(R.id.enrollmain_password);
		mima = (EditText) findViewById(R.id.enrollmain_password_edit);
		mima.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					passwordl.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					passwordl.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		passwordl1 = (LinearLayout) findViewById(R.id.enrollmain_password_2);
		mima1 = (EditText) findViewById(R.id.enrollmain_password_2_edit);
		mima1.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					passwordl1
							.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					passwordl1.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		namel = (LinearLayout) findViewById(R.id.enrollmain_name);
		mingzi = (EditText) findViewById(R.id.enrollmain_name_edit);
		mingzi.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					namel.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					namel.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		e_mail = (LinearLayout) findViewById(R.id.enrollmain_e_mail);
		youxiang = (EditText) findViewById(R.id.enrollmain_e_mail_edit);
		youxiang.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					e_mail.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					e_mail.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		phone = (LinearLayout) findViewById(R.id.enrollmain_phone);
		dianhua = (EditText) findViewById(R.id.enrollmain_phone_edit);
		dianhua.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					phone.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					phone.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		fanhui = (TextView) findViewById(R.id.enrolltool_text1);
		ok = (Button) findViewById(R.id.enrollmain_Button_1);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				judgeusernamerepetitionThread();
			}
		});
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	// �ж��Ƿ��û����ظ����߳�
	public void judgeusernamerepetitionThread() {
		new Thread() {
			@Override
			public void run() {
				thisusername = yonghuming.getText().toString();
				thispassword = mima.getText().toString();
				thispassword1 = mima1.getText().toString();
				thisname = mingzi.getText().toString();
				thise_mail = youxiang.getText().toString();
				thisphone = dianhua.getText().toString();
				thispen=pen.getText().toString();
				thisxueyuan=xueyuan.getText().toString();
				thismajor=major.getText().toString();
				if (!thisusername.equals("") && !thispassword.equals("")
						&& !thispassword1.equals("") && !thisname.equals("")
						&& !thise_mail.equals("") && !thisphone.equals("")&&!thispen.equals("")&&!thisxueyuan.equals("")&&!thismajor.equals("")) {
					datausername = NetInfoUtil.getuserId(thisusername);
					if (datausername.equals("0"))
					{
						if (thispassword.equals(thispassword1)) {
							initMoreInfo(0);
							String message = thisusername + "<#>"
									+ thispassword + "<#>" + thisname + "<#>"
									+ thise_mail +"<#>"+thisphone+"<#>"+thissex+"<#>"+thispen+"<#>"+thisxueyuan+"<#>"+thismajor;
							NetInfoUtil.insertuser(message);
						} else
						{
							initMoreInfo(3);
						}
					}
                   else
					{
						initMoreInfo(1);
					}

				} else
				{
					initMoreInfo(2);
				}

			}
		}.start();
	}
	@SuppressLint("HandlerLeak")
	Handler hd = new Handler() {
		@SuppressWarnings("deprecation")
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 0:
					showDialog(0);
					break;
				case 1:
					showDialog(1);
					break;
				case 2:
					showDialog(2);
					break;
				case 3:
					showDialog(3);
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
			case 0:// ������ͨ�Ի���Ĵ���
				Builder b = new AlertDialog.Builder(this);
				b.setMessage("注册成功，点击设置可完善您的用户信息！");// ������Ϣ
				b.setPositiveButton// Ϊ�Ի������ð�ť
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								finish();
							}
						});
				dialog = b.create();
				break;
			case 1:// ������ͨ�Ի���Ĵ���
				Builder b1 = new AlertDialog.Builder(this);
				b1.setMessage("此用户名已用！");// ������Ϣ
				b1.setPositiveButton// Ϊ�Ի������ð�ť
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
							}
						});
				dialog = b1.create();
				break;
			case 2:// ������ͨ�Ի���Ĵ���
				Builder b2 = new AlertDialog.Builder(this);
				b2.setMessage("注册信息未完善！");// ������Ϣ
				b2.setPositiveButton// Ϊ�Ի������ð�ť
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {

							}
						});
				dialog = b2.create();
				break;
			case 3:// ������ͨ�Ի���Ĵ���
				Builder b3 = new AlertDialog.Builder(this);
				b3.setMessage("两次密码输入不一致！");// ������Ϣ
				b3.setPositiveButton// Ϊ�Ի������ð�ť
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {

							}
						});
				dialog = b3.create();
				break;
		}
		return dialog;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}
	public void setSpinner()
	{
		sexAdapter = new ArrayAdapter<String>(this,
				 android.R.layout.simple_spinner_item, sexArray);
		sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sex.setAdapter(sexAdapter);
		sex.setSelection(0,true); 
		sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
            	thissex=sexArray[position];
//            	sexEd=(EditText)findViewById(R.id.enrollmain_sex_edit);
//            	sexEd.setText(sexArray[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            	

            }
        });
	}
}
