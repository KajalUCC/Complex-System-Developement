import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "serverDetailsServlet")
public class serverDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBConnect dbConnect = new DBConnect();
        String serverID = request.getParameter("ServerID");
        try {

            PreparedStatement ps = dbConnect.connnect().prepareStatement("select * from server_metrics where server_id=?");
            ps.setInt(1, Integer.parseInt(serverID));
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                //RequestDispatcher requestDispatcher=request.getRequestDispatcher("server_details.jsp");
                request.setAttribute("server_load", resultSet.getFloat(2));
                request.setAttribute("request_count", resultSet.getInt(3));
                request.setAttribute("disk_consumption", resultSet.getString(4));
                request.setAttribute("temperature", resultSet.getString(5));
                request.setAttribute("total_storage", resultSet.getString(6));
                request.setAttribute("available_cap", resultSet.getFloat(7));
                request.getRequestDispatcher("server_details.jsp").forward(request, response);
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
