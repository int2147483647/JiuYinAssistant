package com.fun.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fun.util.Constants;
import com.fun.util.IniUtils;

public class GetIniData {

	private static Map<String, List<String>> ngMap = new HashMap<>();
	private static Map<String, List<String>> ngsMap = new HashMap<>();
	private static Map<String, List<String>> ngvMap = new HashMap<>();
	private static Map<String, List<String>> ppMap = new HashMap<>();
	private static Map<String, List<String>> wxMap = new HashMap<>();
	public static void main(String[] args) {
		//getData(path+"proppack.ini", "GBK");
		getAllData();
	}
	
	public static void getAllData() {
		ngMap = IniUtils.read(Constants.NeiGongPath, Constants.FileEncoding);
		ngsMap = IniUtils.read(Constants.NeiGongstaticPath, Constants.FileEncoding);
		ngvMap = IniUtils.read(Constants.NeiGongvarpropPath, Constants.FileEncoding);
		ppMap = IniUtils.read(Constants.proppackPath, Constants.FileEncoding);
		wxMap = IniUtils.read(Constants.wxlevelPath, Constants.FileEncoding);
	}
	
	public static void getNeiGongInfo() {
		
	}
}
