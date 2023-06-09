package org.override.utils;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class WriteUtils {

    @SneakyThrows
    public static void writeFiles(List<BufferedImage> images) {
        //writing sub-images into image files
        for (int i = 0; i < images.size(); i++) {
            File outputFile = new File("src/main/resources/splited/img" + i + ".jpg");
            ImageIO.write(images.get(i), "jpg", outputFile);
        }
    }




}
