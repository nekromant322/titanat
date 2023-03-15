package org.override.filters;

import lombok.AllArgsConstructor;
import org.bytedeco.opencv.opencv_core.Mat;
import org.override.model.NormalizationSettings;
import org.override.service.ImageMatrixConverter;

import java.awt.image.BufferedImage;

import static org.bytedeco.opencv.global.opencv_imgproc.medianBlur;

@AllArgsConstructor
public class BlurFilter implements Filter {

    private ImageMatrixConverter imageMatrixConverter;

    @Override
    public BufferedImage filter(BufferedImage image, NormalizationSettings settings) {
        Mat mat = new ImageMatrixConverter().convertToMat(image);
        if (mat != null) {
            for (int i = 0; i < settings.getMedianBlurIterations(); i++) {
                medianBlur(mat, mat, settings.getMedianBlurBrushSize());
            }

        }
        return new ImageMatrixConverter().convertToImage(mat);
    }



}
