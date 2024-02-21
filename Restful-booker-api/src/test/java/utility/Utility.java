package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utility {


    public static Object convertToAppropriateType(String value){
        if(value.equals("true")||value.equals("false")){
            return Boolean.parseBoolean(value);
        }
        if (value.matches("\\d+")){
            return Integer.parseInt(value);
        }else {
            return value;
        }
    }

    public static String genStrFromRsrc(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }


}
