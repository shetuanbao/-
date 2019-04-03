package com.example.president;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
public class MainActivity extends Activity {
    TextView shetuan;
    TextView yijian;
    TextView zhanghao;
    public  ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		shetuan=(TextView)findViewById(R.id.zhuxi_shetuan);
		shetuan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MainActivity.this,shetuanmanger.class);
				startActivity(it);
			}
		});
		yijian=(TextView)findViewById(R.id.zhuxi_shetuan_yijian);
		yijian.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//pd.show();
				Intent it=new Intent(MainActivity.this,YiJianActivity.class);
				startActivity(it);
				//mHandler.sendEmptyMessage(0);
			}
		});
        zhanghao=(TextView)findViewById(R.id.zhuxi_shetuan_zhanghao);
        zhanghao.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//pd.show();
				Intent it=new Intent(MainActivity.this,guanli_zhanghao.class);
				startActivity(it);
				//mHandler.sendEmptyMessage(0);
			}
		});
	}
	Handler mHandler=new Handler()
	{
		 @Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case 0:pd.dismiss();			
			break;
			case 1:pd.show();
			break;
			}
		}
	};
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
								finish();
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
}
