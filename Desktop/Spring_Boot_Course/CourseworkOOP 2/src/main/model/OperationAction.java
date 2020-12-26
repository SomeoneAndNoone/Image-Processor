package main.model;

import java.awt.image.BufferedImage;

public interface OperationAction {
    public BufferedImage doOperation(final BufferedImage inputImage);
}
