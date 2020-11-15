import java.sql.*;

 public class EX1_20_I
   {

 public static void main (String[] args) throws SQLException
       {

	Connection c = null;
	Statement st = null;
	ResultSet rset = null;


try {
    c =
      	 DriverManager.getConnection("jdbc:mysql://mysql.etu.umontpellier.fr/p00000009432?" +
                                   "user=p00000009432&password=xxxx");

   	 st =  c.createStatement(); 
// insertion
	st.executeUpdate("INSERT INTO lieu VALUES ('12266','SEGUR',2.834599,44.291329,'12')");
	st.executeUpdate("INSERT INTO lieu VALUES ('12145','MILLAU',3.077801,44.100575,'12')");
	  		  						  				  
        System.out.println("-------------------------------");
               
} catch (SQLException ex) {
    // gestion des erreurs
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
finally {
	  st.close();
	  c.close();
	}
}}
