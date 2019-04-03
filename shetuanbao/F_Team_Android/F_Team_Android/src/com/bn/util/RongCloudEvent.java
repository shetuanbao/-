package com.bn.util;


import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import android.content.Context;
import android.view.View;

/**
 * Created by AMing on 15/11/6.
 * Company RongCloud
 */
public class RongCloudEvent implements RongIM.ConversationBehaviorListener, RongIMClient.OnReceiveMessageListener, RongIM.ConversationListBehaviorListener, RongIM.UserInfoProvider, RongIM.GroupInfoProvider, RongIM.LocationProvider {
    private static RongCloudEvent mRongCloudInstance;

    private Context mContext;

    /**
     * 鍒濆鍖�RongCloud.
     *
     * @param context 涓婁笅鏂囥�
     */
    public static void init(Context context) {

        if (mRongCloudInstance == null) {

            synchronized (RongCloudEvent.class) {

                if (mRongCloudInstance == null) {
                    mRongCloudInstance = new RongCloudEvent(context);
                }
            }
        }

    }


    public RongCloudEvent(Context mContext) {
        this.mContext = mContext;
        //鍒濆鍖栦笉闇� connect 灏辫兘 鐩戝惉鐨�Listener
        initListener();
       
    }

    /**
     * init 鍚庡氨鑳借缃殑鐩戝惉
     */
    private void initListener() {
        RongIM.setConversationBehaviorListener(this);//璁剧疆浼氳瘽鐣岄潰鎿嶄綔鐨勭洃鍚櫒銆�
       
    }

    /**
     * 鑾峰彇RongCloud 瀹炰緥銆�
     *
     * @return RongCloud銆�
     */
    public static RongCloudEvent getInstance() {
        return mRongCloudInstance;
    }

    
    /**
     * 浼氳瘽鐣岄潰鐐瑰嚮娑堟伅鐨勭洃鍚�
     *
     * @param context
     * @param view
     * @param message
     * @return
     */
    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
        if (message.getContent() instanceof ImageMessage) { //瀹炵幇浼氳瘽鐣岄潰鐐瑰嚮鏌ョ湅澶у浘閫昏緫  渚濊禆 PhotoActivity 鍜�鍏跺竷灞�浠ュ強 menu/de_fix_username.xml
//            ImageMessage imageMessage = (ImageMessage) message.getContent();
//            Intent intent = new Intent(context, PhotoActivity.class);
//            intent.putExtra("photo", imageMessage.getLocalUri() == null ? imageMessage.getRemoteUri() : imageMessage.getLocalUri());
//            if (imageMessage.getThumUri() != null)
//                intent.putExtra("thumbnail", imageMessage.getThumUri());
//            context.startActivity(intent);
        	 return false;
        }
        return false;
    }

    @Override
    public boolean onMessageLinkClick(Context context, String s) {
        return false;
    }


	@Override
	public void onStartLocation(Context arg0, LocationCallback arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Group getGroupInfo(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserInfo getUserInfo(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean onConversationClick(Context arg0, View arg1,
			UIConversation arg2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onConversationLongClick(Context arg0, View arg1,
			UIConversation arg2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onConversationPortraitClick(Context arg0,
			ConversationType arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onConversationPortraitLongClick(Context arg0,
			ConversationType arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onReceived(Message arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onMessageLongClick(Context arg0, View arg1, Message arg2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onUserPortraitClick(Context arg0, ConversationType arg1,
			UserInfo arg2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onUserPortraitLongClick(Context arg0, ConversationType arg1,
			UserInfo arg2) {
		// TODO Auto-generated method stub
		return false;
	}

   

   

}
