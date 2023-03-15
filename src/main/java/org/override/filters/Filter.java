package org.override.filters;

import org.override.model.NormalizationSettings;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage filter(BufferedImage image, NormalizationSettings settings);
}
