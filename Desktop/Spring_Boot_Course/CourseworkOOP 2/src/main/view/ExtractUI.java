package main.view;

import main.model.ColourComponent;

import javax.swing.*;
import java.awt.*;

public class ExtractUI extends JPanel {
    private final JRadioButton rButton = new JRadioButton("Red");
    private final JRadioButton gButton = new JRadioButton("Green");
    private final JRadioButton bButton = new JRadioButton("Blue");

    private ColourComponent selectedColor = ColourComponent.RED;

    public ExtractUI() {
        super(new BorderLayout());

        final JPanel radioPanel = new JPanel();
        radioPanel.add(this.rButton);
        radioPanel.add(this.gButton);
        radioPanel.add(this.bButton);

        // make radio buttons mutually exclusive
        final ButtonGroup bg = new ButtonGroup();
        bg.add(this.rButton);
        bg.add(this.gButton);
        bg.add(this.bButton);

        this.rButton.setSelected(true);

        this.rButton.addActionListener((ev) -> colourChosen(ColourComponent.RED));
        this.gButton.addActionListener((ev) -> colourChosen(ColourComponent.GREEN));
        this.bButton.addActionListener((ev) -> colourChosen(ColourComponent.BLUE));

        add(radioPanel, BorderLayout.CENTER);
    }

    private void colourChosen(final ColourComponent colour) {
        this.selectedColor = colour;
    }

    public ColourComponent getChosenColor() {
        return this.selectedColor;
    }
}
