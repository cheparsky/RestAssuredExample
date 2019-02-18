package cheparsky.utilities;

import java.util.UUID;

public class RandomStringUUID {

    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }
}
