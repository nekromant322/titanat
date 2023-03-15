package org.override.service;

import org.override.filters.BlurFilter;
import org.override.filters.EmbossFilter;
import org.override.filters.Filter;
import org.override.model.NormalizationSettings;
import org.override.model.Range;
import org.override.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.override.service.GreyScalePartitionService.calculateDominatingGreyScaleRange;
import static org.override.utils.ImageUtils.getGreyScale;

public class NormalizationService {

    private Filter embossFilter = new EmbossFilter();
    private Filter blurFilter = new BlurFilter(new ImageMatrixConverter());
    private SplitterService splitterService = new SplitterService();

    public BufferedImage normalize(BufferedImage image, NormalizationSettings settings) {
        List<BufferedImage> splited = splitterService.split(image, settings.getSplitRows(), settings.getSplitColumns());
        List<BufferedImage> normalizedSplited = new ArrayList<>();

        for(BufferedImage img : splited) {
            Range range = calculateDominatingGreyScaleRange(img, settings.getGreyScaleRange());
            BufferedImage normalized = normalize(img, range);
            normalizedSplited.add(normalized);
        }
        BufferedImage composedImage = splitterService.compose(normalizedSplited, image, settings.getSplitRows(), settings.getSplitColumns());

        return composedImage;
    }


    private BufferedImage normalize(BufferedImage image, Range range) {
        BufferedImage copy = ImageUtils.deepCopy(image);
        for (int x = 0; x < copy.getWidth(); x++) {
            for (int y = 0; y < copy.getHeight(); y++) {
                int greyscale = (int) getGreyScale(image, x, y);
                if (!range.isInRange(greyscale)) {
                    copy.setRGB(x, y, 0);
                } else {
                    int whiteInt = 0xFFFFFF;
                    copy.setRGB(x, y, whiteInt);
                }
            }
        }
        return copy;
    }


}
