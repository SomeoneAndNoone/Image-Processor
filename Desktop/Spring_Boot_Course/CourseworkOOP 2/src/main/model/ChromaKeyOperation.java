package main.model;

import main.controller.ImageProcessor;
import main.utilites.OperationUtilities;
import main.view.ChromaKeyUI;
import main.view.OperationDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ChromaKeyOperation implements OperationAction {
    final ChromaKeyUI chromaKeyUI;
    final ImageProcessor imageProcessor;

    public ChromaKeyOperation(ImageProcessor imageProcessor, JFileChooser jFileChooser) {
        chromaKeyUI = new ChromaKeyUI(jFileChooser);
        this.imageProcessor = imageProcessor;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog(imageProcessor, chromaKeyUI);
        dialog.setVisible(true);
        if (!dialog.wasCancelled()) {
            try {
                double sensitivity = chromaKeyUI.getSensitivity();
                BufferedImage otherImage = ImageIO.read(chromaKeyUI.getOtherImagePath());

                int targetRGB = chromaKeyUI.getTargetColor().getRGB();

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.chromaKey(inputRGB, otherRGB, targetRGB, sensitivity);
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
