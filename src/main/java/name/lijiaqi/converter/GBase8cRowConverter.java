package name.lijiaqi.converter;

import org.apache.flink.connector.jdbc.internal.converter.AbstractJdbcRowConverter;
import org.apache.flink.table.types.logical.RowType;

/**
 * @author lijiaqi
 */
public class GBase8cRowConverter extends AbstractJdbcRowConverter {

    public GBase8cRowConverter(RowType rowType) {
        super(rowType);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String converterName() {
        return "gbase8c";
    }

}