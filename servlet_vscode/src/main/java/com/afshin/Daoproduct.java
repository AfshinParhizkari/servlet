package main.java.com.afshin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.mysql.cj.jdbc.Driver;

public class Daoproduct {

    public void Daoproduct()
    {
    }

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
            PreparedStatement statement=con.prepareStatement("INSERT INTO tbl_product (name, countryid_fk, count, price, createdate) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, myprd.getName());
            statement.setInt(2, myprd.getCountryid());
            statement.setInt(3, myprd.getCount());
            statement.setFloat(4, myprd.getPrice());
            java.sql.Date sqlStartDate = new java.sql.Date(myprd.getCreatedate().getTime());
            statement.setDate(5, sqlStartDate);

            status=statement.executeUpdate();
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;    
    }

    public Integer add(int x,int y)
    {
        return x+y;
    }
    
}
