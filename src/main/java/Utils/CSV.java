package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {
    private final String CSV_file = "file";
    private final String log_file = "factory_logs.log";


    public void input_info(){
        try(BufferedReader reader = new BufferedReader(new FileReader(log_file))) {
            String line;

            while((line=reader.readLine())!=null){

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }
}
