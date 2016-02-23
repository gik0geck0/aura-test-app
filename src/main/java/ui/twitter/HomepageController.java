package ui.twitter;

import org.auraframework.system.Annotations.Controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.auraframework.system.Annotations.AuraEnabled;
import org.auraframework.system.Annotations.Key;

@Controller
public class HomepageController {

	
    @AuraEnabled
    public static String getAppName(@Key("appKey") String importantInfo) {
    	
    	try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
			System.out.println(conn.getSchema());
			conn.close();
    	} catch (Exception e) {
    		
    	}
    	 throw new NullPointerException();
    	
        // return "A twitter clone " + importantInfo;
    }
}
