package filters;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage filter(BufferedImage image);
}
