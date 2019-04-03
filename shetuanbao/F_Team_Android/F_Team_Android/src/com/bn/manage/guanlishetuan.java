package com.bn.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

public class guanlishetuan extends Activity{
	
	List<String[]> namey=new ArrayList<String[]>();
	public static TextView text=null;
	private ListView listview = null;
	private String[] name1=null;
	private String[][] all=null;
	private baseAdapter base=null;
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>(); 
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	TextView biaoti=null;
	TextView tianjia=null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guanli_huodong);
		
		biaoti=(TextView)findViewById(R.id.guanlihuodong_text2);
		biaoti.setText("管理社团");
		tianjia=(TextView)findViewById(R.id.huodongtianjia_text3);
		tianjia.setVisibility(View.GONE);
		
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
			String s=user_guanli.s;
			namey=NetInfoUtil.getshetuan(s);
			
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
	public void initBaseAdapter()
	  {
		  base=new baseAdapter(this);
		  listview=(ListView)findViewById(R.id.huodong_text2);
		  listview.setAdapter(base);
		  listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){ 
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
            int arg2, long arg3) {
					
					//LinearLayout view1=(LinearLayout)arg1;
					//LinearLayout ll=(LinearLayout) view1.getChildAt(0);
					 //text=(TextView) ll.getChildAt(0);
					 
				    }
		  });
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
	static class ViewHolder {
	      
	      private TextView  name;
	    }

}
