package com.fun.test;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dtools.ini.IniUtilities;

import java.util.Scanner;

import com.fun.bean.NeiGong;
import com.fun.bean.NeiGong_static;
import com.fun.bean.NeiGong_varprop;
import com.fun.util.IniUtils;
import com.fun.util.ReadConfigurationFile;
import com.fun.util.SQLiteJDBC;

public class Test {

	private static Map<String, NeiGong> neigongMap = ReadConfigurationFile.getneigongMap();
	private static Map<String, NeiGong_static> neigongstaticMap = ReadConfigurationFile.getneigongstaticMap();
	private static Map<String, NeiGong_varprop> neigongvarpropMap = ReadConfigurationFile.getneigongvarpropMap();
	private static Map<String, Map<String, String>> wuxueMap = ReadConfigurationFile.getwuxueMap();
	
	public static void main(String[] args) {
		
		/*while(true) {
			System.out.println("****start get value*****");
			Scanner sc = new Scanner(System.in);
			sc.useDelimiter("\n");
			String name = sc.nextLine();
			String level = sc.nextLine();
			System.out.println("name:"+name+";level:"+level);
			String detail = getNeiGong(name, Integer.parseInt(level));
			System.out.println(detail);
		}*/
		//Map<String, String> map = ReadConfigurationFile.getpropMap();
		//System.out.println(map.get("1710"));
		/****************************/
		/*ReadConfigurationFile.init();
		InsertIntoDB.CreateTable();
		InsertIntoDB.InsertNeiGong(neigongMap);
		InsertIntoDB.InsertNeiGongstatic(neigongstaticMap);
		InsertIntoDB.InsertNeiGong_varprop(neigongvarpropMap);
		InsertIntoDB.Insertproppack(ReadConfigurationFile.getpropMap());
		InsertIntoDB.InsertWuXue(wuxueMap);
		*/
		Map<String, List<String>> map = IniUtils.read();
		/*for (String string : map.keySet()) {
			System.out.println(string);
		}*/
		List<String> list = map.get("51575");
		for (String string : list) {
			System.out.println(string);
		}
	}

	public static void getInfo() {
		Connection c = null;
		try {
			c = SQLiteJDBC.createConnection();
			
		
		
		}catch (Exception e) {

		}
	}
	public static String getNeiGong(String name, int level) {

		NeiGong ng = neigongMap.get(name);
		if (ng != null) {
			NeiGong_static ngs = neigongstaticMap.get(ng.getStaticData());
			if (ngs != null) {
				String min = ngs.getMinVarPropNo();
				String max = ngs.getMaxVarPropNo();
				for (String key : neigongvarpropMap.keySet()) {
					String[] keys = key.split("_");
					if (Integer.parseInt(keys[0]) >= Integer.parseInt(min)
							&& Integer.parseInt(keys[0]) <= Integer.parseInt(max)
							&& Integer.parseInt(keys[1]) == level) {
						NeiGong_varprop ngv = neigongvarpropMap.get(key);
						String faulty = wuxueMap.get(ng.getQName()).containsKey(level+"")?wuxueMap.get(ng.getQName()).get(level+""):"0";
						return ng.getName() + "\n臂力：" + ngv.getStrAdd() + "\n身法：" + ngv.getDexAdd() + "\n内息："
								+ ngv.getIngAdd() + "\n罡气：" + ngv.getSpiAdd() + "\n体魄：" + ngv.getStaAdd() + "\n气血上限："
								+ ngv.getHP() + "\n内力上限：" + ngv.getMP() + "\n招架耐力上限：" + ngv.getParry() + "\n内功防御："
								+ ngv.getMagicDef()+"\n内功突破还需["+faulty+"]修为\n总共消耗["+getAllFaulty( ng.getQName(), level)+"]修为";
					}
				}
			}
		}
		return "";
	}
	public void getNeiGongSchool() {
		for(Entry<String, NeiGong> ng:neigongMap.entrySet()) {
			ng.getValue().getSchool();
		}
	}
	public static String getAllFaulty(String name,int level) {
		long l = 0;
		for(int i=1;i<=level;i++) {
			l+=Integer.parseInt(wuxueMap.get(name).containsKey(i+"")?wuxueMap.get(name).get(i+""):"0"); 
			//System.out.println(wuxueMap.get(name).get(i+""));
		}
		
		return l+"";
	}
}
