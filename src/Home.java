

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.sql.*;
import java.util.ArrayList;
import java.io.PrintWriter;

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
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
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
			
			JdbcLogin database = new JdbcLogin();
	        Statement stmt = null;
	        String dbUsername = getServletContext().getInitParameter("user");
	        String dbPassword = getServletContext().getInitParameter("pwd");
	        String dbUrl = getServletContext().getInitParameter("url");
	            
	        try {
	            stmt = database.db(dbUsername, dbPassword, dbUrl).createStatement();
	            
	            java.sql.PreparedStatement pr= database.db(dbUsername, dbPassword, dbUrl).prepareStatement("SELECT * FROM account WHERE id =?;");
	            int idAccount = (int) session.getAttribute( "account" ); 
        		pr.setInt(1, idAccount);
	            ResultSet ActualUser = pr.executeQuery();
	            
	            while (ActualUser.next()) {
		        	request.setAttribute("username", ActualUser.getString("login") );   	            	
	            }
	            
	            String allUsersQuery = "SELECT * FROM account";
	            ResultSet allUsers = stmt.executeQuery(allUsersQuery);
	            ArrayList<Object[]> al1 = new ArrayList<Object[]>();
	            while (allUsers.next()) {
                    String user[] = { allUsers.getString("id"), allUsers.getString("login"), allUsers.getString("password") };
                    al1.add(user);
               }
	            request.setAttribute("users", al1);

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
			
			this.getServletContext().getRequestDispatcher("/home.jsp").forward( request, response);
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
