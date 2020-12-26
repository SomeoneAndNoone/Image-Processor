package main.view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import main.controller.ImageProcessor;

import javax.swing.*;
import java.awt.*;

public class OperationDialog extends JDialog {

    private final JButton applyButton = new JButton("Apply");
    private final JButton cancelButton = new JButton("Cancel");
    
    private boolean wasCancelled = true;
    
    public OperationDialog(ImageProcessor frame, JPanel optionPanel) {
        super(frame, true);
      
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        add(optionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        applyButton.addActionListener(ev -> doApplyButton());
        cancelButton.addActionListener(ev -> setVisible(false));

        pack();
    }

    public boolean wasCancelled() {
    	return this.wasCancelled;
    }
    
    private void doApplyButton() {
        setVisible(false);
        this.wasCancelled = false;
    }
}
