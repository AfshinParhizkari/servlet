package main.java.com.afshin;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//import java.lang.ProcessBuilder.Redirect;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="welcome",urlPatterns = {"/welcome"})
public class Contlogin extends HttpServlet
{
    private static final long serialVersionUID = 2L;
    protected static Map< String , Userpas > usrmap =new ConcurrentHashMap<>();
    
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
            {
                String random =UUID.randomUUID().toString();
                usrmap.put(random, upg);
                Cookie cookie = new Cookie("Jsession",random);
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
                response.sendRedirect("index.html");
            }
        else
            {response.sendRedirect("error.html");}
    }
    
}
