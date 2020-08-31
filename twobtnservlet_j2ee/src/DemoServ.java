import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

public class DemoServ extends HttpServlet{


    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String name=req.getParameter("name");

        String str1 = req.getParameter("btn1");       //  AREA submit button
        String str2 = req.getParameter("btn2");       //  PERIMETER submit button

        if(str1 != null)                            // if AREA is clicked
        {
            pw.println("Welcome "+name);
        }
        else if(str2 != null)                       // if PERIMETER is clicked
        {
            pw.println("bye "+name);
        }
    }
}
