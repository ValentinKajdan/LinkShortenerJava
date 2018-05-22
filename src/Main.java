import java.util.ArrayList;
import java.sql.*;
import java.util.Date;
public class Main {	
	
	public static void main(String[] args) {
		
		JdbcLogin database = new JdbcLogin();
        Statement stmt = null;

        try {
            stmt = database.db().createStatement();

//            String sql = "INSERT INTO visite "
//                    + "(nomEtudiant, date, nbVisites, note) "
//                    + "VALUES ('Test', NOW(), 0, 12);";
//            stmt.executeUpdate(sql);


            String strQuery = "SELECT * FROM visite;";
            ResultSet rsUsers = stmt.executeQuery(strQuery);
            while (rsUsers.next()) {
                System.out.print(
                		rsUsers.getString("nomEtudiant") + " le "
                        + rsUsers.getString("date") + "\n Note : "
                        + rsUsers.getInt("note") + "\n");
            }
            rsUsers.close();

        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    database.db().close();
            } catch (SQLException ignored) {
            }// do nothing
            try {
                if (database.db() != null)
                    database.db().close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

//		ArrayList<Visite> listeVisites = new ArrayList<Visite>();
//		
//		Planning p1 = new Planning("Mr Prof", listeVisites);
//
//		System.out.println(p1.toString());		
//		
//		Visite v1 = new Visite("Test", "28 Février à 14h30", 12, "CR1");
//		Visite v2 = new Visite("Rudy", "28 Février à 15h", 1, "CR2");
//		p1.ajouterVisite(v1);
//		p1.ajouterVisite(v2);
//		
//		System.out.println(p1.toString());
//		
//		p1.supprimerVisite("Test");
//		
//		System.out.println(p1.toString());
		
	}

}
