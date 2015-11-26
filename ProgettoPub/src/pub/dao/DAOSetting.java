package pub.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOSetting {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String USER = "server";
	static final String PASS = "server";
	static final String DB_URL = "jdbc:mysql://localhost:3306/IngDelSw";
	
	static{
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public static Statement getStatement() throws SQLException{
		return DriverManager.getConnection(DB_URL, USER, PASS).createStatement();
	}

	public static void closeStatement(Statement stat) throws SQLException{
		stat.getConnection().close();
		stat.close();
	}
	
}

