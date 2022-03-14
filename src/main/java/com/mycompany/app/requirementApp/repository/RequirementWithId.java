package com.mycompany.app.requirementApp.repository;

import com.mycompany.app.requirementApp.model.requirements.Requirement;

public class RequirementWithId {
	private String id;
	private Requirement requirement;

	public RequirementWithId(String id, Requirement requirement) {
		this.id = id;
		this.requirement = requirement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	public String toString() {
		return id + " " + requirement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((requirement == null) ? 0 : requirement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequirementWithId other = (RequirementWithId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (requirement == null) {
			if (other.requirement != null)
				return false;
		} else if (!requirement.equals(other.requirement))
			return false;
		return true;
	}

}
