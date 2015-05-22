package org.openmrs.module.fuschiatokenyaemr.calculations;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.BooleanResult;
import org.openmrs.module.kenyacore.calculation.Filters;
import org.openmrs.module.kenyacore.calculation.PatientFlagCalculation;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by codehub on 27/03/15.
 */
public class VmmcCCalculation extends AbstractPatientCalculation implements PatientFlagCalculation{

    @Override
    public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues, PatientCalculationContext context) {
       CalculationResultMap ret = new CalculationResultMap();
        Set<Integer> alive = Filters.alive(cohort,context);
        Set<Integer> female =Filters.female(cohort,context);

        for(Integer ptId:cohort) {
            boolean isAliveAndMale = false;
            Patient patient = Context.getPatientService().getPatient(ptId);

            if(alive.contains(ptId) && (!female.contains(ptId)) && (patient.getAge() > 30)) {
                isAliveAndMale = true;
            }
            ret.put(ptId, new BooleanResult(isAliveAndMale, this));
        }
        return ret;
    }

    @Override
    public String getFlagMessage() {
        return "Male and Alive";
    }
}
