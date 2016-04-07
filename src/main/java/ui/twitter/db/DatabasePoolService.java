package ui.twitter.db;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.management.RuntimeErrorException;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

// Can't autowire in aura controllers :-(
// @Component
public class DatabasePoolService {
	
	DatabasePoolService() throws URISyntaxException {
		start();
	}

	SessionFactory sessionFactory;
	ServiceRegistry serviceRegistry;

	private static DatabasePoolService dbPoolInstance;
	public static DatabasePoolService get() throws Exception {
		if (dbPoolInstance == null) {
			dbPoolInstance = new DatabasePoolService();
		}
		return dbPoolInstance;
	}
	
	@PostConstruct
	public void start() throws URISyntaxException {

		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String[] unpw = dbUri.getUserInfo().split(":");
		
		// Warning: only tested with h2 and postgres
		String dbScheme = dbUri.getScheme();
		if (dbUri.getScheme().equals("postgres")) {
			dbScheme = dbScheme + "ql";
		} else {
			throw new UnsupportedOperationException();
		}

		String dbUrl = "jdbc:" + dbScheme + "://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?" + dbUri.getQuery();

		// Perform Schema checks/migrations before starting hibernate
		Flyway flyway = new Flyway();
		flyway.setDataSource(dbUrl, unpw[0], unpw[1]);
		if (Boolean.getBoolean("dbclean")) {
			flyway.clean();
		}
		flyway.setBaselineVersionAsString("0");
		flyway.baseline();
		flyway.migrate();
		
		// Ignore hibernate.cfg.xml because I'd rather pretend XML doesn't exist
		Configuration hbcfg = new Configuration();

		if (dbScheme.equals("postgresql")) {
			hbcfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
			hbcfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		} else {
			throw new UnsupportedOperationException();
		}

		// Auto-scan models (hopefully)
		hbcfg.addAnnotatedClass(Tweet.class);
		hbcfg.addPackage("ui.twitter.db");

		hbcfg.setProperty("hibernate.connection.pool_size", "10");

        // Disable the second-level cache
        hbcfg.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");

        // Echo all executed SQL to stdout
        hbcfg.setProperty("hibernate.show_sql", "true");

        // I don't know what this does
        hbcfg.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
		
		hbcfg.setProperty("hibernate.connection.url", dbUrl);

		hbcfg.setProperty("hibernate.connection.username", unpw[0]);
		hbcfg.setProperty("hibernate.connection.password", unpw[1]);
		
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(hbcfg.getProperties()).build();
		sessionFactory = hbcfg.buildSessionFactory(serviceRegistry);
		/*
		System.out.println("Starting dbPool");
		
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		if (dbUrl == null || dbUrl.isEmpty()) {
			Class.forName("org.h2.Driver");
			cp = JdbcConnectionPool.create(
				"jdbc:h2:~/test", "", "");
		} else {
			Connection conn = DriverManager.getConnection(dbUrl);
			/*
					"jdbc:postgresql://ec2-54-83-3-38.compute-1.amazonaws.com:5432/d7mq9gdg5smnr3?sslmode=require",
					System.getProperty("dbun"),
					System.getProperty("dbpw")
				);
			*
		}
		*/
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}
}
