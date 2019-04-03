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
public class tianjiahuodong extends Activity{
	Bitmap bm = null;
	ImageView view;
	LinearLayout linearlay=null;
	EditText mingy=null;
	LinearLayout linearlax=null;
	EditText mingx=null;
	LinearLayout linearlaz=null;
	EditText mingz=null;
	String photopath = "tianjia999";
	EditText name=null;
	EditText time=null;
	EditText place=null;
	EditText detail=null;
	ImageView imageview=null;
	TextView baocun=null;
	String path;
	Bitmap cameraBitmap;
	String zhanghao, name1, time1, didian1, detail1, picture1;
	String mes="";
	int activity_id;
	String community_id=null;
	Button b=null;
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
		name=(EditText)findViewById(R.id.huodongfmain_username_edit);
		name.setFocusable(true);
		time=(EditText)findViewById(R.id.huodongfmain_name_editx);
		place=(EditText)findViewById(R.id.huodongfmain_sex_edit);
		detail=(EditText)findViewById(R.id.huodong_advice_tianjias);
		imageview=(ImageView)findViewById(R.id.huodongset_user_image);
		baocun = (TextView) findViewById(R.id.huodongftool_text3);
		imageview.setOnClickListener(new View.OnClickListener() {
			@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					intent.setType("image/*");
					startActivityForResult(intent, 0);
				}
			});
		baocun.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(name.getText().toString().equals("")||time.getText().toString().equals("")||place.getText().toString().equals("")||detail.getText().toString().equals(""))
				{
					Toast.makeText(tianjiahuodong.this, "不能有空的选项", Toast.LENGTH_SHORT).show();
				}
				else {
					thread_set th = new thread_set();
					th.start();
					try {
						th.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Toast.makeText(tianjiahuodong.this, "添加成功", Toast.LENGTH_SHORT).show();
					Intent it=new Intent(tianjiahuodong.this,huodonglibiao.class);
					finish();
					startActivity(it);
				}
			}
		});
		b=(Button)findViewById(R.id.huodong_Button_1);
		b.setVisibility(View.GONE);
	}
	private void crop(Uri uri) {
		// // 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		//裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputFormat", "PNG");// 图片格式
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
				imageview.setImageBitmap(bm);
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
			activity_id=Integer.parseInt(NetInfoUtil.getmaxid("s"))+1;
			photopath=activity_id+"";
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
			byte[] all_image2 = NetInfoUtil.getPicture(photopath+".png");
			F_GetBitmap.setInSDBitmap(all_image2, photopath+".png");
		}
	}
	class thread_set extends Thread {
		@Override
		public void run() {
			activity_id=Integer.parseInt(NetInfoUtil.getmaxid("s"))+1;
			community_id=NetInfoUtil.getcommunityid(user_guanli.s);
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
			mes = mes + "<#>" + activity_id;
			mes = mes + "<#>" + community_id;
			mes = mes + "<#>" +thisleixing;
			NetInfoUtil.inserthuodongm(mes);
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
