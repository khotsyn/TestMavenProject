package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MethodsBD {
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;

	public static void conn() throws ClassNotFoundException, SQLException {
		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:AccountsTUT.s3db");
		System.out.println("The base is connected!");
	}
	
	public static void createDB() throws ClassNotFoundException, SQLException {
		statmt = conn.createStatement();		
		statmt.execute(
				"CREATE TABLE if not exists 'AccountsTUT' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'email' text, 'pass' text);");

		System.out.println("Table created or already exists.");
	}
	
	public static void deleteDB() throws ClassNotFoundException, SQLException {		
		statmt.executeUpdate("DROP TABLE AccountsTUT");		
	}
	
	public static void writeDB() throws SQLException {
		
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('johnxotsin@tut.by', 'johnxotsin1'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('johnxotsin2@tut.by', 'johnxotsin21'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('johnxotsin3@tut.by', 'johnxotsin31'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('johnxotsin4@tut.by', 'johnxotsin41'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('johnxotsin5@tut.by', 'johnxotsin51'); ");
		
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('alexwite@tut.by', 'alexwite1'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('alexwite2@tut.by', 'alexwite21'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('alexwite3@tut.by', 'alexwite31'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('alexwite4@tut.by', 'alexwite41'); ");
		statmt.execute("INSERT INTO 'AccountsTUT' ('email', 'pass') VALUES ('alexwite5@tut.by', 'alexwite51'); ");
		
		System.out.println("The table is completed");
	}
	
	public static void readDB() throws ClassNotFoundException, SQLException {
		resSet = statmt.executeQuery("SELECT * FROM AccountsTUT");

		while (resSet.next()) {
			int id = resSet.getInt("id");
			String email = resSet.getString("email");
			String password = resSet.getString("pass");
			System.out.println(id + " " + email + " " + password);
			System.out.println();
		}
		
		System.out.println("The table is output");
	}
	public static void closeDB() throws ClassNotFoundException, SQLException {
		conn.close();
		statmt.close();
		resSet.close();

		System.out.println("Connections are closed");
	}

}
