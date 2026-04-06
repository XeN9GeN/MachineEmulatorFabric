package View;

import Model.FactoryComponents.Suppliers.Supplier;
import javax.swing.*;
import java.awt.*;

// Наследуемся от JPanel
public class SliderPanel extends JPanel {
    private final JSlider slider;
    private final JLabel valueLabel;

    public SliderPanel(Supplier<?>... suppls) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        slider = new JSlider(100, 5000, 1000);
        slider.setMajorTickSpacing(1000);
        slider.setMinorTickSpacing(500);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        valueLabel = new JLabel("Currently delay: 1000 mc");
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        slider.addChangeListener(e -> {
            int delay = slider.getValue();
            valueLabel.setText("Currently delay: " + delay + " mc");

            for (Supplier<?> s : suppls) {
                s.setDelay(delay);
            }
        });

        add(slider);
        add(valueLabel);
    }
}
