import Controller.ViewController;
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.Suppliers.*;
import Utils.ExectueMain.Initializer;
import Utils.FIleWork.CSV;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        initializer.GO();

        ViewController vc = new ViewController(initializer,initializer.getFabricPanel(), initializer.getSlider());

    }
}