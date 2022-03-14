package com.mycompany.app.requirementApp.controller;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.app.requirementApp.model.requirements.NoGoodRequirementFormatException;
import com.mycompany.app.requirementApp.model.requirements.Requirement;
import com.mycompany.app.requirementApp.model.requirements.RequirementParser;
import com.mycompany.app.requirementApp.repository.RequirementRepository;
import com.mycompany.app.requirementApp.repository.RequirementWithId;
import com.mycompany.app.requirementApp.view.RequirementView;

public class RequirementController {
	private RequirementParser requirementParser;
	private RequirementView requirementView;
	private RequirementRepository requirementRepository;

	public RequirementController(RequirementView requirementView, RequirementRepository requirementRepository,
			RequirementParser requirementParser) {
		this.requirementView = requirementView;
		this.requirementRepository = requirementRepository;
		this.requirementParser = requirementParser;
	}

	public void allRequirements() {
		requirementView.showAllRequirements(requirementRepository.findAll());
	}

	public void newRequirement(String requirementId, String requirement) {
		requirementId = requirementId.replace(' ', '_').replace('	', '_');
		RequirementWithId existingRequirement = requirementRepository.findById(requirementId);
		if (existingRequirement != null) {
			requirementView
					.showError("Already existing requirement with id " + requirementId + ": " + existingRequirement);
			return;
		}
		try {
			Requirement req = requirementParser.createRequirement(requirement);
			RequirementWithId requirementWithId = new RequirementWithId(requirementId, req);
			requirementRepository.save(requirementWithId);
			requirementView.requirementAdded(requirementWithId);
		} catch (NoGoodRequirementFormatException e) {
			requirementView.showError(e.getMessage());
		}

	}

	public void deleteRequirement(RequirementWithId requirement) {
		if (requirementRepository.findById(requirement.getId()) == null) {
			requirementView.showError("No existing requirement with id " + requirement.getId() + ": " + requirement);
			return;
		}
		requirementRepository.delete(requirement.getId());
		requirementView.requirementRemoved(requirement);
	}

	public void generateTestCases(List<RequirementWithId> requirement) {
		requirementView.generateTestCases(requirement);
	}

	public void importRequirements(String requirementString) {
		String[] requirements = getRequirementStrings(requirementString);
		if (thereAreEmptyRequirement(requirements)) {
			requirementView.showImportError("there is one or more empty line between the requirements to import");
		} else if (firstStringWithoutSpaces(requirements) != null) {
			requirementView.showImportError(
					"there is at least a requirement with a wrong format: " + firstStringWithoutSpaces(requirements));
		} else {
			List<RequirementWithId> requirementList = new ArrayList<RequirementWithId>();
			boolean importable = true;
			for (int i = 0; i < requirements.length; i++) {
				String id = requirements[i].substring(0, requirements[i].indexOf(" "));
				String requirement = requirements[i].substring(requirements[i].indexOf(" ") + 1);
				try {
					requirementList.add(new RequirementWithId(id, requirementParser.createRequirement(requirement)));
				} catch (NoGoodRequirementFormatException e) {
					importable = false;
					requirementView.showImportError(
							"there is at least one format error. " + e.getMessage() + ": " + requirement);
				}
			}
			if (importable) {
				for (int i = 0; i < requirementList.size(); i++) {
					if (!(requirementRepository.findById(requirementList.get(i).getId()) == null)) {
						importable = false;
						requirementView.showImportError(
								"at least one requirement to be imported has the same id of a present requirement: "
										+ requirementList.get(i).getId());
						break;
					}
				}
				if (importable) {
					if (doublesIdInImport(requirementList) == -1) {
						requirementView.importRequirements(requirementList);
						requirementList.stream().forEach((reqWithId) -> {
							requirementRepository.save(reqWithId);
						});
					} else {
						requirementView.showImportError("at least two requirements to be imported has the same id: "
								+ requirementList.get(doublesIdInImport(requirementList)).getId());
					}
				}
			}
		}
	}

	private int doublesIdInImport(List<RequirementWithId> requirementList) {
		if (requirementList.size() > 1) {
			for (int i = 0; i < requirementList.size() - 1; i++) {
				for (int j = i + 1; j < requirementList.size(); j++) {
					if (requirementList.get(i).getId().equals(requirementList.get(j).getId())) {
						return i;
					}
				}
			}
			return -1;
		} else {
			return -1;
		}
	}

	private boolean thereAreEmptyRequirement(String[] requirements) {
		for (int i = 0; i < requirements.length; i++) {
			if (requirements[i].isEmpty())
				return true;
		}
		return false;
	}

	private String firstStringWithoutSpaces(String[] requirements) {
		for (int i = 0; i < requirements.length; i++) {
			if (!requirements[i].contains(" ")) {
				return requirements[i];
			}
		}
		return null;
	}

	private String[] getRequirementStrings(String possibleRequirements) {
		return possibleRequirements.split("\\r?\\n");
	}

}
