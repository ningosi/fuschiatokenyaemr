package org.openmrs.module.fuschiatokenyaemr.page.controller;

import org.openmrs.module.fuschiatokenyaemr.BasicConstants;
import org.openmrs.module.fuschiatokenyaemr.database.AccessDatabase;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by codehub on 17/03/15.
 */
@AppPage(BasicConstants.APP_LAKE_HUB)
public class DisplayPageController {

	public void get(UiUtils ui,
						   PageModel model,
						   @RequestParam(value = "passwords", required = false) String password,
						   @RequestParam(value = "database", required = false) String data,
						   @SpringBean("fuschiatokenyaemr.updateService") AccessDatabase accessDatabase) throws Exception {


			model.addAttribute("patients", accessDatabase.numberOfPatientsAndVisits().keySet());
			model.addAttribute("visits", accessDatabase.numberOfPatientsAndVisits().values());


	}

	public void post(@SpringBean("fuschiatokenyaemr.updateService") AccessDatabase accessDatabase,
					 @RequestParam(value = "path", required = false) String path,
					 @RequestParam(value = "database", required = false) String data,
					 @RequestParam(value = "passwords", required = false) String password,
					 PageModel model) throws Exception {

		//save a patient encounter here
		accessDatabase.savePatients();
		model.addAttribute("patients", accessDatabase.numberOfPatientsAndVisits().keySet());
		model.addAttribute("visits", accessDatabase.numberOfPatientsAndVisits().values());

	}
}
