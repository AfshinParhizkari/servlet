package main.java.com.afshin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@WebServlet(name = "products", urlPatterns = { "/products" })
public class Contproduct extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

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
        Userpas userpas = (Userpas) req.getSession(true).getAttribute("myappsessionsecdata");
        if (userpas == null) {
            res.sendRedirect("error.html");
            return;
        }

        String action = req.getParameter("CRUD");
        System.out.println(action);

        if (action.equals("add")) {
            addproduct(req, res);
        }
        if (action.equals("find")) {
            String[] checked = req.getParameterValues("allrec");
            if (checked == null) {
                try {
                    findallproduct(req, res, " where name like '%" + req.getParameter("findbyid")+"%'");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                if (checked[0].equals("all")) {
                    try {
                        findallproduct(req, res,"");
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        System.out.println("********Start check session**********");
        Userpas userpas = (Userpas) req.getSession(true).getAttribute("myappsessionsecdata");
        if (userpas == null) {
            res.sendRedirect("error.html");
            return;
        }

        String action = req.getParameter("CRUD");
        System.out.println(action);

        if (action.equals("del")) {
            Daoproduct daoproduct =new Daoproduct();
            int reslt=daoproduct.deleteproduct(req.getParameter("id"));
            PrintWriter out;
            try {
                out = res.getWriter();
                out.write("<html>");
                out.write("<body>");
    
                out.write("<br>operation result is : ");
                if (reslt > 0)
                    out.write("Success!");
                else
                    out.write("Error!");
                out.write("<br><a href='Listproduct.html'>return</a>");
                out.write("</body>");
                out.write("</html>");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void addproduct(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Log method is Save");
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
        Integer reslt = daoprd.saveproduct(myproduct);

        PrintWriter out;
        try {
            out = response.getWriter();
            out.write("<html>");
            out.write("<body>");

            out.write("<br>operation result is : ");
            if (reslt > 0)
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

            out.write("<br><a href='Listproduct.html'>return</a>");
            // Simple code: just a simple response
            // out.write("<br>Product name: ");
            // out.write(request.getParameter("proname"));

            // out.write("<br>brand: ");
            // out.write(request.getParameter("brnname"));

            // out.write("<br>countryname: ");
            // out.write(request.getParameter("countryname"));

            // out.write("<br>price: ");
            // out.write(request.getParameter("price"));

            out.write("</body>");
            out.write("</html>");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void findallproduct(HttpServletRequest request, HttpServletResponse response, String whereclause) throws IOException, ServletException, SQLException
    {
        System.out.println("Log method is findallproduct");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write("<html>");
            out.write("<body>");
            Daoproduct daoprd = new Daoproduct();
            ResultSet rs= daoprd.findallproduct(whereclause);
            if(rs!=null)
            {
                out.write("<a href='Addproduct.html'>Add new Product</a>");
                out.write("<form action='welcome' method='GET'>");
                out.write("    <input type='submit' value='logout'> <br></br>");
                out.write("</form>");
                out.write("<form action='products' method='POST'>");
                out.write("find product by name:<input type='text' name='findbyid'></br>");
                out.write("<input type='checkbox' name='allrec' value='all'>AllRecords</input></br>");
                out.write("<input type='hidden' name='CRUD' value='find'>");
                out.write("<input type='submit' name='Find Product'></br>");
                out.write("<label></label>");
                out.write("</form>");
    

                out.write("<table>");
                out.write("<tr>");
                out.write("<td>");out.write("id");out.write("</td>");
                out.write("<td>");out.write("name");out.write("</td>");
                out.write("<td>");out.write("countryid");out.write("</td>");
                out.write("<td>");out.write("count");out.write("</td>");
                out.write("<td>");out.write("price");out.write("</td>");
                out.write("<td>");out.write("createdate");out.write("</td>");
                out.write("<td>");out.write("Delete");out.write("</td>");
                out.write("<td>");out.write("Edit");out.write("</td>");
                out.write("</tr>");
                while (rs.next())
                {
                    out.write("<tr>");
                    out.write("<td>");out.write(rs.getString(1));out.write("</td>");
                    out.write("<td>");out.write(rs.getString(2));out.write("</td>");
                    out.write("<td>");out.write(rs.getString(3));out.write("</td>");
                    out.write("<td>");out.write(rs.getString(4));out.write("</td>");
                    out.write("<td>");out.write(rs.getString(5));out.write("</td>");
                    out.write("<td>");out.write(rs.getString(6));out.write("</td>");
                    out.write("<td>");out.write("<a href='"+getServletContext().getContextPath()+"/products?CRUD=del&id="+rs.getString(1)+"'>delete</a>");out.write("</td>");
                    out.write("</tr>");
                }
                out.write("</table>");
            }
            out.write("</body>");
            out.write("</html>");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

 }