package com.example.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;


public class NetInfoUtil {
	public static Socket ss = null;
	public static DataInputStream din = null;
	public static DataOutputStream dos = null;
	public static String message = "";
	public static byte[] data;
	// 通讯建立
	public static void connect() throws Exception {
		ss = new Socket("10.16.189.195", 10009);
		din = new DataInputStream(ss.getInputStream());
		dos = new DataOutputStream(ss.getOutputStream());
	}

	// 通信关闭
	public static void disConnect() {
		if (dos != null) {
			try {
				dos.flush();
				dos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (din != null) {
			try {
				din.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (ss != null) {
			try {
				ss.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void zengjiashetuan(String content) {
		try {

			connect();
			dos.writeUTF(Constant.zengjiashetuan + MyConverter.escape(content));
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	public static String GetShetuanMaxid()
	{
		try {

			connect();
			dos.writeUTF(Constant.GetShetuanMax );
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static void insertpic(byte[] data,String info)
    {    	
    	try
   	    {
   		    connect();
   		    dos.writeUTF(Constant.InsertPic+MyConverter.escape(info));
    		dos.writeInt(data.length);
    		dos.write(data);
    		dos.flush();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		disConnect();
    	}
    }
	public static byte[] getPicture(String picname)
	{
		try{
			connect();
			dos.writeUTF(Constant.GetOnePicture+MyConverter.escape(picname));
			data=IOUtil.readBytes(din);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
		return data;
	}
	public static List<String[]> getallshetuan(){
		try{
			connect();
			dos.writeUTF(Constant.GetAllShetuan);
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static void deleteshetuan(String content)
	{
		try{
			connect();
			dos.writeUTF(Constant.DeleteShetuan+MyConverter.escape(content));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
	}
	public static List<String[]> getshetuanid(){
		try{
			connect();
			dos.writeUTF(Constant.GetAllShetuanId);
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static List<String[]> getshetuanmessagebyid(String content)
	{
		try{
			connect();
			dos.writeUTF(Constant.GetShetuanMessageById+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static void insertshetuanmessage(String content)
	{
		try {
			connect();
			dos.writeUTF(Constant.InsertShetuanMessage + MyConverter.escape(content));
			//message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	public static String login(String content[])
	{
		try {

			connect();
			dos.writeUTF(Constant.Persident +MyConverter.escape(StrListChange.ArrayToStrAndroid(content)));
			message = din.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static List<String[]> getyijianmessage(){
		try{
			connect();
			dos.writeUTF(Constant.GetYiJianMessage);
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static List<String[]> getuseridandpassword(){
		try{
			connect();
			dos.writeUTF(Constant.GET_USERIDANDPASSWORD);
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static void updatauserpassword(String content)
	{
		try{
			connect();
			dos.writeUTF(Constant.UPDATA_USER_PASSWORD+MyConverter.escape(content));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
	}
	public static List<String[]> getshetuanidbyname(String content){
		try{
			connect();
			dos.writeUTF(Constant.GetShetuanIdByName+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static List<String[]> getshetuannamebyguanli(){
		try{
			connect();
			dos.writeUTF(Constant.GET_SHETUANNAME_BY_ID);
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static List<String[]> getguanlipassword(){
		try{
			connect();
			dos.writeUTF(Constant.GET_GUANLI_MIMA);
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static String getguanlimax(){
		try{
			connect();
			dos.writeUTF(Constant.GET_GUANLIMAXID);
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static void updataguanlimima(String content)
	{
		try{
			connect();
			dos.writeUTF(Constant.UPDATA_GUANLIMIMA+MyConverter.escape(content));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
	}
	public static void insertguanlimima2(String content){
		try{
			connect();
			dos.writeUTF(Constant.INERT_GUANLI_MIMA+MyConverter.escape(content));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
	}
	public static List<String[]> getfengjinshetuan(){
		try{
			connect();
			dos.writeUTF(Constant.GET_FENGJINSHETUAN);
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static void updatajiechushetuan(String content){
		try{
			connect();
			dos.writeUTF(Constant.UPDATA_JIECHUSHETUAN+MyConverter.escape(content));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
	}
	public static List<String[]> getuseridandmima(String content){
		try{
			connect();
			dos.writeUTF(Constant.GETUSERIDANDMM+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static String getuserstatic(String content){
		try{
			connect();
			dos.writeUTF(Constant.GET_USERSTATIC+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return message;
	}
	public static void updatauserstat(String content){
		try{
			connect();
			dos.writeUTF(Constant.UPDATA_USERFENG+MyConverter.escape(content));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
	}
	public static void updatauserstat2(String content){
		try{
			connect();
			dos.writeUTF(Constant.UPDATA_USERJIE+MyConverter.escape(content));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			disConnect();
		}
	}
	public static String[] getallusername(){
		try{
			connect();
			dos.writeUTF(Constant.GET_USERNAME);
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToArray(MyConverter.unescape(message));
	}
	public static String getuserps(String content){
		try{
			connect();
			dos.writeUTF(Constant.GET_USERPS+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return message;
	}
	public static List<String[]> getuseridbyname(String content){
		try{
			connect();
			dos.writeUTF(Constant.GET_USERIDBYNAME+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static String getusershetuan(String content){
		try{
			connect();
			dos.writeUTF(Constant.GET_SUERSHETUAN+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static String getuserphoto(String content){
		try{
			connect();
			dos.writeUTF(Constant.GetUserOnePhoto+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static String getusername(String content){
		try{
			connect();
			dos.writeUTF(Constant.GetUserName+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static List<String[]> getuserpicture(){
		try{
			connect();
			dos.writeUTF(Constant.GET_SHETUANZHUPICTURE);
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static List<String[]> getshetuanpicture(){
		try{
			connect();
			dos.writeUTF(Constant.GET_SHETUANPCS);
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static List<String[]> getshetuanpicture2(){
		try{
			connect();
			dos.writeUTF(Constant.GET_SHETUANPCST);
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return StrListChange.StrToList(MyConverter.unescape(message));
	}
	public static String getuserzhuanye(String content){
		try{
			connect();
			dos.writeUTF(Constant.GET_USERZHUANYE+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static String getuserxueyuan(String content){
		try{
			connect();
			dos.writeUTF(Constant.GET_XUEYUAN+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return MyConverter.unescape(message);
	}
	public static String getusersex(String content){
		try{
			connect();
			dos.writeUTF(Constant.GET_USERSEX+MyConverter.escape(content));
			message = din.readUTF();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			disConnect();
		}
		return MyConverter.unescape(message);
	}
}