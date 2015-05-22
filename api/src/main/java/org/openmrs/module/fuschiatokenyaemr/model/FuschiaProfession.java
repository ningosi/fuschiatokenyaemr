package org.openmrs.module.fuschiatokenyaemr.model;

import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fuschiatokenyaemr.utils.ConceptFuschia;

/**
 * Created by codehub on 22/05/15.
 */
public class FuschiaProfession {

    public static  Concept getProfessionAnswers(int val) {

        ConceptService conceptService = Context.getConceptService();

        Concept profession = null;

        if(val == 174) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.FARMER);
        }

        else if(val == 175) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.TEACHER);
        }

        else if(val == 176) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.HEALTH_WORKER);
        }

        else if(val == 177) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.TRANSPORT_DRIVER);
        }

        else if(val == 178) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.HOUSE_WIFE);
        }

        else if(val == 179) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.SOLDIER);
        }

        else if(val == 180) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.BUSINESS);
        }

        else if(val == 181) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.UNEMPLOYED);
        }

        else if(val == 444) {
            profession = conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.CHILD);
        }

        return profession;
    }
}
