package View;

import Model.FactoryComponents.Suppliers.Supplier;

import javax.swing.*;

public class Slider extends JFrame {
    private final JSlider slider;
    private final JLabel valueLabel;


    public Slider(Supplier<?>... suppls){

        setTitle("Speed");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        slider = new JSlider(100,5000,1000);
        slider.setMajorTickSpacing(1000);
        slider.setMinorTickSpacing(500);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        valueLabel = new JLabel("Currently delay: 1000mc",SwingConstants.CENTER);

        slider.addChangeListener(e->{
            int delay = slider.getValue();
            valueLabel.setText("Currently delay: " + delay +" mc");

            for(Supplier<?> s : suppls){
                s.setDelay(delay);
            }
        });

        add(slider);
        add(valueLabel);
        setVisible(true);
    }
}
