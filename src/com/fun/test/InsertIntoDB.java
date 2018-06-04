package com.fun.test;

import java.sql.Connection;
import java.sql.Statement;

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
	/*public static void InsertNeiGong(Map<String, NeiGong> map) {
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
		
	}*/
	
}
