import java.sql.*;

 public class EX1_20
   {
 public static void main (String[] args)    {
	Connection c = null;
	Statement st = null;
	ResultSet rset = null;
    try {
    c =
      	 DriverManager.getConnection("jdbc:mysql://mysql.etu.umontpellier.fr/p00000009432?" +
                                   "user=p00000009432&password=xxxx");

   	 st =  c.createStatement(); 
               rset = st.executeQuery ("select nom from Monument ");
               ResultSetMetaData rsetSchema = rset.getMetaData();
               int nbCols = rsetSchema.getColumnCount();
               for (int i=1; i<=nbCols;i++)
               { 
               	System.out.print(rsetSchema.getColumnName(i)+ " | ");
               }
               System.out.println();
               System.out.println("-------------------------------");
               while (rset.next ())
               {
               	for (int i=1; i<=nbCols;i++)
               	{
               		System.out.print(rset.getObject(i)+ " | ");;
               	}
               	 System.out.println();
               }
           System.out.println("-------------------------------");
               
} catch (SQLException ex) {
    // gestion des erreurs
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
}}
