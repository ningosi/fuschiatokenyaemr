package org.openmrs.module.fuschiatokenyaemr.model;

import org.openmrs.Concept;
import org.openmrs.DrugOrder;
import org.openmrs.Patient;
import org.openmrs.api.ConceptService;
import org.openmrs.api.OrderService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fuschiatokenyaemr.BasicConstants;
import org.openmrs.module.fuschiatokenyaemr.utils.ConceptFuschia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by codehub on 25/05/15.
 */
public class FuschiaDrugFormulation {

    public static Set<DrugOrder> getDrugFuschiaDrugOder(Integer order, Date startDate, Patient patient, Date dateCreated){
        Set<DrugOrder> drugOrderSet = new HashSet<DrugOrder>();
        ConceptService conceptService = Context.getConceptService();
        OrderService orderService = Context.getOrderService();

        if(order == 39 || order == 226 || order == 227 || order == 238) {
            DrugOrder drugOrderD4T = new DrugOrder();
            drugOrderD4T.setStartDate(startDate);
            drugOrderD4T.setPatient(patient);
            drugOrderD4T.setDateCreated(dateCreated);
            drugOrderD4T.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.D4T));
            drugOrderD4T.setDose(300.0);
            drugOrderD4T.setUnits("mg");
            drugOrderD4T.setFrequency("Once daily");
            drugOrderD4T.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderD4T);
        }
        else if(order == 48 || order == 240 ) {
            DrugOrder drugOrderNVP = new DrugOrder();
            drugOrderNVP.setStartDate(startDate);
            drugOrderNVP.setPatient(patient);
            drugOrderNVP.setDateCreated(dateCreated);
            drugOrderNVP.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.NVP));
            drugOrderNVP.setDose(300.0);
            drugOrderNVP.setUnits("mg");
            drugOrderNVP.setFrequency("Once daily");
            drugOrderNVP.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderNVP);
        }

        else if(order == 34 || order == 236 || order == 244) {
            DrugOrder drugOrder3TC = new DrugOrder();
            drugOrder3TC.setStartDate(startDate);
            drugOrder3TC.setPatient(patient);
            drugOrder3TC.setDateCreated(dateCreated);
            drugOrder3TC.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TC3));
            drugOrder3TC.setDose(300.0);
            drugOrder3TC.setUnits("mg");
            drugOrder3TC.setFrequency("Once daily");
            drugOrder3TC.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrder3TC);

        }

        else if(order == 43 || order == 241 || order == 229) {

            DrugOrder drugOrderEFV = new DrugOrder();
            drugOrderEFV.setStartDate(startDate);
            drugOrderEFV.setPatient(patient);
            drugOrderEFV.setDateCreated(dateCreated);
            drugOrderEFV.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.EFV));
            drugOrderEFV.setDose(600.0);
            drugOrderEFV.setUnits("mg");
            drugOrderEFV.setFrequency("Once daily");
            drugOrderEFV.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderEFV);

        }

        else if(order == 41 || order == 228 || order == 239) {
            DrugOrder drugOrderDDI = new DrugOrder();
            drugOrderDDI.setStartDate(startDate);
            drugOrderDDI.setPatient(patient);
            drugOrderDDI.setDateCreated(dateCreated);
            drugOrderDDI.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.DDI));
            drugOrderDDI.setDose(400.0);
            drugOrderDDI.setUnits("mg");
            drugOrderDDI.setFrequency("Once daily");
            drugOrderDDI.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderDDI);

        }

        else if(order == 218 || order == 219 || order == 233) {

            DrugOrder drugOrderD4T218 = new DrugOrder();
            drugOrderD4T218.setStartDate(startDate);
            drugOrderD4T218.setPatient(patient);
            drugOrderD4T218.setDateCreated(dateCreated);
            drugOrderD4T218.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.D4T));
            drugOrderD4T218.setDose(300.0);
            drugOrderD4T218.setUnits("mg");
            drugOrderD4T218.setFrequency("Once daily");
            drugOrderD4T218.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrder3TC218 = new DrugOrder();
            drugOrder3TC218.setStartDate(startDate);
            drugOrder3TC218.setPatient(patient);
            drugOrder3TC218.setDateCreated(dateCreated);
            drugOrder3TC218.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TC3));
            drugOrder3TC218.setDose(300.0);
            drugOrder3TC218.setUnits("mg");
            drugOrder3TC218.setFrequency("Once daily");
            drugOrder3TC218.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrderNVP218 = new DrugOrder();
            drugOrderNVP218.setStartDate(startDate);
            drugOrderNVP218.setPatient(patient);
            drugOrderNVP218.setDateCreated(dateCreated);
            drugOrderNVP218.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.NVP));
            drugOrderNVP218.setDose(300.0);
            drugOrderNVP218.setUnits("mg");
            drugOrderNVP218.setFrequency("Once daily");
            drugOrderNVP218.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderD4T218);
            drugOrderSet.add(drugOrder3TC218);
            drugOrderSet.add(drugOrderNVP218);

        }

        else if (order == 224){

            DrugOrder drugOrderAZT224 = new DrugOrder();
            drugOrderAZT224.setStartDate(startDate);
            drugOrderAZT224.setPatient(patient);
            drugOrderAZT224.setDateCreated(dateCreated);
            drugOrderAZT224.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.AZT));
            drugOrderAZT224.setDose(300.0);
            drugOrderAZT224.setUnits("mg");
            drugOrderAZT224.setFrequency("Once daily");
            drugOrderAZT224.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrder3TC224 = new DrugOrder();
            drugOrder3TC224.setStartDate(startDate);
            drugOrder3TC224.setPatient(patient);
            drugOrder3TC224.setDateCreated(dateCreated);
            drugOrder3TC224.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TC3));
            drugOrder3TC224.setDose(300.0);
            drugOrder3TC224.setUnits("mg");
            drugOrder3TC224.setFrequency("Once daily");
            drugOrder3TC224.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrder3TC224);
            drugOrderSet.add(drugOrderAZT224);

        }

        else if(order ==222 || order == 223 || order == 235) {
            DrugOrder drugOrderD4T222223 = new DrugOrder();
            drugOrderD4T222223.setStartDate(startDate);
            drugOrderD4T222223.setPatient(patient);
            drugOrderD4T222223.setDateCreated(dateCreated);
            drugOrderD4T222223.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.D4T));
            drugOrderD4T222223.setDose(300.0);
            drugOrderD4T222223.setUnits("mg");
            drugOrderD4T222223.setFrequency("Once daily");
            drugOrderD4T222223.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrder3TC222223 = new DrugOrder();
            drugOrder3TC222223.setStartDate(startDate);
            drugOrder3TC222223.setPatient(patient);
            drugOrder3TC222223.setDateCreated(dateCreated);
            drugOrder3TC222223.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TC3));
            drugOrder3TC222223.setDose(300.0);
            drugOrder3TC222223.setUnits("mg");
            drugOrder3TC222223.setFrequency("Once daily");
            drugOrder3TC222223.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderD4T222223);
            drugOrderSet.add(drugOrder3TC222223);
        }

        else if (order == 221) {
            DrugOrder drugOrderAZT221 = new DrugOrder();
            drugOrderAZT221.setStartDate(startDate);
            drugOrderAZT221.setPatient(patient);
            drugOrderAZT221.setDateCreated(dateCreated);
            drugOrderAZT221.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.AZT));
            drugOrderAZT221.setDose(300.0);
            drugOrderAZT221.setUnits("mg");
            drugOrderAZT221.setFrequency("Once daily");
            drugOrderAZT221.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrder3TC221 = new DrugOrder();
            drugOrder3TC221.setStartDate(startDate);
            drugOrder3TC221.setPatient(patient);
            drugOrder3TC221.setDateCreated(dateCreated);
            drugOrder3TC221.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TC3));
            drugOrder3TC221.setDose(300.0);
            drugOrder3TC221.setUnits("mg");
            drugOrder3TC221.setFrequency("Once daily");
            drugOrder3TC221.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrderABC221 = new DrugOrder();
            drugOrderABC221.setStartDate(startDate);
            drugOrderABC221.setPatient(patient);
            drugOrderABC221.setDateCreated(dateCreated);
            drugOrderABC221.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.ABC));
            drugOrderABC221.setDose(300.0);
            drugOrderABC221.setUnits("mg");
            drugOrderABC221.setFrequency("Once daily");
            drugOrderABC221.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderAZT221);
            drugOrderSet.add(drugOrder3TC221);
            drugOrderSet.add(drugOrderABC221);
        }

        else if(order == 225) {
            DrugOrder drugOrderTDF225 = new DrugOrder();
            drugOrderTDF225.setStartDate(startDate);
            drugOrderTDF225.setPatient(patient);
            drugOrderTDF225.setDateCreated(dateCreated);
            drugOrderTDF225.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TDF));
            drugOrderTDF225.setDose(300.0);
            drugOrderTDF225.setUnits("mg");
            drugOrderTDF225.setFrequency("Once daily");
            drugOrderTDF225.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrderFTC225 = new DrugOrder();
            drugOrderFTC225.setStartDate(startDate);
            drugOrderFTC225.setPatient(patient);
            drugOrderFTC225.setDateCreated(dateCreated);
            drugOrderFTC225.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.FTC));
            drugOrderFTC225.setDose(300.0);
            drugOrderFTC225.setUnits("mg");
            drugOrderFTC225.setFrequency("Once daily");
            drugOrderFTC225.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderTDF225);
            drugOrderSet.add(drugOrderFTC225);
        }

        else if(order == 220 || order == 234 || order == 245) {
            DrugOrder drugOrderAZT220234 = new DrugOrder();
            drugOrderAZT220234.setStartDate(startDate);
            drugOrderAZT220234.setPatient(patient);
            drugOrderAZT220234.setDateCreated(dateCreated);
            drugOrderAZT220234.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.AZT));
            drugOrderAZT220234.setDose(300.0);
            drugOrderAZT220234.setUnits("mg");
            drugOrderAZT220234.setFrequency("Once daily");
            drugOrderAZT220234.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrder3TC220234 = new DrugOrder();
            drugOrder3TC220234.setStartDate(startDate);
            drugOrder3TC220234.setPatient(patient);
            drugOrder3TC220234.setDateCreated(dateCreated);
            drugOrder3TC220234.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TC3));
            drugOrder3TC220234.setDose(300.0);
            drugOrder3TC220234.setUnits("mg");
            drugOrder3TC220234.setFrequency("Once daily");
            drugOrder3TC220234.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrderNVP220234 = new DrugOrder();
            drugOrderNVP220234.setStartDate(startDate);
            drugOrderNVP220234.setPatient(patient);
            drugOrderNVP220234.setDateCreated(dateCreated);
            drugOrderNVP220234.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.NVP));
            drugOrderNVP220234.setDose(300.0);
            drugOrderNVP220234.setUnits("mg");
            drugOrderNVP220234.setFrequency("Once daily");
            drugOrderNVP220234.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderAZT220234);
            drugOrderSet.add(drugOrder3TC220234);
            drugOrderSet.add(drugOrderNVP220234);
        }

        else if(order == 38 || order == 237) {

            DrugOrder drugOrderAZT38 = new DrugOrder();
            drugOrderAZT38.setStartDate(startDate);
            drugOrderAZT38.setPatient(patient);
            drugOrderAZT38.setDateCreated(dateCreated);
            drugOrderAZT38.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.AZT));
            drugOrderAZT38.setDose(300.0);
            drugOrderAZT38.setUnits("mg");
            drugOrderAZT38.setFrequency("Once daily");
            drugOrderAZT38.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderAZT38);
        }

        else if(order == 35) {

            DrugOrder drugOrderABC35 = new DrugOrder();
            drugOrderABC35.setStartDate(startDate);
            drugOrderABC35.setPatient(patient);
            drugOrderABC35.setDateCreated(dateCreated);
            drugOrderABC35.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.ABC));
            drugOrderABC35.setDose(300.0);
            drugOrderABC35.setUnits("mg");
            drugOrderABC35.setFrequency("Once daily");
            drugOrderABC35.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderABC35);
        }

        else if(order == 37) {

            DrugOrder drugOrderAPV37 = new DrugOrder();
            drugOrderAPV37.setStartDate(startDate);
            drugOrderAPV37.setPatient(patient);
            drugOrderAPV37.setDateCreated(dateCreated);
            drugOrderAPV37.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.APV));
            drugOrderAPV37.setDose(300.0);
            drugOrderAPV37.setUnits("mg");
            drugOrderAPV37.setFrequency("Once daily");
            drugOrderAPV37.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderAPV37);

        }

        else if(order == 44) {

            DrugOrder drugOrderIDV44 = new DrugOrder();
            drugOrderIDV44.setStartDate(startDate);
            drugOrderIDV44.setPatient(patient);
            drugOrderIDV44.setDateCreated(dateCreated);
            drugOrderIDV44.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.IDV));
            drugOrderIDV44.setDose(300.0);
            drugOrderIDV44.setUnits("mg");
            drugOrderIDV44.setFrequency("Once daily");
            drugOrderIDV44.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderIDV44);

        }

        else if(order == 45 || order == 243) {

            DrugOrder drugOrderLVP = new DrugOrder();
            drugOrderLVP.setStartDate(startDate);
            drugOrderLVP.setPatient(patient);
            drugOrderLVP.setDateCreated(dateCreated);
            drugOrderLVP.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.LPV));
            drugOrderLVP.setDose(300.0);
            drugOrderLVP.setUnits("mg");
            drugOrderLVP.setFrequency("Once daily");
            drugOrderLVP.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            DrugOrder drugOrderRTV = new DrugOrder();
            drugOrderRTV.setStartDate(startDate);
            drugOrderRTV.setPatient(patient);
            drugOrderRTV.setDateCreated(dateCreated);
            drugOrderRTV.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.RTV));
            drugOrderRTV.setDose(300.0);
            drugOrderRTV.setUnits("mg");
            drugOrderRTV.setFrequency("Once daily");
            drugOrderRTV.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderLVP);
            drugOrderSet.add(drugOrderRTV);

        }

        else if(order == 47 || order == 242) {

            DrugOrder drugOrderNFV = new DrugOrder();
            drugOrderNFV.setStartDate(startDate);
            drugOrderNFV.setPatient(patient);
            drugOrderNFV.setDateCreated(dateCreated);
            drugOrderNFV.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.NFV));
            drugOrderNFV.setDose(300.0);
            drugOrderNFV.setUnits("mg");
            drugOrderNFV.setFrequency("Once daily");
            drugOrderNFV.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderNFV);
        }

        else if(order == 49) {

            DrugOrder drugOrderRTV = new DrugOrder();
            drugOrderRTV.setStartDate(startDate);
            drugOrderRTV.setPatient(patient);
            drugOrderRTV.setDateCreated(dateCreated);
            drugOrderRTV.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.RTV));
            drugOrderRTV.setDose(300.0);
            drugOrderRTV.setUnits("mg");
            drugOrderRTV.setFrequency("Once daily");
            drugOrderRTV.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderRTV);
        }

        else if(order == 50) {

            DrugOrder drugOrderSQV = new DrugOrder();
            drugOrderSQV.setStartDate(startDate);
            drugOrderSQV.setPatient(patient);
            drugOrderSQV.setDateCreated(dateCreated);
            drugOrderSQV.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.SQV));
            drugOrderSQV.setDose(300.0);
            drugOrderSQV.setUnits("mg");
            drugOrderSQV.setFrequency("Once daily");
            drugOrderSQV.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderSQV);
        }

        else if(order == 230) {

            DrugOrder drugOrderFTC = new DrugOrder();
            drugOrderFTC.setStartDate(startDate);
            drugOrderFTC.setPatient(patient);
            drugOrderFTC.setDateCreated(dateCreated);
            drugOrderFTC.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.FTC));
            drugOrderFTC.setDose(300.0);
            drugOrderFTC.setUnits("mg");
            drugOrderFTC.setFrequency("Once daily");
            drugOrderFTC.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderFTC);
        }

        else if(order == 231) {

            DrugOrder drugOrderTDF = new DrugOrder();
            drugOrderTDF.setStartDate(startDate);
            drugOrderTDF.setPatient(patient);
            drugOrderTDF.setDateCreated(dateCreated);
            drugOrderTDF.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.TDF));
            drugOrderTDF.setDose(300.0);
            drugOrderTDF.setUnits("mg");
            drugOrderTDF.setFrequency("Once daily");
            drugOrderTDF.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));


            drugOrderSet.add(drugOrderTDF);
        }

        else if(order == 232) {

            DrugOrder drugOrderATZ = new DrugOrder();
            drugOrderATZ.setStartDate(startDate);
            drugOrderATZ.setPatient(patient);
            drugOrderATZ.setDateCreated(dateCreated);
            drugOrderATZ.setConcept(conceptService.getConceptByUuid(ConceptFuschia._DRUGS.ATZ));
            drugOrderATZ.setDose(300.0);
            drugOrderATZ.setUnits("mg");
            drugOrderATZ.setFrequency("Once daily");
            drugOrderATZ.setOrderType(orderService.getOrderTypeByUuid(BasicConstants._OrderType.DRUG));

            drugOrderSet.add(drugOrderATZ);
        }

        return  drugOrderSet;
    }

    public static  Concept medicationOrderAnswers(Integer value) {
        Concept concept = null;
        ConceptService conceptService = Context.getConceptService();

        if(value == 51 || value == 148) {

            concept = conceptService.getConceptByUuid(ConceptFuschia._DRUGS.COTRIMOXAZOLE);
        }
        else if(value == 52 || value == 53 || value ==149 || value == 150) {

            concept = conceptService.getConceptByUuid(ConceptFuschia._DRUGS.FLUCONAZOLE);
        }

        else if (value == 54 || value == 151) {

            concept = conceptService.getConceptByUuid(ConceptFuschia._DRUGS.ISONIAZID_PROPHYLAXIS);

        }
        else if(value == 247) {

            concept = conceptService.getConceptByUuid(ConceptFuschia._DRUGS.DAPSONE);

        }
        else if(value == 246) {

            concept = conceptService.getConceptByUuid(ConceptFuschia._DRUGS.ITRACONAZOLE);

        }

         return concept;
    }

    public static  Boolean isMedicationOrder(Integer value) {
        Boolean status = false;
        List<Integer> listOfPossibleValues = Arrays.asList(51, 52, 53, 54, 148, 149, 150, 151, 246, 247);

        if(listOfPossibleValues.contains(value)) {

            status = true;
        }

        return status;
    }
}
