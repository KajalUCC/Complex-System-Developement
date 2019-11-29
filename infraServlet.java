import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "infraServlet")
public class infraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int server_count=0;
        double server_aval=0;
        double cap=0,temp=0,power=0;
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        DBConnect dbConnect = new DBConnect();
        String serverID = request.getParameter("ServerID");
        try {

             ps= dbConnect.connnect().prepareStatement("select * from server_metrics");
             resultSet = ps.executeQuery();

            while (resultSet.next()) {

                if (resultSet.getFloat (2)<10)
                {
                    ps= dbConnect.connnect().prepareStatement("delete from server where server_id=?");
                    ps.setInt (1,resultSet.getInt (1));
                    //resultSet = ps.executeQuery();
                    resultSet.next ();
                }
            server_count++;
            cap = cap + Float.parseFloat(resultSet.getString(6));
            if(resultSet.getFloat(2)>10)
                server_aval++;
                temp = temp + Double.parseDouble(resultSet.getString(5));
            }
            power= Math.pow(temp,4) * (5.6703) * Math.pow(0.1,8);
                //RequestDispatcher requestDispatcher=request.getRequestDispatcher("server_details.jsp");
                request.setAttribute("server_count", server_count);
                request.setAttribute("server_cap", cap);
                request.setAttribute("server_aval", server_aval);
                request.setAttribute("server_temp", temp);
                request.setAttribute("server_power", power);
                request.getRequestDispatcher("infraDetails.jsp").forward(request, response);
                

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
