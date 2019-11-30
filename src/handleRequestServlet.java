import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "handleRequestServlet")
public class handleRequestServlet extends HttpServlet {
    DBConnect dbConnect = new DBConnect();
    ResultSet resultSet = null;
    PreparedStatement ps = null;
    Connection c=null;
    int i= 0,j=0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            c=dbConnect.connnect ();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ( );
        }

        if (request.getParameter("type").equals("bulkRequest"))
        {
            bulkHandle (request,response);
        }
        if (request.getParameter("type").equals("singleRequest"))
        {
          singleHandle (request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void bulkHandle(HttpServletRequest request,HttpServletResponse response)
    {
        int count=Integer.parseInt(request.getParameter("req_count"));
        while (count>0)
        {
            try {
                ps= c.prepareStatement("INSERT INTO SERVERS VALUES (default,?,?,?,?,?)",ps.RETURN_GENERATED_KEYS);
                ps.setString(1,"host");
                ps.setString(2,"Linux");
                ps.setString(3,"Cork");
                ps.setString(4,"256");
                ps.setString(5,"application");
                i = ps.executeUpdate();
                if (i>0)
                {
                    ResultSet resultSet1=ps.getGeneratedKeys ();
                    if (resultSet1.next ())
                    {
                        j=resultSet1.getInt (1);
                        ps = c.prepareStatement("INSERT INTO server_metrics VALUES (?,?,?,?,?,?,?)");
                        ps.setInt(1, j);
                        ps.setFloat(2, 0);
                        ps.setInt(3, 0);
                        ps.setString(4, "0");
                        ps.setString(5, "0");
                        ps.setString(6, "500");
                        ps.setFloat(7, 100);
                        j = ps.executeUpdate();
                        count--;
                    }
                }

            }
            catch (SQLException e) {
                e.printStackTrace ( );
            }
        }
        try {
            ps = c.prepareStatement("select * from server_metrics ");
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getFloat(2) < 80 && Float.parseFloat(resultSet.getString(4)) < 50) {
                    double f = (10 + (Double.parseDouble(resultSet.getString(4))));
                    double cons = Double.parseDouble(resultSet.getString(4)) + f;
                    double storage = Double.parseDouble(resultSet.getString(6));
                    double l = (5 + (resultSet.getDouble(2)));
                    double load = resultSet.getDouble(2) + l;
                    int requestcount = resultSet.getInt(3) + 1;
                    double diskconsum = ((cons + Double.parseDouble(resultSet.getString(4))) * 100) / storage;
                    ps = c.prepareStatement("update server_metrics set server_load=?,request_count=?,disk_consumption=?,available_cap=?,temperature=? where server_id=?");
                    ps.setDouble (1, load);
                    ps.setInt(2, requestcount);
                    ps.setString(3, String.valueOf(diskconsum));
                    ps.setDouble(4, 100 - load);
                    ps.setString (5,String.valueOf((Double.parseDouble (resultSet.getString (5)))+10));
                    ps.setInt(6, resultSet.getInt(1));
                    i = ps.executeUpdate();
                    c.close ();
                }
            }
            if (i > 0) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/server_details.jsp");
                requestDispatcher.include(request, response);
            }
        }
        catch (SQLException | ServletException | IOException e) {
            e.printStackTrace ( );
        }

    }
    protected void singleHandle(HttpServletRequest request,HttpServletResponse response)
    {
        int id = 0;
        String appName = request.getParameter("app_name");
        String requestType = request.getParameter("request_type");
        try {
            ps = c.prepareStatement("select * from server_metrics ");
            resultSet = ps.executeQuery();
            if (!resultSet.first()) {
                ps = c.prepareStatement("select server_id,type from servers ");
                resultSet = ps.executeQuery();
                while (resultSet.next() && resultSet.getString(2).equals(requestType)) {
                    id = resultSet.getInt(1);
                    break;
                }
                ps = c.prepareStatement("INSERT INTO server_metrics VALUES (?,?,?,?,?,?,?)");
                ps.setInt(1, id);
                ps.setFloat(2, 10);
                ps.setInt(3, 1);
                ps.setString(4, "10");
                ps.setString(5, "10");
                ps.setString(6, "500");
                ps.setFloat(7, 100);
                i = ps.executeUpdate();
                if (i > 0) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/server_details.jsp");
                    requestDispatcher.include(request, response);
                }
            } else {
                ps = c.prepareStatement("select * from server_metrics ");
                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getFloat(2) < 80 && Float.parseFloat(resultSet.getString(4)) < 50) {
                        float f = ((10 * Float.parseFloat(resultSet.getString(4))) / 100);
                        float cons = Float.parseFloat(resultSet.getString(4)) + f;
                        float storage = Float.parseFloat(resultSet.getString(6));
                        float l = ((5 * resultSet.getFloat(2)) / 100);
                        float load = resultSet.getFloat(2) + l;
                        int requestcount = resultSet.getInt(3) + 1;
                        float diskconsum = ((cons + Float.parseFloat(resultSet.getString(4))) * 100) / storage;
                        float cap=100-load;
                        ps = c.prepareStatement("update server_metrics set server_load=?,request_count=?,disk_consumption=?,available_cap=? where server_id=?");
                        ps.setFloat(1, load);
                        ps.setInt(2, requestcount);
                        ps.setString(3, String.valueOf(diskconsum));
                        ps.setFloat(4, cap);
                        ps.setInt(5, resultSet.getInt(1));
                        i = ps.executeUpdate();
                        c.close ();
                    }
                }
                if (i > 0) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/server_details.jsp");
                    requestDispatcher.include(request, response);
                }
            }
        }
        catch (SQLException | ServletException | IOException e) {
            e.printStackTrace ( );
        }
    }
}
