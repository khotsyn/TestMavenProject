package sqlite;

import java.sql.SQLException;

public class AccountsBD {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		MethodsBD.conn();
		MethodsBD.createDB();
		MethodsBD.writeDB();
		MethodsBD.readDB();
		MethodsBD.deleteDB();		
		MethodsBD.closeDB();
				
	}
}
