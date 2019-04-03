package com.bn.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class guanlichengyuan extends Activity{
	private ListView listview = null;
	public static String id="";
	public static String s="";
	private baseAdapter base=null;
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>(); 
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	private String[] name1=null;
	List<String[]> namey=new ArrayList<String[]>();
	private String[][] all=null;
	public static TextView text=null;
	public TextView tianjia=null;
	private String ss=null;
	private String shetuanid=null;
	TextView fanhui;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chengyuan_);
		
		thread_ntd th=new thread_ntd();
		th.start();
		 try {
	 			th.join();
	 			} catch (InterruptedException e) {
	 				e.printStackTrace();
	 			}
		 tianjia=(TextView)findViewById(R.id.chengyuan_tianjia);
		 tianjia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(guanlichengyuan.this,chengyuanxingming.class);
				finish();
				startActivity(it);
			}
		});
		 fanhui=(TextView)findViewById(R.id.chengyuanguanli_text1);
		 fanhui.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});	 
		 
	}
	public void initBaseAdapter()
	  {
		  base=new baseAdapter(this);
		  listview=(ListView)findViewById(R.id.yychengyuan_text2);
		  listview.setAdapter(base);
		  listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){ 
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                  int arg2, long arg3) {
					ss=name1[arg2];
					initMoreInfo(0);
					return false;
				    }
		  });
		  
	 }
	public void shanchuthread(final String ss)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				id=NetInfoUtil.getuserId2(ss);
				String s=user_guanli.s;
				shetuanid=NetInfoUtil.getshetuanidbymima(s);
				NetInfoUtil.shanchuchengyuan(id+"#"+shetuanid);
				Intent it=new Intent(guanlichengyuan.this,guanlichengyuan.class);
				finish();
				startActivity(it);
			}
		}.start();
	}
	@Override
	@SuppressWarnings("deprecation")
	public Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch (id) 
		{
		case 0:
			AlertDialog b=new AlertDialog.Builder(this).create();
			    b.setTitle("系统提示");
				b.setMessage("确定要删除吗?");// ������Ϣ
				b.setButton// Ϊ�Ի������ð�ť
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog, final int which) {

								//s= text.getText().toString();
								s=ss;
								shanchuthread(s);
							}
						});
                b.setButton2("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
							}
						}
				);
				b.show();
			break;
		}
		return dialog;
	}
	@SuppressLint("HandlerLeak")
	Handler hd = new Handler() 
	{
		@SuppressWarnings("deprecation")
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {	
			showDialog(0);
		}
	};
	public void initMoreInfo(int i) {
		Message msg = new Message();
		msg.what = i;
		hd.sendMessage(msg);
	}
	private class thread_ntd extends Thread
	  {
		@Override
		public void run()
		  {
			String s=user_guanli.s;
			namey=NetInfoUtil.getallshetuanchengyuan(s);
			all=new String[namey.size()][namey.get(0).length];
			name1=new String[all.length];
			if(namey.get(0)[0].equals(""))
			  {
				  
			  }else
			  {
				  for(int i=0;i<namey.size();i++)
				  {
					  for(int j=0;j<namey.get(i).length;j++)
					  {
						  all[i][j]=namey.get(i)[j];
						  name1[i]=all[i][0];
					  }
					  
				  }
				  initList();
			  }
		  }
	  }
	
	@SuppressLint("InflateParams")
	private class baseAdapter extends BaseAdapter
    {     //�ڲ��ࣺ������

		private LayoutInflater mInflater=null;
		public baseAdapter(Context context){
			
			mInflater = LayoutInflater.from(context);
		    
				data.addAll(listItem);
				listItem.clear();
		}
		@Override
		public int getCount() {
			return data.size();//��¼��ǰ�б����ж�������
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder  myViews;
			if (convertView == null)
			{
				myViews = new ViewHolder ();
				convertView =mInflater.inflate(R.layout.chengyuan_list, null) ;
				
				myViews.name = (TextView) convertView.findViewById(R.id.chengyuan_name);
				convertView.setTag(myViews);
			}
			else {
		        myViews = (ViewHolder ) convertView.getTag();
		          
		      }
			
			
			myViews.name.setText( (String) data.get(position).get("name"));
			return convertView;
		}
	}
	public void initList()
	  {
		  for(int i=0;i<name1.length;i++)
		  {
			  Map<String, Object> map = new HashMap<String, Object>();   
			  map.put("name", name1[i]);
			  listItem.add(map);
		  }
		  initBaseAdapter();
	  }
	static class ViewHolder {
	      
	      private TextView  name;
	    }

}
