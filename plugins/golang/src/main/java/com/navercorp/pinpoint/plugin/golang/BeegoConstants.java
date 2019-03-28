package com.navercorp.pinpoint.plugin.golang;

import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeFactory;

import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.INCLUDE_DESTINATION_ID;
import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.RECORD_STATISTICS;
import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.TERMINAL;

/**
 * @author tangxqa
 */
public class BeegoConstants {

    private BeegoConstants() {
    }

    public static final ServiceType BEEGO = ServiceTypeFactory.of(1901, "BEEGO", TERMINAL, INCLUDE_DESTINATION_ID);
    public static final ServiceType BEEGO_METHOD = ServiceTypeFactory.of(1911, "MYSQL_API_CALL", "BEEGO", TERMINAL, RECORD_STATISTICS, INCLUDE_DESTINATION_ID);
}
