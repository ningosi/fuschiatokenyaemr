package org.openmrs.module.fuschiatokenyaemr.database;

import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import org.openmrs.DrugOrder;
import org.openmrs.Encounter;
import org.openmrs.GlobalProperty;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientProgram;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Visit;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.FormService;
import org.openmrs.api.LocationService;
import org.openmrs.api.ObsService;
import org.openmrs.api.OrderService;
import org.openmrs.api.PatientService;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.api.ProviderService;
import org.openmrs.api.UserService;
import org.openmrs.api.VisitService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fuschiatokenyaemr.BasicConstants;
import org.openmrs.module.fuschiatokenyaemr.model.FuschiaCivilStatus;
import org.openmrs.module.fuschiatokenyaemr.model.FuschiaDrugFormulation;
import org.openmrs.module.fuschiatokenyaemr.model.FuschiaMethodOfEnrollment;
import org.openmrs.module.fuschiatokenyaemr.model.FuschiaOpenMRSId;
import org.openmrs.module.fuschiatokenyaemr.model.FuschiaProfession;
import org.openmrs.module.fuschiatokenyaemr.model.FuschiaVisits;
import org.openmrs.module.fuschiatokenyaemr.model.FuschiaWhoStaging;
import org.openmrs.module.fuschiatokenyaemr.utils.ConceptFuschia;
import org.openmrs.module.fuschiatokenyaemr.utils.CoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * this class contains methods that will be able to connect to access database and pull things to mysql
 */
@Service("fuschiatokenyaemr.updateService")

public class AccessDatabase {

    @Autowired
    private PatientService patientService;

    @Autowired
    private EncounterService encounterService;

    @Autowired
    private FormService formService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ConceptService conceptService;

    @Autowired
    @Qualifier("adminService")
    private AdministrationService adminService;

    @Autowired
    private ProgramWorkflowService programWorkflowService;

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;

    public Database getDatabase() throws IOException {
        String path = "/home/codehub/Desktop/MaraguaPOOL.mdb";

        return new DatabaseBuilder(new File(path)).setCodecProvider(new CryptCodecProvider()).open();
    }

    public Table getPatientTable() throws IOException {
        return getDatabase().getTable("tbpatient");

    }

    public Table getVisitsTable() throws IOException {
        return getDatabase().getTable("tbFollowUp");
    }

    List<Row> visitMatchingRows(Integer patientId) throws IOException {
        List<Row> allRowsInVisitTable = new ArrayList<Row>();

        for(Row row :getVisitsTable()) {
            if(row.getInt("FdxReferencePatient").equals(patientId)) {
                allRowsInVisitTable.add(row);
            }
        }

        return allRowsInVisitTable;
    }

    List<Row> getPatientDrugOrders(Integer patientId) throws IOException {
        List<Row> allRowsInDrugTable = new ArrayList<Row>();

        for(Row row :getDatabase().getTable("TbPatientDrug")) {

            if(row.getInt("FdxReferencePatient").equals(patientId)) {
                allRowsInDrugTable.add(row);
            }

        }

        return allRowsInDrugTable;
    }

