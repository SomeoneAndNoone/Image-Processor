package main.controller;

import main.controller.factory.ImageProcessorFactory;
import main.model.MenuOperations;
import main.view.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor extends JFrame {
    private final ImageProcessorFactory imageProcessorFactory;

    private static final long serialVersionUID = 1L;

    private final ImagePanel imagePanel = new ImagePanel();

    private BufferedImage image;

    private File loadedImage;

    private final JMenu opMenu = new JMenu("Operations");

    public ImageProcessor(ImageProcessorFactory imageProcessorFactory) {
        this.imageProcessorFactory = imageProcessorFactory;
        imageProcessorFactory.initializeFactory(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        final JMenuBar menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(ev -> doOpenImage());
        fileMenu.add(openItem);

        final JMenuItem revertItem = new JMenuItem("Revert");
        revertItem.addActionListener(ev -> revert());
        fileMenu.add(revertItem);

        menuBar.add(fileMenu);
        menuBar.add(this.opMenu);
        setJMenuBar(menuBar);

        add(this.imagePanel, BorderLayout.CENTER);
        pack();


        addMenuOperation(MenuOperations.Grayscale);
        addMenuOperation(MenuOperations.Tint);
        addMenuOperation(MenuOperations.ChromaKey);
        addMenuOperation(MenuOperations.Negative);
        addMenuOperation(MenuOperations.Threshold);
        addMenuOperation(MenuOperations.Extract);
        addMenuOperation(MenuOperations.Blend);
        addMenuOperation(MenuOperations.Difference);

        this.setVisible(true);
    }

    private void addMenuOperation(final MenuOperations menuOperation) {
        final JMenuItem item = new JMenuItem(menuOperation.name());
        item.addActionListener(ev -> {
            try {
                BufferedImage newImage = imageProcessorFactory.doNecessaryOperation(menuOperation, this.image);
                setImage(newImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.opMenu.add(item);
    }

    private File chooseImageFile() {
        if (imageProcessorFactory.getJFileChooser().showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return imageProcessorFactory.getJFileChooser().getSelectedFile();
        } else {
            return null;
        }
    }

    private void doOpenImage() {
        final File file = chooseImageFile();
        if (file != null) {
            loadImage(file);
        }
    }

    private void loadImage(final File file) {
        try {
            this.image = ImageIO.read(file);
            this.loadedImage = file;
            setImage(this.image);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setImage(final BufferedImage image) {
        this.image = image;
        this.imagePanel.setImage(image);
        pack();
    }

    private void revert() {
        if (this.loadedImage != null) {
            loadImage(this.loadedImage);
        }
    }
}
