package ui.twitter.db;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.auraframework.util.json.Json;
import org.auraframework.util.json.JsonSerializable;

@Entity
@Table(name = "Tweets", schema="public")
public class Tweet implements JsonSerializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	public long getId() { return id; }

	private String message;
	public String getMessage() { return message; }

	private String name;
	public String getName() { return name; }

	private String imageUrl;
	public String getImageUrl() { return imageUrl; }

	private String date;
	public String getDate() { return date; }
	
	Tweet(String message, String name, String imageUrl, String date) {
		this.message = message;
		this.name = name;
		this.imageUrl = imageUrl;
		this.date = date;
	}

	@Override
	public void serialize(Json arg0) throws IOException {
		arg0.writeMapBegin();
		arg0.writeMapEntry("name", name);
		arg0.writeMapEntry("message", message);
		arg0.writeMapEntry("imageUrl", imageUrl);
		arg0.writeMapEntry("date", date);
		arg0.writeMapEnd();
	}
	
	@Override
	public String toString() {
		return "name=" + name + ", message=" + message + ", imageUrl=" + imageUrl + ", date= " + date;
	}
}
