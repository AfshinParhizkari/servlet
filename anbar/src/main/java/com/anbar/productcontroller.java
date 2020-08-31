package main.java.com.anbar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class productcontroller
 */
@WebServlet(name = "product",urlPatterns = "/productsave")
public class productcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Log: I am in do Get");
		
		Product product=new Product();
		product.setName(request.getParameter("proname"));
		product.setBrand(request.getParameter("brnname"));
		product.setMadein(request.getParameter("countryname"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		
		//proservice srv =new proservice();
		boolean newid =false;
		try {
		 newid = prosrv(product);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		PrintWriter write= response.getWriter();
		write.write("<html>");
		write.write("<body>");
		
		if (newid==false) 
		{
			write.write("<br>Product isn't saved in database");
		}
		else
		{
			write.write("<br>Product is saved in database with id: "+ newid);
		}
	
		/*write.write("<br>Product name:  ");
		write.write(product.getName());
	
		write.write("<br>brand:  ");
		write.write(product.getBrand());
		
		write.write("<br>countryname:  ");
		write.write(product.getMadein());		
		
		write.write("<br>price:  ");
		write.write(product.getPrice().toString());	*/
		
		write.write("</body>");
		write.write("</html>");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am in do Post");
		doGet(request, response);
		
	}
	
	public boolean prosrv(Product pro) throws SQLException
	{
		try {
			Prodao daolayer = new Prodao();
			boolean newid = daolayer.savetodb(pro);
			return newid;
			
		} catch (Exception e) {
			throw new SQLException(e);
			}

		
	}

}
