package org.openmrs.module.fuschiatokenyaemr;

import org.openmrs.EncounterType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.fuschiatokenyaemr.calculations.Cd4Calculation;
import org.openmrs.module.fuschiatokenyaemr.converts.CalculationResultBasicConverter;
import org.openmrs.module.fuschiatokenyaemr.converts.MaritalStatusConverter;
import org.openmrs.module.kenyacore.report.CohortReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractCohortReportBuilder;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.reporting.cohort.definition.*;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.reporting.common.DurationUnit;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.*;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by codehub on 26/03/15.
 */
@Component
@Builds({"fuschiatokenyaemr.report.patients"})
public class HivClientsReportBuilder extends AbstractCohortReportBuilder {

    /**
     *
     * @see org.openmrs.module.kenyacore.report.builder.AbstractCohortReportBuilder#getParameters(org.openmrs.module.kenyacore.report.ReportDescriptor)
     */
    @Override
    protected List<Parameter> getParameters(ReportDescriptor descriptor) {
        return Arrays.asList(
                new Parameter("startDate", "Start Date", Date.class),
                new Parameter("endDate", "End Date", Date.class),
                new Parameter("months" ,"Months" , Integer.class)
        );
    }

    /**
     *
     * @see org.openmrs.module.kenyacore.report.builder.AbstractCohortReportBuilder#addColumns(CohortReportDescriptor, PatientDataSetDefinition)
     */
    @Override
    protected void addColumns(CohortReportDescriptor report, PatientDataSetDefinition dsd) {
        PatientIdentifierType upn = Context.getPatientService().getPatientIdentifierTypeByUuid("05ee9cf4-7242-4a17-b4d4-00f707265c8a");
        DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
        DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(upn.getName(), upn), identifierFormatter);

        DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName}");
        DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);

        dsd.addColumn("id", new PatientIdDataDefinition(), "");
        dsd.addColumn("Name", nameDef, "");
        dsd.addColumn("UPN", identifierDef, "");
        dsd.addColumn("DOB", new BirthdateDataDefinition(), "");
        dsd.addColumn("Sex", new GenderDataDefinition(), "");
        dsd.addColumn("Marital Status", new ObsForPersonDataDefinition("Marital Status", TimeQualifier.LAST, Context.getConceptService().getConceptByUuid("1054AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), null, null), "", new MaritalStatusConverter());
        dsd.addColumn("cd4", new CalculationDataDefinition("cd4", new Cd4Calculation()), "", new CalculationResultBasicConverter());
    }

    @Override
    protected Mapped<CohortDefinition> buildCohort(CohortReportDescriptor crd, PatientDataSetDefinition pdd)
    {

        CohortDefinition cd = hivEnrolledMenBelow30YearsOnly();
        return ReportUtils.map(cd, "onOrBefore=${endDate}");
    }

    private CohortDefinition menOnly() {
        GenderCohortDefinition genderCohortDefinition = new GenderCohortDefinition();
        genderCohortDefinition.setMaleIncluded(false);
        genderCohortDefinition.setFemaleIncluded(true);
        genderCohortDefinition.setName("Men Only");
         return  genderCohortDefinition;
    }

    private CohortDefinition hivEnrollmentFormsOnly() {
        EncounterType hivEncounter = Context.getEncounterService().getEncounterTypeByUuid("de78a6be-bfc5-4634-adc3-5f1a280455cc");
        EncounterCohortDefinition encounterCohortDefinition = new EncounterCohortDefinition();
        encounterCohortDefinition.setEncounterTypeList(Arrays.asList(hivEncounter));
        encounterCohortDefinition.addParameter(new Parameter("onOrBefore", "Before date", Date.class));
        encounterCohortDefinition.setTimeQualifier(TimeQualifier.LAST);
        return encounterCohortDefinition;
    }

    private CohortDefinition age30YearsAndBelow() {
        AgeCohortDefinition age = new AgeCohortDefinition();
        age.setName("age of patients");
        age.setMaxAge(30);
        age.setMaxAgeUnit(DurationUnit.YEARS);
        age.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        return age;
    }

    private CohortDefinition hivEnrolledMenBelow30YearsOnly() {
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.setName("Men and enrolled into hiv");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addSearch("men", ReportUtils.map(menOnly(), ""));
        cd.addSearch("encounters", ReportUtils.map(hivEnrollmentFormsOnly(), "onOrBefore=${onOrBefore}"));
        cd.addSearch("age", ReportUtils.map(age30YearsAndBelow(), "effectiveDate=${onOrBefore}"));
        cd.setCompositionString("men AND encounters AND age");
        return cd;

    }
}
