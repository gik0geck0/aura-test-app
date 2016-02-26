package ui.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.auraframework.system.Annotations.AuraEnabled;
import org.auraframework.system.Annotations.Controller;
import org.auraframework.system.Annotations.Key;

@Controller
public class HomepageController {
	
    @AuraEnabled
    public Tweet getAppName(@Key("appKey") String importantInfo) throws Exception {
    	
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
		
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
