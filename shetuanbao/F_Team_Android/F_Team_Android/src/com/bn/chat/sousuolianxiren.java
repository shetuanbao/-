package com.bn.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bn.community.CommunityStaffActivity;
import com.bn.manage.CaptureActivity;
import com.bn.util.Exit;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class sousuolianxiren extends Activity{
	public ImageView fanhui=null;
	AutoCompleteTextView act=null;
	String s="";
	ImageView sousuo=null;
	TextView tishi=null;
	private String sno[]=null;
	public boolean bool=true;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {  
		 super.onCreate(savedInstanceState);  
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	     setContentView(R.layout.lianxirensousuo); 
	     Exit.getInstance().addActivities(this);
	     fanhui=(ImageView)findViewById(R.id.historytssool_t1);
	     fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCameraIntent = new Intent(sousuolianxiren.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
	     act=(AutoCompleteTextView)findViewById(R.id.titlSOUSUOREN);
	     tishi=(TextView)findViewById(R.id.texcct1);
	     thread_sousuo gg=new thread_sousuo();
			gg.start();
			try{
				gg.join();
			}catch(Exception e){
				e.printStackTrace();
			}
	     sousuo=(ImageView)findViewById(R.id.mn_searddchren_image);
	     sousuo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s=act.getText().toString();
				for(int i=0;i<sno.length;i++)
			    {
			    	 if(s.equals(sno[i]))
			    		 bool=false;
			    }
				if(bool)
				{
					System.out.println("1234556");
					tishi.setText("δ�ҵ����û�!");
					act.setText("");
				}else
				{
					bool=true;
					Intent intent=new Intent(sousuolianxiren.this,CommunityStaffActivity.class);
					Bundle bundle=new Bundle();
				    bundle.putString("sno", s);
				    intent.putExtras(bundle);
				    tishi.setText("");
					startActivity(intent);
				}
				act.setText("");
			}
		});    
	 }
	 private class thread_sousuo extends Thread {
		 @Override
			public void run() {
			 sno=NetInfoUtil.getallchengyuan();
		 }
	 }
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
		
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				String scanResult = bundle.getString("result");
				act.setText(scanResult);
			}
		}
}
