package main.java.com.afshin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import javax.servlet.ServletException;

@WebServlet(name = "save",urlPatterns = {"/save"})
public class Contproduct extends HttpServlet {
   
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    
        /* cookie

        //**cookie** :Start check session via cookie*****
        System.out.println("********Start check session via cookie**********");
        Boolean iscookievalid = false;
        if(request.getCookies()!=null)
        {
            for(Cookie co : request.getCookies())
            {
                if(co.getName().equals("Jsession"))
                {
                    String coval = co.getValue();
                    if(Contlogin.usrmap.containsKey(coval))
                    {
                        iscookievalid = true;
                    }
                }
            }
        }  
        if(!iscookievalid)
        {
            response.sendRedirect("error.html");
            return;
        }
        System.out.println("********session checked via cookie**********");
        //**cookie** :session checked via cookie*****

        */
        Userpas userpas= (Userpas)request.getSession(true).getAttribute("myappsessionsecdata");
        if(userpas == null)
        {
            response.sendRedirect("error.html");
            return;           
        }

        Product myproduct = new Product();
        myproduct.setName(request.getParameter("proname"));
        myproduct.setBrand(request.getParameter("brnname"));
        myproduct.setMadein(request.getParameter("countryname"));
        myproduct.setPrice(Integer.parseInt(request.getParameter("price")));

        Daoproduct daoprd = new Daoproduct();
        Integer reslt =daoprd.saveproduct(myproduct);

        PrintWriter out= response.getWriter();
		out.write("<html>");
        out.write("<body>");
        
        out.write("<br>operation result is : ");
        if(reslt>0)
            out.write("Success!");
        else
            out.write("Error!");

        out.write("<br>Product name: ");
        out.write(myproduct.getName());

		out.write("<br>brand:  ");
		out.write(myproduct.getBrand());
		
		out.write("<br>countryname:  ");
        out.write(myproduct.getMadein());
        
		out.write("<br>price:  ");
        out.write(myproduct.getPrice().toString());  
                      
		out.write("<br><a href='index.html'>return</a>");
        //Simple code: just a simple response
        // out.write("<br>Product name:  ");
		// out.write(request.getParameter("proname"));
	
		// out.write("<br>brand:  ");
		// out.write(request.getParameter("brnname"));
		
		// out.write("<br>countryname:  ");
		// out.write(request.getParameter("countryname"));
		
		// out.write("<br>price:  ");
		// out.write(request.getParameter("price"));
		
		out.write("</body>");
		out.write("</html>");

        System.out.println("Post method: Save");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        /* cookie logout

        //**Cookie : Logout
        System.out.println("**Cookie : Logout");
        if(request.getCookies()!=null)
        {
            for(Cookie co : request.getCookies())
            {
                if(co.getName().equals("mycookieid"))
                {
                    co.setMaxAge(0);
                    response.addCookie(co);
                }
            }
        }
        System.out.println("**Cookie : is Logout");
        //**Cookie : Logout

        */

        request.getSession(true).invalidate();
        response.sendRedirect("login.html");
        System.out.println("Get method: logout");
    }
}