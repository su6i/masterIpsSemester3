import java.sql.*;
import java.util.Scanner;

public class EX1_20_U {

	public static void main(String[] args) 
	throws SQLException
		{
      Connection c = null;
      ResultSet rset = null;
      PreparedStatement pstmt = null;
      Scanner saisie = null;


    try {
    c =
      	 DriverManager.getConnection("jdbc:mysql://mysql.etu.umontpellier.fr/p00000009432?" +
                                   "user=p00000009432&password=xxxx");

    c.setAutoCommit(false);
    // curseur parametre pouvant etre parcouru et modifie
    System.out.println("Entrez un numéro de département ");
    saisie = new Scanner(System.in);
    String dep = saisie.next();
    String sql = "update lieu set dep = ? where codeinsee = '66008'";
    pstmt =  c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
    		ResultSet.CONCUR_UPDATABLE);
    pstmt.setString(1,dep);

    int etat = pstmt.executeUpdate();
    System.out.println("ok "+etat);
    c.commit();
    // testez sans commit 		
      }
	catch (Exception e) {
				      System.err.println("Erreur SQL "+e);
				    }
	finally {
	  pstmt.close();
	  c.close();
	}
	  }	}
