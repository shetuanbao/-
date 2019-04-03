package com.example.util;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class GetBitmap
{
	static Bitmap[] bitmap;
	public static Bitmap[] getBitmap(String[] picGroup,byte[][] bb)
	{
		bitmap=new Bitmap[picGroup.length];
		if(Environment.getExternalStorageState().equals(           //判断SD卡是否存在
	            android.os.Environment.MEDIA_MOUNTED))
		{
			String filePath=Environment.getExternalStorageDirectory().toString()+"/css";
			File file=new File(filePath);
			if(!file.exists())
			{
				file.mkdirs();
			}
			for(int i=0;i<picGroup.length;i++)
			{
				String picFilePath=Environment.getExternalStorageDirectory().toString()+"/css"+"/"+picGroup[i];
				file=new File(picFilePath);
				if(file.exists())       //如果存在这个文件
	    		{
	    			bitmap[i]=BitmapFactory.decodeFile(picFilePath);        //从SD卡读取图片数据
	    		}else 
	    		{
	    			bitmap[i]=BitmapFactory.decodeByteArray(bb[i], 0,bb[i].length);
	    			FileOutputStream fos=null;
	    	        file=new File(filePath,picGroup[i]);
	    	        try
	    	        {
	    	        	fos = new FileOutputStream(file);      //读到SD卡中
	    	        	if(fos!=null)
	    	        	{
	    	        		bitmap[i].compress(Bitmap.CompressFormat.PNG, 90, fos);
	    	        		fos.flush();
	    	        		fos.close();
	    	        	}
	    	        }
	    	        catch(Exception e)
	    	        {
	    	        	e.printStackTrace();
	    	        }
	    		}
			}
		}
		return bitmap;
	}
}
