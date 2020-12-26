package main.model;

import main.controller.ImageProcessor;
import main.utilites.OperationUtilities;
import main.view.OperationDialog;
import main.view.TintUI;
import java.awt.image.BufferedImage;

public class TintOperation implements OperationAction {

    private final ImageProcessor imageProcessor;
    private final TintUI tintUI = new TintUI();

    public TintOperation(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( imageProcessor, tintUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            final ColourComponent band = tintUI.getChosenColor();
            final double alpha = tintUI.getAlpha() / 100.0;
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.tint(inputRGB, alpha, band);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
