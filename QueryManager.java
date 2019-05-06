import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;

public class QueryManager
{   private static final String USERNAME = "tyfeece467";
    private static final String PASSWORD = "c10053";
    private static final String HOST = "localhost"; // Host
    private static final String PORT = "1521"; // Default port
    private static final String SID = System.getenv("ORACLE_SID"); // Oracle SID
	
    
    Connection conn;
    CallableStatement statement;
    String query;

    public QueryManager() {
	createConnection();
}

    private void createConnection()
    {
    try {
    OracleDataSource ods = new OracleDataSource();
    ods.setUser(USERNAME);
    ods.setPassword(PASSWORD);
    ods.setURL("jdbc:oracle:thin:" + "@" + HOST
                                   + ":" + PORT
                                   + ":" + SID);

    conn = ods.getConnection();
	} catch(SQLException e) {
System.err.println("Error connecting to database.");	
System.exit(1);
    }
}


public ResultSet executeQuery(String query) {

ResultSet rset = null;
try {
statement = conn.prepareCall(query);
statement.registerOutParameter(1, OracleTypes.CURSOR);
statement.execute();
rset = ((OracleCallableStatement)statement).getCursor(1); 
} catch(SQLException e) {
System.err.println("Error executing query.");
System.err.println(e.getMessage());
}
return rset;
}

public void executeUpdate(String query) {
				
try {
    statement = conn.prepareCall(query);
    statement.execute();
    
} catch (SQLException e) {
    System.err.println(e.getMessage());

}
		
}

public void closeConnection() {
try {
conn.close();
System.out.println("Connection closed.");
} catch (SQLException e) {
System.err.println("Error: closing connection to database.");
e.printStackTrace();
}
}
        
}
