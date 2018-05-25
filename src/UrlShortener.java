import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UrlServlet
 */
@WebServlet("/url")
public class UrlShortener extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/urlShortener.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String base_url = request.getParameter("base_url");
		String symbols = "azertyuiopqsdfghjklmwxcvbn0123456789AZERTYUIOPQSDFGHJKLMWXCVBN%$";
		
		String url_code = "";
		for(int i = 0; i <= 5; i++)
		{
			double random = Math.random()*60;
			url_code += symbols.charAt((int)random);
		}
		System.out.println(url_code);
		
		JdbcLogin database = new JdbcLogin();
        Statement stmt = null;
        String dbUsername = getServletContext().getInitParameter("user"); 
        String dbPassword = getServletContext().getInitParameter("pwd");
        String dbUrl = getServletContext().getInitParameter("url");
		
		try {
            java.sql.PreparedStatement pr= database.db(dbUsername, dbPassword, dbUrl).prepareStatement("INSERT INTO url (base_url, shortening_code, user_id) VALUES (?, ?, ?);");
    		pr.setString(1, base_url);
    		pr.setString(2, url_code);
    		pr.setString(3, "0");
            int update = pr.executeUpdate();
            
            System.out.println();
            
            request.setAttribute("url", request.getScheme()+
            							"://"+
            							request.getServerName()+
            							":"+request.getServerPort()+
            							request.getRequestURI()+
            							"/"+
            							url_code);
            this.getServletContext().getRequestDispatcher("/showUrlShortener.jsp").forward(request, response);
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
	}
}
