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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
public class shanchushetuan extends Activity{
	List<String[]> namey=new ArrayList<String[]>();
	String all[][]=null;
	String name[]=null;
	ListView listview;
	baseAdapter base;
	String deletename="";
	TextView fanhui;
	ProgressDialog pd;
	List<String[]> imagey=new ArrayList<String[]>();
	String image[]=null;
	String all_2[][]=null;
	byte all_image[][]=null;
	Bitmap imageData[]=null;
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shanchushetuan);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		fanhui=(TextView)findViewById(R.id.foodlist_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
//		thread_get gg=new thread_get();
//		gg.start();
//		try{
//			gg.join();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		new AsyncTask_sousuo().execute();
	}
	private class deleteshetuan extends Thread{
		@Override
		public void run(){
			NetInfoUtil.deleteshetuan(deletename);
		}
	}
	private class thread_get extends Thread{
		@Override
		public void run(){
			namey=NetInfoUtil.getallshetuan();
			imagey=NetInfoUtil.getshetuanpicture();
			all_2=new String[imagey.size()][imagey.get(0).length];
			all_image=new byte[all_2.length][];
			imageData=new Bitmap[all_2.length];
			image=new String[all_2.length];
			all=new String[namey.size()][namey.get(0).length];
			name=new String[all.length];
			if (namey.get(0)[0].equals("")) {

			} else {
				for (int i = 0; i < namey.size(); i++) {
					for (int j = 0; j < namey.get(i).length; j++) {
						all[i][j] = namey.get(i)[j];
						name[i]=all[i][0];
						all_2[i][j]=imagey.get(i)[j];
						image[i]=all_2[i][0]+".png";
					}
				}
				for (int i = 0; i < imagey.size(); i++) {
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
		listview=(ListView)findViewById(R.id.shanchushetuan);
		listview.setAdapter(base);
		listview.setOnItemClickListener(new OnItemClickListener(){ 
			 @Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
	                    final int arg2, long arg3) {
//				  
				 AlertDialog isExit=new AlertDialog.Builder(shanchushetuan.this).create();
					isExit.setTitle("系统提示");
					isExit.setMessage("确定屏蔽"+name[arg2]+"吗?");
					isExit.setButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									 deletename=name[arg2];
									   deleteshetuan dt=new deleteshetuan();
									   dt.start();
									   try{
										   dt.join();
									   }catch(Exception e){
										   e.printStackTrace();
									   }
									   Intent it=new Intent(shanchushetuan.this,shanchushetuan.class);
									   finish();
										startActivity(it);
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
				convertView =mInflater.inflate(R.layout.shanchushetuan_list, null) ;
				myViews.name = (TextView) convertView.findViewById(R.id.shanchushetuan_list);
				myViews.touxiang = (ImageView) convertView.findViewById(R.id.shetuanshanchu_image);
				convertView.setTag(myViews);
			}
			else {
		          myViews = (ViewHolder ) convertView.getTag();
		      }
			//myViews.title.setTypeface(FontManager.tf);
			//myViews.info.setTypeface(FontManager.tf);
			myViews.name.setText( (String) data.get(position).get("name"));
			myViews.touxiang.setImageBitmap((Bitmap) data.get(position).get("image"));
			return convertView;
		}
		
	}
	static class ViewHolder {
	      private TextView  name;
	      private ImageView touxiang;
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
