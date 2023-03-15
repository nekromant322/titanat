package org.override.service;

import lombok.SneakyThrows;
import org.override.model.Range;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.override.utils.ImageUtils.getGreyScale;

public class GreyScalePartitionService {


    @SneakyThrows
    public List<BufferedImage> split(BufferedImage image, int rows, int columns) {
        List<BufferedImage> list = new ArrayList<>();

        // Equally dividing original image into subimages
        int subimageWidth = image.getWidth() / columns;
        int subimageHeight = image.getHeight() / rows;

        int currentImg = 0;
        // iterating over rows and columns for each sub-image
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Creating sub image
                list.add(new BufferedImage(subimageWidth, subimageHeight, image.getType()));
                Graphics2D imgCreator = list.get(currentImg).createGraphics();

                // coordinates of source image
                int srcFirstX = subimageWidth * j;
                int srcFirstY = subimageHeight * i;

                // coordinates of sub-image
                int dstCornerX = subimageWidth * j + subimageWidth;
                int dstCornerY = subimageHeight * i + subimageHeight;

                imgCreator.drawImage(image, 0, 0, subimageWidth, subimageHeight, srcFirstX, srcFirstY, dstCornerX, dstCornerY, null);
                currentImg++;
            }
        }
        return list;

    }

    public BufferedImage compose(List<BufferedImage> bufferedImages, BufferedImage originalImage, int rows, int columns) {
        BufferedImage composedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
        Graphics2D imgCreator = composedImage.createGraphics();

        int i = 0;
        int j = 0;
        for (int k = 0; k < bufferedImages.size(); k++) {
            int subimageWidth = bufferedImages.get(k).getWidth();
            int subimageHeight = bufferedImages.get(k).getHeight();
            int srcFirstX = subimageWidth * i;
            int srcFirstY = subimageHeight * j;

            imgCreator.drawImage(bufferedImages.get(k),  srcFirstX, srcFirstY, srcFirstX + subimageWidth, srcFirstY + subimageHeight,0, 0, subimageWidth, subimageHeight, null);
            i++;
            if (i == columns) {
                j++;
                i = 0;
            }
        }


        return composedImage;
    }

    @SneakyThrows
    public static Range calculateDominatingGreyScaleRange(BufferedImage image, int range) {
        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(0, range));
        for (int i = 1; i < 255 / range; i++) {
            ranges.add(new Range(ranges.get(i - 1).getMax(), ranges.get(i - 1).getMax() + range));
        }

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int greyScale = (int) getGreyScale(image, x, y);
                for (Range aRange : ranges) {
                    if (aRange.isInRange(greyScale)) {
                        aRange.incrementCount();
                    }
                }
            }
        }
        List<Range> collect = ranges.stream().sorted().collect(Collectors.toList());

        return collect.get(collect.size() - 1);
    }
}
