package com.bn.community;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bn.chat.shejiao_lianxiren;
import com.bn.util.Constant;
import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.bn.util.StrListChange;
import com.example.chat.R;
public class CommunityStaffActivity extends Activity{
	private String userId="";
	private String name="";
	private String sex="";
	private String phone="";
	private String email="";
	private String[] total=null;
	private Bitmap touxiang;
	private ImageView pic=null;
	private TextView sno_text=null;
	private EditText name_text=null;
	private EditText sex_text=null;
	private EditText email_text=null;
	private EditText phone_text=null;
	private TextView back=null;
	private Button save=null;
	private String count="";
	private String userpen="";
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yonghuziliao);
		Bundle myBundleForGet=this.getIntent().getExtras();
		userId=myBundleForGet.getString("sno");
		pic=(ImageView)findViewById(R.id.shetuan_user_photo);
		sno_text=(TextView)findViewById(R.id.shetuan_user_sno_edit);
		name_text=(EditText)findViewById(R.id.shetuan_user_name_edit);
		sex_text=(EditText)findViewById(R.id.shetuan_user_sex_edit);
		email_text=(EditText)findViewById(R.id.shetuan_user_e_mail_edit);
		phone_text=(EditText)findViewById(R.id.shetuan_user_phone_edit);
		back=(TextView)findViewById(R.id.go_back);
		save=(Button)findViewById(R.id.save_person);
		FontManager.changeFonts(FontManager.getContentView(this), this);
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				d();
			}
		});
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		init();
	}	
	public void d()
	{
		new Thread()
		{			
			@Override
			public void run()
			{
				String [] temp={
						Constant.userName,userId,"我已经添加你为好友了"," "," "
				};
				String [] uf={
						Constant.userName,userId
				};
				count=NetInfoUtil.searchFriend(Constant.userName+"#"+userId);
				if(count.equals("0"))
				{
					userpen=NetInfoUtil.getuserpen(userId);
					NetInfoUtil.getFriend(StrListChange.ArrayToStrAndroid(temp));
					String data=NetInfoUtil.insertFriend(StrListChange.ArrayToStrAndroid(uf));//单项添加好友	
					if(data.equals("SUCCESS"))
					{	
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("name", name);
						map.put("pen", userpen);
						map.put("photo", touxiang);
						shejiao_lianxiren.list.add(map);
						shejiao_lianxiren.ba.notifyDataSetChanged();
						shejiao_lianxiren.xuehao.add(userId);
						myHandler.sendEmptyMessage(2);
					}
				}
				else if(count.equals("1"))					
				{
					myHandler.sendEmptyMessage(1);
				}
			}
		}.start();
	}
	public void init()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				total=NetInfoUtil.getShetuanuserInfo(userId);
				String photo=NetInfoUtil.getuseronephoto(userId);
				if(F_GetBitmap.isEmpty(photo))
				{						
					 byte[] b=NetInfoUtil.getPicture(photo);					
					 F_GetBitmap.setInSDBitmap(b, photo);
					 InputStream input = null;   
				     BitmapFactory.Options options = new BitmapFactory.Options();  
				     options.inSampleSize = 1;  
				     input = new ByteArrayInputStream(b);  
				     @SuppressWarnings({ "rawtypes", "unchecked" })
					 SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(  
				                input, null, options));  
				     touxiang =(Bitmap) softRef.get(); 
				}
				else
				{	
					 touxiang=F_GetBitmap.getSDBitmap(photo);
					 if(F_GetBitmap.bitmap!=null && !F_GetBitmap.bitmap.isRecycled())
					 {
						 F_GetBitmap.bitmap = null;
					 }
				}
				name=total[0];
				sex=total[1];
				email=total[2];
				phone=total[3];				
                initAdp();			
			}
		}.start();
	}
	public void initAdp()
	{
		Message msg=new Message();
		msg.what=0;
		myHandler.sendMessage(msg);
	}
	Handler myHandler=new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case 0:initView();
			break;
			case 1:Toast.makeText(CommunityStaffActivity.this,"您已添加过对方",Toast.LENGTH_SHORT).show();
			break;
			case 2:Toast.makeText(CommunityStaffActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
			break;
			}
		}
	};
	public void initView()
	{
		sno_text.setText(userId);
		name_text.setText(name);
		sex_text.setText(sex);
		email_text.setText(email);
		phone_text.setText(phone);
		pic.setImageBitmap(touxiang);
	}
}