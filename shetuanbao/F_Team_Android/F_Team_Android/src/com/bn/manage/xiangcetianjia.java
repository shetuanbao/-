package com.bn.manage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.F_GetBitmap;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class xiangcetianjia extends Activity{
	ImageView im;
	Bitmap bm;
	TextView fanhui;
	TextView baocun;
	String photopath = "999";
	String path;
	Bitmap cameraBitmap;
	String length=null;
	String length2[]=new String[2];
	String id=null;
	byte all_image[]=null;
	boolean bb=false;
	public static String count="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiangtianjia);
		bb=false;
		Intent intent = getIntent();
		id=intent.getStringExtra("id");
		count=MainActivity.count+"";
		System.out.println("+++++++++++++"+count);
		length=intent.getStringExtra("length");
		
		fanhui=(TextView)findViewById(R.id.xiangce_tianjia_fanhui_2);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cameraBitmap != null && !cameraBitmap.isRecycled()) {
					cameraBitmap.recycle();
					cameraBitmap = null;
				}
				if (bm != null && !bm.isRecycled()) {
					bm.recycle();
					bm = null;
				}
				finish();
			}
		});
		baocun=(TextView)findViewById(R.id.xiangce_tianjia_tianjia);
		baocun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(bb==true){
					thread_set th = new thread_set();
					th.start();
					try {
						th.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Toast.makeText(xiangcetianjia.this,"保存成功",Toast.LENGTH_LONG).show();
//					Map<String, Object> map = new HashMap<String, Object>();
//					
//					map.put("name", "活动图片"+count);
//					map.put("image", bm);
//					MainActivity.listItem.add(map);
//					MainActivity.data.addAll(MainActivity.listItem);
//					MainActivity.base.notifyDataSetChanged();
//					MainActivity.listItem.clear();
					Intent it=new Intent(xiangcetianjia.this,MainActivity.class);
					it.putExtra("id",id);
					finish();
					startActivity(it);
				}else if(bb==false){
					Toast.makeText(xiangcetianjia.this,"请选择照片",Toast.LENGTH_LONG).show();
				}
				
			}
		});
		im=(ImageView)findViewById(R.id.xiangcetianjia);
		im.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bb=true;
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent.setType("image/*");
				startActivityForResult(intent, 0);
			}
		});
	}
	private void crop(Uri uri) {
		// // 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		//裁剪框的比例，1:1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		//intent.putExtra("outputFormat", "PNG");// 图片格式
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 1);
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode != RESULT_OK) { // 此处的 RESULT_OK 是系统自定义得一个常量
			return;
		}
		@SuppressWarnings("unused")
		
		// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
		ContentResolver resolver = getContentResolver();
		// 此处的用于判断接收的Activity是不是你想要的那个 社团-- 0代表 拿图片路径 1表示设置通过剪切得到的头像
		if (requestCode == 0) {
			try {
				Uri originalUri = data.getData();
				crop(originalUri);// 获得图片的uri
				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
				
				String[] proj = { MediaColumns.DATA };
				// 好像是android多媒体数据库的封装接口，具体的看Android文档
				Cursor cursor = managedQuery(originalUri, proj, null, null,
						null);
				// 按我个人理解 这个是获得用户选择的图片的索引值ֵ
				int column_index = cursor
						.getColumnIndexOrThrow(MediaColumns.DATA);
				// 将光标移至开头 ，这个很重要，不小心很容易引起越界
				cursor.moveToFirst();
				// 最后根据索引值获取图片路径
				path = cursor.getString(column_index);
				File tempFile =new File( path.trim());
				photopath = tempFile.getName();
				photopath=photopath.substring(0,photopath.length() - 4);
				if(length.equals(id)){
					photopath=id+"_1";
				}else{
					length2=length.split("_");
					int length3=Integer.parseInt(length2[1])+1;
					photopath=id+"_"+length3+"";
					}
                thread_insert hs=new thread_insert();
                hs.start();
                try{
                	hs.join();
                }catch(Exception e){
                	e.printStackTrace();
                }
			} catch (IOException e) {
			}

		}
		if (requestCode == 1) {
			cameraBitmap = (Bitmap) data.getExtras().get("data");
			super.onActivityResult(requestCode, resultCode, data);
			im.setImageBitmap(cameraBitmap);
		}
	}
	class thread_insert extends Thread {
		@Override
		public void run() {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] data1 = baos.toByteArray();
			NetInfoUtil.insertpic(data1,photopath+".png");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			all_image = NetInfoUtil.getPicture(photopath+".png");
			F_GetBitmap.setInSDBitmap(all_image, photopath+".png");
		}

	}
	class thread_set extends Thread{
		@Override
		public void run(){
			NetInfoUtil.insertxiangce(id+"#"+photopath);
		}
	}
}
