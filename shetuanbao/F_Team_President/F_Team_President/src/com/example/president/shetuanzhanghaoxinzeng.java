package com.example.president;
import java.util.ArrayList;
import java.util.List;
import com.example.util.NetInfoUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class shetuanzhanghaoxinzeng extends Activity{
	TextView fanhui;
	TextView baocun;
	EditText name;
	EditText password;
	String name2=null;
	String password2=null;
	String mes=null;
	List<String[]> idy=new ArrayList<String[]>();
	List<String[]> mima=new ArrayList<String[]>();
	String all[][]=null;
	String mima2[]=null;
	static int count=0;
	ProgressDialog pd;
	String shetuanname[]=null;
	List<String[]> shetuany=new ArrayList<String[]>();
	String all_2[][]=null;
    static int count2=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shetuanzhanghao_xinzeng);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		name=(EditText)findViewById(R.id.shetuan_zhanghao_baocun_name_3);
		password=(EditText)findViewById(R.id.sheutuanzhanghao_baocun_mima_3);
		baocun=(TextView)findViewById(R.id.shetuan_zhanghao_baocun_baocun);
		baocun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("qweqwesrer"+name.getText()+password.getText());
				for(int i=0;i<mima2.length;i++){
					if(password.getText().toString().equals(mima2[i])){
						count++;
					}
				}
				for(int i=0;i<shetuanname.length;i++){
					if(name.getText().toString().equals(shetuanname[i])){
						count2++;
					}
				}
				if(name.getText().toString().equals("")||password.getText().toString().equals("")){
					Toast.makeText(shetuanzhanghaoxinzeng.this, "不能有空的选项!!",
							Toast.LENGTH_LONG).show();
					
				} 
				else if(count==1){
					Toast.makeText(shetuanzhanghaoxinzeng.this, "不能设置重复的密码!!",
							Toast.LENGTH_LONG).show();
					count=0;
				}else if(count2==0){
					Toast.makeText(shetuanzhanghaoxinzeng.this, "所设置的社团不存在!!",
							Toast.LENGTH_LONG).show();
					count2=0;
				}
				else{
					thread_insert th=new thread_insert();
					th.start();
					try{
						th.join();
					}catch(Exception e){
						e.printStackTrace();
					}
					Toast.makeText(shetuanzhanghaoxinzeng.this, "保存成功!!",
							Toast.LENGTH_LONG).show();
					Intent it=new Intent(shetuanzhanghaoxinzeng.this,shetuanzhanghao.class);
					finish();
					startActivity(it);
				}
			}
		});
		fanhui=(TextView)findViewById(R.id.shetuanzhanghao_baocun_fanhui_2);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(shetuanzhanghaoxinzeng.this,shetuanzhanghao.class);
				finish();
				startActivity(it);
			}
		});
		new AysncTask_get().execute();
	}
    private class thread_insert extends Thread{
    	@Override
		public void run(){
    		if (!name.getText().toString().equals("")) {
				name2 = name.getText().toString();
			} else {
				name2 = name.getHint().toString();
			}
    		idy=NetInfoUtil.getshetuanidbyname(name2);
    		String max=(Integer.parseInt(NetInfoUtil.getguanlimax())+1)+"";
    		if (!password.getText().toString().equals("")) {
    			password2 = password.getText().toString();
			} else {
				password2 = password.getHint().toString();
			}
    		mes=idy.get(0)[0]+"<#>"+max+"<#>"+password2;
    		NetInfoUtil.insertguanlimima2(mes);
    	}
    }
    private class thread_get extends Thread{
    	@Override
		public void run(){
    		mima=NetInfoUtil.getguanlipassword();
    		shetuany=NetInfoUtil.getallshetuan();
    	    all_2=new String[shetuany.size()][shetuany.get(0).length];
    	    shetuanname=new String[all_2.length];
    		all=new String[mima.size()][mima.get(0).length];
    		mima2=new String[all.length];
    		for(int i=0;i<mima.size();i++){
    			for(int j=0;j<mima.get(i).length;j++){
    				all[i][j]=mima.get(i)[j];
    				mima2[i]=all[i][0];
    			}
    		}
    		for(int i=0;i<shetuany.size();i++){
    			for(int j=0;j<shetuany.get(i).length;j++){
    				all_2[i][j]=shetuany.get(i)[j];
    				shetuanname[i]=all_2[i][0];
    			}
    		}
    	}
    }
    class AysncTask_get extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			thread_get th = new thread_get();
			th.start();
			// thread_pinglun th=new thread_pinglun();
			// th.start();
			try {
				// th2.join();
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			pd.setProgress(values[0]);
		}
		@Override
		protected void onPostExecute(Void result) {
			// 进行数据加载完成后的UI操作
			pd.dismiss();
		}
    }
    @Override
	@SuppressWarnings("deprecation")
	public boolean onKeyDown(int keyCode, KeyEvent event) // ��д���ؼ�
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent it=new Intent(shetuanzhanghaoxinzeng.this,shetuanzhanghao.class);
			finish();
			startActivity(it);
		}
		return true;
	}
}
