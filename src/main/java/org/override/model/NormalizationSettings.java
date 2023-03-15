package org.override.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NormalizationSettings {

    private int splitRows = 1;
    private int splitColumns = 1;
    private int greyScaleRange = 30;
    private int medianBlurIterations = 1;
    private int medianBlurBrushSize = 1;

}
