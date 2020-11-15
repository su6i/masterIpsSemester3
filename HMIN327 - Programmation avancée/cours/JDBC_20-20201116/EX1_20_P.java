import java.sql.*;
import java.util.Scanner;

public class EX1_20_P {

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
    System.out.println("Entrez un code insee de lieu ");
    saisie = new Scanner(System.in);
    String codeinsee = saisie.next();
    System.out.println("Entrez un nom de lieu");
    String nom = saisie.next();
    System.out.println("Entrez une longitude");
    Float longitude = saisie.nextFloat();
    System.out.println("Entrez une latitude");
    Float latitude = saisie.nextFloat();
    String sql = "insert into lieu values (?,?,?,?,null)";
    pstmt =  c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
    		ResultSet.CONCUR_UPDATABLE);
    pstmt.setString(1,codeinsee);
    pstmt.setString(2,nom);
    pstmt.setFloat(3,longitude);
    pstmt.setFloat(4,latitude);
    int etat = pstmt.executeUpdate();
    // attention scanner prend des virgules et non points pour la decimale
   // 66008  ARGELES-SUR-MER 3.022911  42.546214
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
