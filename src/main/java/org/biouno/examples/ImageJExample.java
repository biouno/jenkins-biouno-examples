package org.biouno.examples;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;

public class ImageJExample {

    public static void main(String[] args) {
        // Source: http://stackoverflow.com/questions/10676136/how-can-i-use-imagej-as-a-library-for-a-separate-java-application
        ImagePlus imgPlus = new ImagePlus("file:///tmp/in.jpg");
        ImageProcessor imgProcessor = imgPlus.getProcessor();
        //imgProcessor.invert();
        FileSaver fs = new FileSaver(imgPlus);
        //fs.saveAsJpeg("/tmp/out-inverted.jpg");
        BufferedImage bufferedImage = imgProcessor.getBufferedImage();
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                int grayLevel = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int r = grayLevel;
                int g = grayLevel;
                int b = grayLevel;
                int rgb = (r << 16) | (g << 8) | b;
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        ImagePlus grayImg = new ImagePlus("gray", bufferedImage);
        fs = new FileSaver(grayImg);
        fs.saveAsJpeg("/tmp/out-gray.jpg");
    }

}
