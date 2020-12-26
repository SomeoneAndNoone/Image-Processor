package main.model;

import main.controller.ImageProcessor;
import main.utilites.OperationUtilities;
import main.view.OperationDialog;
import main.view.ThresholdUI;

import java.awt.image.BufferedImage;

public class ThresholdOperation implements OperationAction {
    private final ImageProcessor imageProcessor;
    private final ThresholdUI thresholdUI = new ThresholdUI();
    public ThresholdOperation(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( imageProcessor, thresholdUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.threshold(inputRGB, thresholdUI.getThreshold());
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
