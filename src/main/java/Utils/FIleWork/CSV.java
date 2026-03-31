package Utils.FIleWork;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSV {
    private final String log_file = "factory_logs.log";
    private final Map<String, Integer> parseMap = new HashMap<>();
    private String line;

    public void read(){
        try(BufferedReader reader = new BufferedReader(new FileReader(log_file))) {

            class idkMan{
                private String warehouseType;

                public void catchType(){

                }
            }

            while((line = reader.readLine())!=null){

            }







        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }
}
