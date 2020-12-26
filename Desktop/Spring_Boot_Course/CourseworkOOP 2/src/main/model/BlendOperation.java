package main.model;

import main.controller.ImageProcessor;
import main.utilites.OperationUtilities;
import main.view.BlendUI;
import main.view.OperationDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlendOperation implements OperationAction {
    private final ImageProcessor imageProcessor;
    private final JFileChooser jFileChooser;
    public BlendOperation(ImageProcessor imageProcessor, JFileChooser jFileChooser) {
        this.imageProcessor = imageProcessor;
        this.jFileChooser = jFileChooser;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        BlendUI blendUI = new BlendUI(jFileChooser);
        final OperationDialog dialog = new OperationDialog(imageProcessor, blendUI);
        dialog.setVisible(true);
        if (!dialog.wasCancelled()) {
            try {
                double sensitivity = blendUI.getSensitivity();
                BufferedImage otherImage = ImageIO.read(blendUI.getOtherImagePath());

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.blend(inputRGB, otherRGB, sensitivity);
                        OperationUtilities.setRGB(x, y, outputRGB, output);
                    }
                }
                return output;
            } catch (IOException ex) {
                ex.printStackTrace();
                return inputImage;
            }
        } else {
            return inputImage;
        }
    }
}
