package main.model;

import main.controller.ImageProcessor;
import main.utilites.OperationUtilities;
import main.view.GrayscaleUI;
import main.view.OperationDialog;

import java.awt.image.BufferedImage;

public class GrayscaleOperation implements OperationAction {
    private final ImageProcessor imageProcessor;
    private final GrayscaleUI grayscaleUI = new GrayscaleUI();

    public GrayscaleOperation(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog(imageProcessor, grayscaleUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.grayscale(inputRGB);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
