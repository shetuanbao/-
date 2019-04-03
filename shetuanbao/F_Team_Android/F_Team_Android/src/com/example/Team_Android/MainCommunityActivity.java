package com.example.Team_Android;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.community.CommunityCharitableActivity;
import com.bn.community.CommunityDetailActivity;
import com.bn.community.CommunitySearchActivity;
import com.bn.community.CommunitySportsActivity;
import com.bn.community.CommunityLiteratureActivity;
import com.bn.community.CommunityLearningActivity;
import com.bn.util.Exit;
import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.bn.util.RefreshableView;
import com.example.chat.R;

public class MainCommunityActivity extends Activity implements OnPageChangeListener, OnScrollListener {
	private ListView listview = null;
	private String all[][] = null;
	private String all_2[][] = null;
	private String all_3[][] = null;
	private String all_4[][] = null;
	private String all_5[][] = null;
	private String[] shetuan = null;
	private String[] id = null;
	private String[] kouhao = null;
	private String image[] = null;
	private String image2[] = null;
	public static ProgressDialog pd;
	private List<String[]> shetuany = new ArrayList<String[]>();
	private List<String[]> idy = new ArrayList<String[]>();
	private List<String[]> kouhaoy = new ArrayList<String[]>();
	private List<String[]> imagey = new ArrayList<String[]>();
	private List<String[]> zong = new ArrayList<String[]>();
	private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	private baseAdapter base = null;
	private LinearLayout t1 = null;
	private LinearLayout t2 = null;
	private LinearLayout t3 = null;
	private LinearLayout t4 = null;
	Bitmap[] imageData;
	Bitmap[] imageData2;
	byte[][] all_image2;
	byte[][] all_image;
	List<String[]> imaged = new ArrayList<String[]>();
	private RelativeLayout nodate = null;
	private ImageView nodate_image = null;
	private TextView sousuo = null;
	ImageView school;
	ImageView im = null;
	static int num = 0;
	static int n=0;
	static int x = 0;
	static int countid = 0;
	static int countpicture = 0;
	static int count = 0;
	static int reduce = 0;
	byte chuandi[][] = null;
	static String idd[]=null;
	static String kouhaoo[]=null;
	static String namee[]=null;
	private View moreView;
	private List<String[]> msgIds1 = null;
	private RelativeLayout title = null;
	private RelativeLayout title_gone = null;
	private AutoCompleteTextView actv;
	ImageView search = null;
	ImageView back = null;
	private ViewPager viewPager;
	private ImageView[] tips;
	private ImageView[] mImageViews;
	private int[] imgIdArray;
	int currentItem;
	private boolean isRunning = true;
	static int idd2=95001;
	private String shetuanname[]=null;
	private String shetuanid[]=null;
	static int bushu=0;
	boolean shifou=false;
	private RefreshableView rd=null;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Exit.getInstance().addActivities(this);
		shifou=false;
		moreView = getLayoutInflater().inflate(R.layout.load, null);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup2);
		viewPager = (ViewPager) findViewById(R.id.viewPager2);

		// 载入图片资源ID
		imgIdArray = new int[] { R.drawable.zuqiu, R.drawable.jiewu, R.drawable.yumao, R.drawable.pingpang,
				R.drawable.lunhua };

		// 将点点加入到ViewGroup中
		tips = new ImageView[imgIdArray.length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(30, 30));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused1);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused1);
			}
			ViewGroup parent = (ViewGroup) imageView.getParent();
			if (parent != null) {
				parent.removeAllViewsInLayout();
			}
			group.addView(imageView);
		}
		// 将图片装载到数组中
		mImageViews = new ImageView[imgIdArray.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imgIdArray[i]);
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
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中，请稍后........");
		search = (ImageView) findViewById(R.id.shetuan_search_image);
		title = (RelativeLayout) findViewById(R.id.shetuan_1);
		title_gone = (RelativeLayout) findViewById(R.id.main_search_title_clock);
		back = (ImageView) findViewById(R.id.main_search_title_clock_back);
		school = (ImageView) findViewById(R.id.school);
		school.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.ncst.edu.cn"));
				startActivity(intent);
			}
		});
		sousuo = (TextView) findViewById(R.id.shetuantitle_bar_sousuo);
		sousuo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                shifou=true;
                String messa = actv.getText().toString();
				Intent it=new Intent(MainCommunityActivity.this,CommunitySearchActivity.class);
				it.putExtra("messa", messa);
				startActivity(it);
			}
		});
		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title.setVisibility(View.GONE);
				title_gone.setVisibility(View.VISIBLE);
			}
		});
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title.setVisibility(View.VISIBLE);
				title_gone.setVisibility(View.GONE);

			}
		});
		thread_shetuan th = new thread_shetuan();
		th.start();
		try {
			th.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1 = (LinearLayout) findViewById(R.id.shetuan_button_1);
		t1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainCommunityActivity.this, CommunitySportsActivity.class);
				startActivity(it);
			}
		});
		t2 = (LinearLayout) findViewById(R.id.shetuan_button_2);
		t2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainCommunityActivity.this, CommunityLiteratureActivity.class);
				// Toast.makeText(MianShetuanActivity.this,"�ղسɹ�",Toast.LENGTH_LONG).show();
				startActivity(it);
			}
		});
		t3 = (LinearLayout) findViewById(R.id.shetuan_button_3);
		t3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainCommunityActivity.this, CommunityLearningActivity.class);
				startActivity(it);
			}
		});
		t4 = (LinearLayout) findViewById(R.id.shetuan_button_4);
		t4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainCommunityActivity.this, CommunityCharitableActivity.class);
				startActivity(it);
			}
		});
		actv = (AutoCompleteTextView) findViewById(R.id.shetuantitle_bar_name_text);
		actv.setThreshold(1);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainCommunityActivity.this,
				R.layout.autocompletetextview, shetuanname);
		actv.setAdapter(adapter2);
		actv.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				shifou=true;
				for (int i = 0; i < shetuanname.length; i++) {
					if (actv.getText().toString().trim().equals(shetuanname[i].toString().trim())) {
						bushu=i;
						String messa = actv.getText().toString();
						Intent it=new Intent(MainCommunityActivity.this,CommunitySearchActivity.class);
						it.putExtra("messa", messa);
						startActivity(it);
					}
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				// sp.edit().clear().commit();
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}
		});
		FontManager.initTypeFace(this);
		FontManager.changeFonts(FontManager.getContentView(this), this);
	}
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				pd.show();
				break;
			case 2:
				Toast.makeText(MainCommunityActivity.this, "已经滑到底部!",
						Toast.LENGTH_LONG).show();
			}
		}
	};
	private class thread_shetuan extends Thread {
		@Override
		public void run() {
			zong = NetInfoUtil.getshetuanmessage();
			shetuanname=NetInfoUtil.getallshetuan();
			shetuanid=NetInfoUtil.getallshetuanid();
			num = zong.size();
			if (num < 5) {
				n = num;
				x = n;
				num = num - n;
			} else {
				n = 5;
				x = n;
				num = num - n;
			}
			all = new String[n][zong.get(0).length];
			shetuan = new String[all.length];
			id = new String[all.length];
			kouhao = new String[all.length];
			image = new String[all.length];
			all_image = new byte[all.length][];
			imageData = new Bitmap[all.length];
			idd=new String[all.length];
			kouhaoo=new String[all.length];
			namee=new String[all.length];
			if (zong.get(0)[0].equals("")) {

			} else {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < zong.get(i).length; j++) {
						all[i][j] = zong.get(i)[j];
						shetuan[i] = all[i][0];
						id[i] = all[i][1];
						kouhao[i] = all[i][2];
						image[i] = all[i][3] + ".png";
						namee[i]=shetuan[i];
						idd[i]=id[i];
						kouhaoo[i]=kouhao[i];
					}
				}
				for (int i = 0; i < n; i++) {
					if (F_GetBitmap.isEmpty(image[i])) {
						all_image[i] = NetInfoUtil.getPicture(image[i]);
						F_GetBitmap.setInSDBitmap(all_image[i], image[i]);
						InputStream input = null;
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 1;
						input = new ByteArrayInputStream(all_image[i]);
						@SuppressWarnings({ "rawtypes", "unchecked" })
						SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
						imageData[i] = (Bitmap) softRef.get();
						System.out.println(imageData.length);
					} else {
						imageData[i] = F_GetBitmap.getSDBitmap(image[i]);// �õ�����BitMap���͵�ͼƬ����
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
		for (int i = 0; i < shetuan.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", shetuan[i]);
			map.put("id", id[i]);
			map.put("kouhao", kouhao[i]);
			map.put("image", imageData[i]);
			listItem.add(map);
		}
		inithandler();
	}
	public void inithandler() {
		Message msg = new Message();
		msg.what = 2;
		mHandler2.sendMessage(msg);
	}
	private Handler mHandler2 = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loadMoreData(); // 加载更多数据
				base.notifyDataSetChanged();
				moreView.setVisibility(View.GONE);
				break;
			case 1:
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				reduceSomeData();// 删除超出的数据
				loadMoreData(); // 加载更多数据，这里可以使用异步加载
				base.notifyDataSetChanged();
				moreView.setVisibility(View.GONE);
				break;
			case 2:
				initBaseAdapter();
				break;
			case 3:
				pd.show();
				break;
			case 4:
				pd.dismiss();
				break;
			default:
				break;
			}
		}
	};
	public void initBaseAdapter() {
		base = new baseAdapter(this);
		listview = (ListView) findViewById(R.id.shetuan_listview);
		listview.addFooterView(moreView);
		listview.setAdapter(base);
		listItem.clear();
		listview.setOnScrollListener(this);
		// setListViewHeightBasedOnChildren(listview);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				pd.show();
				LinearLayout view1 = (LinearLayout) arg1;
				LinearLayout ll = (LinearLayout) view1.getChildAt(1);
				LinearLayout l = (LinearLayout) ll.getChildAt(0);
				LinearLayout l2 = (LinearLayout) ll.getChildAt(1);
				TextView text = (TextView) l.getChildAt(0);
				TextView text2 = (TextView) l2.getChildAt(0);
				String mes = text.getText().toString();
				String mes2 = text2.getText().toString();
				Intent it = new Intent(MainCommunityActivity.this, CommunityDetailActivity.class);
                it.putExtra("name", mes);
				it.putExtra("id", shetuanid[arg2]);
				it.putExtra("kouhao", mes2);
				startActivity(it);
			}
		});
	}
	private class baseAdapter extends BaseAdapter {
		private LayoutInflater mInflater = null;
		public baseAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			data.addAll(listItem);
			listItem.clear();
		}
		@Override
		public int getCount() {
			return data.size();// ��¼��ǰ�б����ж�������
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
				convertView = mInflater.inflate(R.layout.shetuan_list, null);
				myViews.image = (ImageView) convertView.findViewById(R.id.shetuan_image);
				myViews.name = (TextView) convertView.findViewById(R.id.shetuan_name);
				myViews.id = (TextView) convertView.findViewById(R.id.shetuan_id);
				convertView.setTag(myViews);
			} else {
				myViews = (ViewHolder) convertView.getTag();

			}
			myViews.image.setImageBitmap((Bitmap) data.get(position).get("image"));
			myViews.name.setText((String) data.get(position).get("name"));
			myViews.id.setText((String) data.get(position).get("kouhao"));
			myViews.name.setTypeface(FontManager.tf);
			myViews.id.setTypeface(FontManager.tf);
			return convertView;
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
		private TextView id;
	}
	private void reduceSomeData() {
		for (int i = 0; i < 10; i++) {
			listItem.remove(i); // 清除前十条数据
		}
		reduce = reduce + 10;
		count = listItem.size();// 记录当前列表中有多少条
		data.addAll(listItem);
		listItem.clear();
	}
	private class thread_shetuan2 extends Thread {
		@Override
		public void run() {
			if (num < 5) {
				n = num;
				x = x + n;
				num = num - n;
			} else {
				n = 5;
				x = x + n;
				num = num - n;
			}
			zong = NetInfoUtil.getshetuanmessage();
			all = new String[n][zong.get(0).length];
			shetuan = new String[all.length];
			id = new String[all.length];
			kouhao = new String[all.length];
			image = new String[all.length];
			all_image = new byte[all.length][];
			imageData = new Bitmap[all.length];
			if (zong.get(0)[0].equals("")) {

			} else {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < zong.get(i).length; j++) {
						all[i][j] = zong.get(x - n + i)[j];
						shetuan[i] = all[i][0];
						id[i] = all[i][1];
						kouhao[i] = all[i][2];
						image[i] = all[i][3] + ".png";
					}
				}
				if(n==0){
					mHandler.sendEmptyMessage(2);
				}
				for (int i = 0; i < n; i++) {
					if (F_GetBitmap.isEmpty(image[i])) {
						all_image[i] = NetInfoUtil.getPicture(image[i]);
						F_GetBitmap.setInSDBitmap(all_image[i], image[i]);
						InputStream input = null;
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 1;
						input = new ByteArrayInputStream(all_image[i]);
						@SuppressWarnings({ "rawtypes", "unchecked" })
						SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
						imageData[i] = (Bitmap) softRef.get();
						System.out.println(imageData.length);
					} else {
						imageData[i] = F_GetBitmap.getSDBitmap(image[i]);// �õ�����BitMap���͵�ͼƬ����
						if (F_GetBitmap.bitmap != null && !F_GetBitmap.bitmap.isRecycled()) {
							F_GetBitmap.bitmap = null;
						}
					}
				}
				for (int i = 0; i < shetuan.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", shetuan[i]);
					map.put("id", id[i]);
					map.put("kouhao", kouhao[i]);
					map.put("image", imageData[i]);
					listItem.add(map);
				}
			}
		}
	}
	private void loadMoreData() {
		thread_shetuan2 lmd = new thread_shetuan2();
		lmd.start();
		try {
			lmd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count = listItem.size();// 记录当前列表中有多少条数
		data.addAll(listItem);
		listItem.clear();
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState) {
		// 当不滚动时
		case OnScrollListener.SCROLL_STATE_IDLE:
			// 判断滚动到底部
			if (count < 50 && view.getLastVisiblePosition() == (view.getCount() - 1)) {
				moreView.setVisibility(View.VISIBLE);
				// Toast.makeText(this, "滑倒底部", Toast.LENGTH_SHORT).show();
				mHandler2.sendEmptyMessage(0);
			}
			if (count > 50 && view.getLastVisiblePosition() == (view.getCount() - 1)) {

				moreView.setVisibility(View.VISIBLE);

				mHandler2.sendEmptyMessage(1);
			}
			break;
		}
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

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
					Intent it = new Intent(MainCommunityActivity.this, CommunityDetailActivity.class);
					it.putExtra("name", namee[position % mImageViews.length]);
					it.putExtra("id", idd[position % mImageViews.length ]);
					it.putExtra("kouhao", kouhaoo[position % mImageViews.length]);
					startActivity(it);
				}
			});
			return mImageViews[position % mImageViews.length];
		}
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
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

