package com.example.president;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.example.util.F_GetBitmap;
import com.example.util.NetInfoUtil;
import com.example.util.RoundImageView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class gerenxianshi extends Activity{
	String id=null;
	String name2=null;
	String password=null;
	TextView id2;
	EditText password2;
	TextView baocun;
	String mes=null;
	String p=null;
	TextView fanhui;
	TextView xingming=null;
	String shetuan=null;
	List<String[]> zongy=new ArrayList<String[]>();
    String all[][]=null;
    String idy[]=null;
    String passwordy[]=null;
    Button fenghao;
    Button jiefeng;
    String zhuangtai=null;
    String ps=null;
    String name3=null;
    RelativeLayout rl;
    LinearLayout a;
    LinearLayout b;
    RoundImageView touxiang;
    TextView sex;
    TextView zhuanye;
    TextView xueyuan;
    String sex2=null;
    String zhuanye2=null;
    String xueyuan2=null;
    String image=null;
    Bitmap imageData=null;
    byte all_image[]=null;
    TextView shetuan3=null;
    String shetuan2=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zhengjianzhao2);
		Intent it=getIntent();
		name2=it.getStringExtra("id");
		fenghao=(Button)findViewById(R.id.fengjin);
		jiefeng=(Button)findViewById(R.id.jiefeng);
		rl=(RelativeLayout)findViewById(R.id.weizhaodao);
		a=(LinearLayout)findViewById(R.id.s1);
		b=(LinearLayout)findViewById(R.id.s2);
		//password2=(EditText)findViewById(R.id.renyuan_mima);
		id2=(TextView)findViewById(R.id.renyuan_id);
		shetuan3=(TextView)findViewById(R.id.renyuan_shetuan);
		xingming=(TextView)findViewById(R.id.renyuan_xingming);
		sex=(TextView)findViewById(R.id.renyuan_xingbie);
		zhuanye=(TextView)findViewById(R.id.renyuan_zhuanye);
		xueyuan=(TextView)findViewById(R.id.renyuan_xueyuan);
		touxiang=(RoundImageView)findViewById(R.id.zhengjiamzhao);
		thread_get th=new thread_get();
		th.start();
		try{
			th.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		fanhui=(TextView)findViewById(R.id.shetuanzhanghao_fanhui_2);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		baocun=(TextView)findViewById(R.id.geren_baocun);
		baocun.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				// TODO Auto-generated method stub
				System.out.println("qwwerer"+password2.getHeight());
				thread_insert gg=new thread_insert();
				gg.start();
				try{
					gg.join();
				}catch(Exception e){
					e.printStackTrace();
				}Toast.makeText(gerenxianshi.this, "保存成功!!",
						Toast.LENGTH_LONG).show();
				finish();
			}
		});
		if(!zhuangtai.equals("")){
			if(zhuangtai.equals("1")){
				fenghao.setVisibility(View.VISIBLE);
				jiefeng.setVisibility(View.GONE);
			}else if(zhuangtai.equals("0")){
				fenghao.setVisibility(View.GONE);
				jiefeng.setVisibility(View.VISIBLE);
			}
		}else if(zhuangtai.equals("")){
			fenghao.setVisibility(View.GONE);
			jiefeng.setVisibility(View.GONE);
		}
		fenghao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				thread_feng tf=new thread_feng();
				tf.start();
				try{
					tf.join();
				}catch(Exception e){
					e.printStackTrace();
				}
				fenghao.setVisibility(View.GONE);
				jiefeng.setVisibility(View.VISIBLE);
			}
		});
		jiefeng.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				thread_jin tf2=new thread_jin();
				tf2.start();
				try{
					tf2.join();
				}catch(Exception e){
					e.printStackTrace();
				}
				fenghao.setVisibility(View.VISIBLE);
				jiefeng.setVisibility(View.GONE);
			}
		});
	}
	private class thread_insert extends Thread{
		@Override
		public void run(){
			if (!password2.getText().toString().equals("")) {
				p = password2.getText().toString();
			} else {
				p = password2.getHint().toString();
			}
			mes=p;
			mes=p+"#"+name2;
			NetInfoUtil.updatauserpassword(mes);
		}
	}
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 2:
				Toast.makeText(gerenxianshi.this, "此用户不存在!!",
						Toast.LENGTH_LONG).show();
			}
		}
	};
	private class thread_get extends Thread{
		@Override
		public void run(){
			ps=NetInfoUtil.getuserps(name2);
			name3=NetInfoUtil.getusername(name2);
			sex2=NetInfoUtil.getusersex(name2);
			shetuan2=NetInfoUtil.getusershetuan(name2);
			xueyuan2=NetInfoUtil.getuserxueyuan(name2);
			zhuanye2=NetInfoUtil.getuserzhuanye(name2);
			image=NetInfoUtil.getuserphoto(name2);
			if(ps.equals("")){
				mHandler.sendEmptyMessage(2);
				b.setVisibility(View.GONE);
				a.setVisibility(View.GONE);
				rl.setVisibility(View.VISIBLE);
			}else{
				if (F_GetBitmap.isEmpty(image)) {
					all_image = NetInfoUtil.getPicture(image);
					F_GetBitmap.setInSDBitmap(all_image, image);
					InputStream input = null;
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 2;
					input = new ByteArrayInputStream(all_image);
					@SuppressWarnings({ "rawtypes", "unchecked" })
					SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
					imageData = (Bitmap) softRef.get();
				} else {
					imageData = F_GetBitmap.getSDBitmap(image);// 拿到的是BitMap类型的图片数据
					if (F_GetBitmap.bitmap != null && !F_GetBitmap.bitmap.isRecycled()) {
						F_GetBitmap.bitmap = null;
					}
				}
				shetuan2=shetuan2.substring(0, shetuan2.length()-1);
				initlist();
			}
			zhuangtai=NetInfoUtil.getuserstatic(name2);
		}
	}
	public void initlist(){
		//password2.setText(ps);
		id2.setText("账号:"+name2);
		shetuan3.setText("参加社团:"+shetuan2);
		xingming.setText("姓名:"+name3);
		sex.setText("性别:"+sex2);
		zhuanye.setText("专业:"+zhuanye2);
		xueyuan.setText("学院:"+xueyuan2);
		touxiang.setImageBitmap(imageData);
	}
	private class thread_feng extends Thread{
		@Override
		public void run(){
			NetInfoUtil.updatauserstat(name2);
		}
	}
	private class thread_jin extends Thread{
		@Override
		public void run(){
			NetInfoUtil.updatauserstat2(name2);
		}
	}
}
