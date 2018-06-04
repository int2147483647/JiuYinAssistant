package com.fun.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;

import com.fun.bean.NeiGong;
import com.fun.bean.NeiGong_static;
import com.fun.bean.NeiGong_varprop;
import com.fun.util.SQLiteJDBC;

public class InsertIntoDB {

	public static void CreateTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			c = SQLiteJDBC.createConnection();
			stmt = c.createStatement();
			String sql1 = "CREATE TABLE NeiGong ( \r\n" + 
					"    SessionName VARCHAR,\r\n" + 
					"    ItemType    VARCHAR,\r\n" + 
					"    Name        VARCHAR,\r\n" + 
					"    QName       VARCHAR,\r\n" + 
					"    QTypeID     VARCHAR,\r\n" + 
					"    School      VARCHAR,\r\n" + 
					"    StaticData  VARCHAR,\r\n" + 
					"    script      VARCHAR \r\n" + 
					");";
			String sql2 = "CREATE TABLE NeiGong_static ( \r\n" + 
					"    SessionName   VARCHAR,\r\n" + 
					"    MaxVarPropNo  VARCHAR,\r\n" + 
					"    MinVarPropNo  VARCHAR,\r\n" + 
					"    Photo         VARCHAR \r\n" + 
					");\r\n" + 
					"";
			String sql3 = "CREATE TABLE NeiGong_varprop ( \r\n" + 
					"    SessionName  VARCHAR,\r\n" + 
					"    BufferID     VARCHAR,\r\n" + 
					"    BufferLevel  VARCHAR,\r\n" + 
					"    DexAdd       VARCHAR,\r\n" + 
					"    IngAdd       VARCHAR,\r\n" + 
					"    Level        VARCHAR,\r\n" + 
					"    LimitPercent VARCHAR,\r\n" + 
					"    NeiGongLevel VARCHAR,\r\n" + 
					"    SpiAdd       VARCHAR,\r\n" + 
					"    StaAdd       VARCHAR,\r\n" + 
					"    Stage        VARCHAR,\r\n" + 
					"    StrAdd       VARCHAR,\r\n" + 
					"    HP           VARCHAR,\r\n" + 
					"    MP           VARCHAR,\r\n" + 
					"    Parry        VARCHAR,\r\n" + 
					"    MagicDef     VARCHAR \r\n" + 
					");\r\n" + 
					"";
			String sql4 = "CREATE TABLE proppack ( \r\n" + 
					"    name  VARCHAR,\r\n" + 
					"    value VARCHAR \r\n" + 
					");\r\n" + 
					"";
			String sql5 = "CREATE TABLE WuXue_cost ( \r\n" + 
					"	name    VARCHAR,\r\n" + 
					"    level   VARCHAR,\r\n" + 
					"    faculty VARCHAR \r\n" + 
					");";
			stmt.executeUpdate("drop table if exists NeiGong;");
			stmt.executeUpdate("drop table if exists NeiGong_static;");
			stmt.executeUpdate("drop table if exists NeiGong_varprop;");
			stmt.executeUpdate("drop table if exists proppack;");
			stmt.executeUpdate("drop table if exists WuXue_cost;");
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql4);
			stmt.executeUpdate(sql5);
			stmt.close();
			c.close();
		}catch (Exception e) {

		}
	}
	public static void InsertNeiGong(Map<String, NeiGong> map) {
		Connection c = null;
		try {
			c = SQLiteJDBC.createConnection();
			c.setAutoCommit(false);
			String sql = "INSERT into NeiGong(SessionName,ItemType,Name,QName,QTypeID,School,StaticData,script) values(?,?,?,?,?,?,?,?)";
			PreparedStatement prest = c.prepareStatement(sql);
			for (Entry<String, NeiGong> ngentry : map.entrySet()) {
				NeiGong ng = ngentry.getValue();
				prest.setString(1, ng.getSessionName());
				prest.setString(2, ng.getItemType());
				prest.setString(3, ng.getName());
				prest.setString(4, ng.getQName());
				prest.setString(5, ng.getQTypeID());
				prest.setString(6, ng.getSchool());
				prest.setString(7, ng.getStaticData());
				prest.setString(8, ng.getScript());
				prest.addBatch();
			}
			prest.executeBatch();
			c.commit();
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void InsertNeiGongstatic(Map<String, NeiGong_static> map) {
		Connection c = null;
		try {
			c = SQLiteJDBC.createConnection();
			c.setAutoCommit(false);
			String sql = "INSERT into NeiGong_static(SessionName,MaxVarPropNo,MinVarPropNo,Photo) values(?,?,?,?)";
			PreparedStatement prest = c.prepareStatement(sql);
			for (Entry<String, NeiGong_static> ngentry : map.entrySet()) {
				NeiGong_static ng = ngentry.getValue();
				prest.setString(1, ng.getSessionName());
				prest.setString(2, ng.getMaxVarPropNo());
				prest.setString(3, ng.getMinVarPropNo());
				prest.setString(4, ng.getPhoto());
				prest.addBatch();
			}
			prest.executeBatch();
			c.commit();
			c.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void InsertNeiGong_varprop(Map<String, NeiGong_varprop> map) {
		Connection c = null;
		try {
			c = SQLiteJDBC.createConnection();
			c.setAutoCommit(false);
			String sql = "INSERT into NeiGong_varprop(SessionName,BufferID,BufferLevel,DexAdd,IngAdd,Level,LimitPercent,NeiGongLevel,SpiAdd,StaAdd,Stage,StrAdd,HP,MP,Parry,MagicDef) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prest = c.prepareStatement(sql);
			for (Entry<String, NeiGong_varprop> ngentry : map.entrySet()) {
				NeiGong_varprop ng = ngentry.getValue();
				prest.setString(1, ng.getSessionName());
				prest.setString(2, ng.getBufferID());
				prest.setString(3, ng.getBufferLevel());
				prest.setString(4, ng.getDexAdd());
				prest.setString(5, ng.getIngAdd());
				prest.setString(6, ng.getLevel());
				prest.setString(7, ng.getLimitPercent());
				prest.setString(8, ng.getNeiGongLevel());
				prest.setString(9, ng.getSpiAdd());
				prest.setString(10, ng.getStaAdd());
				prest.setString(11, ng.getStage());
				prest.setString(12, ng.getStrAdd());
				prest.setString(13, ng.getHP());
				prest.setString(14, ng.getMP());
				prest.setString(15, ng.getParry());
				prest.setString(16, ng.getMagicDef());
				prest.addBatch();
			}
			prest.executeBatch();
			c.commit();
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void Insertproppack(Map<String, String> map) {
		Connection c = null;
		try {
			c = SQLiteJDBC.createConnection();
			c.setAutoCommit(false);
			String sql = "INSERT into proppack(name,value) values(?,?)";
			PreparedStatement prest = c.prepareStatement(sql);
			for (Entry<String, String> ngentry : map.entrySet()) {
				prest.setString(1, ngentry.getKey());
				prest.setString(2, ngentry.getValue());
				prest.addBatch();
			}
			prest.executeBatch();
			c.commit();
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void InsertWuXue(Map<String, Map<String, String>> map) {
		Connection c = null;
		try {
			c = SQLiteJDBC.createConnection();
			c.setAutoCommit(false);
			String sql = "INSERT into WuXue_cost(name,level,faculty) values(?,?,?)";
			PreparedStatement prest = c.prepareStatement(sql);
			for (Entry<String, Map<String, String>> ngentry : map.entrySet()) {
				Map<String, String> map1 = ngentry.getValue();
				prest.setString(1, ngentry.getKey());
				for (String str : map1.keySet()) {
					prest.setString(2, str);
					prest.setString(3, map1.get(str));
					prest.addBatch();
				}
			}
			prest.executeBatch();
			c.commit();
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
