package com.example.president;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.util.F_GetBitmap;
import com.example.util.NetInfoUtil;
import com.example.util.RoundImageView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import android.widget.AdapterView.OnItemClickListener;

public class YiJianActivity extends Activity{
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	List<String[]> zong=new ArrayList<String[]>();
	String all[][]=null;
	String name[]=null;
    String time[]=null;
    String detail[]=null;
    String lianxi[]=null;
    String image[]=null;
    byte all_image[][];
    Bitmap imageData[];
    baseAdapter base;
    ListView listview;
    ProgressDialog pd;
    TextView fanhui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yijianxiang);
		fanhui=(TextView)findViewById(R.id.yijianxiang_zhu_1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		
//		thread_get gg=new thread_get();
//		gg.start();
//		try{
//			gg.join();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		new AsyncTask_sousuo().execute();
	}
    private class thread_get extends Thread{
    	@Override
		public void run(){
    		zong=NetInfoUtil.getyijianmessage();
    		all=new String[zong.size()][zong.get(0).length];
    		name=new String[all.length];
    		time=new String[all.length];
    		detail=new String[all.length];
    		lianxi=new String[all.length];
    		image=new String[all.length];
    		all_image=new byte[all.length][];
    		imageData=new Bitmap[all.length];
    		if(zong.get(0)[0].equals(""))
    		{
    			
    		}else{
    			for(int i=0;i<zong.size();i++)
    			{
    				for(int j=0;j<zong.get(i).length;j++)
    				{
    					all[i][j]=zong.get(i)[j];
    					name[i]=all[i][0];
    					image[i]=all[i][1];
    					lianxi[i]=all[i][2];
    					time[i]=all[i][3];
    					detail[i]=all[i][4];
    				}
    			}
    			for (int i = 0; i < zong.size(); i++) {
					if (F_GetBitmap.isEmpty(image[i])) {
						all_image[i] = NetInfoUtil.getPicture(image[i]);
						F_GetBitmap.setInSDBitmap(all_image[i], image[i]);
						InputStream input = null;
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 2;
						input = new ByteArrayInputStream(all_image[i]);
						@SuppressWarnings({ "rawtypes", "unchecked" })
						SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
						imageData[i] = (Bitmap) softRef.get();
						System.out.println(imageData.length);
					} else {
						imageData[i] = F_GetBitmap.getSDBitmap(image[i]);// 拿到的是BitMap类型的图片数据
						if (F_GetBitmap.bitmap != null && !F_GetBitmap.bitmap.isRecycled()) {
							F_GetBitmap.bitmap = null;
						}
					}
				}
    			initList();
    		}
    	}
    }
    public void initList(){
		 for(int i=0;i<name.length;i++)
		  {
			  Map<String, Object> map = new HashMap<String, Object>();   
			  map.put("name", name[i]);
			  map.put("detail", detail[i]);
			  map.put("image", imageData[i]);
			  listItem.add(map);
		  }
		 initadp();
	}
    public void initadp()
	{
		Message msg = new Message();
		msg.what = 2;
		mHandler.sendMessage(msg);
	}
	Handler mHandler=new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			   case 2:initBaseAdapter();
			   break;
		       default:
			   break;
			}
		}
	};
	public void initBaseAdapter(){
		base=new baseAdapter(this);
		listview=(ListView)findViewById(R.id.yijianxiang_listview);
		listview.setAdapter(base);
		listview.setOnItemClickListener(new OnItemClickListener(){ 
			 @Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
	                    int arg2, long arg3) {
				     Intent it=new Intent(YiJianActivity.this,YiJianDetail.class);
				     it.putExtra("name", name[arg2]);
				     it.putExtra("time", time[arg2]);
				     it.putExtra("detail", detail[arg2]);
				     it.putExtra("lianxi", lianxi[arg2]);
				     startActivity(it);
			     }
		       });
		 }
	private class baseAdapter extends BaseAdapter{
		private LayoutInflater mInflater=null;
		public baseAdapter(Context context){
			
			mInflater = LayoutInflater.from(context);
		    
				data.addAll(listItem);
				listItem.clear();
		}
		@Override
		public int getCount() {
			return data.size();//记录当前列表中有多少条数
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
				convertView =mInflater.inflate(R.layout.yijianxiang_list, null) ;
				myViews.name = (TextView) convertView.findViewById(R.id.yijianxiang_name);
				myViews.detail = (TextView) convertView.findViewById(R.id.yijianxiang_detail);
				myViews.image = (RoundImageView) convertView.findViewById(R.id.yijianxiang_image);
				convertView.setTag(myViews);
			}
			else {
		          myViews = (ViewHolder ) convertView.getTag();
		      }
			myViews.name.setText( (String) data.get(position).get("name"));
			myViews.detail.setText( (String) data.get(position).get("detail"));
			myViews.image.setImageBitmap((Bitmap) data.get(position).get("image"));
			return convertView;
		}
	}
	static class ViewHolder {
	      private TextView  name;
	      private TextView  detail;
	      private RoundImageView  image;
	    }
	private class AsyncTask_sousuo extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			thread_get th_s_3 = new thread_get();
			th_s_3.start();
			try {
				th_s_3.join();
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
}
