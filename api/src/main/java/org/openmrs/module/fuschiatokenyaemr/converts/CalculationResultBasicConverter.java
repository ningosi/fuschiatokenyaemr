package org.openmrs.module.fuschiatokenyaemr.converts;

import org.openmrs.calculation.result.CalculationResult;
import org.openmrs.module.reporting.data.converter.DataConverter;

/**
 * Created by codehub on 26/03/15.
 */
public class CalculationResultBasicConverter implements DataConverter {
    @Override
    public Object convert(Object obj) {

        if(obj == null){
            return "";
        }
        Object value = ((CalculationResult) obj).getValue();

        if (value instanceof Double) {
            return ((Double) value).doubleValue();
        }

        return null;
    }

    @Override
    public Class<?> getInputDataType() {
        return CalculationResult.class;
    }

    @Override
    public Class<?> getDataType() {
        return String.class;
    }
}
