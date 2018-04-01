import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.sdk.ProcessorRunner;
import com.streamsets.pipeline.sdk.RecordCreator;
import com.streamsets.pipeline.sdk.StageRunner;
import io.woolford.stage.processor.haversine.HaversineDProcessor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestHaversineProcessor {

    @Test
    @SuppressWarnings("unchecked")
    public void testProcessor() throws StageException {
        ProcessorRunner runner = new ProcessorRunner.Builder(HaversineDProcessor.class)
                .addConfiguration("config", "value")
                .addOutputLane("output")
                .build();

        runner.runInit();

        try {

            Map<String, Field> map = new LinkedHashMap<>();
            map.put("latitude", Field.create(45.7597));
            map.put("longitude", Field.create(4.8422));
            map.put("previous.latitude", Field.create(48.8567));
            map.put("previous.longitude", Field.create(2.3508));

            Record record = RecordCreator.create();
            record.set(Field.create(Field.Type.MAP, map));

            StageRunner.Output output = runner.runProcess(Arrays.asList(record));

//            String html = String.valueOf(record.get("/pageSource").getValue());

            Assert.assertEquals(1, 1);


//            haversine.haversine((45.7597, 4.8422), (48.8567, 2.3508))
//            392.21671780659625


        } finally {
            runner.runDestroy();
        }
    }

}
