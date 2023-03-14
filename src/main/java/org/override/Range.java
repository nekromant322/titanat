package org.override;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Range implements Comparable<Range> {
    private int min;
    private int max;
    private int count;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(int value) {
        return value > min && value < max;
    }

    public void incrementCount() {
        count++;
    }

    @Override
    public int compareTo(Range o) {
        return Integer.compare(count, o.getCount());
    }
}
