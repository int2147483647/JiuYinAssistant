package com.fun.tool;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.fun.bean.NeiGong;
import com.fun.util.CommonUtils;
import com.fun.util.Constants;
import com.fun.util.IniUtils;

public class GetIniData {

	private static Map<String, List<String>> ngMap = new HashMap<>();
	private static Map<String, List<String>> ngsMap = new HashMap<>();
	private static Map<String, List<String>> ngvMap = new HashMap<>();
	private static Map<String, List<String>> ppMap = new HashMap<>();
	private static Map<String, List<String>> wxMap = new HashMap<>();
	private static Map<String, List<String>> buffMap = new HashMap<>();
	private static Map<String, List<String>> tipsMap = new HashMap<>();
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
		ngMap = IniUtils.read(Constants.NeiGongPath, Constants.GBK);
		ngsMap = IniUtils.read(Constants.NeiGongstaticPath, Constants.GBK);
		ngvMap = IniUtils.read(Constants.NeiGongvarpropPath, Constants.GBK);
		ppMap = IniUtils.read(Constants.proppackPath, Constants.GBK);
		wxMap = IniUtils.read(Constants.wxlevelPath, Constants.GBK);
		buffMap = IniUtils.read(Constants.buffPath, Constants.UTF8);
		tipsMap = IniUtils.read(Constants.tipsPath, Constants.UTF8);
	}
	
	public Map<String, String> getNeiGongList(){
		Map<String, String> map = new HashMap<>();
		Map<String, String> map2 = new TreeMap<>();
		for (Entry<String, List<String>> map1 : ngMap.entrySet()) {
			String newName = getByKey(buffMap.get(""),"buf_"+getByKey(map1.getValue(), "QName=")+"=");
			String oldName = getByKey(map1.getValue(), "Name=");
			if (!oldName.equals(newName)&&!newName.equals("")&&CommonUtils.isContainChinese(newName)) {
				map.put(newName+"("+oldName+")", getByKey(map1.getValue(), "QName="));
			}else {
				map.put(oldName, getByKey(map1.getValue(), "QName="));
			}
			map2 = sortMapByKey(map);
		}
		return map2;
	}
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>() {
        	
        	
        	@SuppressWarnings("rawtypes")
			Comparator cmp = com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE);
			@SuppressWarnings("unchecked")
			@Override
			public int compare(String o1, String o2) {
				return cmp.compare(o1, o2);
			}
        	
		});

        sortMap.putAll(map);

        return sortMap;
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
			//String MaxVarPropNo = getByKey(ngsMap.get(staticData),"MaxVarPropNo=");
			String MinVarPropNo = getByKey(ngsMap.get(staticData),"MinVarPropNo=");
			String Photo = getByKey(ngsMap.get(staticData),"Photo=");
			String school = getByKey(ngMap.get(nameid),"School=");
			int levelstr = Integer.parseInt(MinVarPropNo)+level-1;
			NeiGong  nGong = getBean(levelstr+"",nameid,level);
			nGong.setPhoto(Photo);
			String newName = getByKey(buffMap.get(""),"buf_"+nameid+"=");
			System.out.println(newName);
			switch (school) {
			case "ng_em":
				nGong.setSchool("峨眉");
				break;
			case "ng_gb":
				nGong.setSchool("丐帮");
				break;
			case "ng_jl":
				nGong.setSchool("极乐");
				break;
			case "ng_jy":
				nGong.setSchool("锦衣");
				break;
			case "ng_jz":
				nGong.setSchool("君子");
				break;
			case "ng_tm":
				nGong.setSchool("唐门");
				break;
			case "ng_sl":
				nGong.setSchool("少林");
				break;
			case "ng_wd":
				nGong.setSchool("武当");
				break;
			case "ng_mj":
				nGong.setSchool("明教");
				break;
			case "ng_ssg":
				nGong.setSchool("神水");
				break;
			case "ng_cf":
				nGong.setSchool("长风");
				break;
			case "ng_wx":
				nGong.setSchool("五仙");
				break;
			case "ng_xd":
				nGong.setSchool("血刀");
				break;
			case "ng_hs":
				nGong.setSchool("华山");
				break;
			case "ng_nl":
				nGong.setSchool("念萝");
				break;
			case "ng_dm":
				nGong.setSchool("达摩");
				break;
			case "ng_gm":
				nGong.setSchool("古墓");
				break;
			case "ng_sjy":
				nGong.setSchool("神机");
				break;
			case "ng_wg":
				nGong.setSchool("无根");
				break;
			case "ng_ws":
				nGong.setSchool("万兽");
				break;
			case "ng_xj":
				nGong.setSchool("徐家");
				break;
			case "ng_sj":
				nGong.setSchool("沈家");
				break;
			case "ng_yh":
				nGong.setSchool("移花");
				break;
			case "ng_th":
				nGong.setSchool("桃花");
				break;
			case "ng_jh":
				nGong.setSchool("江湖");
				break;
			case "ng_bh":
				nGong.setSchool("帮会");
				break;
			default:
				break;
			}
			//System.out.println(nGong);
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
		ng.setBuffId(getByKey(list,"BufferID="));
		ng.setBufferLevel(getByKey(list,"BufferLevel="));
		switch (getByKey(list,"@SkillModifyPackRec=")) {
		case "2894":
			ng.setShuxing("阴柔");
			break;
		case "2893":
			ng.setShuxing("阳刚");
			break;
		case "2895":
			ng.setShuxing("太极");
			break;
		default:
			break;
		}
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
		//System.out.println(tipsMap.get("").size());
		ng.setEffect(getByKey(tipsMap.get(""),"desc_"+ng.getBuffId()+"_"+ng.getBufferLevel()+"_ngtips="));
		return ng;
	}
}
