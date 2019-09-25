package Converter;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import Model.User;

public class UserConverter {

	// convert Person Object to MongoDB DBObject
	// take special note of converting id String to ObjectId
	public static DBObject toDBObject(User user) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append("version", user.getVersion()).append("url",
				user.getUrl());
		if (user.getId() != null)
			builder = builder.append("_id", new ObjectId(user.getId()));
		return builder.get();
	}

	// convert DBObject Object to Person
	// take special note of converting ObjectId to String
	public static User toUser(DBObject doc) {
		User user = new User();
		user.setVersion((String) doc.get("version"));
		user.setUrl((String) doc.get("url"));
		user.setCpoId((String)doc.get("cpoId"));
//		ObjectId id = (ObjectId) doc.get("_id");
//		user.setId(id.toString());
		return user;

	}

}
