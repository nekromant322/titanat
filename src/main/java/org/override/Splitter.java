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
        BufferedImage image = ImageToPixels.getImage("C:\\Users\\ПК\\IdeaProjects\\titanat\\src\\main\\resources\\photo_2023-03-13_19-43-29.jpg");
        List<BufferedImage> split = split(image, 3, 3);
        WriteUtils.writeFiles(split);
    }

    @SneakyThrows
    public static List<BufferedImage> split(BufferedImage image, int rows, int columns) {
        List<BufferedImage> list = new ArrayList<>();

        // initializing array to hold subimages
//        BufferedImage imgs[] = new BufferedImage[rows * columns];

        // Equally dividing original image into subimages
        int subimage_Width = image.getWidth() / columns;
        int subimage_Height = image.getHeight() / rows;

        int current_img = 0;

        // iterating over rows and columns for each sub-image
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Creating sub image
//                imgs[current_img] = new BufferedImage(subimage_Width, subimage_Height, image.getType());
                list.add(new BufferedImage(subimage_Width, subimage_Height, image.getType()));
                Graphics2D img_creator = list.get(current_img).createGraphics();

                // coordinates of source image
                int src_first_x = subimage_Width * j;
                int src_first_y = subimage_Height * i;

                // coordinates of sub-image
                int dst_corner_x = subimage_Width * j + subimage_Width;
                int dst_corner_y = subimage_Height * i + subimage_Height;

                img_creator.drawImage(image, 0, 0, subimage_Width, subimage_Height, src_first_x, src_first_y, dst_corner_x, dst_corner_y, null);
                current_img++;
            }
        }

//        //writing sub-images into image files
//        for (int i = 0; i < rows * columns; i++) {
//            File outputFile = new File("img" + i + ".jpg");
//            ImageIO.write(list.get(i), "jpg", outputFile);
//        }
        System.out.println("Sub-images have been created.");
        return list;

    }
}
