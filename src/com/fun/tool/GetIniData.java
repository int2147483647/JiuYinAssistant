package com.fun.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fun.bean.NeiGong;
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
		GetIniData getIniData = new GetIniData();
		
		getIniData.getAllData();
		long t1 = System.nanoTime();
		getIniData.getNeiGongInfo("ng_wx_002", 1);
		long t2 = System.nanoTime();
		System.out.println(t2-t1);
	}
	
	public void getAllData() {
		ngMap = IniUtils.read(Constants.NeiGongPath, Constants.FileEncoding);
		ngsMap = IniUtils.read(Constants.NeiGongstaticPath, Constants.FileEncoding);
		ngvMap = IniUtils.read(Constants.NeiGongvarpropPath, Constants.FileEncoding);
		ppMap = IniUtils.read(Constants.proppackPath, Constants.FileEncoding);
		wxMap = IniUtils.read(Constants.wxlevelPath, Constants.FileEncoding);
	}
	
	public Map<String, String> getNeiGongList(){
		Map<String, String> map = new HashMap<>();
		for (Entry<String, List<String>> map1 : ngMap.entrySet()) {
			map.put(getByKey(map1.getValue(), "Name="), getByKey(map1.getValue(), "QName="));
		}
		return map;
	}
	public int getMaxLevel(String nameid) {
		int level = 0;
		String staticData = getByKey(ngMap.get(nameid),"StaticData=");
		String MaxVarPropNo = getByKey(ngsMap.get(staticData),"MaxVarPropNo=");
		String MinVarPropNo = getByKey(ngsMap.get(staticData),"MinVarPropNo=");
		try {
			level = Integer.parseInt(MaxVarPropNo)-Integer.parseInt(MinVarPropNo)+1;
		} catch (NumberFormatException e) {
		}
		return level;
	}
	public NeiGong getNeiGongInfo(String nameid ,int level) {
			String staticData = getByKey(ngMap.get(nameid),"StaticData=");
			String MaxVarPropNo = getByKey(ngsMap.get(staticData),"MaxVarPropNo=");
			String MinVarPropNo = getByKey(ngsMap.get(staticData),"MinVarPropNo=");
			String Photo = getByKey(ngsMap.get(staticData),"Photo=");
			int levelstr = Integer.parseInt(MinVarPropNo)+level-1;
			NeiGong  nGong = getBean(levelstr+"",nameid,level);
			System.out.println(nGong);
			return nGong;
	}
	public String getByKey(List<String> list,String key) {
		if (list==null||list.size()==0) {
			return "";
		}
		for (String string : list) {
			if (string.contains(key)) {
				return string.substring(string.indexOf(key)+key.length(), string.length());
			}
		}
		return "";
	}
	public String getByKey1(List<String> list,String key,String key2) {
		if (list==null||list.size()==0) {
			return "";
		}
		for (String string : list) {
			if (string.contains(key)) {
				String[] strings = string.substring(string.indexOf(key)+key.length(), string.length()).split(",");
				if (strings[1].equals(key2)) {
					return strings[2];
				}
			}
		}
		return "";
	}
	public List<String> getPropModifyPackRec(List<String> list){
		List<String> l = new ArrayList<>();
		if (list==null||list.size()==0) {
			return l;
		}
		for (String string : list) {
			if (string.contains("PropModifyPackRec")) {
				l.add(string.substring(string.indexOf("=")+1, string.length()));
			}
		}
		return l;
	}
	public NeiGong getBean(String level,String nameid,int l) {
		List<String> list = ngvMap.get(level);
		NeiGong ng = new NeiGong();
		ng.setStrAdd(getByKey(list,"StrAdd="));
		ng.setDexAdd(getByKey(list,"DexAdd="));
		ng.setIngAdd(getByKey(list,"IngAdd="));
		ng.setSpiAdd(getByKey(list,"SpiAdd="));
		ng.setStaAdd(getByKey(list,"StaAdd="));
		ng.setLimitPercent(getByKey(list,"LimitPercent="));
		List<String> list1 = getPropModifyPackRec(list);
		for (String string : list1) {
			String hp = getByKey1(ppMap.get(string),"r=","MaxHPAdd");
			if (!hp.equals("")) {
				ng.setHP(hp);
			}
			String mp = getByKey1(ppMap.get(string),"r=","MaxMPAdd");
			if (!mp.equals("")) {
				ng.setMP(mp);
			}
			String parry = getByKey1(ppMap.get(string),"r=","MaxParryAdd");
			if (!parry.equals("")) {
				ng.setParry(parry);
			}
			String magicdef = getByKey1(ppMap.get(string),"r=","MinMagicDefAdd");
			if (!magicdef.equals("")) {
				ng.setMagicDef(magicdef);
			}
		}
		try {
			ng.setFaculty(wxMap.get(nameid).size()==l-1?"0":wxMap.get(nameid).get(l-1).split(",")[1]);
		} catch (Exception e) {
			ng.setFaculty("0");
		}
		return ng;
	}
}
