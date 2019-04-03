package com.bn.community;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class CommunitySearchActivity extends Activity{
	private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	private List<String[]> shetuany = new ArrayList<String[]>();
	private List<String[]> idy = new ArrayList<String[]>();
	private List<String[]> kouhaoy = new ArrayList<String[]>();
	private List<String[]> imaged = new ArrayList<String[]>();
	private String all[][] = null;
	private String all_2[][] = null;
	private String all_3[][] = null;
	private String all_4[][] = null;
	private String[] shetuan = null;
	private String[] id = null;
	private String[] kouhao = null;
	private String image[] = null;
	String messa=null;
	byte all_image[][];
	Bitmap imageData[];
	TextView fanhui;
	ListView listview;
	baseAdapter base;
	RelativeLayout nodate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shetuan_sousuo);
		nodate = (RelativeLayout) findViewById(R.id.nodate);
		listview = (ListView) findViewById(R.id.shetuan_sousuo_listview);
		fanhui=(TextView)findViewById(R.id.shetuan_sousuo_1);
		fanhui.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//mm.pd.dismiss();
				finish();
				
			}
		});
		Intent intent = getIntent();
		messa = intent.getStringExtra("messa");
		thread_sousuo gg=new thread_sousuo();
		gg.start();
		try{
			gg.join();
		}catch(Exception e){
			e.printStackTrace();
		}
   }
	private class thread_sousuo extends Thread {
		@Override
		public void run() {
			shetuany = NetInfoUtil.getshetuanbyname(messa);
			idy = NetInfoUtil.getshetuanidbyname(messa);
			kouhaoy = NetInfoUtil.getshetuankouhaobyname(messa);
			imaged = NetInfoUtil.getshetuanpicturebyname(messa);
			//imagey = NetInfoUtil.getshetuanpicture2byname(messa);
			all = new String[shetuany.size()][shetuany.get(0).length];
			all_2 = new String[idy.size()][idy.get(0).length];
			all_3 = new String[kouhaoy.size()][kouhaoy.get(0).length];
			all_4 = new String[imaged.size()][imaged.get(0).length];
			//all_5 = new String[imagey.size()][imagey.get(0).length];
			shetuan = new String[all.length];
			id = new String[all.length];
			kouhao = new String[all.length];
			image = new String[all.length];
			//image2 = new String[all.length];
			all_image = new byte[all.length][];
			//all_image2 = new byte[all.length][];
			imageData = new Bitmap[all.length];
			//imageData2 = new Bitmap[all.length];
			if (shetuany.get(0)[0].equals("")) {
				 nodate.setVisibility(View.VISIBLE);
					listview.setVisibility(View.GONE);
			} else {
				for (int i = 0; i < shetuany.size(); i++) {
					for (int j = 0; j < shetuany.get(i).length; j++) {
						all[i][j] = shetuany.get(i)[j];
						all_2[i][j] = idy.get(i)[j];
						all_3[i][j] = kouhaoy.get(i)[j];
						all_4[i][j] = imaged.get(i)[j];
						//all_5[i][j] = imagey.get(i)[j];
						shetuan[i] = all[i][0];
						id[i] = all_2[i][0];
						kouhao[i] = all_3[i][0];
						image[i] = all_4[i][0] + ".png";
						//image2[i] = all_5[i][0] + ".png";
					}
				}
				for (int i = 0; i < shetuany.size(); i++) {
					System.out.print("33333333333333333333333333333333333333333");
					if (F_GetBitmap.isEmpty(image[i])) {
						System.out.print("1111111111111111111111111111111111");
						all_image[i] = NetInfoUtil.getPicture(image[i]);

						System.out.print("22222222222222222222222222222222222");
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
		initBaseAdapter();
	}
	public void initBaseAdapter() {
		base = new baseAdapter(this);
		listview.setAdapter(base);
		// setListViewHeightBasedOnChildren(listview);
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
                	
                Intent it=new Intent(CommunitySearchActivity.this,CommunityDetailActivity.class);
                it.putExtra("name", mes);
				//it.putExtra("id", (idd2+arg2)+"");
				it.putExtra("id", id[arg2]);
				it.putExtra("kouhao", mes2);
				//it.putExtra("picture", chuandi[arg2]);
			    startActivity(it);
//				Message msg = new Message();
//				msg.what = 4;
//				mHandler2.sendMessage(msg);
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
				convertView = mInflater.inflate(R.layout.shetuan_sousuo_list, null);
				myViews.image = (ImageView) convertView.findViewById(R.id.shetuan_image_sousuo);
				myViews.name = (TextView) convertView.findViewById(R.id.shetuan_name_sousuo);
				myViews.id = (TextView) convertView.findViewById(R.id.shetuan_id_sousuo);
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
	static class ViewHolder {

		private ImageView image;
		private TextView name;
		private TextView id;
	}

}
