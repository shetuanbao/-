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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class CommunityCharitableActivity extends Activity {
	private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	private ListView listview = null;
	private String all[][] = null;
	private String all_2[][] = null;
	private String id[] = null;
	private String shetuan[] = null;
	private baseAdapter base = null;
	private List<String[]> shetuany = new ArrayList<String[]>();
	private List<String[]> idy = new ArrayList<String[]>();
	private String all_3[][] = null;
	private String all_4[][] = null;
	private String all_5[][] = null;
	private String kouhao[] = null;
	private String image[] = null;
	private String image2[] = null;

	private List<String[]> kouhaoy = new ArrayList<String[]>();
	private List<String[]> imagey = new ArrayList<String[]>();
	private List<String[]> imagey2 = new ArrayList<String[]>();
	private View footView;
	TextView js;
	private byte[][] all_image2;
	private byte[][] all_image;
	private Bitmap imageData[];
	private Bitmap imageData2[];
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shetuan_cishan);
		footView = getLayoutInflater().inflate(R.layout.listfoot, null);
		js = (TextView) footView.findViewById(R.id.iv_down_2);
		pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("加载中...请稍后");
//		thread_tiyu th = new thread_tiyu();
//		th.start();
//		try {
//			th.join();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		new AysncTask_team().execute();
		FontManager.initTypeFace(this);
		FontManager.changeFonts(FontManager.getContentView(this), this);
	}

	private class thread_tiyu extends Thread {
		@Override
		public void run() {
			shetuany = NetInfoUtil.getcishanshetuan();
			idy = NetInfoUtil.getcishanshetuanid();
			kouhaoy = NetInfoUtil.getshetuancishankouhao();
			imagey = NetInfoUtil.getshetuancishanpicture();
			//imagey2 = NetInfoUtil.getshetuancishanpicture2();
			// idy=NetInfoUtil.getallshetuan();
			all = new String[shetuany.size()][shetuany.get(0).length];
			all_2 = new String[idy.size()][idy.get(0).length];
			all_3 = new String[kouhaoy.size()][kouhaoy.get(0).length];
			all_4 = new String[imagey.size()][imagey.get(0).length];
			//all_5 = new String[imagey2.size()][imagey.get(0).length];
			shetuan = new String[all.length];
			id = new String[all_2.length];
			kouhao = new String[all_3.length];
			image = new String[all_4.length];
			//image2 = new String[all_5.length];
			all_image = new byte[all_4.length][];
			//all_image2 = new byte[all_5.length][];
			imageData = new Bitmap[all_4.length];
			//imageData2 = new Bitmap[all_5.length];
			if (shetuany.get(0)[0].equals("")) {

			} else {
				for (int i = 0; i < shetuany.size(); i++) {
					for (int j = 0; j < shetuany.get(i).length; j++) {
						all[i][j] = shetuany.get(i)[j];
						all_2[i][j] = idy.get(i)[j];
						all_3[i][j] = kouhaoy.get(i)[j];
						all_4[i][j] = imagey.get(i)[j];
						shetuan[i] = all[i][0];
						id[i] = all_2[i][0];
						kouhao[i] = all_3[i][0];
						image[i] = all_4[i][0] + ".png";
					}
				}
				
				for (int i = 0; i < shetuany.size(); i++) {
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
	public void initBaseAdapter() {
		
		listview = (ListView) findViewById(R.id.shetuan_listview_cishan);
		listview.addFooterView(footView);
		base = new baseAdapter(this);
		listview.setAdapter(base);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				LinearLayout view1 = (LinearLayout) arg1;
				LinearLayout ll = (LinearLayout) view1.getChildAt(1);
				LinearLayout l = (LinearLayout) ll.getChildAt(0);
				LinearLayout l2 = (LinearLayout) ll.getChildAt(1);
				TextView text = (TextView) l.getChildAt(0);
				TextView text2 = (TextView) l2.getChildAt(0);
				String mes = text.getText().toString();
				String mes2 = text2.getText().toString();
				Intent it = new Intent(CommunityCharitableActivity.this, CommunityDetailActivity.class);
				it.putExtra("name", mes);
				it.putExtra("id", id[arg2]);
				it.putExtra("kouhao", mes2);
				//it.putExtra("picture", imageData2[arg2]);
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
			myViews.name.setTypeface(FontManager.tf);
			myViews.id.setTypeface(FontManager.tf);
			js.setText("共"+shetuany.size()+""+"个社团 ");
			myViews.image.setImageBitmap((Bitmap) data.get(position).get("image"));
			myViews.name.setText((String) data.get(position).get("name"));
			myViews.id.setText((String) data.get(position).get("kouhao"));
			return convertView;
		}
	}
	static class ViewHolder {

		private ImageView image;
		private TextView name;
		private TextView id;
	}
	class AysncTask_team extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			thread_tiyu th2 = new thread_tiyu();
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
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) // ��д���ؼ�
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}
}
