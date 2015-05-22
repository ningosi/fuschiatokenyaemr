package org.openmrs.module.fuschiatokenyaemr.calculations;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.fuschiatokenyaemr.utils.BasicCalculationUtils;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.Calculations;
import org.openmrs.module.kenyacore.calculation.Filters;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by codehub on 26/03/15.
 */
public class Cd4Calculation extends AbstractPatientCalculation{


    @Override
    public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues, PatientCalculationContext context) {

        CalculationResultMap ret = new CalculationResultMap();
        Set<Integer> female = Filters.female(cohort, context);
        Concept cd4 = Context.getConceptService().getConceptByUuid("5497AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        CalculationResultMap lastCd4 = Calculations.lastObs(cd4, female, context);
        for(Integer ptId:cohort){
            Double cd4Value = null;

            Double cd4s = BasicCalculationUtils.numericObsResultForPatient(lastCd4, ptId);

            if(female.contains(ptId) && cd4s != null){
                cd4Value = cd4s;
            }
            ret.put(ptId, new SimpleResult(cd4Value, this));

        }

        return ret;
    }


}
