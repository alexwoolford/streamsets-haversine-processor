package io.woolford.stage.processor.haversine;

import com.streamsets.pipeline.api.*;

@StageDef(
        version = 1,
        label = "Haversine Processor",
        description = "",
        icon = "haversine.png",
        onlineHelpRefUrl = ""
)
@ConfigGroups(Groups.class)
@GenerateResourceBundle
public class HaversineDProcessor extends HaversineProcessor {

    @ConfigDef(
            required = true,
            type = ConfigDef.Type.STRING,
            defaultValue = "default",
            label = "Haversine Config",
            displayPosition = 10,
            group = "HAVERSINE"
    )
    public String config;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfig() {
        return config;
    }

}
