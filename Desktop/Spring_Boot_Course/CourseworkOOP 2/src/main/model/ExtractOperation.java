package main.model;

import main.controller.ImageProcessor;
import main.utilites.OperationUtilities;
import main.view.ExtractUI;
import main.view.OperationDialog;

import java.awt.image.BufferedImage;

public class ExtractOperation implements OperationAction {
    final private ImageProcessor imageProcessor;
    final private ExtractUI extractUI = new ExtractUI();
    public ExtractOperation(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( imageProcessor, extractUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            final ColourComponent band = extractUI.getChosenColor();
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.extract(inputRGB, band);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
