package com.bn.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class CommunityPeopleActivity extends Activity implements OnItemClickListener{
	List<String[]> namey=new ArrayList<String[]>();
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>(); 
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	private String[] name1=null;
	private String[][] all=null;
	private String sno[]=null;
	private baseAdapter base=null;
	private ListView listview = null;
	public static TextView text=null;
	public static TextView fanhui=null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shetuanrenyuan);
		fanhui=(TextView)findViewById(R.id.shetuanrenyuani_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		thread_ntd th=new thread_ntd();
		th.start();
		 try {
	 			th.join();
	 			} catch (InterruptedException e) {
	 				e.printStackTrace();
	 			}
	}
	private class thread_ntd extends Thread
	  {
		@Override
		public void run()
		  {
			String s=CommunityDetailActivity.s;
			namey=NetInfoUtil.getshetuanchengyuan(s);	
			sno=NetInfoUtil.getShetuanRenYunId(s);
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
	public void initBaseAdapter()
	  {
		  base=new baseAdapter(this);
		  listview=(ListView)findViewById(R.id.shetuancheng_text2);
		  listview.setAdapter(base);
		  listview.setOnItemClickListener(this);   
	 }
	public void initMoreInfo(int i) {
		Message msg = new Message();
		msg.what = i;
		hd.sendMessage(msg);
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
	};@SuppressWarnings("deprecation")
	@SuppressLint("InflateParams")
	private class baseAdapter extends BaseAdapter
    {   
		private LayoutInflater mInflater=null;
		public baseAdapter(Context context){
			
			mInflater = LayoutInflater.from(context);
		    
				data.addAll(listItem);
				listItem.clear();
		}
		@Override
		public int getCount() {
			return data.size();
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
			myViews.name.setTypeface(FontManager.tf);
			return convertView;
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(CommunityPeopleActivity.this,CommunityStaffActivity.class);
		Bundle bundle=new Bundle();
	    bundle.putString("sno", sno[arg2]);
	    intent.putExtras(bundle);
		startActivity(intent);		
	}
	
	
	
	

}
