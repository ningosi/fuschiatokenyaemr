package org.openmrs.module.fuschiatokenyaemr.model;

import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fuschiatokenyaemr.utils.ConceptFuschia;

/**
 * Created by codehub on 22/05/15.
 */
public class FuschiaMethodOfEnrollment {

   public static  Concept methodOfEnrollment(int val) {

       ConceptService conceptService = Context.getConceptService();
        Concept method = null;

        if(val == 172) {
            method = conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.TB_CLINIC);
        }

        else if(val == 171) {
            method = conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.PMTCT);
        }

        else if(val == 167 || val == 168 || val == 400) {
            method = conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.VCT);
        }

        else if(val == 169) {
            method = conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.OPD);
        }

        else if(val == 390) {
            method = conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.MCH);
        }

        else if(val == 443) {
            method = conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.HCT);
        }

        else {
            method = conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.OTHER);
        }

        return method;
    }
}
