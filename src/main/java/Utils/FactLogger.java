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
        log.info("Exe is running");
    }

    public static void info(String m){log.info(m);}
    public static void error(String m, Throwable e) {
        log.log(Level.SEVERE,m,e);
    }
}



//
//static Logger loger = Logger.getLogger(Main.class.getName());
//
//public static void main(String[] args) throws Exception {
//
//    try{
//        FileHandler fh= new FileHandler("calc_logs.log",true);
//        loger.addHandler(fh);//return void
//        fh.setFormatter(new SimpleFormatter());
//    }
//    catch (Exception e){
//        loger.log(Level.SEVERE, "Log dead");
//    }
//    loger.info("Exe is running");