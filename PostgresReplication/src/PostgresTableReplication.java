import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresTableReplication {
	String sourceIp;
	String sourcePort;
	String sourceDbName;
	String sourceUserName;
	String sourcePasswd;
	
	String targetIp;
	String targetPort;
	String targetDbName;
	String targetUserName;
	String targetPasswd;
	
	String tableName;
	
	Connection connection;
	
	public PostgresTableReplication() {
		
	}

	public PostgresTableReplication(
			String sourceIp, String sourcePort, String sourceDbName, String sourceUserName, String sourcePasswd,
			String targetIp, String targetPort, String targetDbName, String targetUserName, String targetPasswd, 
			String tableName) throws Exception{
		this.sourceIp = sourceIp;
		this.sourcePort = sourcePort;
		this.sourceDbName = sourceDbName;
		this.sourceUserName = sourceUserName;
		this.sourcePasswd = sourcePasswd;
		
		this.targetIp = targetIp;
		this.targetPort = targetPort;
		this.targetDbName = targetDbName;
		this.targetUserName = targetUserName;
		this.targetPasswd = targetPasswd;
		
		this.tableName = tableName;
		
		Class.forName("org.postgresql.Driver");		
	}
	
	public void getTableDDLfromSourceDb() throws Exception {
		connectToSourceDb();
		
//		PrintWriter pw = new PrintWriter(new File("copyddl.bat"));
//		pw.println("SET PGPASSWORD="+sourcePasswd);
//		pw.println("\"C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe\""+
//				   " -t "+tableName+
//				   " -U "+sourceUserName+
//				   " -f ddl.sql"+ 
//				   " "+sourceDbName);
//		pw.close();
//		
//		Process p = Runtime.getRuntime().exec("copyddl.bat");	
//		p.waitFor();
		
		Process p = null;
		String scrpt = "cmd.exe SET PGPASSWORD="+sourcePasswd;
		p = Runtime.getRuntime().exec(scrpt);
//		p.waitFor();

//		System.out.println("????");
		scrpt = "\"C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe\""+
				   " -t "+tableName+
				   " -U "+sourceUserName+
				   " -f ddl.sql"+ 
				   " "+sourceDbName;

		p = Runtime.getRuntime().exec(scrpt);

//		p.waitFor();
	}
	
	public void copyTableDDLtoTargetDb() throws Exception {
		connectToTargetDb();
		
//		PrintWriter pw = new PrintWriter(new File("applyddl.bat"));
//		pw.println("SET PGPASSWORD="+targetPasswd);
//		pw.println("\"C:\\Program Files\\PostgreSQL\\9.3\\bin\\psql.exe\""+
//				   " -U "+targetUserName+
//				   " -f ddl.sql"+ 
//				   " "+targetDbName);
//		pw.close();
//		
//		Process p = Runtime.getRuntime().exec("applyddl.bat");	
//		p.waitFor();
		
		Process p = null;
		String scrpt = "cmd.exe SET PGPASSWORD="+targetPasswd;
		p = Runtime.getRuntime().exec(scrpt);
		
//		p.waitFor();
		scrpt = "\"C:\\Program Files\\PostgreSQL\\9.3\\bin\\psql.exe\""+
				   " -U "+targetUserName+
				   " -f ddl.sql"+ 
				   " "+targetDbName;
		p = Runtime.getRuntime().exec(scrpt);
//		p.waitFor();
	}
	
	public void connectToSourceDb() throws Exception {
		connection = DriverManager.getConnection("jdbc:postgresql://"+sourceIp+":"+sourcePort+"/"+sourceDbName, sourceUserName, sourcePasswd);
	}
	
	public void connectToTargetDb() throws Exception {
		connection = DriverManager.getConnection("jdbc:postgresql://"+targetIp+":"+targetPort+"/"+targetDbName, targetUserName, targetPasswd);
	}
	
	public void cleanUp() throws Exception {
		connection.close();
		File file = null;
		file = new File("copyddl.bat");
		file.delete();
		file = new File("applyddl.bat");
		file.delete();
		file = new File("ddl.sql");
		file.delete();
	}
	
	public List<String> getTableNames() throws Exception {
		connectToSourceDb();
		Statement stmt = connection.createStatement();
		String sql = "select * from information_schema.tables WHERE table_schema='public'";
		ResultSet rs = stmt.executeQuery(sql);
		
		List<String> tableList = new ArrayList<>();
		while (rs.next()) {
			tableList.add(rs.getString(3));
		}
		
		rs.close();
		stmt.close();
		return tableList;
	}
	
	public static void main(String[] args) {
		try {
//			PostgresTableReplication pgTableReplication = 
//					new PostgresTableReplication(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
			
//			pgTableReplication.getTableDDLfromSourceDb();
//			pgTableReplication.copyTableDDLtoTargetDb();
//			pgTableReplication.cleanUp();
			
//			pgTableReplication.connectToSourceDb();
//			pgTableReplication.getTableNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
