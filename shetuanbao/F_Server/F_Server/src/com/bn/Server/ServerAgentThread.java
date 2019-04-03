package com.bn.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.bn.Database.DBUtil;
import com.bn.util.Constant;
import com.bn.util.IOUtil;
import com.bn.util.ImageUtil;
import com.bn.util.MyConverter;
import com.bn.util.StrListChange;

import io.example.Example;
public class ServerAgentThread extends Thread{
	Socket sc;
	DataInputStream din;
	DataOutputStream dout;
	public ServerAgentThread(Socket sc) // 创建构造器
	{
		this.sc = sc; // 接收Socket
	}
	public void run() // 重写run方法
	{
		try {
			din = new DataInputStream(sc.getInputStream()); // 创建数据输入流
			dout = new DataOutputStream(sc.getOutputStream()); // 创建数据输出流

			//List<String[]> ls = new ArrayList<String[]>(); // 创建List列表
			List<String[]> ls = new ArrayList<String[]>();
			String msg = din.readUTF(); // 将流中读到的信息赋值给字符串
			String mess = null; // 创建字符串
			String content = null; // 创建字符串
			String[] array = null; // 创建字符数组
			//String path = "res/img/"; // 将图片路径复制到字符串
			System.out.println(msg);
			System.out.println("=================================");
			if (msg.startsWith(Constant.GetAllHuodong))
			{
				    content = MyConverter.unescape(msg.substring(17, msg.length()));
					ls = DBUtil.getAllHuodong();
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetAllHuodongId))
			{
				content = MyConverter.unescape(msg.substring(19, msg.length()));
			    
			    ls=DBUtil.getallid();
			    mess = StrListChange.ListToStr(ls);
			    dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetAllHuodongTime))
			{
				    content = MyConverter.unescape(msg.substring(21, msg.length()));
				    
				    ls=DBUtil.getHudongTime();
				    mess = StrListChange.ListToStr(ls);
				    dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetAllHuodongPlace))
			{
				    content = MyConverter.unescape(msg.substring(22, msg.length()));
				    ls = DBUtil.getHuodongPlace();
				    mess = StrListChange.ListToStr(ls);
				    dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetOneHuodongTime))
			{
				content = MyConverter.unescape(msg.substring(21, msg.length()));
				mess = DBUtil.getonehuodongtime(content);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetOneHuodongPlace))
			{
				content = MyConverter.unescape(msg.substring(22, msg.length()));
				mess = DBUtil.getonehuodongplace(content);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetAllHuodongDetail))
			{
				    content = MyConverter.unescape(msg.substring(23, msg.length()));
				    mess = DBUtil.getHuodongDetail(content);
				    dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetAllHuodongPicture))
			{
				content = MyConverter.unescape(msg.substring(23, msg.length()));
			    ls=DBUtil.getHuodongPicture();
			    mess = StrListChange.ListToStr(ls);
			    dout.writeUTF(MyConverter.escape(mess));
			}
		    else if(msg.startsWith(Constant.GetAllHuadongPicture))
			{
                content = MyConverter.unescape(msg.substring(24, msg.length()));
			    
			    ls=DBUtil.getallhuadongpicture();
			    mess = StrListChange.ListToStr(ls);
			    dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetHuodongName))
			{
				content = MyConverter.unescape(msg.substring(18, msg.length()));
				ls=DBUtil.gethuodongname();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetOneHuodongByName))
			{
				content = MyConverter.unescape(msg.substring(23, msg.length()));
				ls = DBUtil.getonehuodongbyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetOneHuodongTimeByName))
			{
				content = MyConverter.unescape(msg.substring(27, msg.length()));
				ls = DBUtil.getonehuodongtimebyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetOneHuodongPlaceByName))
			{
				content = MyConverter.unescape(msg.substring(28, msg.length()));
				ls = DBUtil.getonehuodongplacebyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetOneHuodongIdByName))
			{
				content = MyConverter.unescape(msg.substring(25, msg.length()));
				ls = DBUtil.getonehuodongidbyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetOneHuodongPictureByName))
			{
				content = MyConverter.unescape(msg.substring(30, msg.length()));
				ls = DBUtil.getonehuodongimagebyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetHuodongPinglun))
			{
				content = MyConverter.unescape(msg.substring(21, msg.length()));
				ls = DBUtil.gethuodongpinglun(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.InsertHuodongPinglun))
			{
				content = MyConverter.unescape(msg.substring(24, msg.length()));
				DBUtil.inserthuodongpinglun(content);
			}else if(msg.startsWith(Constant.GetAllShetuan))
			{
				content = MyConverter.unescape(msg.substring(17, msg.length()));
				ls = DBUtil.getallshetuan();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetAllShetuanId))
			{
				content = MyConverter.unescape(msg.substring(19, msg.length()));
				ls = DBUtil.getshetuanid();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanDetail))
			{
				content = MyConverter.unescape(msg.substring(20,msg.length()));
				mess = DBUtil.getshetuandetail(content);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanPeople))
			{
				content = MyConverter.unescape(msg.substring(20, msg.length()));
				mess = DBUtil.getshetuanpeople(content);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetToken))
			{
				content = MyConverter.unescape(msg.substring(12,msg.length()));
				mess = Example.getToken(content);
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetTiyuShetuan))
			{
				content = MyConverter.unescape(msg.substring(18, msg.length()));
				ls = DBUtil.gettiyushetuan();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetTiyuShetuanId))
			{
				content = MyConverter.unescape(msg.substring(20, msg.length()));
				ls = DBUtil.gettiyushetuanid();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetWenyiShetuan))
			{
				content = MyConverter.unescape(msg.substring(19,msg.length()));
				ls = DBUtil.getwenyishetuan();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetWenyiShetuanId))
			{
				content = MyConverter.unescape(msg.substring(21, msg.length()));
				ls = DBUtil.getwenyishetuanid();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetCishanShetuan))
			{
				content = MyConverter.unescape(msg.substring(20,msg.length()));
				ls = DBUtil.getcishanshetuan();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetCishanShetuanId))
			{
				content = MyConverter.unescape(msg.substring(22,msg.length()));
				ls = DBUtil.getcishanshetuanid();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetXueshuShetuan))
			{
				content = MyConverter.unescape(msg.substring(20, msg.length()));
				ls = DBUtil.getxueshushetuan();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetXueshuShetuanId))
			{
				content = MyConverter.unescape(msg.substring(22, msg.length()));
				ls = DBUtil.getxueshushetuanid();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetShetuanKouhao))
			{
				content = MyConverter.unescape(msg.substring(20, msg.length()));
				ls = DBUtil.getshetuankouhao();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetAllShetuanPicture))
			{
				content = MyConverter.unescape(msg.substring(24, msg.length()));
				ls = DBUtil.getshetuanpicture();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetOneShetuanPicture))
			{
				content = MyConverter.unescape(msg.substring(24, msg.length()));
				
				ls = DBUtil.getoneshetuanpciture();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanxiangce))
			{
				content = MyConverter.unescape(msg.substring(21,msg.length()));
				ls = DBUtil.getshetuanxiangce(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuantiyupicture))
			{
				content = MyConverter.unescape(msg.substring(25, msg.length()));
				ls = DBUtil.getshetuantiyupicture();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuantiyukouhao))
			{
				content = MyConverter.unescape(msg.substring(24, msg.length()));
				ls = DBUtil.getshetuantiyukouhao();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuantiyupicture2))
			{
				content = MyConverter.unescape(msg.substring(26, msg.length()));
				ls = DBUtil.getshetuantiyupicture2();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanXueshupicture))
			{
				content = MyConverter.unescape(msg.substring(27, msg.length()));
				ls = DBUtil.getshetuanxueshupicture();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanXueshupciture2))
			{
				content = MyConverter.unescape(msg.substring(28, msg.length()));
				ls = DBUtil.getxueshupciture2();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanXueshukouhao)){
				content = MyConverter.unescape(msg.substring(26,msg.length()));
				ls = DBUtil.getshetuanxueshukouhao();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanCishankouhao))
			{
				content = MyConverter.unescape(msg.substring(26, msg.length()));
				ls = DBUtil.getshetuanxueshukouhao();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanCishanpicture))
			{
				content = MyConverter.unescape(msg.substring(27, msg.length()));
				ls = DBUtil.getshetuancishanpicture();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanCishanpicture2))
			{
				content = MyConverter.unescape(msg.substring(28, msg.length()));
				ls = DBUtil.getshetuancishanpicture2();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanWenyikouhao))
			{
				content = MyConverter.unescape(msg.substring(25, msg.length()));
				ls = DBUtil.getshetuanwenyikouhao();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanWenyipicture))
			{
				content = MyConverter.unescape(msg.substring(26, msg.length()));
				ls = DBUtil.getshetuanwenyipicture();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanWenyipicture2))
			{
				content = MyConverter.unescape(msg.substring(27, msg.length()));
				ls = DBUtil.getshetuanwenyipicture2();
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanByName))
			{
				content = MyConverter.unescape(msg.substring(20,msg.length()));
				ls = DBUtil.getshetuannamebyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanIdByName))
			{
				content = MyConverter.unescape(msg.substring(22, msg.length()));
				ls = DBUtil.getshetuanidbyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanKouhaoByName))
			{
				content = MyConverter.unescape(msg.substring(26, msg.length()));
				ls = DBUtil.getshetuankouhaobyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanPictureByName))
			{
				content = MyConverter.unescape(msg.substring(27, msg.length()));
				ls = DBUtil.getshetuanpicturebyname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}else if(msg.startsWith(Constant.GetShetuanPicture2ByName))
			{
				content = MyConverter.unescape(msg.substring(28,msg.length()));
				ls = DBUtil.getshetuanpicture2byname(content);
				mess = StrListChange.ListToStr(ls);
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetUserName)){
				content=MyConverter.unescape(msg.substring(15,msg.length()));
			    mess=DBUtil.getusername(content);
				dout.writeUTF(MyConverter.escape(mess));		
				}
			else if(msg.startsWith(Constant.GetUserFriendName)){
				content=MyConverter.unescape(msg.substring(21,msg.length()));
				mess=StrListChange.ListToStr(DBUtil.getuserfriendname(content));
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetUserFriendPen))
			{
				content=MyConverter.unescape(msg.substring(20,msg.length()));
				mess=StrListChange.ListToStr(DBUtil.GetUserfriendPen(content));
				dout.writeUTF(MyConverter.escape(mess));		
			}
			else if(msg.startsWith(Constant.GetUserFriendPhoto))
			{
				content=MyConverter.unescape(msg.substring(22,msg.length()));
				mess=StrListChange.ListToStr(DBUtil.getuserfriendphoto(content));
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetOnePicture))
			{
				content=MyConverter.unescape(msg.substring(17,msg.length()));
				byte[] bb=ImageUtil.getImage(Constant.picPathPre+content);			
				dout.writeInt(bb.length);
				dout.write(bb);
				dout.flush();
			}
			else if(msg.startsWith(Constant.GetUserFriendId))
			{
				content=MyConverter.unescape(msg.substring(19,msg.length()));
				mess=StrListChange.ListToStr(DBUtil.getuserfriendid(content));
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetUserFriend))
			{
				content=MyConverter.unescape(msg.substring(17,msg.length()));
				mess=StrListChange.ListToStr(DBUtil.getuserfriend(content));
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetUserJoinTeam))
			{
				content=MyConverter.unescape(msg.substring(19,msg.length()));
				mess=StrListChange.ListToStr(DBUtil.getuserjointeam(content));
				dout.writeUTF(MyConverter.escape(mess));
			}
			else if(msg.startsWith(Constant.GetUserOnePhoto))
			{
			
				content=MyConverter.unescape(msg.substring(19,msg.length()));
				mess=DBUtil.getuseronephoto(content);
				dout.writeUTF(MyConverter.escape(mess));
			}
			//登录
			else if(msg.startsWith(Constant.LOGIN))
			  {
				  String result=MyConverter.unescape(msg.substring(9,msg.length()));
				  mess=DBUtil.getlogin(result);
				  if(mess.equals("1"))
				  {
					dout.writeUTF(MyConverter.escape(mess));						
				  } 
				  else
				  {
					dout.writeUTF(MyConverter.escape("2"));
				  }
			  }
			  //注册
			  else if(msg.startsWith(Constant.REGISTER))
				{	
					String[]  result =StrListChange.StrToArray(MyConverter.unescape(msg.substring(12,msg.length())));
				    DBUtil.insertuser(result);
				}
			  else if(msg.startsWith(Constant.GETUSERID))
				{	
					String result =MyConverter.unescape(msg.substring(13,msg.length()));
				    mess=DBUtil.getuserId(result);
					dout.writeUTF(MyConverter.escape(mess));
				}	
			  else if(msg.startsWith(Constant.GetToken))
				{
					content = MyConverter.unescape(msg.substring(12,msg.length()));
					mess = Example.getToken(content);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GetHuodongPinglunPicture))
				{
					content = MyConverter.unescape(msg.substring(28,msg.length()));
					ls = DBUtil.gethuodongpinglunpicture(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GetHuodongPinglunName))
				{
					content = MyConverter.unescape(msg.substring(25,msg.length()));
					ls = DBUtil.gethuodongpinglunname(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GetHuodongPinglunId))
				{
					content = MyConverter.unescape(msg.substring(23,msg.length()));
					ls = DBUtil.gethuodongpinglunid(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.DeleteHuodongPinglun))
				{
					content = MyConverter.unescape(msg.substring(24,msg.length()));
					DBUtil.delectpinglun(content);
				}else if(msg.startsWith(Constant.Getusermessagebyid)){
					content = MyConverter.unescape(msg.substring(22, msg.length()));
					ls = DBUtil.getusermessagebyid(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.Setusermessagebyid))
				{
					content = MyConverter.unescape(msg.substring(22,msg.length()));
					array = StrListChange.StrToArray(content);
					DBUtil.setusermessagebyid(array);
				}else if(msg.startsWith(Constant.GetShetuanMessage))
				{
					content = MyConverter.unescape(msg.substring(21, msg.length()));
					ls = DBUtil.getshetuanmessage();
					mess = StrListChange.ListToStr(ls);
					System.out.println(mess);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GetAllHuodongMessage)){
					content = MyConverter.unescape(msg.substring(24, msg.length()));
					ls = DBUtil.getallhuodongmessage();
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.InsertPic))
		  		{   			
		  			content=MyConverter.unescape(msg.substring(13,msg.length()));
		  			byte[] data=IOUtil.readBytes(din);  				
					ImageUtil.savePic(data,Constant.picPathPre+content);
		  		}else if(msg.startsWith(Constant.GetHuodongFenbu)){
		  			content = MyConverter.unescape(msg.substring(19,msg.length()));
		  			array = StrListChange.StrToArray(content);
					ls = DBUtil.gethuodongfenbu(array);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
		  		}else if(msg.startsWith(Constant.GetHuodongMessageByName))
		  		{
		  			content = MyConverter.unescape(msg.substring(27, msg.length()));
		  			ls = DBUtil.gethuodongmessagebyname(content);
		  			mess = StrListChange.ListToStr(ls);
		  			dout.writeUTF(MyConverter.escape(mess));
		  		}else if(msg.startsWith(Constant.InsertHuodongMessage))
		  		{
		  			content = MyConverter.unescape(msg.substring(24,msg.length()));
					array = StrListChange.StrToArray(content);
					DBUtil.inserthuodong(array);
		  		}else if(msg.startsWith(Constant.GETFRIEND))
				{
					content=MyConverter.unescape(msg.substring(13,msg.length()));
					Example.publishMessage(content);
				}
				else if(msg.startsWith(Constant.INSERTFRIEND))
				{
					content=MyConverter.unescape(msg.substring(16,msg.length()));
					DBUtil.insertFriend(StrListChange.StrToArray(content));
					dout.writeUTF("SUCCESS");
				}
				else if(msg.startsWith(Constant.SEARCH))
				{
					content=MyConverter.unescape(msg.substring(10,msg.length()));
					mess=DBUtil.searchFriend(content);
					dout.writeUTF(mess);
				} else if(msg.startsWith(Constant.getshetuanchengyuan))
				{
					content = MyConverter.unescape(msg.substring(23, msg.length()));
					ls = DBUtil.getshetuanchengyuan(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GETSHETUANRENYUANID))
				{
					content=MyConverter.unescape(msg.substring(23,msg.length()));
					ls=DBUtil.getSheTuanRenYuanId(content);
					dout.writeUTF(StrListChange.ListToStr(ls));
				}
				else if(msg.startsWith(Constant.GETSHETUANUSERINFO))
				{
					content=MyConverter.unescape(msg.substring(22,msg.length()));
					ls=DBUtil.getshetuanuserInfo(content);
					dout.writeUTF(StrListChange.ListToStr(ls));
				}else if(msg.startsWith(Constant.GetHuodongPinglunTime))
				{
					content = MyConverter.unescape(msg.substring(25, msg.length()));
					ls = DBUtil.gethuodongpingluntimebyname(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GetShetuanDetailPicture))
				{
					content = MyConverter.unescape(msg.substring(27,msg.length()));
					mess = DBUtil.getshetuandetailpicture(content);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.zengjiashetuan))
				{
					content = MyConverter.unescape(msg.substring(18,msg.length()));
					array = StrListChange.StrToArray(content);
					DBUtil.zengjiashetuan(array);
					
				}else if(msg.startsWith(Constant.GetShetuanMax)){
					content = MyConverter.unescape(msg.substring(17,msg.length()));
					mess = DBUtil.getshetuanmaxid();
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.DeleteShetuan))
				{
					content = MyConverter.unescape(msg.substring(17,msg.length()));
					DBUtil.deleteshetuan(content);
				}else if(msg.startsWith(Constant.GetShetuanMessageById)){
					content = MyConverter.unescape(msg.substring(25,msg.length()));
					ls = DBUtil.getshetuanmessagebyid(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.InsertShetuanMessage)){
					content = MyConverter.unescape(msg.substring(24, msg.length()));
					array = StrListChange.StrToArray(content);
					DBUtil.insertintoshetuanmessage(array);
				}else if(msg.startsWith(Constant.Persident)){
					content = MyConverter.unescape(msg.substring(13,msg.length()));
					mess = DBUtil.getpresidentlogin(content);
					if(mess.equals("1"))
					  {
						dout.writeUTF(MyConverter.escape(mess));						
					  } 
					  else
					  {
						dout.writeUTF(MyConverter.escape("2"));
					  }
				}else if(msg.startsWith(Constant.GetXiangceById)){
					content = MyConverter.unescape(msg.substring(18,msg.length()));
					ls = DBUtil.getxiangcepicturebyid(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.deletexiangce)){
					content = MyConverter.unescape(msg.substring(17,msg.length()));
					DBUtil.deletexiangcepicture(content);
				}else if(msg.startsWith(Constant.insertxiangce)){
					content = MyConverter.unescape(msg.substring(17,msg.length()));
					DBUtil.insertxiangce(content);
				}else if(msg.startsWith(Constant.updataxiangce)){
					content = MyConverter.unescape(msg.substring(17,msg.length()));
					DBUtil.updataxiangce(content);
				}else if(msg.startsWith(Constant.Feedback)){
					content = MyConverter.unescape(msg.substring(12,msg.length()));
					DBUtil.insertyijian(content);
				}else if(msg.startsWith(Constant.GetYiJian)){
					content = MyConverter.unescape(msg.substring(13,msg.length()));
					ls=DBUtil.getyijianren(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GetYiJianMessage)){
					content = MyConverter.unescape(msg.substring(20,msg.length()));
					ls = DBUtil.getyijianmessage();
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.getguanliid))
				{	
					content =MyConverter.unescape(msg.substring(15,msg.length()));
				    mess=DBUtil.getguanliid(content);
					dout.writeUTF(MyConverter.escape(mess));
				}
				else if(msg.startsWith(Constant.tianjiachenghyuan))
				{
					content = MyConverter.unescape(msg.substring(21, msg.length()));
					DBUtil.tianjiachenghyuan(content);
					
				}else if(msg.startsWith(Constant.shanchuchengyuan))
				{
					content = MyConverter.unescape(msg.substring(20, msg.length()));
					DBUtil.shanchuchengyuan(content);
					
				}else if(msg.startsWith(Constant.getallshetuanchengyuan))
				{
					content = MyConverter.unescape(msg.substring(26, msg.length()));
					ls = DBUtil.getallshetuanchengyuan(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.getshetuan))
				{
					content = MyConverter.unescape(msg.substring(14, msg.length()));
					ls = DBUtil.getshetuan(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}
				else if(msg.startsWith(Constant.getallguanlihuodong))
				{
					content = MyConverter.unescape(msg.substring(23, msg.length()));
					ls = DBUtil.getallguanlihuodong(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.getmaxid))
				{	
					content =MyConverter.unescape(msg.substring(12,msg.length()));
				    mess=DBUtil.getmaxid(content);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.getcommunityid))
				{	
					content =MyConverter.unescape(msg.substring(18,msg.length()));
				    mess=DBUtil.getcommunityid(content);
				    
					dout.writeUTF(MyConverter.escape(mess));
				}	else if(msg.startsWith(Constant.inserthuodongm))
		  		{
		  			content = MyConverter.unescape(msg.substring(18,msg.length()));
					array = StrListChange.StrToArray(content);
					DBUtil.inserthuodongm(array);
					System.out.println(array[0]);
		  		}else if(msg.startsWith(Constant.getguanliyuankouling))
				{
					content = MyConverter.unescape(msg.substring(24,msg.length()));
					ls = DBUtil.getguanliyuankouling();
					mess = StrListChange.ListToStr(ls);
					System.out.println(mess);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.deletehuodong))
				{
					content = MyConverter.unescape(msg.substring(17, msg.length()));
					DBUtil.deletehuodong(content);
					
				}else if(msg.startsWith(Constant.getid)){
					content = MyConverter.unescape(msg.substring(9,msg.length()));
					mess = DBUtil.getuserId2(content);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.getallguanlihuodongid)){
					content = MyConverter.unescape(msg.substring(25,msg.length()));
					ls = DBUtil.getallguanlihuodongid(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}/*else if(msg.startsWith(Constant.gethuseridbyname)){
					content = MyConverter.unescape(msg.substring(20,msg.length()));
					mess = DBUtil.getuseridbyname(content);
					dout.writeUTF(MyConverter.escape(mess));
				}*/else if(msg.startsWith(Constant.GETUSERPEN))
				{
					content=MyConverter.unescape(msg.substring(14,msg.length()));
					mess=DBUtil.getuserpen(content);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.getshetuantohuodong)){
					content = MyConverter.unescape(msg.substring(23,msg.length()));
					ls = DBUtil.getshetuantohuodong(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GET_USERNAME_AND_PICTURE)){
					content = MyConverter.unescape(msg.substring(28,msg.length()));
					ls = DBUtil.getusernameandpicture(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GET_HUODONG_XIANGCE)){
					content = MyConverter.unescape(msg.substring(23,msg.length()));
					ls = DBUtil.gethuodongxiangce(content);
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.getallchengyuan))
				{
					content = MyConverter.unescape(msg.substring(19, msg.length()));
				    
				    ls=DBUtil.getallchengyuan();
				    mess = StrListChange.ListToStr(ls);
				    System.out.println("============================="+mess);
				    dout.writeUTF(MyConverter.escape(mess));
				    System.out.println(mess);
				}else if(msg.startsWith(Constant.shanchuhaoyou))
				{
					content = MyConverter.unescape(msg.substring(17, msg.length()));
					DBUtil.shanchuhaoyou(content);
					
				}else if(msg.startsWith(Constant.GET_USERIDANDPASSWORD)){
					content = MyConverter.unescape(msg.substring(25,msg.length()));
					ls = DBUtil.getuseridandpassword();
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.UPDATA_USER_PASSWORD)){
					content = MyConverter.unescape(msg.substring(24,msg.length()));
					DBUtil.updatauserpassword(content);
					
				}else if(msg.startsWith(Constant.GET_SHETUANNAME_BY_ID)){
					content = MyConverter.unescape(msg.substring(25,msg.length()));
					ls = DBUtil.getshetuannamebyguanli();
					mess = StrListChange.ListToStr(ls);
					dout.writeUTF(MyConverter.escape(mess));
				}else if(msg.startsWith(Constant.GET_GUANLI_MIMA)){
					content = MyConverter.unescape(msg.substring(19,msg.length()));
					ls = DBUtil.getguanlipassword();
					mess = StrListChange.ListToStr(ls);
					System.out.println("wwwwwwwwwwwwwwwww"+mess);
					dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_GUANLIMAXID)){
			    	content = MyConverter.unescape(msg.substring(19,msg.length()));
			    	mess = DBUtil.getguanlimax();
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.UPDATA_GUANLIMIMA)){
			    	content = MyConverter.unescape(msg.substring(21,msg.length()));
			    	DBUtil.updataguanlimima(content);
			    }else if(msg.startsWith(Constant.INERT_GUANLI_MIMA)){
			    	content = MyConverter.unescape(msg.substring(21,msg.length()));
			    	array=StrListChange.StrToArray(content);
			    	DBUtil.insertguanlimima2(array);
			    }else if(msg.startsWith(Constant.GET_ALLUSERID)){
			    	content = MyConverter.unescape(msg.substring(17,msg.length()));
			    	ls=DBUtil.getalluserid();
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_USERPICTURE)){
			    	content = MyConverter.unescape(msg.substring(19,msg.length()));
			    	mess=DBUtil.getuserpicture(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }
			    else if(msg.startsWith(Constant.GET_USERNAMEBUID)){
			    	content = MyConverter.unescape(msg.substring(20,msg.length()));
			    	ls=DBUtil.getusernamebyid(content);
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_FENGJINSHETUAN)){
			    	content = MyConverter.unescape(msg.substring(22,msg.length()));
			    	ls = DBUtil.getfengjinshetuan();
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.UPDATA_JIECHUSHETUAN)){
			    	content = MyConverter.unescape(msg.substring(24,msg.length()));
			    	DBUtil.updatashetuian(content);
			    }else if(msg.startsWith(Constant.GETUSERIDANDMM)){
			    	content = MyConverter.unescape(msg.substring(18,msg.length()));
			    	ls = DBUtil.getusermessagebynameandid(content);
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_USERSTATIC)){
			    	content = MyConverter.unescape(msg.substring(18,msg.length()));
			    	mess = DBUtil.getuserstat(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_SHETUANIDBYMIMA)){
			    	content = MyConverter.unescape(msg.substring(23,msg.length()));
			    	mess = DBUtil.getshetuanbymima(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_CUNZAISHETUANCHENGYUAN)){
			    	content = MyConverter.unescape(msg.substring(30,msg.length()));
			    	ls = DBUtil.getcunzaishetuanchengyuan(content);
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_SHANCHUSHETUANCHENGYUAN)){
			    	content = MyConverter.unescape(msg.substring(31,msg.length()));
			    	ls = DBUtil.getshanchushetuanchengyuan(content);
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_TIANJIACUNZAICHENGYUAN)){
			    	content = MyConverter.unescape(msg.substring(30,msg.length()));
			    	DBUtil.tianjiacunzaichengyuan(content);
			    }else if(msg.startsWith(Constant.UPDATA_USERFENG)){
			    	content = MyConverter.unescape(msg.substring(19,msg.length()));
			    	DBUtil.ipdatauserstat(content);		
			    }else if(msg.startsWith(Constant.UPDATA_USERJIE)){
			    	content = MyConverter.unescape(msg.substring(18,msg.length()));
			    	DBUtil.ipdatauserstat2(content);
			    }
			    else if(msg.startsWith(Constant.GET_USERNAME)){
			    	content =MyConverter.unescape(msg.substring(16,msg.length()));
			    	ls = DBUtil.getallusername();
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_USERPS)){
			    	content = MyConverter.unescape(msg.substring(14,msg.length()));
			    	mess =DBUtil.getuserpassword(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_USERIDBYNAME)){
			    	content = MyConverter.unescape(msg.substring(20,msg.length()));
			    	ls = DBUtil.getuseridbyname(content);
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_SUERSHETUAN)){
			    	content = MyConverter.unescape(msg.substring(19,msg.length()));
			    	mess = DBUtil.getusercanjiashetuan(content);
			    	System.out.println("eeeeeeeeeeeeeeeeeeee"+mess);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_SHETUANZHUPICTURE)){
			    	content = MyConverter.unescape(msg.substring(25,msg.length()));
			    	ls = DBUtil.getshetuanzhupicture();
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_SHETUANPCS)){
			    	content = MyConverter.unescape(msg.substring(18,msg.length()));
			    	ls = DBUtil.getshetuanpicturestat();
			    	mess = StrListChange.ListToStr(ls);
			    	System.out.println("ggggggg"+mess);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_SHETUANPCST)){
			    	content = MyConverter.unescape(msg.substring(19,msg.length()));
			    	ls = DBUtil.getshetuanpicturestat2();
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_USERZHUANYE)){
			    	content = MyConverter.unescape(msg.substring(19,msg.length()));
			    	mess = DBUtil.getuserzhuanye(content);
			    	System.out.println("tttttttttttt"+mess);
			    	dout.writeUTF(MyConverter.escape(mess)); 
			    }else if(msg.startsWith(Constant.GET_XUEYUAN)){
			    	content = MyConverter.unescape(msg.substring(15,msg.length()));
			    	mess = DBUtil.getuserxueyuan(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_USERSEX)){
			    	content = MyConverter.unescape(msg.substring(15,msg.length()));
			    	mess = DBUtil.getusersex(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_HUODONGRENYUANXINGMING)){
			    	content = MyConverter.unescape(msg.substring(30,msg.length()));
			    	ls = DBUtil.gethuodongrenyuan(content);
			    	mess = StrListChange.ListToStr(ls);
			    	System.out.println("jjjjjjjjjjjjjjjjjj"+mess);

			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_HUODONGRENYUANID)){
			    	content = MyConverter.unescape(msg.substring(24,msg.length()));
			    	ls = DBUtil.gethuodongrenyuanID(content);
			    	mess = StrListChange.ListToStr(ls);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.PD_SHETUANRENYUAN)){
			    	content = MyConverter.unescape(msg.substring(21,msg.length()));
			    	mess = DBUtil.pdshetuanrenyuan(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.INSERT_HUODONGRENYUAN)){
			    	content = MyConverter.unescape(msg.substring(25,msg.length()));
			    	array = StrListChange.StrToArray(content);
			    	DBUtil.inserthuodongrenyuan2(array);
			    }else if(msg.startsWith(Constant.SHIFOUBAOMING)){
			    	content = MyConverter.unescape(msg.substring(17,msg.length()));
			    	mess = DBUtil.shifoubaoming(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_HUODONGLEIXING)){
			    	content = MyConverter.unescape(msg.substring(22,msg.length()));
			    	mess = DBUtil.getactivityleixing(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }else if(msg.startsWith(Constant.GET_SHETUANRENZT)){
			    	content = MyConverter.unescape(msg.substring(20,msg.length()));
			    	mess = DBUtil.getshetuanpeoplezhuangtai(content);
			    	dout.writeUTF(MyConverter.escape(mess));
			    }
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				din.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
