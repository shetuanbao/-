package com.bn.community;

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
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bn.activity.HuoDongDetailActivity;
import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.bn.util.ObservableScrollView;
import com.bn.util.ObservableScrollView.ScrollViewListener;
import com.example.Team_Android.MainCommunityActivity;
import com.example.chat.R;

public class CommunityDetailActivity extends Activity implements ScrollViewListener{
	ProgressDialog pd;
	String re_name = null;
	String re_id = null;
	String re_kouhao = null;
	TextView tt = null;
	TextView tt2 = null;
	TextView tt3 = null;
	TextView tt4 = null;
	ImageView tt5 = null;
	String detail = null;
	String people = null;
	String kouhao = null;
	public static String s = null;
	private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	Bitmap imageData[]=null;
	byte all_image2[];
	Bitmap imageData2[];
	byte[][] all_image;
	private String image[]=null;
	private String id[]=null;
	private String time[]=null;
	private String place[]=null;
	private String name[]=null;
	List<String[]> imagey = new ArrayList<String[]>();
	String all[][]=null;
	private LayoutInflater mInflater;
	private LinearLayout mGallery;
	private int[] mImgIds;
	ImageView tt6 = null;
	private ImageView imageView;
	private ImageView imageView2;
	private ImageView[] imageViews = null;
	private ViewGroup viewPoints;
	private int index = 0;
	TextView tt0 = null;
	byte jieshou[] = null;
	Bitmap bp = null;
	MainCommunityActivity mm=new MainCommunityActivity();
	private ViewPager viewPager;
	private ImageView[] tips;
	private ImageView[] mImageViews;
	private int[] imgIdArray;
	private boolean isRunning = true;
	int currentItem;
	ListView listview;
	baseAdapter base;
	private View footView;
	private ImageView ivDown;
	static int itemCount=0;
	private int height ; 
	String zhuangtai=null;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			// 执行滑动到下一个页面
			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
			if (isRunning) {
				// 在发一个handler延时
				handler.sendEmptyMessageDelayed(0, 2000);
			}
		};
	};
	RelativeLayout biaotilan;
	ObservableScrollView sc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shetuan_detail);
		//initView();  
		mInflater = LayoutInflater.from(this);
		footView = getLayoutInflater().inflate(R.layout.view_foot, null);
		ivDown = (ImageView) footView.findViewById(R.id.iv_down);
		// tt5=(ImageView)findViewById(R.id.shetuan_top_image);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中...请稍后");
		tt0 = (TextView) findViewById(R.id.shetuan_tool_text1);
		tt0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainCommunityActivity.pd.dismiss();
				finish();
			}
		});
		Intent intent = getIntent();
		re_name = intent.getStringExtra("name");
		re_id = intent.getStringExtra("id");
		re_kouhao = intent.getStringExtra("kouhao");
