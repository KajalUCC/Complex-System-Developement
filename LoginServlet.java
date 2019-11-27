import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    public class LoginServlet extends HttpServlet {
        LoginService ls=new LoginService();
        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("username");
            String password = request.getParameter("password");

           // boolean isUserValid = ls.isUserValid(name, password);

            if (name.equals("admin") && password.equals("admin")) {
                //request.getSession().setAttribute("name", name);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/infraServlet");
                requestDispatcher.include(request,response);
            } else {
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.jsp");
                requestDispatcher.include(request,response);
            }
        }
    }
