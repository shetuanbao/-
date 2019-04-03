package com.bn.chat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bn.util.Constant;
import com.bn.util.Exit;
import com.bn.util.F_GetBitmap;
import com.bn.util.FontManager;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

public class ConversationActivity extends FragmentActivity
{
	private TextView title;
	private String sName;
	private String sId;
	private TextView back;
    String count=null;
    TextView baocun;
    Bitmap touxiang=null;
   private  String pen="";
	    @Override
	    protected void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.conversation);
	        title=(TextView)findViewById(R.id.liaotian_name);
	        back=(TextView)findViewById(R.id.back_conversition);
	        baocun=(TextView)findViewById(R.id.baocun);
	        back.setTypeface(FontManager.tf);
	        Exit.getInstance().addActivities(this);
	        sId=getIntent().getData().getQueryParameter("targetId");
	        back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	        init();
            baocun.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					init2();
				}
			});
	    }
	    public void init()
	    {
	    	new Thread()
	    	{
	    		@Override
				public void run()
	    		{
	    			sName=NetInfoUtil.getusername(sId);
	    			count=NetInfoUtil.searchFriend(Constant.userName+"#"+sId);
	    			if(count.equals("0")){
	    				mHandler.sendEmptyMessage(1);
	    			}
	    			initTitle();
	    		}
	    		
	    	}.start();
	    }
	    public void init2()
	    {
	    	new Thread()
	    	{
	    		@Override
				public void run()
	    		{
	    			NetInfoUtil.insertFriend(Constant.userName+"<#>"+sId);
	    			pen=NetInfoUtil.getuserpen(sId);
	    			System.out.println("====================="+pen+"��");
	    			String photo=NetInfoUtil.getuseronephoto(sId);
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
	    			mHandler.sendEmptyMessage(2);
	    		}
	    		
	    	}.start();
	    }
	    public void initTitle()
	    {
	    	Message msg=new Message();
			msg.what=0;
			mHandler.sendMessage(msg);
	    }
	    Handler mHandler=new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				switch(msg.what)
				{
				case 0:title.setText(sName);
				title.setTypeface(FontManager.tf);
				break;
				case 1:baocun.setVisibility(View.VISIBLE);
				break;
				
				case 2:
					baocun.setVisibility(View.GONE);
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("name", sName);
					map.put("pen", pen);
					map.put("photo", touxiang);
					shejiao_lianxiren.list.add(map);
					shejiao_lianxiren.ba.notifyDataSetChanged();
					shejiao_lianxiren.xuehao.add(sId);
				break;
				}
			}
		};
	    

}