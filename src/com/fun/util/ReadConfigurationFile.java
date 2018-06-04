package com.fun.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dtools.ini.BasicIniFile;
import org.dtools.ini.IniFile;
import org.dtools.ini.IniSection;

import com.fun.bean.NeiGong;
import com.fun.bean.NeiGong_static;
import com.fun.bean.NeiGong_varprop;
import com.fun.bean.WuXue_cost;

public class ReadConfigurationFile {
	
	private static final String NeiGong_Path = "E:\\out\\share.package.files\\res\\share\\skill\\neigong\\neigong.ini";
	private static final String NeiGong_static_Path = "E:\\out\\share.package.files\\res\\share\\skill\\neigong\\neigong_static.ini";
	private static final String NeiGong_varprop_Path = "E:\\out\\share.package.files\\res\\share\\skill\\neigong\\neigong_varprop.ini";
	private static final String proppack_Path = "E:\\out\\share.package.files\\res\\share\\modifypack\\proppack.ini";
	private static final String wuxuelevelinfo_Path = "E:\\out\\share.package.files\\res\\share\\faculty\\wuxuelevelinfo.ini";
	private static Map<String, NeiGong> neigongMap = new HashMap<>();
	private static Map<String, NeiGong_static> neigongstaticMap = new HashMap<>();
	private static Map<String, NeiGong_varprop> neigongvarpropMap = new HashMap<>();
	private static Map<String, String> propMap = new HashMap<>();
	private static Map<String, Map<String, String>> wuxueMap = new HashMap<>();
	public static Map<String, NeiGong> getneigongMap(){
		return neigongMap;
	}
	public static Map<String, NeiGong_static> getneigongstaticMap(){
		return neigongstaticMap;
	}
	public static Map<String, NeiGong_varprop> getneigongvarpropMap(){
		return neigongvarpropMap;
	}
	public static Map<String, String> getpropMap(){
		return propMap;
	}
	public static Map<String, Map<String, String>> getwuxueMap(){
		return wuxueMap;
	}
	
	static {
		
	}
	
