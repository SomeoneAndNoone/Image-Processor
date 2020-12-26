package main;

import main.controller.factory.ImageProcessorFactoryImpl;
import main.controller.ImageProcessor;

import javax.swing.*;

public class Main {

    public static void main(final String[] args) {

        SwingUtilities.invokeLater(() ->
            new ImageProcessor(new ImageProcessorFactoryImpl())
        );
    }
}
