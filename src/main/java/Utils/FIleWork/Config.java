package Utils.FIleWork;

import java.io.*;
import java.util.Properties;

public class Config {
    private final Properties pr = new Properties();

    public Config(){
        try(InputStream f = getClass().getClassLoader().getResourceAsStream("config")){
            if(f==null){
                System.out.println("gg null");
            }
            pr.load(f);
        } catch (IOException e) {
            System.out.println("gg");
        }
    }

    public int getInt(String key){
        return Integer.parseInt(pr.getProperty(key));
    }

    public boolean getBol(String key){
        return Boolean.parseBoolean(pr.getProperty(key));
    }

}
