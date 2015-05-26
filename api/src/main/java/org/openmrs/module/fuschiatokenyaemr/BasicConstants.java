package org.openmrs.module.fuschiatokenyaemr;

/**
 * Created by codehub on 18/03/15.
 */
public class BasicConstants {

    /**
     * Module ID
     */
    public static final String MODULE_ID = "fuschiatokenyaemr";

    //app privileges
    public static final String APP_LAKE_HUB = MODULE_ID + ".immunize";

    //global proprery using metadata deploy
    public static  final String GP_URL = MODULE_ID+ ".url";

    // encounter type
    public static final class _EncounterType {
        public static final String HIV_CONSULTATION = "a0034eee-1940-4e35-847f-97537a35d05e";
        public static final String HIV_DISCONTINUATION = "2bdada65-4c72-4a48-8730-859890e25cee";
        public static final String HIV_ENROLLMENT = "de78a6be-bfc5-4634-adc3-5f1a280455cc";
        public static final String REGISTRATION = "de1f9d67-b73e-4e1b-90d0-036166fc6995";
    }

    //forms
    public static final class _Form {
        public static final String MOH_257_VISIT_SUMMARY = "23b4ebbd-29ad-455e-be0e-04aa6bc30798";
        public static final String HIV_ENROLLMENT = "e4b506c1-7379-42b6-a374-284469cba8da";
        public static final String CLINICAL_ENCOUNTER_HIV_ADDENDUM = "bd598114-4ef4-47b1-a746-a616180ccfc0";
        public static final String HIV_DISCONTINUATION = "e3237ede-fa70-451f-9e6c-0908bc39f8b9";
    }

    public static final class _PatientIdentifierType {
        public static final String UNIQUE_PATIENT_NUMBER = "05ee9cf4-7242-4a17-b4d4-00f707265c8a";
    }




    public static final class _VisitType {
        public static final String OUTPATIENT = "3371a4d4-f66f-4454-a86d-92c7b3da990c";
    }

    public static final class _OrderType {
        public static final String DRUG = "131168f4-15f5-102d-96e4-000c29c2a5d7";
    }


}
