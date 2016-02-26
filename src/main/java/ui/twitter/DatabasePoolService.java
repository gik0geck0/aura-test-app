package ui.twitter;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.stereotype.Component;

@Component
public class DatabasePoolService {

	JdbcConnectionPool cp;
	
	@PostConstruct
	public void start() throws ClassNotFoundException {
		System.out.println("Starting dbPool");
		Class.forName("org.h2.Driver");
		cp = JdbcConnectionPool.create(
            "jdbc:h2:~/test", null, null);
	}
	
	public Connection getConnection() throws SQLException {
		return cp.getConnection();
	}
}
