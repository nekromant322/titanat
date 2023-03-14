package org.override;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Splitter {

    @SneakyThrows
    public static void main(String[] args) {
//        BufferedImage image = ImageToPixels.getImage("C:\\Users\\ПК\\IdeaProjects\\titanat\\src\\main\\resources\\photo_2023-03-13_19-43-29.jpg");
//        List<BufferedImage> split = split(image, 3, 3);
//        WriteUtils.writeFiles(split);
    }

    @SneakyThrows
    public static List<BufferedImage> split(BufferedImage image, int rows, int columns) {
        List<BufferedImage> list = new ArrayList<>();

        // Equally dividing original image into subimages
        int subimageWidth = image.getWidth() / columns;
        int subimageHeight = image.getHeight() / rows;

        int currentImg = 0;

        // iterating over rows and columns for each sub-image
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Creating sub image
//                imgs[currentImg] = new BufferedImage(subimageWidth, subimageHeight, image.getType());
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

    public static BufferedImage compose(List<BufferedImage> bufferedImages, BufferedImage originalImage, int rows, int columns) {
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
}
