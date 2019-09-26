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

public class DbConnection

{

	public List<User> readAll() throws UnknownHostException

	{
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("sumit");
		DBCollection coll = db.getCollection("data");
		System.out.println("Connected to Mongo Db");

		List<User> data = new ArrayList<User>();
		DBObject allQuery = new BasicDBObject();
		DBObject removeIdProjection = new BasicDBObject("_id", 0);
		DBCursor cursor = coll.find(allQuery, removeIdProjection);
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

	public DBObject createToken(User user) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("sumit");
		DBCollection coll = db.getCollection("token");
		System.out.println("Connected to Mongo Db");

		BasicDBObject document = new BasicDBObject();
		document.put("token", user.getCpoId());
		coll.insert(document);

		return document;

	}

	public String readToken() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("sumit");
		DBCollection coll = db.getCollection("token");
		System.out.println("Connected to Mongo Db");

		DBObject allQuery = new BasicDBObject();
		DBObject removeIdProjection = new BasicDBObject("_id", 0);
		DBCursor cursor = coll.find(allQuery, removeIdProjection);
		String stored = (String) cursor.one().get("token");
		System.out.println("Stored token is " + stored);

		return stored;

	}

	public DBObject getSession() throws UnknownHostException {

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("sumit");
		DBCollection coll = db.getCollection("sessions");
		DBObject allQuery = new BasicDBObject();
		DBObject removeId = new BasicDBObject("_id", 0);
		DBCursor cursor = coll.find(allQuery, removeId);
		DBObject doc = cursor.next();
		return doc;

	}

	public DBCollection updateSession() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("sumit");
		DBCollection coll = db.getCollection("sessions");

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("kwh", 50.25));

		BasicDBObject searchQuery = new BasicDBObject().append("id", "101");
		coll.update(searchQuery, newDocument);
		return coll;
	}

}
