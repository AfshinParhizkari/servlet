package main.java.com.afshin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Daologin {

    public void Daologin()
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

    public Userpas getusr(String strusr)
    {
        
        Userpas up=new Userpas();
        try{
            Connection con= getConnection();
            PreparedStatement statement=con.prepareStatement("SELECT * FROM test01.tbl_auth WHERE usr=?");
            statement.setString(1,strusr);
            ResultSet rs = statement.executeQuery();
            if(!rs.first())
                up.setUsr("null");

            else
            {
                //rs.next();
                up.setUsr(rs.getString("usr"));
                up.setPws(rs.getString("pws"));
                up.setLocate(rs.getString("locate"));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return up;    
    }

    
}
