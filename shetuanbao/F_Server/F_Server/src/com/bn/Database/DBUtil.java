package com.bn.Database;
import java.util.*;

import com.bn.util.StrListChange;

import java.sql.*;
public class DBUtil {
	private static Connection getConnection()
	{
		Connection con = null;
		try
		{
			 Class.forName("com.mysql.jdbc.Driver");
			 String url = "jdbc:mysql://localhost:3306/test?useUnicode=true" +
	    		 		"&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false";
	    	     con = DriverManager.getConnection(url,"root","");  //得到连接
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	public static String getuserId2(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql="";
		String mess="";
		try{
			st=con.createStatement();
			sql="select user_id from users where username="+"'"+content+"';";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				mess=rs.getString(1);		
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return mess;
	}
	public static String getuserId(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql="";
		String mess="";
		try{
			st=con.createStatement();
			sql="select count(*) from users where user_id="+"'"+content+"';";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				mess=rs.getString(1);		
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return mess;
	}
	public static String getuserstates(String content) {//查看用户是否被封禁
		 Connection con = getConnection();
		 Statement st = null;
	     ResultSet rs = null;
		 String sql = null;
		 String mess="";
		 try{
		      st=con.createStatement();
			  sql="select userstatus from users where user_id='"+content+"';";
		      rs=st.executeQuery(sql);
				
			  while(rs.next())
			  {
				  mess+=rs.getString(1);
			 }
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
	   	 return mess;
	}
	
	
	public static String getuserpassword(String content)//得到用户密码
	{
		Connection con = getConnection();
		 Statement st = null;
	     ResultSet rs = null;
		 String sql = null;
		 String mess="";
		 try{
		      st=con.createStatement();
			  sql="select userpassword from users where user_id='"+content+"';";
		      rs=st.executeQuery(sql);
				
			  while(rs.next())
			  {
				  mess+=rs.getString(1);
			  }
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
	   	 return mess;
	}
	public static List<String[]> getallid()
	{
		Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_id from activities;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
   	 return lstr;
	}
	 public static List<String[]> getAllHuodong()//得到活动
     {
    	 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_title from activities;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
    	 return lstr;
     }
	public static List<String[]> getHudongTime()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_time from activities;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
    	 return lstr;
	}
	public static List<String[]> getHuodongPlace()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_place from activities;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
    	 return lstr;
	}
	public static String getHuodongDetail(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String lstr=null;
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_introduce from activities where  activity_id='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 lstr=rs.getString(1);
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
    	 return lstr;
	}
	public static List<String[]> getHuodongPicture()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_picture from activities;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
    	 return lstr;
	}
	public static String getonehuodongtime(String name)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String lstr=null;
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_time from activities where  activity_id='"+name+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 lstr=rs.getString(1);
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
   	 return lstr;
	}
	public static String getonehuodongplace(String name)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String lstr=null;
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_place from activities where  activity_id='"+name+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 lstr=rs.getString(1);
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
  	 return lstr;
	}
	public static List<String[]> getallhuadongpicture()
	{
		Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select sname from huodongimage;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
   	 return lstr;
	}
	public static List<String[]> gethuodongname()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		List<String[]> lstr=new ArrayList<String[]>();
		try{
			 st=con.createStatement();
			 sql="select activity_title from activities;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
		return lstr;
	}
	public static List<String[]> getonehuodongbyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_title from activities where  activity_title='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
 	 return lstr;
	}
	public static List<String[]> getonehuodongtimebyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_time from activities where  activity_title='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getonehuodongplacebyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_place from activities where  activity_title='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getonehuodongidbyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_id from activities where  activity_title='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getonehuodongimagebyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select activity_picture from activities where  activity_title='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static String getpinglunmaxid()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String lstr=null;
		 try 
		 {
			 st=con.createStatement();
			 sql="select max(sid) from activity_pinglun;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 lstr=rs.getString(1);
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static void inserthuodongpinglun(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String ss[]=new String[6];
		 ss=content.split("#");
		 try 
		 {
			 st=con.createStatement();
			 sql="insert into activity_pinglun values"+"("+"'"+ss[0]+"',"+"'"+ss[1]+"',"+"'"+ss[2]+"',"+"'"+ss[3]+"',"+"'"+ss[4]+"',"+"'"+ss[5]+"'"+");";
			 st.execute(sql);
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}

	public static List<String[]> gethuodongpinglun(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select sdetail from activity_pinglun where  sid_activity='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getallshetuan()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_name from community where community_stat='1';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanid()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_id from community where community_stat='1';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static String getshetuandetail(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String lstr=null;
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_introduce from community where  community_id='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 lstr=rs.getString(1);
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static String getshetuanpeople(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String lstr=null;
		 String sttc="1";
		 int count1=0;
		 try 
		 {
			 st=con.createStatement();
			 sql="select username from users where users.user_id in "+"("+"select user_id from community_users where community_id='"+content+"'and stat='"+sttc+"'"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 if(count1==0)
				 {
					 lstr=rs.getString(1)+" ";
				 }
				 else{
					 lstr+=rs.getString(1)+" ";
				 }
				 count1++;
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static String getshetuanpeoplezhuangtai(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String lstr=null;
		 String sttc="1";
		 int count1=0;
		 try 
		 {
			 st=con.createStatement();
			 sql="select count(*) from users where users.user_id in "+"("+"select user_id from community_users where community_id='"+content+"'and stat='"+sttc+"'"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 if(count1==0)
				 {
					 lstr=rs.getString(1)+" ";
				 }
				 else{
					 lstr+=rs.getString(1)+" ";
				 }
				 count1++;
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> gettiyushetuan()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_name from community_tiyu;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> gettiyushetuanid()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_id from community_tiyu;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getwenyishetuan()
	{
		Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_name from community_wenyi;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getwenyishetuanid()
	{

		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_id from community_wenyi;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getcishanshetuan()
	{

		Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_name from community_cishan;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getcishanshetuanid()
	{

		Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_id from community_cishan;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getxueshushetuan()
	{

		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_name from community_xueshu;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getxueshushetuanid()
	{

		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_id from community_xueshu;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanpicture()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_tubiao from community;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
   	 return lstr;
	}
	public static List<String[]> getoneshetuanpciture()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_picture from community;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuankouhao()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_kouhao from community;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
  	 return lstr;
	}
	public static List<String[]> getshetuanxiangce(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_name from community_xiangce where  community_id='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuantiyupicture()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_tubiao from community where  community_id in"+"("+"select community_id from community_tiyu"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuantiyukouhao()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_kouhao from community where  community_id in"+"("+"select community_id from community_tiyu"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuantiyupicture2()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_picture from community where  community_id in"+"("+"select community_id from community_tiyu"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanxueshupicture()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_tubiao from community where  community_id in"+"("+"select community_id from community_xueshu"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getxueshupciture2()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_picture from community where  community_id in"+"("+"select community_id from community_xueshu"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanxueshukouhao()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_kouhao from community where  community_id in"+"("+"select community_id from community_xueshu"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuancishanpicture()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_tubiao from community where  community_id in"+"("+"select community_id from community_cishan"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuancishanpicture2()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_picture from community where  community_id in"+"("+"select community_id from community_cishan"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuancishankouhao()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_kouhao from community where  community_id in"+"("+"select community_id from community_cishan"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanwenyikouhao()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_kouhao from community where  community_id in"+"("+"select community_id from community_wenyi"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanwenyipicture()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_tubiao from community where  community_id in"+"("+"select community_id from community_wenyi"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }	 
		}catch(Exception e) 
		     {
				e.printStackTrace();
		     }
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanwenyipicture2()
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_picture from community where  community_id in"+"("+"select community_id from community_wenyi"+");";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuannamebyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_name from community where  community_name='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanidbyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_id from community where  community_name='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuankouhaobyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_kouhao from community where  community_name='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanpicturebyname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_tubiao from community where  community_name='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static List<String[]> getshetuanpicture2byname(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select community_picture from community where  community_name='"+content+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	public static String getusername(String content){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String mess=null;
		try{
			st=con.createStatement();
			sql="select username from users where user_id="+"'"+content+"';";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				mess=rs.getString(1);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
			
		}
		return mess;
	}
	//获取好友姓名
	public static List<String[]> getuserfriendname(String content)
	{
		
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		List<String[]> lstr=new ArrayList<String[]>();
		
		try{
			st=con.createStatement();
			sql="select username from users where user_id in"+
				"("+"select friend_id from friends where user_id="+"'"+content+"');";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				String[] str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	//获取好友的学号
	public static List<String[]> getuserfriendid(String content)
	{
		
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		List<String[]> lstr=new ArrayList<String[]>();
		
		try{
			st=con.createStatement();
			sql="select friend_id from friends where user_id="+content+";";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				String[] str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	
	//获取好友的个性签名
	public static List<String[]> GetUserfriendPen(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select userpen from users where user_id in"+
			"("+"select friend_id from friends where user_id="+"'"+content+"');";				
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	//获取好友头像名字
	public static List<String[]> getuserfriendphoto(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select userphoto from users where user_id in"+
			"("+"select friend_id from friends where user_id="+"'"+content+"');";				
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	//获得一位好友的头像的名字
	public static String getuseronephoto(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String mess=null;
		
		try{
			st=con.createStatement();
			sql="select userphoto from users where user_id="+"'"+content+"';";				
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				
				mess=rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return mess;
	}
	//获得好友的基本信息
	public static List<String[]> getuserfriend(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select username ,sex,userphone,xueyuan,major,useremail,userpen from users where user_id="+"'"+content+"';";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[7];
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				str[4]=rs.getString(5);
				str[5]=rs.getString(6);
				str[6]=rs.getString(7);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			System.out.println(lstr.size()+"========================");
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	//获取用户所在社团
	public static List<String[]> getuserjointeam(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
	
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select  community_name from community where community_id in"+
					"("+" select community_id from community_users where user_id="+"'"+content+"');";		
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	//能否登录
	public static String  getlogin(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String mess=null;
		String str[]=StrListChange.StrToArray(content);
		try{
			st=con.createStatement();
			sql="select count(*) from users where user_id="+"'"+str[0]+"'"+"and userpassword="+"'"+str[1]+"';";		
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				mess=rs.getString(1).toString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return mess;
	}
	
	private static Object insertuser=new Object();
	public static void insertuser(String[] array) {
		synchronized(insertuser)
		{
		Connection con = getConnection();
		Statement st = null;
		try
		{	
			st = con.createStatement();
			String sql;
			sql="insert into users (user_id,userpassword,username,useremail,userphone,sex,userpen,userphoto,static,xueyuan,major) values ("+"'"+array[0]+"',"+"'"+array[1]+"',"+"'"+array[2]+"',"+"'"+array[3]+"',"+"'"+array[4]+"',"+"'"+array[5]+"',"+"'"+array[6]+"',"+"'touxiang.png',"+"'1'"+",'"+array[7]+"',"+"'"+array[8]+"');";
			st.executeUpdate(sql);
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		finally
		{
			try{st.close();}catch(Exception e) {e.printStackTrace();}	
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		}
	}
	public static List<String[]> gethuodongpinglunpicture(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		List<String[]> lstr=new ArrayList<String[]>();
		
		try{
			st=con.createStatement();
			sql="select  spicture from activity_pinglun where sid_activity='"+content+"';";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				String[] str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	public static List<String[]> gethuodongpinglunname(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		List<String[]> lstr=new ArrayList<String[]>();
		
		try{
			st=con.createStatement();
			sql="select  sname from activity_pinglun where sid_activity='"+content+"';";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				String[] str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}

	public static List<String[]> gethuodongpinglunid(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		List<String[]> lstr=new ArrayList<String[]>();
		
		try{
			st=con.createStatement();
			sql="select  sid_user from activity_pinglun where sid_activity='"+content+"';";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				String[] str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	public static void delectpinglun(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String ss[]=new String[2];
		 ss=content.split("#");
		 try 
		 {
			 st=con.createStatement();
			 sql="delete from activity_pinglun where sid_activity='"+ss[0]+"'and  sid_user='"+ss[1]+"';";
			 st.execute(sql);
			 //count++;
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 //try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	public static List<String[]> getusermessagebyid(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs = null;
		String sql = null;
		List<String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select userphoto,user_id,username,sex,useremail,userphone,userpen ,xueyuan,major from users where user_id='"+content+"';";
			rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[9];
				 str[0]=rs.getString(1);
				 str[1]=rs.getString(2);
				 str[2]=rs.getString(3);
				 str[3]=rs.getString(4);
				 str[4]=rs.getString(5);
				 str[5]=rs.getString(6);
				 str[6]=rs.getString(7);
				 str[7]=rs.getString(8);
				 str[8]=rs.getString(9);
				 lstr.add(str); }
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 try{rs.close();}catch(SQLException e){e.printStackTrace();}
				 try{st.close();}catch(SQLException e){e.printStackTrace();}
				 try{con.close();}catch(SQLException e){e.printStackTrace();}
			 }
		
		return lstr;
	}
	public static void setusermessagebyid(String[] array)
    {
   	 Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 try 
		 {
			 st=con.createStatement();
			 array[6]=array[6]+".png";
			 sql="update users set username='"+array[1]+"'," +
			 		"sex='"+array[2]+"',useremail='"+array[3]+"',userphone='"+array[4]+"',userpen='"+array[5]+"'";
			 if(array[6].equals("999"))
			 {
				 sql=sql+"where user_id='"+array[0]+"';";
			 }
			 else
			 {
				 sql=sql+",userphoto='"+array[6]+"' where user_id='"+array[0]+"';";
			 }
			 st.executeUpdate(sql);	 
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
    }
	public static List<String[]> gethuodongname2()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		List<String[]> lstr=new ArrayList<String[]>();
		try{
			 st=con.createStatement();
			 sql="select activity_title,activity_time,activity_place from activities;";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[3];
				 str[0]=rs.getString(1);
				 str[1]=rs.getString(2);
				 str[2]=rs.getString(3);
				 //lstr=rs.getString(1);
				 /*str[1]=rs.getString(2);
				 str[2]=rs.getString(3);
				 str[3]=rs.getString(4);
				 str[4]=rs.getString(5);*/
				 lstr.add(str); 
				 //str[0]=rs.getString(1);
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
		return lstr;
	}
	public static List<String[]> getshetuanmessage(){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs = null;
		String sql = null;
		List<String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select community_name,community_id,community_kouhao,community_tubiao,community_picture from community where community_stat='1';";
			rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[5];
				 str[0]=rs.getString(1);
				 str[1]=rs.getString(2);
				 str[2]=rs.getString(3);
				 str[3]=rs.getString(4);
				 str[4]=rs.getString(5);
				 
				 lstr.add(str); }
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 try{rs.close();}catch(SQLException e){e.printStackTrace();}
				 try{st.close();}catch(SQLException e){e.printStackTrace();}
				 try{con.close();}catch(SQLException e){e.printStackTrace();}
			 }
		
		return lstr;
	}
	public static List<String[]> getallhuodongmessage()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs = null;
		String sql = null;
		List<String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select activity_title,activity_time,activity_place,activity_id,activity_picture from activities;";
			rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[5];
				 str[0]=rs.getString(1);
				 str[1]=rs.getString(2);
				 str[2]=rs.getString(3);
				 str[3]=rs.getString(4);
				 str[4]=rs.getString(5);
				 
				 lstr.add(str); }
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 try{rs.close();}catch(SQLException e){e.printStackTrace();}
				 try{st.close();}catch(SQLException e){e.printStackTrace();}
				 try{con.close();}catch(SQLException e){e.printStackTrace();}
			 }
		
		return lstr;
	}
	public static List<String[]> gethuodongfenbu(String[] array)
	{
		
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs = null;
		String sql = null;
		List<String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select activity_title,activity_time,activity_place,activity_id,activity_picture from activities where (activity_id='"+array[0]+"' ";
			for(int i=1;i<array.length;i++)
			 {
				 sql=sql+" or activity_id='"+array[i]+"'";
			 }
			 sql=sql+") ;";
			rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[5];
				 str[0]=rs.getString(1);
				 str[1]=rs.getString(2);
				 str[2]=rs.getString(3);
				 str[3]=rs.getString(4);
				 str[4]=rs.getString(5);
				 
				 lstr.add(str); }
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 try{rs.close();}catch(SQLException e){e.printStackTrace();}
				 try{st.close();}catch(SQLException e){e.printStackTrace();}
				 try{con.close();}catch(SQLException e){e.printStackTrace();}
			 }
		
		return lstr;
	}
	public static List<String[]> gethuodongmessagebyname(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs = null;
		String sql = null;
		List<String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select activity_picture,activity_title,activity_time,activity_place,activity_introduce from activities where activity_title='"+content+"';";
			
			rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[5];
				 str[0]=rs.getString(1);
				 str[1]=rs.getString(2);
				 str[2]=rs.getString(3);
				 str[3]=rs.getString(4);
				 str[4]=rs.getString(5);
				 
				 lstr.add(str); }
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 try{rs.close();}catch(SQLException e){e.printStackTrace();}
				 try{st.close();}catch(SQLException e){e.printStackTrace();}
				 try{con.close();}catch(SQLException e){e.printStackTrace();}
			 }
		
		return lstr;
	}
	public static void inserthuodong(String[] array)
	{
		Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String leixing=null;
		 if(array[5].equals("不公开")){
			 leixing="0";
		 }else {
			 leixing="1";
		 }
		 try 
		 {
			 st=con.createStatement();
			// array[4]=array[4];
			 sql="update activities set activity_time='"+array[1]+"'," +
			 		"activity_place='"+array[2]+"',activity_introduce='"+array[3]+"',leixing='"+leixing+"'";
			 if(array[4].equals("999"))
			 {
				 sql=sql+"where activity_title='"+array[0]+"';";
			 }
			 else
			 {
				 sql=sql+",activity_picture='"+array[4]+"' where activity_title='"+array[0]+"';";
			 }
			 st.executeUpdate(sql);	 
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	private static Object insertFriend=new Object();
	public static void insertFriend(String[] array) {
		synchronized(insertFriend)
		{
		Connection con = getConnection();
		Statement st = null;
		
		try
		{	
			st = con.createStatement();
			String sql;
			sql="insert into friends values('"+array[0]+"','"+array[1]+"');";
			st.executeUpdate(sql);
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		finally
		{
			try{st.close();}catch(Exception e) {e.printStackTrace();}	
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		}
	}
	///查看自己是否有某个好友
	public static String searchFriend(String friend)
	{
		Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;		 
		 String lstr=null;
		 String array[]=friend.split("#");
		 try 
		 {
			 st=con.createStatement();
			 sql="select count(*) from friends where user_id="+"'"+array[0]+"' and friend_id="+"'"+array[1]+"';";
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 lstr=rs.getString(1);
			 }
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
  	 return lstr;
	}
	public static List<String[]> getshetuanchengyuan(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String sttc="1";
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			 st=con.createStatement();
			 sql="select username from users where users.user_id in "+
			    "(select user_id from community_users where community_users.community_id in"+
			    "(select community_id from community where community_name='"+content+"') and stat='"+sttc+"');" ;
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }	 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		 }
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	//查找某个社团成员的ID
	public static List<String[]> getSheTuanRenYuanId(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 ResultSet rs = null;
		 String sql = null;
		 String stat="1";
		 List<String[]> lstr=new ArrayList<String[]>();
		 try 
		 {
			  st=con.createStatement();
			  sql="select user_id from users where users.user_id in "+
			    "(select user_id from community_users where community_users.community_id in"+
			    "(select community_id from community where community_name='"+content+"')and stat='"+stat+"');" ;
			 rs=st.executeQuery(sql);
			 while(rs.next())
			 {
				 String[] str=new String[1];
				 str[0]=rs.getString(1);
				 lstr.add(str); 
			 }	 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	 return lstr;
	}
	//获得社团用户的基本信息
		public static List<String[]> getshetuanuserInfo(String content)
		{
			Connection con=getConnection();
			Statement st=null;
			ResultSet rs=null;
			String sql=null;
			String str[]=null;
			List <String[]> lstr=new ArrayList<String[]>();
			try{
				st=con.createStatement();
				sql="select username ,sex,useremail,userphone from users where user_id="+"'"+content+"';";			
				rs=st.executeQuery(sql);
				while(rs.next())
				{
					str=new String[4];
					str[0]=rs.getString(1);
					str[1]=rs.getString(2);
					str[2]=rs.getString(3);
					str[3]=rs.getString(4);
					lstr.add(str);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}finally
			{
				try{rs.close();}catch(SQLException e){e.printStackTrace();}
				try{st.close();}catch(SQLException e){e.printStackTrace();}
				try{con.close();}catch(SQLException e){e.printStackTrace();}
			}
			return lstr;
		}
	public static List<String[]> gethuodongpingluntimebyname(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select stime from activity_pinglun where sid_activity="+"'"+content+"';";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	public static String getshetuandetailpicture(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str=null;
		//List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select community_picture from community where community_id="+"'"+content+"';";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				//str=new String[1];
				str=rs.getString(1);
				//lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	public static String getshetuanmaxid()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str=null;
		//List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select max(community_id) from community;";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				//str=new String[1];
				str=rs.getString(1);
				//lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return str;
	}
	public static void zengjiashetuan(String[] array)
	{
		
		 Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String sss=getshetuanmaxid();
	     int s1=Integer.parseInt(sss)+1;
		 String s=s1+"";
		 String x=array[3]+"_fu";
		 String stat="1";
		 try 
		 {
			 st=con.createStatement();
			 sql="insert into community values"+"("+"'"+s+"',"+"'"+array[0]+"',"+"'"+array[1]+"',"+"'"+array[2]+"',"+"'"+array[3]+"',"+"'"+x+"',"+"'"+stat+"'"+");";
			 st.execute(sql);
			 //count++;
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 //try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	
	public static void deleteshetuan(String content){
		       Connection con = getConnection();
		       Statement st = null;
		       String sql = null;
				try
				{	
					st = con.createStatement();
					
					sql="update community set community_stat='0'where community_name='"+content+"';";
					st.executeUpdate(sql);
				}
				catch(Exception e)
				{
					e.printStackTrace() ;
				}
				finally
				{
					try{st.close();}catch(Exception e) {e.printStackTrace();}	
					try{con.close();}catch(Exception e) {e.printStackTrace();}
				}
				
	}
	public static List<String[]> getshetuanmessagebyid(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select community_name,community_introduce,community_kouhao,community_tubiao,community_picture from community where community_id="+"'"+content+"';";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[5];
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				str[4]=rs.getString(5);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	public static void insertintoshetuanmessage(String[] array)
	{
		Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 try 
		 {
			 st=con.createStatement();
			 sql="update community set community_name='"+array[0]+"'where community_id='"+array[1]+"';";
			
			 st.executeUpdate(sql);	 
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	public static String  getpresidentlogin(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String mess=null;
		String str[]=StrListChange.StrToArray(content);
		try{
			st=con.createStatement();
			sql="select count(*) from president where id="+"'"+str[0]+"'"+"and password="+"'"+str[1]+"';";		
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				mess=rs.getString(1).toString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return mess;
	}
	public static List<String[]> getxiangcepicturebyid(String content){
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select community_name from community_xiangce where community_id='"+content+"';";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[1];
				str[0]=rs.getString(1);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	private static Object deletexiangce=new Object();
	public static void deletexiangcepicture(String content){
			synchronized(deletexiangce)
				{
				Connection con = getConnection();
				Statement st = null;
				try
				{	
					st = con.createStatement();
					String sql;
					sql="delete from activity_picture where picture_id='"+content+"' ;";
					st.executeUpdate(sql);
				}
				catch(Exception e)
				{
					e.printStackTrace() ;
				}
				finally
				{
					try{st.close();}catch(Exception e) {e.printStackTrace();}	
					try{con.close();}catch(Exception e) {e.printStackTrace();}
				}
				}
	}
	public static void insertxiangce(String content){
		 Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String ss[]=new String[2];
		 ss=content.split("#");
		 try 
		 {
			 st=con.createStatement();
			 sql="insert into activity_picture values('"+ss[0]+"',"+"'"+ss[1]+"'"+");";
			 st.execute(sql);
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 //try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	public static void updataxiangce(String content)
	{
		Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String ss[]=new String[2];
		 ss=content.split("#");
		 try 
		 {
			 st=con.createStatement();
			 sql="update community_xiangce set community_name='"+ss[0]+"'where community_name='"+ss[1]+"';";
			
			 st.executeUpdate(sql);	 
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	public static void insertyijian(String content){
		 Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String ss[]=new String[6];
		 ss=content.split("#");
		 try 
		 {
			 st=con.createStatement();
			 sql="insert into yijian values"+"("+"'"+ss[0]+"',"+"'"+ss[1]+"',"+"'"+ss[2]+"',"+"'"+ss[3]+"',"+"'"+ss[4]+"',"+"'"+ss[5]+"'"+");";
			 st.execute(sql);
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 //try{rs.close();}catch(SQLException e){e.printStackTrace();}
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	public static List<String[]> getyijianren(String content)
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select username,userphoto from users where user_id='"+content+"';";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[2];
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	public static List<String[]> getyijianmessage()
	{
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		String str[]=null;
		List <String[]> lstr=new ArrayList<String[]>();
		try{
			st=con.createStatement();
			sql="select username,userpicture,lianxi,time,detail from yijian;";			
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				str=new String[5];
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				str[4]=rs.getString(5);
				lstr.add(str);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return lstr;
	}
	public static String getguanliid(String content)
	{
		
		Connection con=getConnection();
		Statement st=null;
		ResultSet rs=null;
		String sql="";
		String mess="";
		try{
			st=con.createStatement();
			sql="select community_id from administrator where password='"+content+"';";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				mess=rs.getString(1);		
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			try{st.close();}catch(SQLException e){e.printStackTrace();}
			try{con.close();}catch(SQLException e){e.printStackTrace();}
		}
		return mess;
	}
	public static void tianjiachenghyuan(String content)
	{
		 Connection con = getConnection();
		 Statement st = null;
		 String sql = null;
		 String ss[]=new String[2];
		 ss=content.split("#");
		 String stat="1";
		 try 
		 {
			 st=con.createStatement();
			 sql="insert into community_users values"+"("+"'"+ss[0]+"',"+"'"+ss[1]+"',"+"'"+stat+"'"+");";
			 st.execute(sql);
			 
		 }catch(Exception e) 
		 {
				e.printStackTrace();
		}
		 finally{
			 
			 try{st.close();}catch(SQLException e){e.printStackTrace();}
			 try{con.close();}catch(SQLException e){e.printStackTrace();}
		 }
	}
	
        public static void shanchuchengyuan(String content) {
		Connection con = getConnection();
		Statement st = null;
		String ss[]=new String[2];
		ss=content.split("#");
		String stat="0";
		try
		{	
			st = con.createStatement();
			String sql;
			sql="update community_users set stat='"+stat+"' where user_id='"+ss[0]+"' and community_id='"+ss[1]+"';";
			st.executeUpdate(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{st.close();}catch(Exception e) {e.printStackTrace();}	
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
	}
        public static void tianjiacunzaichengyuan(String content) {
    		Connection con = getConnection();
    		Statement st = null;
    		String ss[]=new String[2];
    		ss=content.split("#");
    		String stat="1";
    		try
    		{	
    			st = con.createStatement();
    			String sql;
    			sql="update community_users set stat='"+stat+"' where user_id='"+ss[0]+"' and community_id='"+ss[1]+"';";
    			st.executeUpdate(sql);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		finally
    		{
    			try{st.close();}catch(Exception e) {e.printStackTrace();}	
    			try{con.close();}catch(Exception e) {e.printStackTrace();}
    		}
    	}
        public static List<String[]> getallshetuanchengyuan(String content)
    	{
    		 Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 String stat="1";
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 
    			  sql="select username from users where users.user_id in "+
    			    "(select user_id from community_users where community_users.community_id in"+
    			    "(select community_id from administrator where password='"+content+"') and stat='"+stat+"');" ;
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			 }	 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	 return lstr;
    	}
        
        public static List<String[]> getshetuan(String content)
    	{
    		 Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 
    			  sql="select community_name from community where community.community_id in "+
    			    "(select community_id from administrator where password='"+content+"');";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			 }	 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	 return lstr;
    	}
        public static List<String[]> getcunzaishetuanchengyuan(String content)
    	{
    		 Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 
    			  sql="select user_id from community_users where community_id in "+
    			    "(select community_id from administrator where password='"+content+"') and stat='1';";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			 }	 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	 return lstr;
    	}
        public static List<String[]> getshanchushetuanchengyuan(String content)
    	{
    		 Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 
    			  sql="select user_id from community_users where community_id in "+
    			    "(select community_id from administrator where password='"+content+"') and stat='0';";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			 }	 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	 return lstr;
    	}
        public static List<String[]> getallguanlihuodong(String content)
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 
    			  sql="select activity_title from activities where activities.community_id in "+
    			    "(select community_id from administrator where password='"+content+"');";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			 }	 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	 return lstr;
    	}
        public static List<String[]> getallguanlihuodongid(String content)
    	{
    		 Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 
    			  sql="select activity_id from activities where activities.community_id in "+
    			    "(select community_id from administrator where password='"+content+"');";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			 }	 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	 return lstr;
    	}
        public static String getmaxid(String content)
    	{
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql="";
    		String mess="";
    		try{
    			st=con.createStatement();
    			sql="select max(activity_id) from activities;";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1);		
    			}	
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
        public static String  getcommunityid(String content)
    	{
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		try{
    			st=con.createStatement();
    			sql="select community_id from administrator where password ='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
        private static Object inserthuodongm2=new Object();
        public static void inserthuodongm(String[] array)
    	{
        	synchronized(inserthuodongm2)
    		{
    		 Connection con = getConnection();
    		 Statement st = null;
    		 String leixing=null;
    		 if(array[7].equals("不公开")){
    			 leixing="0";
    		 }else {
    			 leixing="1";
    		 }
    		 try 
    		 {
    			 st=con.createStatement();
    			 String sql;
    			 sql="insert into activities values('"+array[5]+"','"+array[0]+"','"+array[1]+"','"+array[2]+"','"+array[3]+"','"+array[4]+"','"+array[6]+"','"+leixing+"');";
    			 st.executeUpdate(sql);	 
    			 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    		}
    	}
        public static void deletehuodong(String content) {
    		
    		Connection con = getConnection();
    		Statement st = null;
    		try
    		{	
    			st = con.createStatement();
    			String sql;
    			sql="delete from activities where activity_title ='"+content+"';";
    			st.executeUpdate(sql);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace() ;
    		}
    		finally
    		{
    			try{st.close();}catch(Exception e) {e.printStackTrace();}	
    			try{con.close();}catch(Exception e) {e.printStackTrace();}
    		}
    		
    	}
    	public static List<String[]> getguanliyuankouling()
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="select password from administrator;";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			 }
    			 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
       	 return lstr;
    	}
    	public static String getuserpen(String content)
    	{
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		try{
    			st=con.createStatement();
    			sql="select userpen from users where user_id ='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static List<String[]> getshetuantohuodong(String content){
    		
   		 Connection con = getConnection();
      		 Statement st = null;
      		 ResultSet rs = null;
      		 String sql = null;
      		 List<String[]> lstr=new ArrayList<String[]>();
      		 
      		 try 
      		 {
      			 st=con.createStatement();
      			 sql="select activity_id,activity_title,activity_time,activity_place,activity_picture from (select *from activities order by  str_to_date(activity_time,'%Y-%m-%d %H:%i:%s') desc ) s where community_id='"+content+"' limit 5;";
      			
      			 rs=st.executeQuery(sql);
      			 while(rs.next())
      			 {
      				 String[] str=new String[5];
      				 str[0]=rs.getString(1);
      				 str[1]=rs.getString(2);
      				 str[2]=rs.getString(3);
      				 str[3]=rs.getString(4);
      				 str[4]=rs.getString(5);
      				 lstr.add(str); 
      			 }
      			 
      		 }catch(Exception e) 
      		 {
      				e.printStackTrace();
      		}
      		 finally{
      			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
      			 try{st.close();}catch(SQLException e){e.printStackTrace();}
      			 try{con.close();}catch(SQLException e){e.printStackTrace();}
      		 }
         	 return lstr;
   	}
    	public static List<String[]> getusernameandpicture(String content){
    		Connection con = getConnection();
      		 Statement st = null;
      		 ResultSet rs = null;
      		 String sql = null;
      		 List<String[]> lstr=new ArrayList<String[]>();
      		 
      		 try 
      		 {
      			 st=con.createStatement();
      			 sql="select username,userphoto from users where user_id='"+content+"';";
      			 rs=st.executeQuery(sql);
      			 while(rs.next())
      			 {
      				 String[] str=new String[2];
      				 str[0]=rs.getString(1);
      				 str[1]=rs.getString(2);
      				 lstr.add(str); 
      			 }
      			 
      		 }catch(Exception e) 
      		 {
      				e.printStackTrace();
      		}
      		 finally{
      			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
      			 try{st.close();}catch(SQLException e){e.printStackTrace();}
      			 try{con.close();}catch(SQLException e){e.printStackTrace();}
      		 }
         	 return lstr;
    	}
    	public static List<String[]> gethuodongxiangce(String content){
    		Connection con = getConnection();
      		 Statement st = null;
      		 ResultSet rs = null;
      		 String sql = null;
      		 List<String[]> lstr=new ArrayList<String[]>();
      		 
      		 try 
      		 {
      			 st=con.createStatement();
      			 sql="select picture_id from activity_picture where activity_id='"+content+"';";
      			 rs=st.executeQuery(sql);
      			 while(rs.next())
      			 {
      				 String[] str=new String[1];
      				 str[0]=rs.getString(1);
      				 
      				 lstr.add(str); 
      			 }
      			 
      		 }catch(Exception e) 
      		 {
      				e.printStackTrace();
      		}
      		 finally{
      			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
      			 try{st.close();}catch(SQLException e){e.printStackTrace();}
      			 try{con.close();}catch(SQLException e){e.printStackTrace();}
      		 }
         	 return lstr;
    	}
    	public static List<String[]> getallchengyuan()
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="select user_id from users;";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1); 
    				 lstr.add(str);  
    			 }
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	 return lstr;
    	}
    	
    	public static void shanchuhaoyou(String content) {
    		
    		Connection con = getConnection();
    		Statement st = null;
    		try
    		{	String s[]=content.split("#");
    		
    			st = con.createStatement();
    			String sql;
    			sql="delete from friends where user_id ='"+s[0]+"'and friend_id='"+s[1]+"';";
    			st.executeUpdate(sql);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace() ;
    		}
    		finally
    		{
    			try{st.close();}catch(Exception e) {e.printStackTrace();}	
    			try{con.close();}catch(Exception e) {e.printStackTrace();}
    		}
    	}
    	public static List<String[]> getuseridandpassword(){
    		Connection con = getConnection();
      		 Statement st = null;
      		 ResultSet rs = null;
      		 String sql = null;
      		 List<String[]> lstr=new ArrayList<String[]>();
      		 try 
      		 {
      			 st=con.createStatement();
      			 sql="select user_id,userpassword from users;";
      			 rs=st.executeQuery(sql);
      			 while(rs.next())
      			 {
      				 String[] str=new String[2];
      				 str[0]=rs.getString(1);
      				 str[1]=rs.getString(2);
      				 lstr.add(str); 
      			 }
      		 }catch(Exception e) 
      		 {
      				e.printStackTrace();
      		}
      		 finally{
      			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
      			 try{st.close();}catch(SQLException e){e.printStackTrace();}
      			 try{con.close();}catch(SQLException e){e.printStackTrace();}
      		 }
         	 return lstr;
    	}
    	public static void updatauserpassword(String content)
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 String sql = null;
    		 String ss[]=new String[2];
    		 ss=content.split("#");
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="update users set userpassword='"+ss[0]+"'where user_id='"+ss[1]+"';";
    			
    			 st.executeUpdate(sql);	 
    			 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	}
    	public static List<String[]> getshetuannamebyguanli(){
    		 Connection con = getConnection();
     		 Statement st = null;
     		 ResultSet rs = null;
     		 String sql = null;
     		 List<String[]> lstr=new ArrayList<String[]>();
     		 try 
     		 {
     			 st=con.createStatement();
     			 sql="select community_name from community where community_id in "+
    			    "(select community_id from administrator );";
     			 rs=st.executeQuery(sql);
     			 while(rs.next())
     			 {
     				 String[] str=new String[1];
     				 str[0]=rs.getString(1);
     				 
     				 lstr.add(str); 
     			 }
     		 }catch(Exception e) 
     		 {
     				e.printStackTrace();
     		}
     		 finally{
     			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
     			 try{st.close();}catch(SQLException e){e.printStackTrace();}
     			 try{con.close();}catch(SQLException e){e.printStackTrace();}
     		 }
        	 return lstr;
    	}
    	public static List<String[]> getguanlipassword(){
    		 Connection con = getConnection();
     		 Statement st = null;
     		 ResultSet rs = null;
     		 String sql = null;
     		 List<String[]> lstr=new ArrayList<String[]>();
     		 try 
     		 {
     			 st=con.createStatement();
     			 sql="select password from administrator;";
     			 rs=st.executeQuery(sql);
     			 while(rs.next())
     			 {
     				 String[] str=new String[1];
     				 str[0]=rs.getString(1);
     				
     				 lstr.add(str); 
     			 }
     		 }catch(Exception e) 
     		 {
     				e.printStackTrace();
     		}
     		 finally{
     			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
     			 try{st.close();}catch(SQLException e){e.printStackTrace();}
     			 try{con.close();}catch(SQLException e){e.printStackTrace();}
     		 }
        	 return lstr;
    	}
    	public static String getguanlimax(){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		
    		try{
    			st=con.createStatement();
    			sql="select max(user_id) from administrator;";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static void updataguanlimima(String content)
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 String sql = null;
    		 String ss[]=new String[2];
    		 ss=content.split("#");
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="update administrator set password='"+ss[0]+"'where community_id='"+ss[1]+"';";
    			
    			 st.executeUpdate(sql);	 
    			 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	}
    	private static Object insertguanlimima=new Object();
    	public static void insertguanlimima2(String[] array) {
    		synchronized(insertguanlimima)
    		{
    		Connection con = getConnection();
    		Statement st = null;
    		
    		try
    		{	
    			st = con.createStatement();
    			String sql;
    			sql="insert into administrator values('"+array[0]+"','"+array[1]+"','"+array[2]+"');";
    			st.executeUpdate(sql);
    			
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace() ;
    		}
    		finally
    		{
    			try{st.close();}catch(Exception e) {e.printStackTrace();}	
    			try{con.close();}catch(Exception e) {e.printStackTrace();}
    		}
    		}
    	}
    	public static List<String[]> getalluserid(){
    		Connection con = getConnection();
    		 Statement st = null;
    		 ResultSet rs = null;
    		 String sql = null;
    		 List<String[]> lstr=new ArrayList<String[]>();
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="select user_id from users;";
    			 rs=st.executeQuery(sql);
    			 while(rs.next())
    			 {
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				
    				 lstr.add(str); 
    			 }
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
       	 return lstr;
    	}
    	public static String getuserpicture(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		
    		try{
    			st=con.createStatement();
    			sql="select userphoto from users where user_id='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static List<String[]> getusernamebyid(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select username from users where user_id='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static List<String[]> getfengjinshetuan(){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select community_name from community where community_stat='0';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static void updatashetuian(String content)
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 String sql = null;
    		 String stat="1";
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="update community set community_stat='"+stat+"'where community_name='"+content+"';";
    			
    			 st.executeUpdate(sql);	 
    			 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	}
    	public static List<String[]> getusermessagebynameandid(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String[] ss=new String[2];
    		ss=content.split("#");
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select user_id,userpassword from users where username='"+ss[0]+"'and user_id in(select user_id from community_users where community_id in(select community_id from community where community_name='"+ss[1]+"'));";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[2];
    				 str[0]=rs.getString(1);
    				 str[1]=rs.getString(2);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static String getuserstat(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		
    		try{
    			st=con.createStatement();
    			sql="select static from users where user_id='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static String getshetuanbymima(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		
    		try{
    			st=con.createStatement();
    			sql="select community_id from administrator where password='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static void ipdatauserstat(String content)
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 String sql = null;
    		 String stat="0";
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="update users set static='"+stat+"'where user_id='"+content+"';";
    			
    			 st.executeUpdate(sql);	 
    			 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	}
    	public static void ipdatauserstat2(String content)
    	{
    		Connection con = getConnection();
    		 Statement st = null;
    		 String sql = null;
    		 String stat="1";
    		 try 
    		 {
    			 st=con.createStatement();
    			 sql="update users set static='"+stat+"'where user_id='"+content+"';";
    			
    			 st.executeUpdate(sql);	 
    			 
    		 }catch(Exception e) 
    		 {
    				e.printStackTrace();
    		}
    		 finally{
    			 try{st.close();}catch(SQLException e){e.printStackTrace();}
    			 try{con.close();}catch(SQLException e){e.printStackTrace();}
    		 }
    	}
    	public static List<String[]> getallusername(){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select distinct username from users;";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[2];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static List<String[]> getuseridbyname(String username){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select user_id from users where username like '%"+username+"%';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static String getusercanjiashetuan(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		int count1=0;
    		String lstr=null;
    		try{
    			st=con.createStatement();
    			sql="select community_name from community where community_id in(select community_id from community_users where user_id='"+content+"');";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				if(count1==0)
   				 {
   					 lstr=rs.getString(1)+"、";
   				 }
   				 else{
   					 lstr+=rs.getString(1)+"、";
   				 }
   				 count1++;
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static List<String[]> getshetuanzhupicture(){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select community_id from administrator;";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static List<String[]> getshetuanpicturestat(){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select community_tubiao from community where community_stat='1';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static List<String[]> getshetuanpicturestat2(){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select community_tubiao from community where community_stat='0';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static String getuserzhuanye(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		String lstr=null;
    		try{
    			st=con.createStatement();
    			sql="select major from users where user_id='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static String getuserxueyuan(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		String lstr=null;
    		try{
    			st=con.createStatement();
    			sql="select xueyuan from users where user_id='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static String getusersex(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		String lstr=null;
    		try{
    			st=con.createStatement();
    			sql="select sex from users where user_id='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static List<String[]> gethuodongrenyuan(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select username from users where user_id in(select user_id from activity_user where activity_id='"+content+"');";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static List<String[]> gethuodongrenyuanID(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		List<String[]> lstr=new ArrayList<String[]>();
    		try{
    			st=con.createStatement();
    			sql="select user_id from users where user_id in(select user_id from activity_user where activity_id='"+content+"');";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				 String[] str=new String[1];
    				 str[0]=rs.getString(1);
    				 lstr.add(str); 
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return lstr;
    	}
    	public static String pdshetuanrenyuan(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		String lstr=null;
    		String[] ss=new String[2];
    		ss=content.split("#");
    		try{
    			st=con.createStatement();
    			sql="select count(*) from community_users where user_id='"+ss[0]+"' and community_id in(select community_id from activities where activity_id='"+ss[1]+"');";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	private static Object inserthuodongrenyuan=new Object();
    	public static void inserthuodongrenyuan2(String[] array) {
    		synchronized(inserthuodongrenyuan)
    		{
    		Connection con = getConnection();
    		Statement st = null;
    		try
    		{	
    			st = con.createStatement();
    			String sql;
    			sql="insert into activity_user values('"+array[1]+"','"+array[0]+"');";
    			st.executeUpdate(sql);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace() ;
    		}
    		finally
    		{
    			try{st.close();}catch(Exception e) {e.printStackTrace();}	
    			try{con.close();}catch(Exception e) {e.printStackTrace();}
    		}
    		}
    	}
    	public static String shifoubaoming(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		String lstr=null;
    		String[] ss=new String[2];
    		ss=content.split("#");
    		try{
    			st=con.createStatement();
    			sql="select count(*) from activity_user where user_id='"+ss[0]+"' and activity_id='"+ss[1]+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	public static String getactivityleixing(String content){
    		Connection con=getConnection();
    		Statement st=null;
    		ResultSet rs=null;
    		String sql=null;
    		String mess=null;
    		String lstr=null;
    		try{
    			st=con.createStatement();
    			sql="select leixing from activities where activity_id='"+content+"';";
    			rs=st.executeQuery(sql);
    			while(rs.next())
    			{
    				mess=rs.getString(1).toString();
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}finally
    		{
    			try{rs.close();}catch(SQLException e){e.printStackTrace();}
    			try{st.close();}catch(SQLException e){e.printStackTrace();}
    			try{con.close();}catch(SQLException e){e.printStackTrace();}
    		}
    		return mess;
    	}
    	
}