//	@Override
//	@SuppressWarnings("deprecation")
//	public boolean onKeyDown(int keyCode, KeyEvent event) {  
//		// 如果是返回键,直接返回到桌面  
//		if(keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME){  
//		      showExitGameAlert();  
//		}  
//		return super.onKeyDown(keyCode, event);  
//		}  
//		private void showExitGameAlert() {  
//		final AlertDialog dlg = new AlertDialog.Builder(this).create();  
//		dlg.show();  
//		Window window = dlg.getWindow();  
//		// *** 主要就是在这里实现这种效果的.  
//		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容  
//		window.setContentView(R.layout.jianting);  
//		// 为确认按钮添加事件,执行退出应用操作  
//		ImageButton ok = (ImageButton) window.findViewById(R.id.btn_ok);  
//		ok.setOnClickListener(new View.OnClickListener() {  
//		@Override
//		public void onClick(View v) {  
//			Exit.exitActivity(); // 退出应用...  
//		}  
//		});  
//		// 关闭alert对话框架  
//		ImageButton cancel = (ImageButton) window.findViewById(R.id.btn_cancel);  
//		cancel.setOnClickListener(new View.OnClickListener() {  
//		@Override
//		public void onClick(View v) {  
//		dlg.cancel();  
//		}  
//		});  
//		}  
	
	@Override
	@SuppressWarnings("deprecation")
	public boolean onKeyDown(int keyCode, KeyEvent event)      //��д���ؼ�
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			AlertDialog isExit=new AlertDialog.Builder(this).create();
			isExit.setTitle("系统提示");
			isExit.setMessage("确定退出吗？");
			isExit.setButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Exit.exitActivity();
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
		return true;
	}
}
