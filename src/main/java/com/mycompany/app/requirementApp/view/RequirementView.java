package com.mycompany.app.requirementApp.view;

import java.util.List;

import com.mycompany.app.requirementApp.repository.RequirementWithId;

public interface RequirementView {
	void showAllRequirements(List<RequirementWithId> list);
	void showError(String message);
	void generateTestCases(List<RequirementWithId> listRequirementModel);
	void importRequirements(List<RequirementWithId> listRequirementToImport);
	void requirementAdded(RequirementWithId requirement);
	void requirementRemoved(RequirementWithId requirement);
	void showImportError(String message);
}
