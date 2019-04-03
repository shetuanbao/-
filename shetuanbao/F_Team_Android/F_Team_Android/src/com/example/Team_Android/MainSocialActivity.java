package com.example.Team_Android;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bn.chat.FragmentAdapter;
import com.bn.chat.MyApp;
import com.bn.chat.shejiao_lianxiren;
import com.bn.chat.sousuolianxiren;
import com.bn.community.CommunityStaffActivity;
import com.bn.manage.CaptureActivity;
import com.bn.util.Constant;
import com.bn.util.Exit;
import com.bn.util.NetInfoUtil;
import com.example.chat.R;

  
public class MainSocialActivity extends FragmentActivity {  
  
    private ViewPager viewPager;//页卡内容  
    private ImageView imageView;// 动画图片  
    private TextView textView1,textView2;  
    private List<Fragment> views;// Tab页面列表  
    private int offset = 0;// 动画图片偏移量  
    private int currIndex = 0;// 当前页卡编号  
    private int bmpW;// 动画图片宽度  
    private Fragment  mFriendFg=null;
    private Fragment mCoversationList;
    private SharedPreferences st;
    ImageView school;
    ImageView sousuo=null;
    String scanResult=null;
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main_shejiao); 
        Exit.getInstance().addActivities(this);
        
        sousuo=(ImageView)findViewById(R.id.main_searc_image);
        sousuo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MainSocialActivity.this,sousuolianxiren.class);
				startActivity(it);
				//showPopupMenu(sousuo);
			}
		});
        school=(ImageView)findViewById(R.id.school_3);
        school.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			Intent intent=new Intent(Intent.ACTION_VIEW);
    			intent.setData(Uri.parse("http://www.ncst.edu.cn"));
    			startActivity(intent);
    		}
    	});
        mCoversationList=initConversation();
        st=this.getSharedPreferences("token",MODE_WORLD_READABLE);
        if(st.getBoolean("CHECK", false))
        {   
        	
        	connect(st.getString("TOKEN", ""));
        }
        else
        {
        	getToken(Constant.userName);
        }        
        InitImageView();  
        InitTextView();         
    }  
    private void showPopupMenu(View view) {
		// View当前PopupMenu显示的相对View的位置
		PopupMenu popupMenu = new PopupMenu(this, view);

		// menu布局
		popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());

		// menu的item点击事件
		popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {  
				case R.id.action_new:
					Intent it=new Intent(MainSocialActivity.this,sousuolianxiren.class);
					startActivity(it);
					break;
				case R.id.action_open:
					Intent openCameraIntent = new Intent(MainSocialActivity.this,CaptureActivity.class);
					startActivityForResult(openCameraIntent, 0);
					
					break;
				}
				return false;
			}
		});

		// PopupMenu关闭事件
		popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
			@Override
			public void onDismiss(PopupMenu menu) {
				//Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
			}
		});

		popupMenu.show();
	}
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			scanResult = bundle.getString("result");
			Intent intent=new Intent(MainSocialActivity.this,CommunityStaffActivity.class);
		}
	

	}
	private void getToken(String userId)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{	
				String token=NetInfoUtil.getToken(Constant.userToken);
				Editor editor = st.edit();  
	            editor.putBoolean("CHECK", true);  
	            editor.putString("TOKEN",token);  
	            editor.commit();
				connect(token);
			}
			
		}).start();
		
	}
    private void InitViewPager() {
        viewPager=(ViewPager) findViewById(R.id.vPager);  
        views=new ArrayList<Fragment>(); 
        views.add(new shejiao_lianxiren());
        views.add(mCoversationList);
        viewPager.setAdapter(new FragmentAdapter(
        this.getSupportFragmentManager(), views));  
        viewPager.setCurrentItem(0);  
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());  
    }  
     /** 
      *  初始化头标 
      */  
  
    private void InitTextView() {  
        textView1 = (TextView) findViewById(R.id.text1);  
        textView2 = (TextView) findViewById(R.id.text2);  
        textView1.setOnClickListener(new MyOnClickListener(0));  
        textView2.setOnClickListener(new MyOnClickListener(1));  
       
    }  
  
    /** 
     2      * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据 
     3 */  
  
    private void InitImageView() {  
        imageView= (ImageView) findViewById(R.id.cursor);  
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.d).getWidth();// 获取图片宽度  
        DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        int screenW = dm.widthPixels;// 获取分辨率宽度  
        offset = (screenW / 2 - bmpW) / 2;// 计算偏移量  
        Matrix matrix = new Matrix();  
        matrix.postTranslate(offset, 0);  
        imageView.setImageMatrix(matrix);// 设置动画初始位置  
    }  
    private class MyOnClickListener implements OnClickListener{  
        private int index=0;  
        public MyOnClickListener(int i){  
            index=i;  
        }  
        @Override
		public void onClick(View v) {  
            viewPager.setCurrentItem(index);              
        }  
          
    }   
    public class MyOnPageChangeListener implements OnPageChangeListener
    {  
  
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量  
        @Override
		public void onPageScrollStateChanged(int arg0)
        {    
        	
        } 
        @Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
        {  
                        
        }  
  
        @Override
		public void onPageSelected(int arg0) {  
            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。  
            currIndex = arg0;  
            animation.setFillAfter(true);// True:图片停在动画结束位置  
            animation.setDuration(120);  
            imageView.startAnimation(animation);  
        }  
          
    }  
    private Fragment initConversation()
    {
    	if (mCoversationList == null) {
            ConversationListFragment listFragment = ConversationListFragment.getInstance();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//讨论组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//系统
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        }else
        {
        	return mCoversationList;
        }
        
    }
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
	 private void connect(String token) {

	        if (getApplicationInfo().packageName.equals(MyApp.getCurProcessName(getApplicationContext()))) {

	            /**
	             * IMKit SDK调用第二步,建立与服务器的连接
	             */
	            RongIM.connect(token, new RongIMClient.ConnectCallback() {

	                /**
	                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
	                 */
	                @Override
	                public void onTokenIncorrect() {

	                	InitViewPager();
	                }

	                /**
	                 * 连接融云成功
	                 * @param userid 当前 token
	                 */
	                @Override
	                public void onSuccess(String userid) {

	                	setConnectedListener();
						 InitViewPager(); 
	                }

	                /**
	                 * 连接融云失败
	                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
	                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
	                 */
	                @Override
	                public void onError(RongIMClient.ErrorCode errorCode) {

	                	InitViewPager();
	                }
	            });
	        }
	    }
	
	public void setConnectedListener() {
        
        InputProvider.ExtendProvider[] singleProvider = {
                new ImageInputProvider(RongContext.getInstance()),//图片
                new CameraInputProvider(RongContext.getInstance()),//锟斤拷锟�
              
        };

        InputProvider.ExtendProvider[] muiltiProvider = {
                new ImageInputProvider(RongContext.getInstance()),//图片
                new CameraInputProvider(RongContext.getInstance()),//锟斤拷锟�
        };

        RongIM.getInstance();
		RongIM.resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, singleProvider);
        RongIM.getInstance();
		RongIM.resetInputExtensionProvider(Conversation.ConversationType.DISCUSSION, muiltiProvider);
        RongIM.getInstance();
		RongIM.resetInputExtensionProvider(Conversation.ConversationType.CUSTOMER_SERVICE, muiltiProvider);
        RongIM.getInstance();
		RongIM.resetInputExtensionProvider(Conversation.ConversationType.GROUP, muiltiProvider);
    }
    
}  