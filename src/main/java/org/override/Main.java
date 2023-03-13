package org.override;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static final double RED_SCALE = 0.299;
    public static final double GREEN_SCALE = 0.587;
    public static final double BLUE_SCALE = 0.114;

    public static final int GREY_SCALE_SEARCH_RANGE = 15;


    @SneakyThrows
    public static Range calculateDominatingGreyScaleRange(BufferedImage image, int range) {
        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(0, range));
        for (int i = 1; i < 255 / range; i ++) {
            ranges.add(new Range(ranges.get(i - 1).getMax(), ranges.get(i - 1).getMax() + range));
        }


        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int greyScale = (int) getGreyScale(image, x, y);
                for(Range aRange : ranges) {
                    if (aRange.isInRange(greyScale)) {
                        aRange.incrementCount();
                    }
                }
            }
        }
        List<Range> collect = ranges.stream().sorted().collect(Collectors.toList());

        return collect.get(collect.size() - 1);
    }

    public static BufferedImage normalize(BufferedImage image, Range range) {
        BufferedImage copy = ImageToPixels.deepCopy(image);
        for (int x = 0; x < copy.getWidth(); x++) {
            for (int y = 0; y < copy.getHeight(); y++) {
                int greyscale = (int) getGreyScale(image, x, y);
                if (range.isInRange(greyscale)) {
                    copy.setRGB(x, y, 0);
                }
                else {
                    int whiteInt = 0xFFFFFF;
                    copy.setRGB(x, y, whiteInt);
                }


            }

        }
        return copy;

    }

    @SneakyThrows
    public static void main(String[] args) {

        BufferedImage image = ImageToPixels.getImage("C:\\Users\\ПК\\IdeaProjects\\titanat\\src\\main\\resources\\photo_2023-03-13_19-43-29.jpg");


        Range range = calculateDominatingGreyScaleRange(image, GREY_SCALE_SEARCH_RANGE);

        BufferedImage normalized = normalize(image, range);

        File outputfile = new File("src/main/resources/copy.jpg");
        ImageIO.write(normalized, "jpg", outputfile);


    }

    private static double getGreyScale(BufferedImage image, int x, int y) {
        WritableRaster raster = image.getRaster();
        Object dataElements = raster.getDataElements(x, y, null);
        ColorModel colorModel = image.getColorModel();
        return RED_SCALE * colorModel.getRed(dataElements) +
                GREEN_SCALE * colorModel.getGreen(dataElements) +
                BLUE_SCALE * colorModel.getBlue(dataElements);
    }

//    private static double filterByGreyScale(double minScale) {
//
//    }

}