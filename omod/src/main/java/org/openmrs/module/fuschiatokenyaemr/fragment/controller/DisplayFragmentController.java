package org.openmrs.module.fuschiatokenyaemr.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 * Created by codehub on 17/03/15.
 */
public class DisplayFragmentController {
	public void  controller(FragmentModel model){
		model.addAttribute("patients", Context.getPatientService().getAllPatients());
	}
}
