package io.woolford.stage.processor.haversine;

import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.SingleLaneProcessor;
import com.streamsets.pipeline.api.base.SingleLaneRecordProcessor;
import io.woolford.stage.lib.haversine.Errors;

import java.util.List;

public abstract class HaversineProcessor extends SingleLaneRecordProcessor {

    /**
     * Gives access to the UI configuration of the stage provided by the {@link HaversineDProcessor} class.
     */
    public abstract String getConfig();

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ConfigIssue> init() {
        // Validate configuration values and open any required resources.
        List<ConfigIssue> issues = super.init();

        if (getConfig().equals("invalidValue")) {
            issues.add(
                    getContext().createConfigIssue(
                            Groups.HAVERSINE.name(), "config", Errors.HAVERSINE_00, "Here's what's wrong..."
                    )
            );
        }

        // If issues is not empty, the UI will inform the user of each configuration issue in the list.
        return issues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        // Clean up any open resources.
        super.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(Record record, SingleLaneBatchMaker batchMaker) throws StageException {

        HaversineDistance haversineDistance = new HaversineDistance();

        Double lat1 = (Double) record.get("/latitude").getValue();
        Double lon1 = (Double) record.get("/longitude").getValue();
        Double lat2 = (Double) record.get("/previous.latitude").getValue();
        Double lon2 = (Double) record.get("/previous.longitude").getValue();

        Double distance = haversineDistance.calculateDistance(lat1, lon1, lat2, lon2);
        Field distanceField = Field.create(distance);

        record.set("/distance", distanceField);

        batchMaker.addRecord(record);

    }

}
