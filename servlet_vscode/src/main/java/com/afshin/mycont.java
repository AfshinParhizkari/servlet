package main.java.com.afshin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;

@WebServlet(name = "save",urlPatterns = {"/save"})
public class mycont extends HttpServlet {
   
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        Product myproduct = new Product();
        myproduct.setName(request.getParameter("proname"));
        myproduct.setBrand(request.getParameter("brnname"));
        myproduct.setMadein(request.getParameter("countryname"));
        myproduct.setPrice(Integer.parseInt(request.getParameter("price")));

        Daoproduct daoprd = new Daoproduct();
        //daoprd.saveproduct(myproduct); => void
        //Integer rslt = daoprd.add(4, 6);
        //System.out.println(rslt.toString());
        
        
        Integer reslt = daoprd.saveproduct(myproduct);

		PrintWriter out= response.getWriter();
		out.write("<html>");
        out.write("<body>");
        
        out.write("<br>Product ID is: ");
        out.write(reslt.toString());

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

        System.out.println("I am in Post");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        System.out.println("I am in Get");
    }
}