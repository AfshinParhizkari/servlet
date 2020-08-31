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

		PrintWriter Writer= response.getWriter();
		Writer.write("<html>");
		Writer.write("<body>");
        Writer.write("<br>Product name:  ");
		Writer.write(request.getParameter("proname"));
	
		Writer.write("<br>brand:  ");
		Writer.write(request.getParameter("brnname"));
		
		Writer.write("<br>countryname:  ");
		Writer.write(request.getParameter("countryname"));
		
		Writer.write("<br>price:  ");
		Writer.write(request.getParameter("price"));
		
		Writer.write("</body>");
		Writer.write("</html>");

        System.out.println("I am in Post");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        System.out.println("I am in Get");
    }
}