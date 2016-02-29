package ui.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.auraframework.annotations.Annotations.ServiceComponentController;
import org.auraframework.system.Annotations.AuraEnabled;
import org.auraframework.system.Annotations.Controller;
import org.auraframework.system.Annotations.Key;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceComponentController
public class HomepageController {
	
	@Autowired
	DatabasePoolService dbPoolService;
	
    @AuraEnabled
    public Tweet getAppName(@Key("appKey") String importantInfo) throws Exception {
    	
    		/* Using H2
    		Class.forName("org.postgresql.Driver");
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
		*/
		/* Using Postgres
		Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-83-3-38.compute-1.amazonaws.com:5432/d7mq9gdg5smnr3?sslmode=require",
				System.getProperty("dbun"),
				System.getProperty("dbpw")
			);
		*/
    		Connection conn = dbPoolService.getConnection();
		
		boolean dropResult = conn.prepareStatement("DROP TABLE IF EXISTS testTable").execute();
		boolean createResult = conn.prepareStatement("CREATE TABLE testTable (ID INT PRIMARY KEY, NAME VARCHAR(255) )").execute();
		boolean insertResult = conn.prepareStatement("INSERT INTO testTable VALUES (1, 'hello')").execute();
		ResultSet queryResult = conn.prepareStatement("SELECT ID, NAME FROM testTable WHERE ID = 1").executeQuery();
		
		Tweet tweet = null;
		if (queryResult.next()) {
			tweet = new Tweet("Bob", "Happy Birthday!", null, "today");
		}
		conn.close();

		return tweet;
    }
}
