package main.java.com.anbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Prodao {
	
	String jdbcURL;
	String jdbcUsername;
	String jdbcPassword;
	Connection jdbcConnection;	

	public boolean savetodb (Product mypro) throws SQLException
	{

		String sql = "INSERT INTO product (name, brand, madein, price) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, mypro.getName());
        statement.setString(2, mypro.getBrand());
        statement.setString(3, mypro.getMadein());
        statement.setInt(4, mypro.getPrice());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;		
		
	}
	

	protected void connect() throws SQLException 
	{
		if (jdbcConnection == null || jdbcConnection.isClosed())
		{
            try {Class.forName("com.mysql.jdbc.Driver");}
            catch (ClassNotFoundException e) {throw new SQLException(e);}
		jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01", "admin", "123456");
		}
	}
	protected void disconnect() throws SQLException {
	     if (jdbcConnection != null && !jdbcConnection.isClosed()) {
	            jdbcConnection.close();
	     }
	    }
	
}
