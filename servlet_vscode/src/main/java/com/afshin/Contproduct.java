package main.java.com.afshin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet(name = "save",urlPatterns = {"/save"})
public class Contproduct extends HttpServlet {
   
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    
        System.out.println("*************Save to db***************");
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
        if(request.getCookies()!=null)
        {
            for(Cookie co : request.getCookies())
            {
                if(co.getName().equals("Jsession"))
                {
                    co.setMaxAge(0);
                    response.addCookie(co);
                    response.sendRedirect("login.html");
                    return;    
                }
            }
        }

        System.out.println("Get method: logout");
    }
}