package MongoDb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import Converter.UserConverter;
import Model.User;

public class DbConnection {

	public List<User> readAll() throws UnknownHostException

	{

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("sumit");
		DBCollection coll = db.getCollection("data");
		System.out.println("Connected to Mongo Db");

		List<User> data = new ArrayList<User>();
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			User user = UserConverter.toUser(doc);
			data.add(user);
		}
		return data;

	}

	public DBObject createDocument(User user) throws UnknownHostException

	{
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("sumit");
		DBCollection coll = db.getCollection("data");
		System.out.println("Connected to Mongo Db");

		BasicDBObject document = new BasicDBObject();
		document.put("version", user.getVersion());
		document.put("url", user.getUrl());
		coll.insert(document);

		return document;

	}

}