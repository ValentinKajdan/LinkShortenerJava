

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Add
 */
@WebServlet("/add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute( "account" ) != null) {
			if(session.getAttribute( "addError" ) != null) {
	        	request.setAttribute("addError", session.getAttribute( "addError" ));
	        	session.removeAttribute("addError");
			}
			this.getServletContext().getRequestDispatcher("/add.jsp").forward( request, response);			
		} else {
			// No account session
        	request.setAttribute("error", "Vous devez vous connecter pour accéder à cette page.");
    		this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response);
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
	            
	            java.sql.PreparedStatement pr= database.db(dbUsername, dbPassword, dbUrl).prepareStatement("INSERT INTO account (login, password) VALUES (?, ?);");
        		pr.setString(1, login);
        		pr.setString(2, password);
	            int update = pr.executeUpdate();

		        response.sendRedirect("home");

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
        	session.setAttribute( "addError", "Veuillez renseigner tous les champs." );
        	doGet(request, response);
		}	
	}

}
