package org.openmrs.module.fuschiatokenyaemr.model;

import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.idgen.service.IdentifierSourceService;

import java.util.Date;

/**
 * Created by codehub on 22/05/15.
 */
public class FuschiaOpenMRSId {

    public static PatientIdentifier _OpenMRSId(){

        PatientService patientService = Context.getPatientService();
        LocationService locationService = Context.getLocationService();

        //create openmrs id which is compusory
        PatientIdentifier openmrsId = new PatientIdentifier();
        PatientIdentifierType openmrsIdType = patientService.getPatientIdentifierTypeByUuid("dfacd928-0370-4315-99d7-6ec1c9f7ae76");
        String generated = Context.getService(IdentifierSourceService.class).generateIdentifier(openmrsIdType, "Registration");
        openmrsId.setIdentifierType(openmrsIdType);
        openmrsId.setDateCreated(new Date());
        openmrsId.setLocation(locationService.getLocation(2338));
        openmrsId.setIdentifier(generated);
        openmrsId.setPreferred(true);

        return openmrsId;
    }
}
