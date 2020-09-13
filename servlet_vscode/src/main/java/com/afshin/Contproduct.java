package main.java.com.afshin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.ServletException;

@WebServlet(name = "addnewproduct", urlPatterns = { "/addnewproduct" })
public class Contproduct extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /*
         * cookie
         * 
         * //**cookie** :Start check session via cookie*****
         * System.out.println("********Start check session via cookie**********");
         * Boolean iscookievalid = false; if(request.getCookies()!=null) { for(Cookie co
         * : request.getCookies()) { if(co.getName().equals("Jsession")) { String coval
         * = co.getValue(); if(Contlogin.usrmap.containsKey(coval)) { iscookievalid =
         * true; } } } } if(!iscookievalid) { response.sendRedirect("error.html");
         * return; } System.out.println("********session checked via cookie**********");
         * //**cookie** :session checked via cookie*****
         * 
         */
        System.out.println("********Start check session**********");
        Userpas userpas = (Userpas) request.getSession(true).getAttribute("myappsessionsecdata");
        if (userpas == null) {
            response.sendRedirect("error.html");
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Product myproduct = new Product();
        myproduct.setName(request.getParameter("proname"));
        myproduct.setCountryid(Integer.parseInt(request.getParameter("countryname")));
        try {
            myproduct.setCreatedate(formatter.parse(request.getParameter("createdate")));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.toString());
        }
        myproduct.setPrice(Float.parseFloat(request.getParameter("price")));
        myproduct.setCount(Integer.parseInt(request.getParameter("count")));

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

		out.write("<br>Countryid:  ");
		out.write(Integer.toString(myproduct.getCountryid()));
		
		out.write("<br>Count:  ");
        out.write(Integer.toString(myproduct.getCount()));
        
		out.write("<br>price:  ");
        out.write(myproduct.getPrice().toString());  

        out.write("<br>Create date:  ");
        out.write(myproduct.getCreatedate().toString()); 

		out.write("<br><a href='Addproduct.html'>return</a>");
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