package ui.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.auraframework.annotations.Annotations.ServiceComponentController;
import org.auraframework.system.Annotations.AuraEnabled;
import org.auraframework.system.Annotations.Controller;
import org.auraframework.system.Annotations.Key;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceComponentController
public class HomepageController {
	
    @AuraEnabled
    public String getAppName(@Key("appKey") String importantInfo) throws Exception {
    		return "echo: " + importantInfo;
    }
    
    @AuraEnabled
    public List<Tweet> getTweets() throws Exception {
    		Connection conn = DatabasePoolService.get().getConnection();
		
		boolean dropResult = conn.prepareStatement("DROP TABLE IF EXISTS testTable").execute();
		boolean createResult = conn.prepareStatement("CREATE TABLE testTable (ID INT PRIMARY KEY, name VARCHAR(255), message VARCHAR(255), imageUrl VARCHAR(255), date VARCHAR(255) )").execute();
		boolean insertResult = conn.prepareStatement("INSERT INTO testTable VALUES (1, 'bob', 'My first tweet', 'https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png', 'today')").execute();
		ResultSet queryResult = conn.prepareStatement("SELECT name, message, imageUrl, date FROM testTable WHERE ID = 1").executeQuery();
		
		List<Tweet> tweets = new ArrayList<>();
		if (queryResult.next()) {
			tweets.add(
				new Tweet(
					queryResult.getString("name"),
					queryResult.getString("message"),
					queryResult.getString("imageUrl"), 
					queryResult.getString("date")));
		}
		conn.close();

		return tweets;
    }
}
