package main.view;

import javax.swing.*;
import java.awt.*;

public class ThresholdUI extends JPanel {
    private final JSlider thresholdSlider = new JSlider(0, 255);

    public ThresholdUI() {
        super(new BorderLayout());

        add(this.thresholdSlider, BorderLayout.SOUTH);
    }

    public int getThreshold() {
        return this.thresholdSlider.getValue();
    }

}
