package filters;

import org.override.ImageToPixels;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class EmbossFilter implements Filter {

    @Override
    public BufferedImage filter(BufferedImage image) {
        //        Kernel kernel = new Kernel(3, 3,
//                new float[]{
//                        -2, 0, -1,
//                        0, 1, 0,
//                        1, 0, 2});
        Kernel kernel = new Kernel(3, 3,
                new float[]{
                        -2, 0, 0,
                        0, 1, 0,
                        0, 0, 2});

        BufferedImageOp op = new ConvolveOp(kernel);
        BufferedImage embossedBufferedImage = ImageToPixels.deepCopy(image);
        op.filter(image, embossedBufferedImage);
        return embossedBufferedImage;
    }

}
