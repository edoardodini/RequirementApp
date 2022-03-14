package com.mycompany.app.requirementApp.repository;

import java.util.List;

public interface RequirementRepository {
	public List<RequirementWithId> findAll();
	public RequirementWithId findById(String id);
	public void save(RequirementWithId requirement);
	public void delete(String id);
}
