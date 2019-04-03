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
public class sousuoliebiao extends Activity{
	String id[]=null;
	String username=null;
	ListView listview;
	baseAdapter base;
	TextView fanhui;
	String shetuan[]=null;
	String image[]=null;
	byte all_image[][];
	Bitmap imageData[];
	String name[]=null;
	ProgressDialog pd;
	String xueyuan[]=null;
	String zhuanye[]=null;
	private View footView;
	TextView js;
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sousuojieguo); 
		footView = getLayoutInflater().inflate(R.layout.listfoot, null);
		js = (TextView) footView.findViewById(R.id.iv_down_2);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中...请稍后");
	    fanhui=(TextView)findViewById(R.id.shetuan_zhanghao_fanhui_sousuo);
	    fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Intent intent = getIntent();
	    id = intent.getStringArrayExtra("DATA");
	    for(int i=0;i<id.length;i++){
	    	id[i]="["+id[i]+"]";
	    }
//	    thread_get th=new thread_get();
//	    th.start();
//	    try{
//	    	th.join();
//	    }catch(Exception e){
//	    	e.printStackTrace();
//	    }
	    new AysncTask_team().execute();
	}
	private class thread_get extends Thread{
		@Override
		public void run(){
			shetuan=new String[id.length];
			image=new String[id.length];
			all_image=new byte[id.length][];
			imageData=new Bitmap[id.length];
			name=new String[id.length];
			xueyuan=new String[id.length];
			zhuanye=new String[id.length];
			for(int i=0;i<id.length;i++){
				image[i]=NetInfoUtil.getuserphoto(id[i].substring(1, id[i].length()-1));
				name[i]=NetInfoUtil.getusername(id[i].substring(1, id[i].length()-1));
				xueyuan[i]=NetInfoUtil.getuserxueyuan(id[i].substring(1, id[i].length()-1));
				zhuanye[i]=NetInfoUtil.getuserzhuanye(id[i].substring(1, id[i].length()-1));
				zhuanye[i]=xueyuan[i]+" "+zhuanye[i];
				System.out.println("hhhhhhhhhhhhhhh"+zhuanye[i]);
				name[i]=name[i]+id[i];
				shetuan[i]=NetInfoUtil.getusershetuan(id[i].substring(1, id[i].length()-1));
				shetuan[i]=shetuan[i].substring(0, shetuan[i].length()-1);
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
	public void initList(){
		 for(int i=0;i<id.length;i++)
		 {
			  Map<String, Object> map = new HashMap<String, Object>();   
			  map.put("name", name[i]);
			  map.put("id", id[i]);
			  map.put("shetuan", shetuan[i]);
			  map.put("zhuanye", zhuanye[i]);
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
		listview=(ListView)findViewById(R.id.shetuan_sousuo_listview);
		listview.addFooterView(footView);
		base=new baseAdapter(this);
		listview.setAdapter(base);
		listview.setOnItemClickListener(new OnItemClickListener(){ 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
	                    int arg2, long arg3) {
				   Intent it=new Intent(sousuoliebiao.this,gerenxianshi.class);
				   it.putExtra("id", id[arg2].substring(1, id[arg2].length()-1));
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
				convertView =mInflater.inflate(R.layout.sousuo_list, null) ;
				myViews.touxiang=(RoundImageView)convertView.findViewById(R.id.sousuo_image);
				myViews.name = (TextView) convertView.findViewById(R.id.sousuo_name);
				myViews.id = (TextView) convertView.findViewById(R.id.sousuo_id);
				myViews.shetuanname = (TextView) convertView.findViewById(R.id.sousuo_shetuan);
				convertView.setTag(myViews);
			}
			else {
		        myViews = (ViewHolder ) convertView.getTag();
		    }
			myViews.touxiang.setImageBitmap((Bitmap) data.get(position).get("image"));
			js.setText("共"+id.length+""+"个相关结果 ");
			myViews.name.setText( (String) data.get(position).get("name"));
			myViews.id.setText( (String) data.get(position).get("zhuanye"));
			myViews.shetuanname.setText( (String) data.get(position).get("shetuan"));
			return convertView;
		}
	}
	static class ViewHolder {
		  private RoundImageView touxiang;
	      private TextView  name;
	      private TextView id;
	      private TextView shetuanname;
    }
	class AysncTask_team extends AsyncTask<Void, Integer, Void> {//异步加载线程
		@Override
		protected void onPreExecute() {
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			thread_get th2 = new thread_get();
			th2.start();
			try {
				th2.join();
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
