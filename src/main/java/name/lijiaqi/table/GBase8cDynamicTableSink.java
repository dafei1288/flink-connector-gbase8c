package name.lijiaqi.table;

import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.connector.jdbc.internal.options.JdbcOptions;
import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.table.connector.format.EncodingFormat;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.connector.sink.SinkFunctionProvider;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.types.DataType;

/**
 * @author lijiaqi
 */
public class GBase8cDynamicTableSink implements DynamicTableSink {

    private final JdbcOptions jdbcOptions;
    private final EncodingFormat<SerializationSchema<RowData>> encodingFormat;
    private final DataType dataType;

    public GBase8cDynamicTableSink(JdbcOptions jdbcOptions, EncodingFormat<SerializationSchema<RowData>> encodingFormat, DataType dataType) {
        this.jdbcOptions = jdbcOptions;
        this.encodingFormat = encodingFormat;
        this.dataType = dataType;
    }

    @Override
    public ChangelogMode getChangelogMode(ChangelogMode requestedMode) {
        return requestedMode;
    }

    @Override
    public SinkRuntimeProvider getSinkRuntimeProvider(Context context) {
        System.out.println("SinkRuntimeProvider");
        System.out.println(dataType);

//        SerializationSchema<RowData> serializationSchema = encodingFormat.createRuntimeEncoder(context, dataType);
        GBase8cSinkFunction gbasedbtSinkFunction = new GBase8cSinkFunction(jdbcOptions,dataType);
        return SinkFunctionProvider.of(gbasedbtSinkFunction);
    }

    @Override
    public DynamicTableSink copy() {
        return new GBase8cDynamicTableSink(jdbcOptions, encodingFormat, dataType);
    }

    @Override
    public String asSummaryString() {
        return "GBase8c Table Sink";
    }

}
