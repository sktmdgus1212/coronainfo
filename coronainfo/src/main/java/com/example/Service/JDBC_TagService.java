package com.example.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JDBC_TagService {
	 private DB_Info db_info;
	 
		public JDBC_TagService(DB_Info db_info) {
			this.db_info = db_info;
		}

		public JSONObject get_tag_list(String tag) throws ClassNotFoundException, SQLException {
			ArrayList<String> list = new  ArrayList<>();
			String sql = "SELECT TAG_NAME FROM TAG WHERE TAG_NAME='"+tag+"'";
			Connection con = DriverManager.getConnection(db_info.getUrl(), db_info.getUid(), db_info.getPwd());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String tag_name;
			while (rs.next()) {
				tag_name = rs.getString("TAG_NAME");
				list.add(tag_name);
			}
			JSONObject json = new JSONObject();
			
			for(int i = 0 ; i < list.size() ; i++) {
				json.put(i, list.get(i));
			}
			rs.close();
			st.close();
			con.close();

			return json;
		}
}
