import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class cardao {

    public static Connection getConnection(){
        String dburl= "jdbc:mysql://localhost:3306/learncrud";
        String username="admin";
        String password="123456";

        Connection con=null;
        try
            {
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(dburl, username, password);
            }
        catch (Exception e){System.out.println(e);}
        return con;
    }

    public static int create(car c){
        int status=0;
        try{
            Connection con=cardao.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into Mashin(id,name) values (?,?)");
            ps.setString(1,c.getId().toString());
            ps.setString(2,c.getName());
            status=ps.executeUpdate();
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
}