package com.fun.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
* <p>Title: IniUtils.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>   
* @author liyy  
* @date 2018年6月4日  
* @version 1.0
 */
public class IniUtils {


	public static Map<String, List<String>> read(String path, String code) {
		//long t1 = System.nanoTime();
		String str = "";
		BufferedReader bufferedReader = null;
		Map<String, List<String>> listResult = new HashMap<>();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), code));
			String currentSection = "";
			List<String> currentProperties = new ArrayList<>();
			while ((str = bufferedReader.readLine()) != null) {
				str = str.trim();
				if ("".equals(str) || str == null) {
					continue;
				}
				// 是否一个新section开始了
				if (str.startsWith("[") && str.endsWith("]")) {
					String newSection = str.substring(1, str.length() - 1).trim();

					// 如果新section不是现在的section，则把当前section存进listResult中
					if (!currentSection.equals(newSection)) {
						if (!currentSection.equals("")||currentProperties.size()!=0) {
							listResult.put(currentSection, currentProperties);
						}
						currentSection = newSection;
						// 新section是否重复的section
						// 如果是，则使用原来的list来存放properties
						// 如果不是，则new一个List来存放properties
						currentProperties = listResult.get(currentSection);
						if (currentProperties == null) {
							currentProperties = new ArrayList<>();
						}
					}
				} else {
					currentProperties.add(str);
				}
			}
			listResult.put(currentSection, currentProperties);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//long t2 = System.nanoTime();
		//System.out.println(t2-t1);
		return listResult;
	}
}
