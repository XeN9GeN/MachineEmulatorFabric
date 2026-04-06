package Utils.FIleWork;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSV{
    String log_file = "factory_logs.log";
    String ex_file = "logs_factory.csv";

    public CSV(){}

    public void BD(){
        // Регулярка: [Группа 1: Время] [Группа 2: Объект] [Группа 3: Действие] [Группа 4: Данные]
        Pattern pattern = Pattern.compile("INFO: Time: (\\d{1,2}:\\d{2}:\\d{2}) (Dealer \\d+): (Auto \\d+) \\((.*)\\)");
        try(BufferedReader reader = new BufferedReader(new FileReader(log_file));
            PrintWriter writer = new PrintWriter(new FileWriter(ex_file))) {

            writer.println("Time;Component;Action;Details");
            String line;
            while((line=reader.readLine())!=null){
                Matcher m = pattern.matcher(line);//класс сравнения строки с регуляркой под Pattern
                if(m.find()){
                    //time dealer auto details
                    writer.printf("%s;%s;%s;%s%n", m.group(1), m.group(2), m.group(3), m.group(4));
                }
            }
            System.out.println("CSV export finished successfully.");


        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}