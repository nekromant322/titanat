package org.override;

import lombok.SneakyThrows;
import org.override.model.NormalizationSettings;
import org.override.service.NormalizationService;
import org.override.utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Main {



    @SneakyThrows
    public static void main(String[] args) {
        NormalizationService normalizationService = new NormalizationService();
        BufferedImage image = ImageUtils.getImage("src/main/resources/original/photo_2023-03-13_19-43-29.jpg");
        NormalizationSettings settings =
                NormalizationSettings.builder()
                        .splitRows(32)
                        .splitColumns(18)
                        .greyScaleRange(30)
                        .medianBlurIterations(1)
                        .medianBlurBrushSize(3)
                        .build();

        BufferedImage normalized = normalizationService.normalize(image, settings);

        ImageUtils.writeImgToFile(normalized, "result/result");
    }


}