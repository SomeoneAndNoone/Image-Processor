package main.controller.factory;

import main.model.*;
import main.controller.ImageProcessor;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;



public class ImageProcessorFactoryImpl implements ImageProcessorFactory {
    private final JFileChooser chooser = new JFileChooser();

    private ImageProcessor imageProcessor;

    private OperationAction chromaKeyOperation;
    private OperationAction grayscaleOperation;
    private OperationAction tintOperation;
    private OperationAction negativeOperation;
    private OperationAction thresholdOperation;
    private OperationAction extractOperation;
    private OperationAction blendOperation;
    private OperationAction differenceOperation;

    public ImageProcessorFactoryImpl() {
        this.chooser.setMultiSelectionEnabled(false);
        this.chooser.setCurrentDirectory(new File(".")); // set current directory
    }

    @Override
    public void initializeFactory(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;

        this.chooser.setMultiSelectionEnabled(false);
        this.chooser.setCurrentDirectory(new File("."));
    }

    @Override
    public JFileChooser getJFileChooser() {
        return chooser;
    }

    private OperationAction getChromaKeyOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");
        if(chromaKeyOperation==null)
            chromaKeyOperation = new ChromaKeyOperation(imageProcessor, chooser);

        return chromaKeyOperation;
    }

    private OperationAction getGrayscaleOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");

        if(grayscaleOperation==null)
            grayscaleOperation = new GrayscaleOperation(imageProcessor);

        return grayscaleOperation;
    }

    private OperationAction getTintOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");

        if(tintOperation==null)
            tintOperation = new TintOperation(imageProcessor);

        return tintOperation;
    }

    private OperationAction getNegativeOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");

        if(negativeOperation==null)
            negativeOperation = new NegativeOperation(imageProcessor);

        return negativeOperation;
    }

    public OperationAction getThresholdOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");

        if(thresholdOperation==null)
            thresholdOperation = new ThresholdOperation(imageProcessor);

        return thresholdOperation;
    }

    public OperationAction getExtractOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");

        if(extractOperation==null)
            extractOperation = new ExtractOperation(imageProcessor);

        return extractOperation;
    }

    public OperationAction getBlendOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");

        if(blendOperation==null)
            blendOperation = new BlendOperation(imageProcessor, getJFileChooser());

        return blendOperation;
    }

    public OperationAction getDifferenceOperation() throws Exception {
        if(imageProcessor==null)
            throw new Exception("Unintialized Factory Exception");

        if(differenceOperation==null)
            differenceOperation = new DifferenceOperation(imageProcessor, getJFileChooser());

        return differenceOperation;
    }

    @Override
    public BufferedImage doNecessaryOperation(MenuOperations menuOperation, BufferedImage bufferedImage) throws Exception {
        return switch (menuOperation) {
            case Grayscale -> getGrayscaleOperation().doOperation(bufferedImage);
            case Tint -> getTintOperation().doOperation(bufferedImage);
            case ChromaKey -> getChromaKeyOperation().doOperation(bufferedImage);
            case Negative -> getNegativeOperation().doOperation(bufferedImage);
            case Threshold -> getThresholdOperation().doOperation(bufferedImage);
            case Extract -> getExtractOperation().doOperation(bufferedImage);
            case Blend -> getBlendOperation().doOperation(bufferedImage);
            case Difference -> getDifferenceOperation().doOperation(bufferedImage);
        };
    }
}




