    public Map<Integer, Integer> numberOfPatientsAndVisits() throws IOException{
        Map<Integer, Integer> values = new HashMap<Integer, Integer>();
        values.put(getPatientTable().getRowCount(), getVisitsTable().getRowCount());
        return values;
    }
   //@Transactional
    public void savePatients() throws Exception {

        for(Row row : getPatientTable()) {


            Date dob;
            String name = row.getString("FdsIdOther");
            String gender = row.getShort("FdnGender").toString();
            Date dobReal = row.getDate("FddBirth");
            Date dobApproxFrom = row.getDate("FddAgeDate");
            Byte age = row.getByte("FdnAge");
            Integer maritalStatus = row.getInt("FdxReferenceMaritalStatus");
            Integer modeOfEntry = row.getInt("FdxReferenceModeEntry");
            Integer profession = row.getInt("FdxReferenceProfession");
            String identifier1 = row.getString("FdsId");
            Boolean transferOut = row.getBoolean("FdbTransfered");
            Date transferredOutDate = row.getDate("FddTransfered");
            Date hivEnrollmentDate = row.getDate("FddHIV");
            String fdnHiv = row.getShort("FdnHIV").toString();
            Short aliveOrDead = row.getShort("FdnHIVDead");
            Date deathDate = row.getDate("FddDead");
            String whoStage = row.getString("FdsDiagnosis01");
            Date dateCreated = row.getDate("FddCreated");

            PersonName personName = new PersonName();
            PatientIdentifier patientIdentifier1 = new PatientIdentifier();

            //fetch this patient database id to use to query other tables like visits and drug orders
            Integer fuschiaPatientDataBaseId =  row.getInt("FdxReference");


            //Deal with patient information here

            if (name != null) {

                String[] allNames = String.valueOf(name).replaceAll("\\s+", " ").trim().split(" ");


                if (allNames.length > 2) {
                    personName.setGivenName(allNames[0]);
                    personName.setFamilyName(allNames[1]);
                    personName.setMiddleName(allNames[2]);
                } else if (allNames.length < 3 && allNames.length > 1) {
                    personName.setGivenName(allNames[0]);
                    personName.setFamilyName(allNames[1]);
                } else if (allNames.length == 1) {
                    personName.setGivenName(allNames[0]);
                    personName.setFamilyName("????");
                }
                else {
                    personName.setGivenName("??????");
                    personName.setFamilyName("????");
                }
            }

            if (identifier1 != null && CoreUtils.integersOnly(identifier1)) {
                patientIdentifier1.setIdentifierType(patientService.getPatientIdentifierTypeByUuid(BasicConstants._PatientIdentifierType.UNIQUE_PATIENT_NUMBER));
                patientIdentifier1.setIdentifier(identifier1);
                patientIdentifier1.setDateCreated(dateCreated);
                patientIdentifier1.setLocation(locationService.getLocation(2338));
                patientIdentifier1.setPreferred(false);
            }



                if (gender.equals("0")) {
                    gender = "M";
                }

                else {
                    gender = "F";
                }

                if (dobReal != null) {
                    dob = dobReal;
                } else if(dobApproxFrom != null && age != null){
                    dob = CoreUtils.addDate(dobApproxFrom, age.intValue());
                }

                else {
                    //those patients with missing birthdate need to be given arbitrary dates
                    //will give them 1/6/1900 this need to be changed there after confirming their correct dob
                    Calendar birthDate = Calendar.getInstance();
                    birthDate.set(1900, Calendar.JANUARY, 1);
                    dob = birthDate.getTime();
                }


            //checking for patients who are 120 years and below
                Calendar c = Calendar.getInstance();
                c.setTime(dateCreated);
                c.add(Calendar.YEAR, -120);

                Patient newPatient = new Patient();

                if(dob.after(c.getTime()) && dob.before(new Date())) {
                    newPatient.addName(personName);
                    newPatient.setGender(gender);
                    newPatient.setBirthdate(dob);
                    if(patientIdentifier1.getIdentifier() != null) {
                        newPatient.addIdentifier(patientIdentifier1);
                    }
                    else {
                        newPatient.addIdentifier((FuschiaOpenMRSId._OpenMRSId()));
                    }
                    //save the patient here
                    patientService.savePatient(newPatient);

                    //check if this patient is a live or dead
                    if (aliveOrDead.intValue() == 1 && deathDate != null) {
                        Person person  = Context.getPersonService().getPerson(newPatient.getPersonId());
                        person.setDead(true);
                        person.setDeathDate(deathDate);
                        person.setCauseOfDeath(conceptService.getConceptByUuid(ConceptFuschia._HIV_DISCONTINUATION.UNKOWN));

                        //save the person in the database
                        Context.getPersonService().savePerson(person);
                    }

                }

            //create a registration encounter
            if(newPatient.getPatientId() != null) {
                //save the patient in the database here
                //start encounter information
                Encounter registrationEncounter = new Encounter();
                registrationEncounter.setEncounterDatetime(dateCreated);
                registrationEncounter.setDateCreated(new Date());
                registrationEncounter.setCreator(userService.getUser(1));
                registrationEncounter.setEncounterType(encounterService.getEncounterTypeByUuid(BasicConstants._EncounterType.REGISTRATION));
                registrationEncounter.setPatient(newPatient);
                registrationEncounter.setLocation(locationService.getLocation(2338));
                registrationEncounter.setProvider(encounterService.getEncounterRoleByUuid("a0b03050-c99b-11e0-9572-0800200c9a66"), providerService.getProviderByUuid("ae01b8ff-a4cc-4012-bcf7-72359e852e14"));


                Obs maritalStatusObs = new Obs();
                Set<Obs> registrationObs = new HashSet<Obs>();
                if (maritalStatus != null) {
                    maritalStatusObs.setObsDatetime(dateCreated);
                    maritalStatusObs.setPerson(newPatient);
                    maritalStatusObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._Marital_Status.CIVIL_STATUS));
                    maritalStatusObs.setValueCoded(FuschiaCivilStatus.getMaritalStatusAnswers(maritalStatus));
                    maritalStatusObs.setDateCreated(new Date());
                    maritalStatusObs.setLocation(locationService.getLocation(2338));

                    registrationObs.add(maritalStatusObs);

                }

                //create the profession observation
                Obs professionObs = new Obs();
                if (profession != null) {
                    professionObs.setObsDatetime(dateCreated);
                    professionObs.setPerson(newPatient);
                    professionObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._Profession_Type.OCCUPATION));
                    professionObs.setValueCoded(FuschiaProfession.getProfessionAnswers(profession));
                    professionObs.setDateCreated(new Date());
                    professionObs.setLocation(locationService.getLocation(2338));

