

import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UrlRedirect
 */
@WebServlet("/url/*")
public class UrlRedirect extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] parts = request.getRequestURI().split("/");
		String url_code = parts[3];
		
		JdbcLogin database = new JdbcLogin();
        Statement stmt = null;
        String dbUsername = getServletContext().getInitParameter("user"); 
        String dbPassword = getServletContext().getInitParameter("pwd");
        String dbUrl = getServletContext().getInitParameter("url");
        
        try {
            java.sql.PreparedStatement pr= database.db(dbUsername, dbPassword, dbUrl).prepareStatement("SELECT base_url FROM url WHERE shortening_code = ?");
            pr.setString(1, url_code);
            ResultSet url = pr.executeQuery();
            
            if(url.first()) {
            	String url_redirect = url.getString("base_url");
                System.out.println(url_redirect);
                response.sendRedirect("http://"+url_redirect);
            } else {
            	
            }
            this.getServletContext().getRequestDispatcher("/showUrlShortener.jsp").forward(request, response);
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
	}
}
