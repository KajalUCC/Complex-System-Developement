import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class add_NewServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnect dbConnect=new DBConnect();
        String loc = request.getParameter("location");
        String sname = request.getParameter("server_name");
        String config = request.getParameter("config");
        String type = request.getParameter("type");
        String os = request.getParameter("OS");
        try {

            PreparedStatement ps= dbConnect.connnect().prepareStatement("INSERT INTO SERVERS VALUES (default,?,?,?,?,?)");
            ps.setString(1,sname);
            ps.setString(2,os);
            ps.setString(3,loc);
            ps.setString(4,config);
            ps.setString(5,type);
            int i= ps.executeUpdate();
            if (i>0)
            {
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("server_details.jsp");
                requestDispatcher.include(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
