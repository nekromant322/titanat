package org.override.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.override.model.NormalizationSettings;
import org.override.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbossFilter implements Filter {

    private Kernel kernel = new Kernel(3, 3,
            new float[]{
                    -2, 0, 0,
                    0, 1, 0,
                    0, 0, 2});

    @Override
    public BufferedImage filter(BufferedImage image, NormalizationSettings settings) {
        BufferedImageOp op = new ConvolveOp(kernel);
        BufferedImage embossedBufferedImage = ImageUtils.deepCopy(image);
        op.filter(image, embossedBufferedImage);
        return embossedBufferedImage;
    }

}
