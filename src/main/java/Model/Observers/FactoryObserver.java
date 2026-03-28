package Model.Observers;

import java.io.*;
import java.util.Map;

public class FactoryObserver {
    private final Map<String, Integer> obs;
    private final String logPath;
    private final String csvPath;

    public FactoryObserver(Map<String,Integer> m, String lp, String sp){
        this.obs = m;
        this.csvPath=sp;
        this.logPath=lp;
    }


    public void parceAndPut() throws FileNotFoundException {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(logPath))) {
            PrintWriter csvWritter = new PrintWriter(new BufferedWriter(new FileWriter(csvPath, true)));




        } catch (IOException r) {

        }
    }

}
