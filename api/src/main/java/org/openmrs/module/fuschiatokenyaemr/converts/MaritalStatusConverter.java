package org.openmrs.module.fuschiatokenyaemr.converts;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.module.reporting.data.converter.DataConverter;

/**
 * Created by codehub on 26/03/15.
 */
public class MaritalStatusConverter implements DataConverter {

    @Override
    public Object convert(Object obj) {

        if (obj == null) {
            return "";
        }

        Concept value = ((Obs) obj).getValueCoded();
        if(value != null && value.getName() != null) {
            return  value.getName().getName();
        }

        return null;
    }

    @Override
    public Class<?> getInputDataType() {
        return null;
    }

    @Override
    public Class<?> getDataType() {
        return null;
    }
}
