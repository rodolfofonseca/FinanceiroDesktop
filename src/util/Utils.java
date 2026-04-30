package util;
import org.bson.types.ObjectId;
import org.bson.BsonDateTime;

import java.time.Instant;

public class Utils {

    public static BsonDateTime now() {
        return new BsonDateTime(Instant.now().toEpochMilli());
    }

    public static ObjectId toId(String id) {
        return new ObjectId(id);
    }
}