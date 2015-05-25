package org.openmrs.module.fuschiatokenyaemr.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.CalculationContext;
import org.openmrs.calculation.patient.PatientCalculation;
import org.openmrs.calculation.patient.PatientCalculationService;
import org.openmrs.calculation.result.CalculationResult;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.CoreContext;
import org.openmrs.module.kenyacore.calculation.CalculationUtils;
import org.openmrs.module.kenyaemr.regimen.RegimenDefinition;
import org.openmrs.module.kenyaemr.regimen.RegimenManager;
import org.openmrs.module.kenyaemr.regimen.RegimenOrder;
import org.openmrs.util.OpenmrsUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by codehub on 26/03/15.
 */
public class BasicCalculationUtils {


    public static int daysSince(Date date, CalculationContext calculationContext) {
        DateTime d1 = new DateTime(date.getTime());
        DateTime d2 = new DateTime(calculationContext.getNow().getTime());
        return Days.daysBetween(d1, d2).getDays();
    }

}
