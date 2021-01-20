package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;


public class Connecteur {
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
	private static final String DBNAME = "covid";

	public static Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver introuvable !!! ");
			e.printStackTrace();
		}
		try {
			c = DriverManager.getConnection(URL + DBNAME, LOGIN, PASSWORD);			
		} catch (SQLException e) {
			System.out.println("Connexion impossible!!! ");
			e.printStackTrace();
		}
		System.out.println("!connexion établie !!!");
		return c;
	}

}
