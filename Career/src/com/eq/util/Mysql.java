package com.eq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Mysql {
    String sql;
    Statement stmt = null;
    ResultSet rs = null;
    public static Connection conn = null;
    int a;
    float x, y, z;

    public Mysql() {
    }

    public static Connection getMysqlConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/career?useUnicode=true&characterEncoding=UTF-8", "root",
                    "megaeq");
        }
        return conn;
    }
    
	public static Connection getPostgresConnection() throws Exception {
		if (conn == null || conn.isClosed()) {
			Class.forName("org.postgresql.Driver").newInstance();
			String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
			
			String user = "postgres";
			String password = "postgres";
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn;
	}
}
