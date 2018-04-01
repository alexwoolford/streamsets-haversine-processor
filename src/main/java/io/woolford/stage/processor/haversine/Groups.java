package io.woolford.stage.processor.haversine;

import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;

@GenerateResourceBundle
public enum Groups implements Label {
    HAVERSINE("Haversine"),;

    private final String label;

    private Groups(String label) {
        this.label = label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return this.label;
    }
}
