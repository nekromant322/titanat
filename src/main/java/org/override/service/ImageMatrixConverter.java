package org.override.service;

import lombok.SneakyThrows;
import org.bytedeco.opencv.opencv_core.Mat;
import org.override.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;

public class ImageMatrixConverter {

    private static final String TEMP_OPEN_CVFILE_READ = "src/main/resources/tmp/tempOpenCVFileRead.jpg";
    private static final String TEMP_OPEN_CVFILE_WRITE = "src/main/resources/tmp/tempOpenCVFileWRITE.jpg";


    @SneakyThrows
    public Mat convertToMat(BufferedImage image) {
        ImageIO.write(image, "jpg", new File(TEMP_OPEN_CVFILE_READ));
        Mat mat = imread(TEMP_OPEN_CVFILE_READ);
        Files.deleteIfExists(Path.of(TEMP_OPEN_CVFILE_READ));
        return mat;
    }

    @SneakyThrows
    public BufferedImage convertToImage(Mat mat) {
        imwrite(TEMP_OPEN_CVFILE_WRITE, mat);
        BufferedImage image = ImageUtils.getImage(TEMP_OPEN_CVFILE_WRITE);
        Files.deleteIfExists(Path.of(TEMP_OPEN_CVFILE_WRITE));
        return image;
    }
}
