package main.java.com.afshin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

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

	public ResultSet findallproduct(String whereclause) {
        ResultSet rs = null;
        try{
            Connection con= getConnection();
            PreparedStatement statement=con.prepareStatement("select * from tbl_product "+whereclause);
            rs = statement.executeQuery();
        }catch(Exception ex){ex.printStackTrace();}
        return rs;    
	}
    
    public Integer deleteproduct(String myprd)
    {
        Integer affectedrow=0;
        try{
            Connection con= getConnection();
            PreparedStatement statement=con.prepareStatement("delete from tbl_product where id = ?");
            statement.setInt(1, Integer.parseInt(myprd));
            System.out.println(statement.toString());
            affectedrow=statement.executeUpdate();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.toString());
        }

        return affectedrow;    
    }

    public Product findbyid(String id)
    {
        
        Product pro =new Product();
        try{
            Connection con= getConnection();
            PreparedStatement statement=con.prepareStatement("select * from tbl_product where id =?");
            statement.setInt(1,Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();
            if(!rs.first())
                return null;

            else
            {
                //rs.next();
                pro.setId(rs.getInt("id"));
                pro.setName(rs.getString("name"));
                pro.setCountryid(rs.getInt("countryid_fk"));
                pro.setCount(rs.getInt("count"));
                pro.setPrice(rs.getFloat("price"));
                //pro.setCreatedate(rs.getDate("createdate"));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return pro;    
    }

    public Integer updateproduct(Product myprd)
    {
        Integer affectedrow=0;
        try{
            Connection con= getConnection();
            PreparedStatement statement=con.prepareStatement(
              "UPDATE tbl_product SET name= ? , countryid_fk = ?, count = ? , price = ? WHERE id = ? ");
            statement.setString(1, myprd.getName());
            statement.setInt(2, myprd.getCountryid());
            statement.setInt(3, myprd.getCount());
            statement.setFloat(4, myprd.getPrice());
            statement.setInt(5, myprd.id);
            System.out.println(statement.toString());

            affectedrow=statement.executeUpdate();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.toString());
        }

        return affectedrow;    
    }
}
