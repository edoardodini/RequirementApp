package com.mycompany.app.requirementApp;

import com.mycompany.app.requirementApp.controller.RequirementController;
import com.mycompany.app.requirementApp.model.requirements.RequirementParser;
import com.mycompany.app.requirementApp.repository.RealRequirementRepository;
import com.mycompany.app.requirementApp.repository.RequirementRepository;
import com.mycompany.app.requirementApp.view.swing.RequirementSwingView;

public class RequirementSwingApp {

	public static void main(String[] args) {
		RequirementRepository requirementRepository = new RealRequirementRepository();
		RequirementSwingView requirementView = new RequirementSwingView();
		RequirementController requirementController = new RequirementController(requirementView, requirementRepository,
				new RequirementParser());
		requirementView.setRequirementController(requirementController);
		requirementView.setVisible(true);
		requirementController.allRequirements();

	}
}
