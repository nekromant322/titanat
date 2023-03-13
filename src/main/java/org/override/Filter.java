package org.override;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Filter {

//    public static void main(String[] args) {
//
//        Kernel kernel = new Kernel(3, 3,
//                new float[]{
//
//                        -1, 0, 0,
//
//                        0, 1, 0,
//
//                        0, 0, 2});
//
//        BufferedImageOp op = new ConvolveOp(kernel);
//
//        BufferedImage image = ImageToPixels.getImage("C:\\Users\\ПК\\IdeaProjects\\titanat\\src\\main\\resources\\photo_2023-03-13_19-43-29.jpg");
//        image = op.filter(image, null);
//
//        WriteUtils.writeImgToFile(image, "emboss1");
//
//
//    }

    //todo поэксперементировать с матрицей
    public static BufferedImage emboss(BufferedImage image) {
        Kernel kernel = new Kernel(3, 3,
                new float[]{

                        -1, 0, 0,

                        0, 1, 0,

                        0, 0, 2});

        BufferedImageOp op = new ConvolveOp(kernel);
        BufferedImage embossedBufferedImage = ImageToPixels.deepCopy(image);
        op.filter(image, embossedBufferedImage);
        return embossedBufferedImage;
    }


}
