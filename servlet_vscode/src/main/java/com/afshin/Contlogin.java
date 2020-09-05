package main.java.com.afshin;

import java.io.IOException;
//import java.lang.ProcessBuilder.Redirect;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="welcome",urlPatterns = {"/welcome"})
public class Contlogin extends HttpServlet{
    
    private static final long serialVersionUID = 2L;
    
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        //vergleischen
        //RequestDispatcher rd= request.getRequestDispatcher("Prosave");
        //rd.forward(request, response);
        Daologin dl=new Daologin();
        Userpas upg = new Userpas();

        upg = dl.getusr(request.getParameter("usrname"));

        if(upg.getUsr().equals(request.getParameter("usrname")) &&
           upg.getPws().equals(request.getParameter("pwd"))
          )
            {response.sendRedirect("index.html");}
        else
            {response.sendRedirect("error.html");}
    }
}
