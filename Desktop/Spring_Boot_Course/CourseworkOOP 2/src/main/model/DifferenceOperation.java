package main.model;

import main.controller.ImageProcessor;
import main.utilites.OperationUtilities;
import main.view.DifferenceUI;
import main.view.OperationDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DifferenceOperation implements OperationAction {
    final private ImageProcessor imageProcessor;
    final private DifferenceUI differenceUI;

    public DifferenceOperation(ImageProcessor imageProcessor, JFileChooser jFileChooser) {
        this.imageProcessor = imageProcessor;
        differenceUI = new DifferenceUI(jFileChooser);
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog(imageProcessor, differenceUI);
        dialog.setVisible(true);
        if (!dialog.wasCancelled()) {
            try {
                BufferedImage otherImage = ImageIO.read(differenceUI.getOtherImagePath());

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.difference(inputRGB, otherRGB);
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
