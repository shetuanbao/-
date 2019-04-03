package com.example.president;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.example.util.NetInfoUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class gerenActivity extends Activity{
	
	List<String[]> namey=new ArrayList<String[]>();
	String all[][]=null;
	private ArrayAdapter<String> sexAdapter = null;  
	private String name[]=null;
	String id[]=null;
	private Spinner shetuan=null;
	TextView fanhui;
	ProgressDialog pd;
	String thisshetuan=null;
	String username=null;
	Button tiaozhuan=null;
	EditText xingming=null;
	String namezong[]=null;
	private AutoCompleteTextView actv;
	List<String[]> zongy=new ArrayList<String[]>();
	String all2[][]=null;
	String id2[]=null;
	boolean bb=false;
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.geren); 
		thread_get tg=new thread_get();
		tg.start();
		try{
			tg.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		actv = (AutoCompleteTextView) findViewById(R.id.geren_id_3_zong);
//		actv.setThreshold(1);
//		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(gerenActivity.this,
//				R.layout.autocompletetextview, namezong);
//		actv.setAdapter(adapter2);
//		actv.addTextChangedListener(new TextWatcher() {
//			@Override
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//				for (int i = 0; i < namezong.length; i++) {
//					if (actv.getText().toString().trim().equals(namezong[i].toString().trim())) {
//						String messa = actv.getText().toString();
//					}
//				}
//			}
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//				// TODO Auto-generated method stub
//				// sp.edit().clear().commit();
//			}
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				// TODO Auto-generated method stub
//
//			}
//		});
		shetuan=(Spinner)findViewById(R.id.shetuan);
		shetuan.setPrompt("请选择社团：" ); 
		setSpinner();
		xingming=(EditText)findViewById(R.id.geren_id_3_zong);
		tiaozhuan=(Button)findViewById(R.id.sousuo);
		tiaozhuan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				username=xingming.getText().toString();
				System.out.println("jjjjjjjjjjjjjjjj"+thisshetuan);
				if(bb==false){
					System.out.println("jjjjjjjjjjjjjjjj"+thisshetuan);
					thread_gg2 gg2=new thread_gg2();
					gg2.start();
					try{
						gg2.join();
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{
				
					thread_gg gg=new thread_gg();
					gg.start();
					try{
						gg.join();
					}catch(Exception e){
						e.printStackTrace();
					}
					//bb=false;
				}
				
				if(xingming.getText().toString().equals("")){
					Toast.makeText(gerenActivity.this, "姓名不可为空!!",
							Toast.LENGTH_LONG).show();
				}
				else{
					   if(id2.length>=2){
						   Intent intent=new Intent(gerenActivity.this,sousuoliebiao.class);
						   Bundle bundle = new Bundle() ;
						   bundle.putSerializable("DATA", id2) ;
						   intent.putExtras(bundle) ;
						   startActivity(intent) ;
					   }else{
						   Intent intent=new Intent(gerenActivity.this,gerenxianshi.class);
						   intent.putExtra("name", username);
						   intent.putExtra("id", id2[0]);
						   startActivity(intent);
					   }
					  
				}
				
			}
		});
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		fanhui=(TextView)findViewById(R.id.geren_zhu_2);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
	}
	private class thread_get extends Thread{
		@Override
		public void run(){
			namey=NetInfoUtil.getallshetuan();
			namezong=NetInfoUtil.getallusername();
			all=new String[namey.size()][namey.get(0).length];
			name=new String[all.length+1];
			name[0]="全部社团";
			for(int i=0;i<namey.size();i++){
				for(int j=0;j<namey.get(i).length;j++){
					all[i][j]=namey.get(i)[j];
					name[i+1]=all[i][0];
				}
			}
		}
	}
	private class thread_gg extends Thread{
		@Override
		public void run(){
			zongy=NetInfoUtil.getuseridandmima(username+"#"+thisshetuan);
			all2=new String[zongy.size()][zongy.get(0).length];
			id2=new String[all2.length];
			
			for(int i=0;i<zongy.size();i++){
				for(int j=0;j<zongy.get(i).length;j++){
					all2[i][j]=zongy.get(i)[j];
					id2[i]=all2[i][0];
				}
			}
		}
	}
	private class thread_gg2 extends Thread{
		@Override
		public void run(){
			zongy=NetInfoUtil.getuseridbyname(username);
			all2=new String[zongy.size()][zongy.get(0).length];
			id2=new String[all2.length];
			for(int i=0;i<zongy.size();i++){
				for(int j=0;j<zongy.get(i).length;j++){
					all2[i][j]=zongy.get(i)[j];
					id2[i]=all2[i][0];
				}
			}
		}
	}
	public void setSpinner()
	{
		sexAdapter = new ArrayAdapter<String>(this,
				 android.R.layout.simple_spinner_item, name);
		sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		shetuan.setAdapter(sexAdapter);
		shetuan.setSelection(0,true); 
		shetuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
            	if(position!=0){
            		bb=true;
            	}else if(position==0){
            		bb=false;
            	}
            	System.out.println("ggggggggggggg"+name[0]);
            	thisshetuan=name[position];
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
