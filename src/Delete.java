

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
			// User connected
			if(request.getParameter("id") != null) {
				// Delete user
								
				JdbcLogin database = new JdbcLogin();
		        Statement stmt = null;
		        String dbUsername = getServletContext().getInitParameter("user");
		        String dbPassword = getServletContext().getInitParameter("pwd");
		        String dbUrl = getServletContext().getInitParameter("url");
		            
		        try {
		            
		            java.sql.PreparedStatement pr= database.db(dbUsername, dbPassword, dbUrl).prepareStatement("DELETE FROM account WHERE id =?;");
	        		pr.setString(1, request.getParameter("id"));
		            int delete = pr.executeUpdate();
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
		        response.sendRedirect("Home");				
			}
			
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
		doGet(request, response);
	}

}