                    registrationObs.add(professionObs);
                }

                //tie registration obs to encounter
                registrationEncounter.setObs(registrationObs);

                //save the registration encounter
                if (!(registrationEncounter.getAllObs().isEmpty()) && registrationEncounter.getEncounterDatetime().before(new Date())) {
                    encounterService.saveEncounter(registrationEncounter);
                }

                //now create the program enrollment in the hiv program
                //prepare a hiv enrollment encounter
                //only enroll into hiv if the patient is hiv positive
                //if they have no date then pick the date when the encounter was made

                if (hivEnrollmentDate == null) {
                    hivEnrollmentDate = dateCreated;
                }

                if (fdnHiv.equals("1")) {
                    Encounter hivEnrollmentEncounter = new Encounter();
                    hivEnrollmentEncounter.setEncounterDatetime(hivEnrollmentDate);
                    hivEnrollmentEncounter.setEncounterType(encounterService.getEncounterTypeByUuid(BasicConstants._EncounterType.HIV_ENROLLMENT));
                    hivEnrollmentEncounter.setPatient(newPatient);
                    hivEnrollmentEncounter.setLocation(locationService.getLocation(2338));
                    hivEnrollmentEncounter.setForm(formService.getFormByUuid(BasicConstants._Form.HIV_ENROLLMENT));
                    hivEnrollmentEncounter.setProvider(encounterService.getEncounterRoleByUuid("a0b03050-c99b-11e0-9572-0800200c9a66"), providerService.getProviderByUuid("ae01b8ff-a4cc-4012-bcf7-72359e852e14"));
                    hivEnrollmentEncounter.setDateCreated(new Date());
                    hivEnrollmentEncounter.setCreator(userService.getUser(1));

                    //prepare observations that will accompanied this form
                    Obs methodOfEnroll = new Obs();

                    if (modeOfEntry != null) {
                        methodOfEnroll.setObsDatetime(dateCreated);
                        methodOfEnroll.setConcept(conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.METHOD_OF_ENROLLMENT));
                        methodOfEnroll.setValueCoded(FuschiaMethodOfEnrollment.methodOfEnrollment(modeOfEntry));
                        methodOfEnroll.setDateCreated(new Date());
                        methodOfEnroll.setPerson(newPatient);
                        methodOfEnroll.setLocation(locationService.getLocation(2338));

                        hivEnrollmentEncounter.addObs(methodOfEnroll);
                    }

                    else {
                        methodOfEnroll.setObsDatetime(dateCreated);
                        methodOfEnroll.setConcept(conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.METHOD_OF_ENROLLMENT));
                        methodOfEnroll.setValueCoded(conceptService.getConceptByUuid(ConceptFuschia._Mode_Of_Entry.OTHER));
                        methodOfEnroll.setDateCreated(new Date());
                        methodOfEnroll.setPerson(newPatient);
                        methodOfEnroll.setLocation(locationService.getLocation(2338));

                        hivEnrollmentEncounter.addObs(methodOfEnroll);
                    }

                    //transfer in details
                    Obs transferOutDateObs = new Obs();
                    Obs transferOutObs = new Obs();
                    Obs deathObs = new Obs();
                    Obs deathDateObs = new Obs();
                    Set<Obs> hivDiscontinueObsSetTransferOut = new HashSet<Obs>();
                    Set<Obs> hivDiscontinueObsSetDead = new HashSet<Obs>();

                    //create a discontinuation enconter
                    Encounter hivDiscontinuationEncounter = new Encounter();
                    if(transferOut || deathDate != null ) {
                        hivDiscontinuationEncounter.setEncounterDatetime(dateCreated);
                        hivDiscontinuationEncounter.setDateCreated(new Date());
                        hivDiscontinuationEncounter.setCreator(userService.getUser(1));
                        hivDiscontinuationEncounter.setEncounterType(encounterService.getEncounterTypeByUuid(BasicConstants._EncounterType.HIV_DISCONTINUATION));
                        hivDiscontinuationEncounter.setPatient(newPatient);
                        hivDiscontinuationEncounter.setLocation(locationService.getLocation(2338));
                        hivDiscontinuationEncounter.setForm(formService.getFormByUuid(BasicConstants._Form.HIV_DISCONTINUATION));
                        hivDiscontinuationEncounter.setProvider(encounterService.getEncounterRoleByUuid("a0b03050-c99b-11e0-9572-0800200c9a66"), providerService.getProviderByUuid("ae01b8ff-a4cc-4012-bcf7-72359e852e14"));
                    }


                    if (transferOut) {

                        transferOutObs.setObsDatetime(dateCreated);
                        transferOutObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._HIV_DISCONTINUATION.REASON_FOR_PROGRAM_DISCONTINUATION));
                        transferOutObs.setValueCoded(conceptService.getConceptByUuid(ConceptFuschia._HIV_DISCONTINUATION.TRANSFERRED_OUT));
                        transferOutObs.setDateCreated(new Date());
                        transferOutObs.setPerson(newPatient);
                        transferOutObs.setLocation(locationService.getLocation(2338));
                        //transfer out date obs

                        transferOutDateObs.setObsDatetime(dateCreated);
                        transferOutDateObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._HIV_DISCONTINUATION.DATE_TRANSFERED_OUT));
                        transferOutDateObs.setValueDatetime(transferredOutDate);
                        transferOutDateObs.setDateCreated(new Date());
                        transferOutDateObs.setPerson(newPatient);
                        transferOutObs.setLocation(locationService.getLocation(2338));

                        //add to a set of obs to be tied to discontinution encounter
                        hivDiscontinueObsSetTransferOut.add(transferOutObs);
                        hivDiscontinueObsSetTransferOut.add(transferOutDateObs);

                        //add this to discontinuation encounter
                        hivDiscontinuationEncounter.setObs(hivDiscontinueObsSetTransferOut);
                    }

                    if (deathDate != null) {
                        deathObs.setObsDatetime(dateCreated);
                        deathObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._HIV_DISCONTINUATION.REASON_FOR_PROGRAM_DISCONTINUATION));
                        deathObs.setValueCoded(conceptService.getConceptByUuid(ConceptFuschia._HIV_DISCONTINUATION.DIED));
                        deathObs.setDateCreated(new Date());
                        deathObs.setPerson(newPatient);
                        deathObs.setLocation(locationService.getLocation(2338));

                        //get the date when the patient died
                        deathDateObs.setObsDatetime(dateCreated);
                        deathDateObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._HIV_DISCONTINUATION.DATE_OF_DEATH));
                        deathDateObs.setValueDatetime(deathDate);
                        deathDateObs.setDateCreated(new Date());
                        deathDateObs.setPerson(newPatient);
                        deathDateObs.setLocation(locationService.getLocation(2338));

                        //add this to discontinuation
                        hivDiscontinueObsSetDead.add(deathObs);
                        hivDiscontinueObsSetDead.add(deathDateObs);
                        hivDiscontinuationEncounter.setObs(hivDiscontinueObsSetDead);

                    }

                    if (!(hivEnrollmentEncounter.getAllObs().isEmpty()) && hivEnrollmentEncounter.getEncounterDatetime().before(new Date()) && CoreUtils.integersOnly(identifier1)) {
                        encounterService.saveEncounter(hivEnrollmentEncounter);
                        PatientProgram hivProgramEnrollment = new PatientProgram();
                        hivProgramEnrollment.setDateEnrolled(hivEnrollmentDate);
                        hivProgramEnrollment.setProgram(programWorkflowService.getProgramByUuid("dfdc6d40-2f2f-463d-ba90-cc97350441a8"));
                        hivProgramEnrollment.setLocation(locationService.getLocation(2338));
                        hivProgramEnrollment.setPatient(newPatient);
                        hivProgramEnrollment.setDateCreated(new Date());

                        //save the program
                        programWorkflowService.savePatientProgram(hivProgramEnrollment);
                    }

                    if ((hivDiscontinuationEncounter.getAllObs().size() > 3) && hivDiscontinuationEncounter.getEncounterDatetime().before(new Date()) && CoreUtils.integersOnly(identifier1)) {
                        encounterService.saveEncounter(hivDiscontinuationEncounter);
                        PatientProgram hivProgramDiscontinuation = new PatientProgram();
                        hivProgramDiscontinuation.setDateCompleted(dateCreated);
                        hivProgramDiscontinuation.setProgram(programWorkflowService.getProgramByUuid("dfdc6d40-2f2f-463d-ba90-cc97350441a8"));
                        hivProgramDiscontinuation.setLocation(locationService.getLocation(2338));
                        hivProgramDiscontinuation.setPatient(newPatient);
                        hivProgramDiscontinuation.setDateCreated(new Date());

                        //discontinue from care
                        programWorkflowService.savePatientProgram(hivProgramDiscontinuation);

                    }


                    //prepare an encounter to save the who stage as presented in the tables
                    if(age != null) {
                        Encounter hivConsultationAdd = new Encounter();
                        hivConsultationAdd.setEncounterDatetime(dateCreated);
                        hivConsultationAdd.setEncounterType(encounterService.getEncounterTypeByUuid(BasicConstants._EncounterType.HIV_CONSULTATION));
                        hivConsultationAdd.setPatient(newPatient);
                        hivConsultationAdd.setLocation(locationService.getLocation(2338));
                        hivConsultationAdd.setForm(formService.getFormByUuid(BasicConstants._Form.CLINICAL_ENCOUNTER_HIV_ADDENDUM));
                        hivConsultationAdd.setProvider(encounterService.getEncounterRoleByUuid("a0b03050-c99b-11e0-9572-0800200c9a66"), providerService.getProviderByUuid("ae01b8ff-a4cc-4012-bcf7-72359e852e14"));
                        hivConsultationAdd.setDateCreated(new Date());
                        hivConsultationAdd.setCreator(userService.getUser(1));

                        //populate the who stage obs
                        Obs whoStageObs = new Obs();

                        whoStageObs.setObsDatetime(dateCreated);
                        whoStageObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._WHO_STAGE.CURRENT_WHO_STAGE));
                        whoStageObs.setValueCoded(FuschiaWhoStaging.whoStaging(whoStage, age.intValue()));
                        whoStageObs.setDateCreated(new Date());
                        whoStageObs.setPerson(newPatient);
                        whoStageObs.setLocation(locationService.getLocation(2338));
                        whoStageObs.setEncounter(hivConsultationAdd);


                        //tie the who stage to the addendum and save
                        hivConsultationAdd.addObs(whoStageObs);
                        if ((hivConsultationAdd.getAllObs().size() > 3) && hivConsultationAdd.getEncounterDatetime().before(new Date()) && CoreUtils.integersOnly(identifier1)) {
                            encounterService.saveEncounter(hivConsultationAdd);
                        }

                    }
                }

                //save visits from here
                for(Row visitRow: visitMatchingRows(fuschiaPatientDataBaseId)) {

                    FuschiaVisits fuschiaVisits = new FuschiaVisits();
                    fuschiaVisits.setDateCreated(visitRow.getDate("FddCreated"));
                    fuschiaVisits.setVisitDate(visitRow.getDate("FddVisit"));
                    fuschiaVisits.setNextAppointmentDate(visitRow.getDate("FddVisitNext"));
                    fuschiaVisits.setCd4(visitRow.getShort("FdnLymphocyteCD4"));
                    fuschiaVisits.setViralLoad(visitRow.getInt("FdnHIVLoad"));
                    fuschiaVisits.setHeight(visitRow.getShort("FdnHeight"));
                    fuschiaVisits.setWeight(visitRow.getFloat("FdrWeight"));
                    fuschiaVisits.setHgb(visitRow.getFloat("FdrHemoglobinemia"));

                    Date visitDate = fuschiaVisits.getVisitDate() ;
                    if(visitDate == null) {
                        visitDate = fuschiaVisits.getDateCreated();
                    }

                    if(visitDate != null && visitDate.before(new Date())) {
                        //create a visit for that row
                        Visit visit = new Visit();
                        visit.setVisitType(visitService.getVisitTypeByUuid(BasicConstants._VisitType.OUTPATIENT));
                        visit.setLocation(locationService.getLocation(2338));
                        visit.setDateCreated(new Date());
                        visit.setStartDatetime(visitDate);
                        visit.setPatient(newPatient);

                        //create an encounter for that row
                        Encounter consultationsEncounter = new Encounter();
                        consultationsEncounter.setEncounterDatetime(visitDate);
                        consultationsEncounter.setLocation(locationService.getLocation(2338));
                        consultationsEncounter.setProvider(encounterService.getEncounterRoleByUuid("a0b03050-c99b-11e0-9572-0800200c9a66"), providerService.getProviderByUuid("ae01b8ff-a4cc-4012-bcf7-72359e852e14"));
                        consultationsEncounter.setEncounterType(encounterService.getEncounterTypeByUuid(BasicConstants._EncounterType.HIV_CONSULTATION));
                        consultationsEncounter.setForm(formService.getFormByUuid(BasicConstants._Form.MOH_257_VISIT_SUMMARY));
                        consultationsEncounter.setDateCreated(new Date());
                        consultationsEncounter.setCreator(userService.getUser(1));
                        consultationsEncounter.setPatient(newPatient);

                        //create observations for this encounter
                        //return visit date
                        //create a set of all the observations
                        Set<Obs> visitObs = new HashSet<Obs>();

                        if(fuschiaVisits.getVisitDate() != null) {
                            Obs dateOfNextAppointment = new Obs();
                            dateOfNextAppointment.setPerson(newPatient);
                            dateOfNextAppointment.setConcept(conceptService.getConceptByUuid(ConceptFuschia._VISIT_METADATA.RETURN_VISIT_DATE));
                            dateOfNextAppointment.setValueDatetime(fuschiaVisits.getNextAppointmentDate());
                            dateOfNextAppointment.setObsDatetime(visitDate);
                            dateOfNextAppointment.setDateCreated(new Date());
                            dateOfNextAppointment.setLocation(locationService.getLocation(2338));
                            dateOfNextAppointment.setEncounter(consultationsEncounter);

                            visitObs.add(dateOfNextAppointment);
                        }

                        //cd4

                        if(fuschiaVisits.getCd4() != null) {
                            Obs cd4 = new Obs();
                            cd4.setPerson(newPatient);
                            cd4.setConcept(conceptService.getConceptByUuid(ConceptFuschia._VISIT_METADATA.CD4_COUNT));
                            cd4.setValueNumeric(fuschiaVisits.getCd4().doubleValue());
                            cd4.setObsDatetime(visitDate);
                            cd4.setDateCreated(new Date());
                            cd4.setLocation(locationService.getLocation(2338));
                            cd4.setEncounter(consultationsEncounter);


                            visitObs.add(cd4);
                        }

                        //viral load

                        if(fuschiaVisits.getViralLoad() != null) {
                            Obs viralLoad = new Obs();
                            viralLoad.setObsDatetime(visitDate);
                            viralLoad.setPerson(newPatient);
                            viralLoad.setConcept(conceptService.getConceptByUuid(ConceptFuschia._VISIT_METADATA.HIV_VIRAL_LOAD));
                            viralLoad.setValueNumeric(fuschiaVisits.getViralLoad().doubleValue());
                            viralLoad.setDateCreated(new Date());
                            viralLoad.setLocation(locationService.getLocation(2338));
                            viralLoad.setEncounter(consultationsEncounter);

                            visitObs.add(viralLoad);
                        }

                        //weight

                        if(fuschiaVisits.getWeight() != null) {
                            Obs weight = new Obs();
                            weight.setObsDatetime(visitDate);
                            weight.setPerson(newPatient);
                            weight.setConcept(conceptService.getConceptByUuid(ConceptFuschia._VISIT_METADATA.WEIGHT_KG));
                            weight.setValueNumeric(fuschiaVisits.getWeight().doubleValue());
                            weight.setDateCreated(new Date());
                            weight.setLocation(locationService.getLocation(2338));
                            weight.setEncounter(consultationsEncounter);

                            visitObs.add(weight);
                        }

                        //Height

                        if(fuschiaVisits.getHeight() != null) {
                            Obs height = new Obs();
                            height.setObsDatetime(visitDate);
                            height.setPerson(newPatient);
                            height.setConcept(conceptService.getConceptByUuid(ConceptFuschia._VISIT_METADATA.HEIGHT_CM));
                            height.setValueNumeric(fuschiaVisits.getHeight().doubleValue());
                            height.setDateCreated(new Date());
                            height.setLocation(locationService.getLocation(2338));
                            height.setEncounter(consultationsEncounter);

                            visitObs.add(height);
                        }

                        //hgb

                        if(fuschiaVisits.getHgb() != null) {
                            Obs hgb = new Obs();
                            hgb.setObsDatetime(visitDate);
                            hgb.setPerson(newPatient);
                            hgb.setConcept(conceptService.getConceptByUuid(ConceptFuschia._VISIT_METADATA.HGB));
                            hgb.setValueNumeric(fuschiaVisits.getHgb().doubleValue());
                            hgb.setDateCreated(new Date());
                            hgb.setLocation(locationService.getLocation(2338));
                            hgb.setEncounter(consultationsEncounter);

                            visitObs.add(hgb);
                        }
                        //add those obs to an encounter
                        if((visitObs.size() > 0)) {
                            consultationsEncounter.setObs(visitObs);
                        }

                        //tie that encounter to a visit
                        if((consultationsEncounter.getAllObs().size() > 3)) {
                            encounterService.saveEncounter(consultationsEncounter);
                            //add that encounter to the visit
                            visit.addEncounter(consultationsEncounter);
                            //save the visit in the database
                            visitService.saveVisit(visit);
                        }

                    }

                }
                //save drug orders from here

                //list all the data variables that will be fetched from the drug table

                Integer order;
                Date startDate;
                Date dateDrugOrderCreated;
                for(Row drugOrder : getPatientDrugOrders(fuschiaPatientDataBaseId)) {
                    OrderService orderService = Context.getOrderService();
                    order = drugOrder.getInt("FdxReferenceDrug");
                    startDate = drugOrder.getDate("FddBeginning");
                    dateDrugOrderCreated = drugOrder.getDate("FddCreated");

                    Date mustDrugStartDate = startDate;

                    if(mustDrugStartDate == null) {
                        mustDrugStartDate = dateDrugOrderCreated;
                    }
                    //loop through all the drug orders given for this row
                    for(DrugOrder orders : FuschiaDrugFormulation.getDrugFuschiaDrugOder(order, mustDrugStartDate, newPatient, dateDrugOrderCreated)){
                        //call service method to save the drug order
                        if(mustDrugStartDate.before(new Date())) {
                            orderService.saveOrder(orders);
                        }
                    }

                    //save medication orders here if any for this row
                    if(FuschiaDrugFormulation.isMedicationOrder(order)) {
                        //you will create a medication order for the observation and save those
                        //we create an encounter
                        Encounter medicationOrderEncounter = new Encounter();
                        medicationOrderEncounter.setEncounterDatetime(dateDrugOrderCreated);
                        medicationOrderEncounter.setLocation(locationService.getLocation(2338));
                        medicationOrderEncounter.setProvider(encounterService.getEncounterRoleByUuid("a0b03050-c99b-11e0-9572-0800200c9a66"), providerService.getProviderByUuid("ae01b8ff-a4cc-4012-bcf7-72359e852e14"));
                        medicationOrderEncounter.setEncounterType(encounterService.getEncounterTypeByUuid(BasicConstants._EncounterType.HIV_CONSULTATION));
                        medicationOrderEncounter.setForm(formService.getFormByUuid(BasicConstants._Form.MOH_257_VISIT_SUMMARY));
                        medicationOrderEncounter.setDateCreated(new Date());
                        medicationOrderEncounter.setCreator(userService.getUser(1));
                        medicationOrderEncounter.setPatient(newPatient);

                        //create an obs group for medication orders
                        ObsService obsService = Context.getObsService();
                        Obs medicationOrdersObsGroup = new Obs();
                        medicationOrdersObsGroup.setObsDatetime(dateDrugOrderCreated);
                        medicationOrdersObsGroup.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.MEDICATION_ORDER_GROUPING));
                        medicationOrdersObsGroup.setPerson(newPatient);
                        medicationOrdersObsGroup.setDateCreated(new Date());
                        medicationOrdersObsGroup.setLocation(locationService.getLocation(2338));
                        medicationOrdersObsGroup.setEncounter(medicationOrderEncounter);

                        //add this observation to an encounter and save the encounter
                        medicationOrderEncounter.addObs(medicationOrdersObsGroup);
                        //save the encounter with the obs

                        encounterService.saveEncounter(medicationOrderEncounter);



                        //create observation for that obs value
                        Obs medicationOrderObs = new Obs();
                        medicationOrderObs.setObsDatetime(dateDrugOrderCreated);
                        medicationOrderObs.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.MEDICATION_ORDERS));
                        medicationOrderObs.setPerson(newPatient);
                        medicationOrderObs.setDateCreated(new Date());
                        medicationOrderObs.setValueCoded(FuschiaDrugFormulation.medicationOrderAnswers(order));
                        medicationOrderObs.setObsGroup(medicationOrdersObsGroup);
                        medicationOrderObs.setLocation(locationService.getLocation(2338));
                        medicationOrderObs.setEncounter(medicationOrderEncounter);

                        //tie that observation to an encounter
                        medicationOrderEncounter.addObs(medicationOrderObs);

                        //tie that encounter to a visit
                        Visit medicationOrderVisit = new Visit();
                        medicationOrderVisit.setStartDatetime(dateDrugOrderCreated);
                        medicationOrderVisit.setPatient(newPatient);
                        medicationOrderVisit.setLocation(locationService.getLocation(2338));
                        medicationOrderVisit.setVisitType(visitService.getVisitTypeByUuid(BasicConstants._VisitType.OUTPATIENT));



                        //save the visit to the database
                        if(medicationOrderVisit.getStartDatetime() != null && medicationOrderVisit.getStartDatetime().before(new Date())) {
                            encounterService.saveEncounter(medicationOrderEncounter);
                            medicationOrderVisit.addEncounter(medicationOrderEncounter);
                            visitService.saveVisit(medicationOrderVisit);
                        }

                    }

                }


            }

        }
       //close the database instance
       getDatabase().close();
    }


    @Transactional(readOnly = true)
    public String getPath() {
        String path = adminService.getGlobalProperty(BasicConstants.GP_URL) +"/";
        return path;
    }

    @Transactional
    public void savePath(String path) {
        GlobalProperty url = adminService.getGlobalPropertyObject(BasicConstants.GP_URL);
        url.setPropertyValue(path);
        adminService.saveGlobalProperty(url);
    }

}