	public static void init() {
		System.out.println("******start loading******");
		
		long t1 = getNeiGong();
		long t2 = getNeiGong_static();
		long t3 = getProppack();
		long t4 = getNeiGong_varprop();
		long t5 = getwuxuelevelinfo();
		
		System.out.println("******loading all completed******");
	}
	public static List<String> getNeiGongSchool() {
		List<String> list = new ArrayList<>(); 
		for(Entry<String, NeiGong> ng:neigongMap.entrySet()) {
			list.add(ng.getValue().getSchool());
		}
		return list;
	}
	private static long getNeiGong() {
		long t1 = System.currentTimeMillis();
		IniFile iniFile=new BasicIniFile();  
        File file=new File(NeiGong_Path);
        ReadBuff rad=new ReadBuff(iniFile, file);  
        try {  
            rad.read("GBK");  
            Collection<IniSection> iniSections=iniFile.getSections();
            for(IniSection iniSection:iniSections) {
            	String sessionname = iniSection.getName();
            	String ItemType = iniSection.getItem("ItemType").getValue();
            	String Name = iniSection.getItem("Name").getValue();
            	String QName = iniSection.getItem("QName").getValue();
            	String QTypeID = iniSection.getItem("QTypeID").getValue();
            	String School = iniSection.getItem("School").getValue();
            	String StaticData = iniSection.getItem("StaticData").getValue();
            	String script = iniSection.getItem("script").getValue();
            	NeiGong ng = new NeiGong();
            	ng.setSessionName(sessionname);
            	ng.setItemType(ItemType);
            	ng.setName(Name);
            	ng.setQName(QName);
            	ng.setQTypeID(QTypeID);
            	ng.setSchool(School);
            	ng.setStaticData(StaticData);
            	ng.setScript(script);
            	neigongMap.put(Name, ng);
            }
         } catch (IOException e) {
        	 e.printStackTrace();
         }
        long t2 = System.currentTimeMillis();
        return t2-t1;
	}
	private static long getNeiGong_static() {
		long t1 = System.currentTimeMillis();
		IniFile iniFile=new BasicIniFile();  
        File file=new File(NeiGong_static_Path);
        ReadBuff rad=new ReadBuff(iniFile, file);  
        try {  
            rad.read("GBK");  
            Collection<IniSection> iniSections=iniFile.getSections();
            for(IniSection iniSection:iniSections) {
            	String name = iniSection.getName();
            	String MaxVarPropNo = iniSection.getItem("MaxVarPropNo").getValue();
            	String MinVarPropNo = iniSection.getItem("MinVarPropNo").getValue();
            	String Photo = iniSection.getItem("Photo").getValue();
            	NeiGong_static ngs = new NeiGong_static();
            	ngs.setMaxVarPropNo(MaxVarPropNo);
            	ngs.setMinVarPropNo(MinVarPropNo);
            	ngs.setPhoto(Photo);
            	ngs.setSessionName(name);
            	neigongstaticMap.put(name, ngs);
            }
         } catch (IOException e) {
        	 e.printStackTrace();
         }
        long t2 = System.currentTimeMillis();
        return t2-t1;
	}
	private static long getProppack() {
		long t1 = System.currentTimeMillis();
		IniFile iniFile=new BasicIniFile();  
        File file=new File(proppack_Path);
        ReadBuff rad=new ReadBuff(iniFile, file);  
        try {  
            rad.read("GBK");  
            Collection<IniSection> iniSections=iniFile.getSections();
            for(IniSection iniSection:iniSections) {
            	String name = iniSection.getName();
            	String r = getValue(iniSection,"r");
            	propMap.put(name, r);
            }
         } catch (IOException e) {
        	 e.printStackTrace();
         }
        long t2 = System.currentTimeMillis();
        return t2-t1;
	}
	private static long getNeiGong_varprop() {
		long t1 = System.currentTimeMillis();
		IniFile iniFile=new BasicIniFile();  
        File file=new File(NeiGong_varprop_Path);
        ReadBuff rad=new ReadBuff(iniFile, file);  
        try {  
            rad.read("GBK");  
            Collection<IniSection> iniSections=iniFile.getSections();
            for(IniSection iniSection:iniSections) {
            	String name = iniSection.getName();
            	String BufferID = getValue(iniSection,"BufferID");
            	String BufferLevel = getValue(iniSection,"BufferLevel");
            	String DexAdd = getValue(iniSection,"DexAdd");
            	String IngAdd = getValue(iniSection,"IngAdd");
            	String Level = getValue(iniSection,"Level");
            	String LimitPercent = getValue(iniSection,"LimitPercent");
            	String NeiGongLevel = getValue(iniSection,"NeiGongLevel");
            	String SpiAdd = getValue(iniSection,"SpiAdd");
            	String StaAdd = getValue(iniSection,"StaAdd");
            	String Stage = getValue(iniSection,"Stage");
            	String StrAdd = getValue(iniSection,"StrAdd");
            	String PropModifyPackRec = getValue(iniSection,"@PropModifyPackRec");
            	
            	int i = 0;
            	NeiGong_varprop ngv = new NeiGong_varprop();
            	ngv.setSessionName(name);
            	ngv.setBufferID(BufferID);
            	ngv.setBufferLevel(BufferLevel);
            	ngv.setDexAdd(DexAdd);
            	ngv.setIngAdd(IngAdd);
            	ngv.setLevel(Level);
            	ngv.setLimitPercent(LimitPercent);
            	ngv.setNeiGongLevel(NeiGongLevel);
            	ngv.setSpiAdd(SpiAdd);
            	ngv.setStaAdd(StaAdd);
            	ngv.setStage(Stage);
            	ngv.setStrAdd(StrAdd);
            	try {
            		if (!"".equals(PropModifyPackRec)) {
            			String[] arrayss =propMap.get(PropModifyPackRec).split(",");
    					switch (arrayss[1]) {
    					case "MaxHPAdd":
    						ngv.setHP(arrayss[2]);
    						break;
    					case "MaxMPAdd":
    						ngv.setMP(arrayss[2]);
    						break;
    					case "MaxParryAdd":
    						ngv.setParry(arrayss[2]);
    						break;
    					case "MinMagicDefAdd":
    						ngv.setMagicDef(arrayss[2]);
    						break;
    					default:
    						break;
    					}
					}
				} catch (Exception e) {
					System.out.println("error:"+propMap.get(PropModifyPackRec)+">>>>"+PropModifyPackRec);
				}
            	while(iniSection.getItem("@PropModifyPackRec"+i)!=null) {
            		
            		String PropModifyPackRectemp = iniSection.getItem("@PropModifyPackRec"+i).getValue();
            		String[] arrays =propMap.get(PropModifyPackRectemp).split(",");
            		switch (arrays[1]) {
					case "MaxHPAdd":
						ngv.setHP(arrays[2]);
						break;
					case "MaxMPAdd":
						ngv.setMP(arrays[2]);
						break;
					case "MaxParryAdd":
						ngv.setParry(arrays[2]);
						break;
					case "MinMagicDefAdd":
						ngv.setMagicDef(arrays[2]);
						break;
					default:
						break;
					}
            		i++;
            	}
            	neigongvarpropMap.put(name+"_"+ngv.getLevel(), ngv);
            }
         } catch (IOException e) {
        	 e.printStackTrace();
         }
        long t2 = System.currentTimeMillis();
        return t2-t1;
	}
	private static long getwuxuelevelinfo() {
		long t1 = System.currentTimeMillis();
		IniFile iniFile=new BasicIniFile();  
        File file=new File(wuxuelevelinfo_Path);
        ReadBuff rad=new ReadBuff(iniFile, file);  
        try {  
            rad.read("GBK");  
            Collection<IniSection> iniSections=iniFile.getSections();
            for(IniSection iniSection:iniSections) {
            	String name = iniSection.getName();
            	String r = getValue(iniSection,"r");
            	String[] value = r.split(",");
            	Map<String, String> map = new HashMap<>();
            	map.put(value[0], value[1]);
            	int i = 0;
            	while(iniSection.getItem("r"+i)!=null) {
            		String[] rvalue = iniSection.getItem("r"+i).getValue().split(",");;
                	map.put(rvalue[0], rvalue[1]);
            		i++;
            	}
            	wuxueMap.put(name, map);
            }
         } catch (IOException e) {
        	 e.printStackTrace();
         }
        long t2 = System.currentTimeMillis();
        return t2-t1;
	}
	
	public static String getValue(IniSection iniSection,String name) {
		String value = "";
		if(iniSection.getItem(name)!=null) {
			value = iniSection.getItem(name).getValue();
		}
		return value;
	}
}
