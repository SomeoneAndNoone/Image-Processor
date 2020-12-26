package main.controller.factory;

import main.model.MenuOperations;
import main.controller.ImageProcessor;

import javax.swing.*;
import java.awt.image.BufferedImage;

public interface ImageProcessorFactory {
    public JFileChooser getJFileChooser();
    public void initializeFactory(ImageProcessor imageProcessor);
    public BufferedImage doNecessaryOperation(MenuOperations menuOperation, BufferedImage bufferedImage) throws Exception;
}
