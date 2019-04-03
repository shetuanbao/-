package com.bn.manage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.util.F_GetBitmap;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;
public class xiugaihuodong extends Activity{
	List<String[]> ls=new ArrayList<String[]>();
	String message[][]=null;
	EditText name=null;
	EditText time=null;
	EditText place=null;
	EditText detail=null;
	String image=null; 
	byte all_image[];
	Bitmap imageData;
	byte all_image2[];
	Bitmap imageData2;
	String huodongname=null;
	ImageView view;
	LinearLayout linearlay=null;
	EditText mingy=null;
	LinearLayout linearlax=null;
	EditText mingx=null;
	LinearLayout linearlaz=null;
	EditText mingz=null;
	String photopath = "tianjia999";
	String path;
	static Uri uri;
	Bitmap cameraBitmap;
	TextView baocun=null;
	ImageView imageview=null;
	Bitmap bm = null;
	String zhanghao, name1, time1, didian1, detail1, picture1;
	String mes="";
	Button delete=null;
	TextView fanhui=null;
	String id=null;
	private Spinner huodong=null;
	private ArrayAdapter<String> sexAdapter = null;  
	private String leixing[]={"公开","不公开"};
	String thisleixing=null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiugaihuodong);
		huodong=(Spinner)findViewById(R.id.huodong_leixing);
		huodong.setPrompt("请选择类型：" ); 
		setSpinner();
		Intent intent = getIntent();
		id = intent.getStringExtra("id");
		photopath=id;
		view=(ImageView)findViewById(R.id.huodongset_user_image);
		name=(EditText)findViewById(R.id.huodongfmain_username_edit);
		name.setFocusable(false);
		name.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(xiugaihuodong.this, "活动名称不可修改", Toast.LENGTH_SHORT).show();
			}
		});
		time=(EditText)findViewById(R.id.huodongfmain_name_editx);
		place=(EditText)findViewById(R.id.huodongfmain_sex_edit);
		detail=(EditText)findViewById(R.id.huodong_advice_tianjias);
		//imageview=(ImageView)findViewById(R.id.huodongset_user_image);
		delete=(Button)findViewById(R.id.huodong_Button_1);
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder b = new AlertDialog.Builder(xiugaihuodong.this);
				
				b.setMessage("确定要删除吗?");// 设置信息
				b.setPositiveButton// 为对话框设置按钮
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								thread_delete de=new thread_delete();
								de.start();
								try {
									de.join();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								Toast.makeText(xiugaihuodong.this, "删除成功", Toast.LENGTH_SHORT).show();
								Intent it=new Intent(xiugaihuodong.this,huodonglibiao.class);
								finish();
								startActivity(it);
							}
						});
				b.setNegativeButton// 为对话框设置按钮
				("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				b.create().show();
			}
		});
		baocun = (TextView) findViewById(R.id.huodongftool_text3);
		baocun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				thread_set th = new thread_set();
				th.start();
				try {
					th.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Builder b = new AlertDialog.Builder(xiugaihuodong.this);
				b.setTitle("信息保存");
				b.setMessage("保存成功！");// 设置信息
				b.setPositiveButton// 为对话框设置按钮
						("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								String picFilePath = path;
								BitmapFactory.Options options = new BitmapFactory.Options();
								options.inDither = false; /* 不进行图片抖动处理 */
								options.inPreferredConfig = null; /* 设置让解码器以最佳方式解码 */
								options.inSampleSize = 1; /* 图片长宽方向缩小倍数 */
								bm = BitmapFactory.decodeFile(picFilePath, options);
								
								finish();
							}
						});
				b.create().show();
			}
		});
		fanhui=(TextView)findViewById(R.id.huodongtool_text1);
		fanhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		view.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent.setType("image/*");
				startActivityForResult(intent, 0);
			}
		});
		thread_get gg=new thread_get();
		gg.start();
		try{
			gg.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		linearlay = (LinearLayout) findViewById(R.id.huodongfmain_name);
		mingy = (EditText) findViewById(R.id.huodongfmain_name_editx);
		mingy.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					linearlay.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					linearlay.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		linearlax = (LinearLayout) findViewById(R.id.huodongfmain_sex);
		mingx = (EditText) findViewById(R.id.huodongfmain_sex_edit);
		mingx.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					linearlax.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					linearlax.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
		linearlaz = (LinearLayout) findViewById(R.id.huodong_advice);
		mingz = (EditText) findViewById(R.id.huodong_advice_tianjias);
		mingz.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					linearlaz.setBackgroundResource(R.drawable.roundshapepress);
				} else {
					linearlaz.setBackgroundResource(R.drawable.roundshape);
				}
			}
		});
	}
	private void crop(Uri uri) {
		// // 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		//裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		//intent.putExtra("outputFormat", "PNG");// 图片格式
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 1);
	}
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
				view.setImageBitmap(bm);
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
				photopath=id;
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
			//touxiang.setImageBitmap(cameraBitmap);
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
			all_image2 = NetInfoUtil.getPicture(photopath+".png");
			F_GetBitmap.setInSDBitmap(all_image2, photopath+".png");
		}

	}
	
	class thread_delete extends Thread {
		@Override
		public void run() {
			NetInfoUtil.deletehuodong(name.getText().toString());
			
		}
	}
	private class thread_get extends Thread{
		@Override
		public void run(){
			ls = NetInfoUtil.gethuodongmessagebyname(huodonglibiao.s);
			message = new String[ls.size()][ls.get(0).length];
			for (int i = 0; i < ls.size(); i++) {
				for (int j = 0; j < ls.get(i).length; j++) {
					message[i][j] = ls.get(i)[j];
				}
			}
			name.setText(message[0][1]);
			time.setText(message[0][2]);
			place.setText(message[0][3]);
			detail.setText(message[0][4]);
			String image = message[0][0]+".png";
			
			if (F_GetBitmap.isEmpty(image)) {
				all_image = NetInfoUtil.getPicture(image);
				F_GetBitmap.setInSDBitmap(all_image, image);
				InputStream input = null;
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 2;
				input = new ByteArrayInputStream(all_image);
				@SuppressWarnings({ "unchecked", "rawtypes" })
				SoftReference softRef = new SoftReference(
						BitmapFactory.decodeStream(input, null, options));
				imageData = (Bitmap) softRef.get();
			} else {
				imageData = F_GetBitmap.getSDBitmap(image);// �õ�����BitMap���͵�ͼƬ����
				if (F_GetBitmap.bitmap != null
						&& !F_GetBitmap.bitmap.isRecycled()) {
					F_GetBitmap.bitmap = null;
				}
			}
			view.setImageBitmap(imageData);
		}
   }
	class thread_set extends Thread {
		@Override
		public void run() {
			if (!name.getText().toString().equals("")) {
				name1 = name.getText().toString();
			} else {
				name1 = name.getHint().toString();
			}
			mes = name1;
			if (!time.getText().toString().equals("")) {
				time1 = time.getText().toString();
			} else {
				time1 = time.getHint().toString();
			}
			mes = mes + "<#>" + time1;
			if (!place.getText().toString().equals("")) {
				didian1 = place.getText().toString();
			} else {
				didian1 = place.getHint().toString();
			}
			mes = mes + "<#>" + didian1;
			if (!detail.getText().toString().equals("")) {
				detail1 = detail.getText().toString();
			} else {
				detail1 = detail.getHint().toString();
			}
			mes = mes + "<#>" + detail1;
			mes = mes + "<#>" + photopath;
			mes = mes + "<#>" +thisleixing;
			NetInfoUtil.inserthuodongmessage(mes);
		}
	}
	public void setSpinner()
	{
		sexAdapter = new ArrayAdapter<String>(this,
				 android.R.layout.simple_spinner_item, leixing);
		sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		huodong.setAdapter(sexAdapter);
		huodong.setSelection(0,true); 
		huodong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
            
            	thisleixing=leixing[position];
//            	sexEd=(EditText)findViewById(R.id.enrollmain_sex_edit);
//            	sexEd.setText(sexArray[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });
	}
}


