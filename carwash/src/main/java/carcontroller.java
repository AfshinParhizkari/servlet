
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/save")
public class carcontroller extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        car m=new car();
        m.setId (Integer.parseInt(id));
        m.setName(name);
        cardao.create(m);
    }

}
