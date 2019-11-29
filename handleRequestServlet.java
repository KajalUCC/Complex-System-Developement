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

@WebServlet(name = "handleRequestServlet")
public class handleRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnect dbConnect = new DBConnect();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        int i= 0;
        if (request.getParameter("type").equals("bulkRequest"))
        {
            int count=Integer.parseInt(request.getParameter("req_count"));
            while (count>0)
            {
                try {
                    ps= dbConnect.connnect().prepareStatement("INSERT INTO SERVERS VALUES (default,?,?,?,?,?)");
                    ps.setString(1,"host");
                    ps.setString(2,"Linux");
                    ps.setString(3,"Cork");
                    ps.setString(4,"256");
                    ps.setString(5,"application");
                    i = ps.executeUpdate();

                }
                catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace ( );
                }
                count--;
            }
            try {
                ps = dbConnect.connnect().prepareStatement("select * from server_metrics ");
                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getFloat(2) < 80 && Float.parseFloat(resultSet.getString(4)) < 50) {
                        float f = ((10 * Float.parseFloat(resultSet.getString(4))) / 100);
                        float cons = Float.parseFloat(resultSet.getString(4)) + f;
                        System.out.println(cons);
                        float storage = Float.parseFloat(resultSet.getString(6));
                        float l = ((5 * resultSet.getFloat(2)) / 100);
                        float load = resultSet.getFloat(2) + l;
                        System.out.println(load);
                        int requestcount = resultSet.getInt(3) + 1;
                        float diskconsum = ((cons + Float.parseFloat(resultSet.getString(4))) * 100) / storage;
                        System.out.println(diskconsum);
                        ps = dbConnect.connnect().prepareStatement("update server_metrics set server_load=?,request_count=?,disk_consumption=?,available_cap=? where server_id=?");
                        ps.setFloat(1, load);
                        ps.setInt(2, requestcount);
                        ps.setString(3, String.valueOf(diskconsum));
                        ps.setFloat(4, 100 - load);
                        ps.setInt(5, resultSet.getInt(1));
                        i = ps.executeUpdate();
                        if (i > 0) {
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("server_details.jsp");
                            requestDispatcher.include(request, response);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else {
            int id = 0;
            String appName = request.getParameter("app_name");
            String requestType = request.getParameter("request_type");
            try {
                ps = dbConnect.connnect().prepareStatement("select * from server_metrics ");
                resultSet = ps.executeQuery();
                if (!resultSet.first()) {
                    ps = dbConnect.connnect().prepareStatement("select server_id,type from servers ");
                    resultSet = ps.executeQuery();
                    while (resultSet.next() && resultSet.getString(2).equals(requestType)) {
                        id = resultSet.getInt(1);
                        break;
                    }
                    ps = dbConnect.connnect().prepareStatement("INSERT INTO server_metrics VALUES (?,?,?,?,?,?,?)");
                    ps.setInt(1, id);
                    ps.setFloat(2, 10);
                    ps.setInt(3, 1);
                    ps.setString(4, "10");
                    ps.setString(5, "10");
                    ps.setString(6, "500");
                    ps.setFloat(7, 90);
                    i = ps.executeUpdate();
                    if (i > 0) {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("server_details.jsp");
                        requestDispatcher.include(request, response);
                    }
                } else {
                    ps = dbConnect.connnect().prepareStatement("select * from server_metrics ");
                    resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        if (resultSet.getFloat(2) < 80 && Float.parseFloat(resultSet.getString(4)) < 50) {
                            float f = ((10 * Float.parseFloat(resultSet.getString(4))) / 100);
                            float cons = Float.parseFloat(resultSet.getString(4)) + f;
                            System.out.println(cons);
                            float storage = Float.parseFloat(resultSet.getString(6));
                            float l = ((5 * resultSet.getFloat(2)) / 100);
                            float load = resultSet.getFloat(2) + l;
                            System.out.println(load);
                            int requestcount = resultSet.getInt(3) + 1;
                            float diskconsum = ((cons + Float.parseFloat(resultSet.getString(4))) * 100) / storage;
                            System.out.println(diskconsum);
                            ps = dbConnect.connnect().prepareStatement("update server_metrics set server_load=?,request_count=?,disk_consumption=?,available_cap=? where server_id=?");
                            ps.setFloat(1, load);
                            ps.setInt(2, requestcount);
                            ps.setString(3, String.valueOf(diskconsum));
                            ps.setFloat(4, 100 - load);
                            ps.setInt(5, resultSet.getInt(1));
                            i = ps.executeUpdate();
                            if (i > 0) {
                                RequestDispatcher requestDispatcher = request.getRequestDispatcher("server_details.jsp");
                                requestDispatcher.include(request, response);
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