//		jieshou = intent.getByteArrayExtra("picture");
//		bp = BitmapFactory.decodeByteArray(jieshou, 0, jieshou.length);
		thread_get th = new thread_get();
		th.start();
		try {
			th.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//new thread_delete2().execute();
		inittextview();
//		imgIdArray = new int[] { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
//				R.drawable.image5 };
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup3);
		viewPager = (ViewPager) findViewById(R.id.viewPager3);
		tips = new ImageView[imageData.length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(30, 30));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused1);
			}else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused1);
			}
			ViewGroup parent = (ViewGroup) imageView.getParent();
			if (parent != null) {
				parent.removeAllViewsInLayout();
			}
			group.addView(imageView);
		}
		// 将图片装载到数组中
		mImageViews = new ImageView[imageData.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			//imageView.setBackgroundResource(imgIdArray[i]);
			imageView.setImageBitmap(imageData[i]);
		}
		// 设置Adapter
				viewPager.setAdapter(new MyAdapter());
				// 设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
				viewPager.setCurrentItem((mImageViews.length) * 100);
				// 设置监听，主要是设置点点的背景
				viewPager.setOnPageChangeListener(new OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						setImageBackground(position % mImageViews.length);
						// lastPosition = position;
					}
					@Override
					public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
						// 页面正在滑动时间回调
					}
					@Override
					public void onPageScrollStateChanged(int state) {
						// 当pageView 状态发生改变的时候，回调
					}
				});
				handler.sendEmptyMessageDelayed(0, 2000);
			//initView();
		FontManager.initTypeFace(this);
		FontManager.changeFonts(FontManager.getContentView(this), this);
		s = tt.getText().toString();
	}
	private void initView() {  
		 sc = (ObservableScrollView) findViewById(R.id.gundong);  
		 biaotilan = (RelativeLayout) findViewById(R.id.shetuan_tool);
		 //biaotilan.getBackground().setAlpha(0);
		 biaotilan.setBackgroundColor(Color.argb(255, 73, 196, 214));
		
		 //biaotilan.setBackgroundColor(Color.argb(1, 73, 196, 214));
		 ViewTreeObserver vto = biaotilan.getViewTreeObserver();    
	        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {  
	            @Override    
	            public void onGlobalLayout() {  
	            	biaotilan.getViewTreeObserver().removeGlobalOnLayoutListener(this);  
	                height =   biaotilan.getHeight();  
	                biaotilan.getWidth();  
	                sc.setScrollViewListener(CommunityDetailActivity.this);  
	            }    
	        });
	        //sc.setScrollViewListener(shetuandetailactivity.this);
	}
	    @Override
		public void onScrollChanged(ObservableScrollView scrollView, int x, int y,  
	            int oldx, int oldy) {  
//	      Log.i("TAG","y--->"+y+"    height-->"+height);  
	        if(y<=height&&y>0 ){  
	           
	            float scale =(float) (height-y) /height;  
	            float alpha =  (255 * scale);  
//	            Log.i("TAG","alpha--->"+alpha);  
	            //layout全部透明  
//	            layoutHead.setAlpha(scale);  
	            //只是layout背景透明(仿知乎滑动效果)  
	            //biaotilan.setBackgroundColor(Color.argb(1, 73, 196, 214));
	            biaotilan.setBackgroundColor(Color.argb((int) alpha, 73, 196, 214));  
	        	tt0.setTextColor(this.getResources().getColor(R.color.white));
	        	
	        }  
	          else if(y > height&&y<=2*height){
//	        	  float scale =(float) (y-height) /height;  
//		            float alpha =  (255 * scale);  
//		            Log.i("TAG","alpha--->"+alpha);  
		            //layout全部透明  
//		            layoutHead.setAlpha(scale);  
		            //只是layout背景透明(仿知乎滑动效果)  
		            biaotilan.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));  
		        	tt0.setTextColor(this.getResources().getColor(R.color.blue2));
	        }else if(y>2*height) {
	        	//biaotilan.getBackground().setAlpha(255);
	        	 // tt0.setTextColor(this.getResources().getColor(R.color.white));
	        	biaotilan.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b)); 
	        	tt0.setTextColor(this.getResources().getColor(R.color.blue2));
	        }
	    }  
	public class thread_get extends Thread {
		@Override
		public void run() {
			detail = NetInfoUtil.getshetuandetail(re_id);
			zhuangtai=NetInfoUtil.getshetuanrenzt(re_id);
			if(zhuangtai.equals("0")){
				people="无";
			}else{
				people = NetInfoUtil.getshetuanpeople(re_id);
			}
			imagey = NetInfoUtil.getshetuantohuodong(re_id.trim());
			//System.out.println("tttttttttttttttttttt"+people.length());
			all=new String[imagey.size()][imagey.get(0).length];
			image=new String[all.length];
			name=new String[all.length];
			time=new String[all.length];
			place=new String[all.length];
			id=new String[all.length];
			imageData=new Bitmap[all.length];
			all_image=new byte[all.length][];
			if(imagey.get(0)[0].equals("")){
				
			}else{
				for(int i=0;i<imagey.size();i++){
					for(int j=0;j<imagey.get(i).length;j++){
						all[i][j]=imagey.get(i)[j];
			            id[i]=all[i][0];
			            name[i]=all[i][1];
			            time[i]=all[i][2];
			            place[i]=all[i][3];
						image[i]=all[i][4]+".png";
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
						//System.out.println(imageData.length);
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
	public void initList() {
		for (int i = 0; i < name.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name[i]);
			map.put("image", imageData[i]);
			map.put("place",  place[i]);
			map.put("time", time[i]);
			listItem.add(map);
		}
		initBaseAdapter();
	}
	public void initBaseAdapter() {
		listview=(ListView)findViewById(R.id.shetuandetail_listview);
		listview.addFooterView(footView);
		ivDown.setImageDrawable(getResources()
				.getDrawable(R.drawable.icon_down));
		base = new baseAdapter(this);
		listview.setAdapter(base);
		setListViewHeightBasedOnChildren(listview);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent it=new Intent(CommunityDetailActivity.this,HuoDongDetailActivity.class);
				it.putExtra("name", name[arg2]);
				it.putExtra("id", id[arg2]);
				it.putExtra("time", time[arg2]);
				it.putExtra("place", place[arg2]);
				
				startActivity(it);
			}
		});
		initEvent();
	}
	private void initEvent() {
		ivDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// 判断getCount()数据的数量，如果等于3点击后就设置getCount()为全部数量，设置修改标识，刷新。
				// 否则，相反。
				if (base.getCount() == 3) {
					base.addItemNum(data.size());
					ivDown.setImageDrawable(getResources().getDrawable(
							R.drawable.icon_up));
					setListViewHeightBasedOnChildren(listview);
					base.notifyDataSetChanged();
				} else {
					base.addItemNum(3);
					ivDown.setImageDrawable(getResources().getDrawable(
							R.drawable.icon_down));
					setListViewHeightBasedOnChildren(listview);
					base.notifyDataSetChanged();
				}
			}
		});
	}

	private class baseAdapter extends BaseAdapter { // 内部类：适配器

		private LayoutInflater mInflater = null;

		public baseAdapter(Context context) {

			mInflater = LayoutInflater.from(context);

			data.addAll(listItem);
			listItem.clear();
		}

		@Override
		public int getCount() {
			if (data.size() > 3)
	        {
				return itemCount;
	        }else 
	        {
	        	return data.size();
			}
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
				convertView = mInflater.inflate(R.layout.sthuodong, null);
				myViews.image = (ImageView) convertView.findViewById(R.id.huodong_image);
				myViews.name = (TextView) convertView.findViewById(R.id.huodong_name);
				myViews.didian=(TextView)convertView.findViewById(R.id.huodong_didian);
				myViews.time=(TextView)convertView.findViewById(R.id.huodong_time);
				convertView.setTag(myViews);
			} else {
				myViews = (ViewHolder) convertView.getTag();

			}
			myViews.image.setImageBitmap((Bitmap) data.get(position).get("image"));
			myViews.name.setText((String) data.get(position).get("name"));
			myViews.didian.setText((String) data.get(position).get("place"));
			myViews.time.setText((String) data.get(position).get("time"));
			myViews.name.setTypeface(FontManager.tf);
			myViews.didian.setTypeface(FontManager.tf);
			myViews.time.setTypeface(FontManager.tf);
			return convertView;
		}
		public void addItemNum(int number)
	    {
		    itemCount = number;
	    }

	}
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
		listView.setLayoutParams(params);
	}

	static class ViewHolder {

		private ImageView image;
		private TextView name;
		private TextView time;
		private TextView didian;
	}
	private void inittextview() {
		// TODO Auto-generated method stub
		tt = (TextView) findViewById(R.id.shetuan_name_2);
		tt2 = (TextView) findViewById(R.id.shetuan_detail_2);
		tt3 = (TextView) findViewById(R.id.shetuan_people_2);
		tt4 = (TextView) findViewById(R.id.shetuan_kouhao_2);
		tt.setText(re_name);
		tt2.setText(detail);
		tt3.setText(people);
		tt4.setText(re_kouhao);
		tt3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(CommunityDetailActivity.this, CommunityPeopleActivity.class);
				startActivity(it);
			}
		});
	}
	class thread_delete2 extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {// thread_insert th2=new
														// thread_insert();
														// th2.start();
			thread_get th = new thread_get();
			th.start();
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
			/*
			 * if(detaily.get(0)[0].equals("")) {
			 * //nodate.setVisibility(View.VISIBLE);
			 * listview.setVisibility(View.GONE); base.notifyDataSetChanged(); }
			 * else { listview.setVisibility(View.VISIBLE);
			 * //nodate.setVisibility(View.GONE); base.notifyDataSetChanged(); }
			 * listItem.clear(); //base.notifyDataSetChanged(); for (int i = 0;
			 * i <detail.length; i++) { Map<String, Object> map = new
			 * HashMap<String, Object>(); map.put("pinglun", detail[i]);
			 * map.put("image", imageData[i]); map.put("name", name[i]);
			 * listItem.add(map);
			 * 
			 * } data.addAll(listItem);
			 */
			pd.dismiss();
			// base.notifyDataSetChanged();

		}
	}
	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);

		}

		/**
		 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
		 */
		@Override
		public Object instantiateItem(View container, final int position) {
			if (mImageViews[position % mImageViews.length].getParent() != null) {
				((ViewGroup) mImageViews[position % mImageViews.length].getParent())
						.removeView(mImageViews[position % mImageViews.length]);
			}

			((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
			currentItem = position;
			mImageViews[position % mImageViews.length].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					// Toast.makeText(MainHuodongActivity.this,(position %
					// mImageViews.length)+"",Toast.LENGTH_LONG).show();
					Intent it=new Intent(CommunityDetailActivity.this,HuoDongDetailActivity.class);
					it.putExtra("name", name[position % mImageViews.length]);
					it.putExtra("id", id[position % mImageViews.length]);
					it.putExtra("time", time[position % mImageViews.length]);
					it.putExtra("place", place[position % mImageViews.length]);
					
					startActivity(it);
				}
			});

			return mImageViews[position % mImageViews.length];

		}
	}

	public void onPageScrollStateChanged(int arg0) {

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	public void onPageSelected(int arg0) {
		setImageBackground(arg0 % mImageViews.length);

	}

	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused1);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused1);
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) // 重写返回键
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			MainCommunityActivity.pd.dismiss();
			finish();
		}
		return false;
	}
}
