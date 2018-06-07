package com.fun.bean;

public class NeiGong {

	private String StrAdd;//臂力
	private String DexAdd;//身法
	private String IngAdd;//内息
	private String SpiAdd;//罡气
	private String StaAdd;//体魄
	private String StrAddE;//臂力
	private String DexAddE;//身法
	private String IngAddE;//内息
	private String SpiAddE;//罡气
	private String StaAddE;//体魄
	private String HP;//
	private String MP;//
	private String Parry;//
	private String MagicDef;//
	private String faculty;//内功突破所需修为
	private String LimitPercent;
	public String getLimitPercent() {
		return LimitPercent;
	}
	public void setLimitPercent(String limitPercent) {
		LimitPercent = limitPercent;
	}
	public String getStrAdd() {
		return StrAdd;
	}
	public void setStrAdd(String strAdd) {
		StrAdd = strAdd;
	}
	public String getDexAdd() {
		return DexAdd;
	}
	public void setDexAdd(String dexAdd) {
		DexAdd = dexAdd;
	}
	public String getIngAdd() {
		return IngAdd;
	}
	public void setIngAdd(String ingAdd) {
		IngAdd = ingAdd;
	}
	public String getSpiAdd() {
		return SpiAdd;
	}
	public void setSpiAdd(String spiAdd) {
		SpiAdd = spiAdd;
	}
	public String getStaAdd() {
		return StaAdd;
	}
	public void setStaAdd(String staAdd) {
		StaAdd = staAdd;
	}
	public String getStrAddE() {
		return getEadd(StrAdd, LimitPercent);
	}
	
	public String getDexAddE() {
		return getEadd(DexAdd, LimitPercent);
	}
	
	public String getIngAddE() {
		return getEadd(IngAdd, LimitPercent);
	}
	
	public String getSpiAddE() {
		return getEadd(SpiAdd, LimitPercent);
	}
	
	public String getStaAddE() {
		return getEadd(StaAdd, LimitPercent);
	}
	
	public String getHP() {
		return HP;
	}
	public void setHP(String hP) {
		HP = hP;
	}
	public String getMP() {
		return MP;
	}
	public void setMP(String mP) {
		MP = mP;
	}
	public String getParry() {
		return Parry;
	}
	public void setParry(String parry) {
		Parry = parry;
	}
	public String getMagicDef() {
		return MagicDef;
	}
	public void setMagicDef(String magicDef) {
		MagicDef = magicDef;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	private String getEadd(String add,String percent) {
		try {
			int nadd = (int)Math.floor(Integer.parseInt(add)*Integer.parseInt(percent)/100);
			return nadd+"";
		} catch (Exception e) {
		}
		return add;
	}
	@Override
	public String toString() {
		return getStrAdd()+";"+getDexAdd()+";"+getIngAdd()+";"+getSpiAdd()+";"+getStaAdd()+";\n"+
				getStrAddE()+";"+getDexAddE()+";"+getIngAddE()+";"+getSpiAddE()+";"+getStaAddE()+"\n"+
				getHP()+";"+getMP()+";"+getParry()+";"+getMagicDef()+"\n"+
				getFaculty();
	}
}
