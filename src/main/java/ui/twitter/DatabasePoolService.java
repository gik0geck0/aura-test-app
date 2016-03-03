package ui.twitter;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.stereotype.Component;

@Component
public class DatabasePoolService {
	
	DatabasePoolService() throws Exception {
		start();
	}

	JdbcConnectionPool cp;
	
	private static DatabasePoolService dbPoolInstance;
	public static DatabasePoolService get() throws Exception {
		if (dbPoolInstance == null) {
			dbPoolInstance = new DatabasePoolService();
		}
		return dbPoolInstance;
	}
	
	@PostConstruct
	public void start() throws ClassNotFoundException {
		System.out.println("Starting dbPool");
		
		// H2
		Class.forName("org.h2.Driver");
		cp = JdbcConnectionPool.create(
            "jdbc:h2:~/test", "", "");

		/* Postgres
		Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-83-3-38.compute-1.amazonaws.com:5432/d7mq9gdg5smnr3?sslmode=require",
				System.getProperty("dbun"),
				System.getProperty("dbpw")
			);
		*/
	}
	
	public Connection getConnection() throws SQLException {
		return cp.getConnection();
	}
}
