package com.bn.Server;

import java.net.*;

public class ServerThread extends Thread{
      ServerSocket ss;                                   //创建ServerSocket
      @Override
      public void run()                                  //重写run方法
      {
    	  try{
    		  ss=new ServerSocket(10009);                 //启动服务器，并且监听10006
    		  System.out.println("Listen on 10009....");  //标示10006已监听
    		  while(true)
    		  {			 
    			  Socket sc=ss.accept();  			      //等待客户端 连接10006端口
    			  new ServerAgentThread(sc).start();      //创建新的线程并启动
    		  }
    	  }catch(Exception e)
    	  {
    		  e.printStackTrace();                        //打印异常信息
    	  }
      }
     
      public static void main(String args[])
      {
    	  new ServerThread().start();                      //启动新线程
      }
}
