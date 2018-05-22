

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();
		if(session.getAttribute( "account" ) == null) {
			if(session.getAttribute( "error" ) != null) {
	        	request.setAttribute("error", session.getAttribute( "error" ));    	
	        	session.invalidate();		
			}
			this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response);
		} else {
			response.sendRedirect("Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Boolean error = false;
		
		if (login == null || login.isEmpty()) {
			error = true;
		}
		if (password == null || password.isEmpty()) {
			error = true;
		}
		
		if (!error) {

			JdbcLogin database = new JdbcLogin();
	        Statement stmt = null;
	        String dbUsername = getServletContext().getInitParameter("user");
	        String dbPassword = getServletContext().getInitParameter("pwd");
	        String dbUrl = getServletContext().getInitParameter("url");
	            
	        try {
//	            stmt = database.db().createStatement();
	            
	            java.sql.PreparedStatement pr= database.db(dbUsername, dbPassword, dbUrl).prepareStatement("SELECT * FROM account WHERE login =? AND password =?;");
        		pr.setString(1, login);
                pr.setString(2, password);
	            ResultSet user = pr.executeQuery();

	            if (user.first()) {
	            	HttpSession session = request.getSession();
	            	session.setAttribute( "account", user.getInt("id") );
	    	        response.sendRedirect("home");
		            
//		            this.getServletContext().getRequestDispatcher("/servlet1.jsp").forward( request, response);
	            } else {
	            	HttpSession session = request.getSession();
	            	session.setAttribute( "error", "Les identifiants sont incorrects" );
	            	doGet(request, response);
	            }

	        } catch (Exception e) {
	            //Handle errors for Class.forName
	            e.printStackTrace();
	        } finally {
	            //finally block used to close resources
	            try {
	                if (stmt != null)
	                    database.db(dbUsername, dbPassword, dbUrl).close();
	            } catch (SQLException ignored) {
	            }// do nothing
	            try {
	                if (database.db(dbUsername, dbPassword, dbUrl) != null)
	                    database.db(dbUsername, dbPassword, dbUrl).close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }//end finally try
	        }//end try
	        
			
		} else {
        	HttpSession session = request.getSession();
        	session.setAttribute( "error", "Veuillez renseigner tous les champs." );
        	doGet(request, response);
		}	
	}

}
