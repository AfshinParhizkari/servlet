package main.java.com.afshin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Daoproduct {

    public void Daoproduct()
    {}

    public static Connection getConnection(){
        
        Connection con=null;
        try
            {
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01", "admin", "123456");
            }
        catch (Exception e){System.out.println(e);}
        return con;
    }

    public Integer saveproduct(Product myprd)
    {
        Integer status=0;
        try{
            Connection con= getConnection();
            PreparedStatement statement=con.prepareStatement("INSERT INTO product (name, brand, madein, price) VALUES (?, ?, ?, ?)");
            statement.setString(1, myprd.getName());
            statement.setString(2, myprd.getBrand());
            statement.setString(3, myprd.getMadein());
            statement.setInt(4, myprd.getPrice());

            status=statement.executeUpdate();
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;    
    }

    public Integer add(int x,int y)
    {
        // Some code to save product in mysql
    
        return x+y;
    }
    
}
