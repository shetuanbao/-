package com.bn.manage;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bn.util.F_GetBitmap;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class MainActivity extends Activity {
    ListView listview;
    List<String[]> imagey=new ArrayList<String[]>();
    String all[][]=null;
    String image[]=null;
    String name[]=null;
    String name2[]=null;
    byte all_image[][];
    Bitmap imageData[];
    TextView fanhui;
    TextView tianjia;
    public static baseAdapter base;
    public static int x=0;
    ProgressDialog pd;
    Bitmap bm;
    Bitmap cameraBitmap;
    String photopath = "999"; 
	String path;
	boolean pdd=false;
	String id=null;
	static int count=0;
    public static List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
	public static List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiangce_xiugai);
		listItem.clear();
		data.clear();
		pdd=false;
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中...请稍后");
		Intent intent = getIntent();
		id=intent.getStringExtra("id");
		fanhui=(TextView)findViewById(R.id.xiangce_fanhui_2);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tianjia=(TextView)findViewById(R.id.xiangce_tianjia);
		tianjia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MainActivity.this,xiangcetianjia.class);
				if(pdd==false){
					it.putExtra("id", id);
					it.putExtra("length",id);
					System.out.println("33333333333333333333333333333333333");
				}else{
					it.putExtra("id", id);
					it.putExtra("length",name2[name2.length-1]);
				}
				finish();
				startActivity(it);
			}
		});
		thread_get gg=new thread_get();
		gg.start();
		try{
			gg.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		//new AsyncTask_get().execute();
	}
    private class thread_get extends Thread{
    	@Override
		public void run(){
    		imagey=NetInfoUtil.gethuodongxiangce(id);
    		all=new String[imagey.size()][imagey.get(0).length];
    		image=new String[all.length];
    		name=new String[all.length];
    		all_image=new byte[all.length][];
    		imageData=new Bitmap[all.length];
    		name2=new String[all.length];
    		//NetInfoUtil.updataxiangce("95002_1"+"#"+imagey.get(0)[0]);
    		System.out.println("dfgdfg"+name[0]);
    		if (imagey.get(0)[0].equals("")) {
                   pdd=false;
			} else {
				pdd=true;
				for (int i = 0; i < imagey.size(); i++) {
					for (int j = 0; j < imagey.get(i).length; j++) {
						all[i][j]=imagey.get(i)[j];
						name2[i]=all[i][0];
						image[i]=all[i][0]+".png";
						name[i]="活动图片"+(i+1)+"";
						count=i+1;
					}
				}
				count++;
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
    	for (int i = 0; i < name.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name[i]);
			map.put("image", imageData[i]);
			listItem.add(map);
		}
    	initBaseAdapter();
    }
    public void initBaseAdapter() {
    	listview=(ListView)findViewById(R.id.xiangce_listview);
		base = new baseAdapter(this);
		listview.setAdapter(base);
		listItem.clear();
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MainActivity.this,chakanxiangceActivity.class);
				it.putExtra("name", all[arg2][0]);
				x=arg2;
				
				it.putExtra("xiangce", imageData[arg2]);
				startActivity(it);
			}
		});
	}
    public class baseAdapter extends BaseAdapter { // 内部类：适配器

		private LayoutInflater mInflater = null;

		public baseAdapter(Context context) {

			mInflater = LayoutInflater.from(context);

			data.addAll(listItem);
			listItem.clear();
		}

		@Override
		public int getCount() {
			return data.size();// 记录当前列表中有多少条数
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
			ViewHolder myViews;
			if (convertView == null) {
				myViews = new ViewHolder();
				convertView = mInflater.inflate(R.layout.xiangce_manger_list, null);
				myViews.image = (ImageView) convertView.findViewById(R.id.xiangce_image2);
				myViews.name = (TextView) convertView.findViewById(R.id.xiangce_name_3);
				convertView.setTag(myViews);
			} else {
				myViews = (ViewHolder) convertView.getTag();

			}
			myViews.image.setImageBitmap((Bitmap) data.get(position).get("image"));
			myViews.name.setText((String) data.get(position).get("name"));
			return convertView;
		}
	}
    static class ViewHolder {

		private ImageView image;
		private TextView name;
	}
    private class AsyncTask_get extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
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
