package Utils;


import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FactLogger {
    private static final Logger log = Logger.getLogger("Fac");

    static {
        try {
            FileHandler fh = new FileHandler("factory_logs.log",true);
            log.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Log dead");
        }
    }

    public static void info(String m){log.info(m);}
}


