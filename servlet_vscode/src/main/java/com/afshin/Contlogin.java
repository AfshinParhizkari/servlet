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
import javax.servlet.http.HttpSession;

@WebServlet(name="welcome",urlPatterns = {"/welcome"})
public class Contlogin extends HttpServlet
{
    private static final long serialVersionUID = 2L;
    protected static Map<String,Userpas> usrmap =new ConcurrentHashMap<>();
    
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
       
        //rd.forward(request, response);
        Daologin dl=new Daologin();
        Userpas welcomeuser = new Userpas();

        welcomeuser = dl.getusr(request.getParameter("usrname"));

        if(welcomeuser.getUsr().equals(request.getParameter("usrname")) &&
        welcomeuser.getPws().equals(request.getParameter("pwd")))
            {
                /*  in cookie way

                //**cookie** :Start create session mgmnt via cookie*****
                System.out.println("**Start create session mgmnt via cookie*****");
                String random =UUID.randomUUID().toString();
                //servlet: Hotel room lock
                usrmap.put(random, welcomeuser);
                //client: key card
                Cookie cookie = new Cookie("mycookieid",random);
                cookie.setMaxAge(-1);
                //reception give key to client 
                response.addCookie(cookie);
                System.out.println("**session is created via cookie*****");
                //**cookie** :session is created via cookie  
                */
                HttpSession session =request.getSession(true);
                session.setAttribute("myappsessionsecdata", welcomeuser);

                response.sendRedirect("index.html");
            }
        else
            {response.sendRedirect("error.html");}
    }
}