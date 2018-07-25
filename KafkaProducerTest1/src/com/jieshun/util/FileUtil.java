package com.jieshun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileUtil {
	private static List<String> cars=new ArrayList<String>();
	
	//类加载时，车牌信息初始化到缓存
	static {
		readTxtFile(System.getProperty("user.dir")+File.separator+"data"+File.separator+"carNumbers.txt");
	}
		
	
	private static void readTxtFile(String path) {
		// TODO Auto-generated method stub
		
		try{
			File file = new File(path);
			if(file.isFile() && file.exists()){
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = null ;
				while((lineTxt = br.readLine()) != null){
					cars.add(lineTxt);
				}
				br.close();
			}else{
				System.out.println("文件不存在。");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("文件读取错误。");
		}
		
	}
	
	  //get carNumber from file
	  public static String getCarNumber(){
		  Random random = new Random();
		  
		  int num = random.nextInt(cars.size());
		  
		  return cars.get(num);
		  
	  }
	  public static void main(String[] args) {
		while (true)
		{
			  System.out.println(FileUtil.getCarNumber());
		}
	}
}
