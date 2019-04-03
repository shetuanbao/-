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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class shetuanzhanghao extends Activity{
	TextView fanhui;
	TextView xinzeng;
	private List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>(); 
	List<String[]> namey=new ArrayList<String[]>();
	List<String[]> passwordy=new ArrayList<String[]>();
	String all[][]=null;
	String all_2[][]=null;
	String name[]=null;
	String password[]=null;
	ListView listview;
	baseAdapter base;
	ProgressDialog pd;
	String image[]=null;
	byte all_image[][]=null;
	Bitmap imageData[]=null;
	String all_3[][]=null;
	List<String[]> imagey=new ArrayList<String[]>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shetuanzhanghao);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........"); 
		fanhui=(TextView)findViewById(R.id.shetuan_zhanghao_fanhui);
		fanhui.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		xinzeng=(TextView)findViewById(R.id.shetuan_zhanghao_xinzeng);
		xinzeng.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(shetuanzhanghao.this,shetuanzhanghaoxinzeng.class);
				finish();
				startActivity(it);
			}
		});
		new AysncTask_get().execute();
	}
	private class thread_get extends Thread{
		@Override
		public void run(){
			namey=NetInfoUtil.getshetuannamebyguanli();
			passwordy=NetInfoUtil.getguanlipassword();
			imagey=NetInfoUtil.getuserpicture();
			all=new String[namey.size()][namey.get(0).length];
			all_2=new String[passwordy.size()][passwordy.get(0).length];
			all_3=new String[imagey.size()][imagey.get(0).length];
			image=new String[all_3.length];
			all_image=new byte[all_3.length][];
			imageData=new Bitmap[all_3.length];
			name=new String[all.length];
			password=new String[all_2.length];
			if (namey.get(0)[0].equals("")) {

			}else{
				for(int i=0;i<namey.size();i++){
					for(int j=0;j<namey.get(i).length;j++){
						all[i][j]=namey.get(i)[j];
						name[i]=all[i][0];
						all_2[i][j]=passwordy.get(i)[j];
						password[i]=all_2[i][0];
						all_3[i][j]=imagey.get(i)[j];
						image[i]=all_3[i][0]+".png";
						System.out.println("ggggggggggggggggg"+image[i]);
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
		msg.what = 0;
		mHandler.sendMessage(msg);
	}
	Handler mHandler=new Handler()
	{
		 @Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case 0:initBaseAdapter();			
			break;
			default:
				break;
			}
		}
	};
	public void initBaseAdapter(){
		base=new baseAdapter(this);
		listview=(ListView)findViewById(R.id.shetuan_zhanghao_listview);
		listview.setAdapter(base);
		listview.setOnItemClickListener(new OnItemClickListener(){ 
			 @Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
	                    int arg2, long arg3) {
				  Intent it=new Intent(shetuanzhanghao.this,shetuanzhanghao_detail.class);
				  it.putExtra("name", name[arg2]);
				  it.putExtra("password", password[arg2]);
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
				convertView =mInflater.inflate(R.layout.shetuan_zhanghao_list, null) ;
				myViews.touxiang=(ImageView)convertView.findViewById(R.id.shetuan_image);
				myViews.name = (TextView) convertView.findViewById(R.id.shetuanzhanghao_list);
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
}
