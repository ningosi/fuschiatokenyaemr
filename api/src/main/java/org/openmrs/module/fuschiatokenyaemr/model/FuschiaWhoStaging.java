package org.openmrs.module.fuschiatokenyaemr.model;

import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fuschiatokenyaemr.utils.ConceptFuschia;

/**
 * Created by codehub on 22/05/15.
 */
public class FuschiaWhoStaging {

   public static Concept whoStaging(String val, Integer age) {

        ConceptService conceptService = Context.getConceptService();
        Concept stage = null;
        Integer stageValue = integersValuesFromString(val);

        if(stageValue == 1 && age < 16) {
            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_1_PEDS);
        }
        else if (stageValue == 2 && age < 16) {
            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_2_PEDS);
        }

        else if (stageValue == 3 && age < 16) {
            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_3_PEDS);
        }

        else if (stageValue == 4 && age < 16) {
            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_4_PEDS);
        }

        else if (stageValue == 1 && age > 15) {
            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_1_ADULT);
        }

        else if (stageValue == 2 && age > 15) {

            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_2_ADULT);
        }

        else if (stageValue == 3 && age > 15) {
            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_3_ADULT);
        }

        else if (stageValue == 4 && age > 15) {
            stage = conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.WHO_STAGE_4_ADULT);
        }

        return stage;
    }

    public static Integer integersValuesFromString(String val) {
        Integer valueFromString;
        String value = null ;
        if(val != null) {
            value =  val.replaceAll("\\D+","");
        }
        if(value == null || value.isEmpty()) {
            valueFromString = 0;
        }

        else {
            valueFromString = Integer.parseInt(value);
        }
        return valueFromString;
    }
}
