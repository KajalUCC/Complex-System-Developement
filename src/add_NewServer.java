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
import java.sql.ResultSet;
import java.sql.SQLException;

public class add_NewServer extends HttpServlet {
    Connection c=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnect dbConnect=new DBConnect();
        try {
            c=dbConnect.connnect ();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ( );
        }
        PreparedStatement ps=null;
        String loc = request.getParameter("location");
        String sname = request.getParameter("server_name");
        String config = request.getParameter("config");
        String type = request.getParameter("type");
        String os = request.getParameter("OS");
        try {

            ps= c.prepareStatement("INSERT INTO SERVERS VALUES (default,?,?,?,?,?)",ps.RETURN_GENERATED_KEYS);
            ps.setString(1,sname);
            ps.setString(2,os);
            ps.setString(3,loc);
            ps.setString(4,config);
            ps.setString(5,type);
            int i= ps.executeUpdate();
            if (i>0)
            {
                ResultSet resultSet1=ps.getGeneratedKeys ();
                if (resultSet1.next ())
                {
                    i=resultSet1.getInt (1);
                    ps = c.prepareStatement("INSERT INTO server_metrics VALUES (?,?,?,?,?,?,?)");
                    ps.setInt(1, i);
                    ps.setFloat(2, 0);
                    ps.setInt(3, 0);
                    ps.setString(4, "0");
                    ps.setString(5, "0");
                    ps.setString(6, "500");
                    ps.setFloat(7, 100);
                    i = ps.executeUpdate();
                    c.close ();
                    if (i>0)
                    {
                        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/server_details.jsp");
                        requestDispatcher.include(request,response);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
