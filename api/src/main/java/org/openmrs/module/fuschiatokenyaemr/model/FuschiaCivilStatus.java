package org.openmrs.module.fuschiatokenyaemr.model;

import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fuschiatokenyaemr.utils.ConceptFuschia;

/**
 * Created by codehub on 22/05/15.
 */
public class FuschiaCivilStatus {



    public static  Concept getMaritalStatusAnswers(Integer val) {
        ConceptService conceptService = Context.getConceptService();

        Concept status = null;

        if(val == 161) {
            status = conceptService.getConceptByUuid(ConceptFuschia._Marital_Status.SINGLE);
        }
        else if(val == 162){
            status = conceptService.getConceptByUuid(ConceptFuschia._Marital_Status.MARRIED_MONOGOMOUS);
        }
        else if (val == 163){
            status = conceptService.getConceptByUuid(ConceptFuschia._Marital_Status.DIVORCED_SEPARTED);
        }
        else if (val == 164){
            status = conceptService.getConceptByUuid(ConceptFuschia._Marital_Status.COHARBITING);
        }

        else if (val == 165){
            status = conceptService.getConceptByUuid(ConceptFuschia._Marital_Status.WIDOW);
        }
        else if (val == 182){
            status = conceptService.getConceptByUuid(ConceptFuschia._Marital_Status.MARRIED_POLYGAMOUS);
        }

        return status;
    }
}
