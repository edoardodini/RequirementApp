package com.mycompany.app.requirementApp.repository;

import java.util.ArrayList;
import java.util.List;

public class RealRequirementRepository implements RequirementRepository{
	
	private ArrayList<RequirementWithId> requirementsList;
	
	public RealRequirementRepository() {
		requirementsList = new ArrayList<RequirementWithId>();
	}
	@Override
	public List<RequirementWithId> findAll() {
		return requirementsList;
	}

	@Override
	public RequirementWithId findById(String id) {
		for (int i = 0; i < requirementsList.size(); i++) {
			if (requirementsList.get(i).getId().equals(id)){
				return requirementsList.get(i);
			}
		}
		return null;
	}

	@Override
	public void save(RequirementWithId requirement) {
		requirementsList.add(requirement);
	}

	@Override
	public void delete(String id) {
		for (int i = 0; i < requirementsList.size(); i++) {
			if (requirementsList.get(i).getId().equals(id)){
				requirementsList.remove(i);
			}
		}
	}

}
