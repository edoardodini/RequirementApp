package com.mycompany.app.requirementApp.model.requirements;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.app.requirementApp.model.booleanLogic.BooleanExpression;

public class Requirement {
	private BooleanExpression prerequisite;
	private String effect;

	public Requirement(BooleanExpression prerequisite, String effect) {
		this.prerequisite = prerequisite;
		this.effect = effect;
	}

	public String requirementText() {
		return "WHEN " + prerequisite.booleanExpressionText() + " THEN " + effect;
	}

	@Override
	public String toString() {
		return requirementText();
	}

	public List<String> requirementTestCase() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < prerequisite.trueWhen().size(); i++) {
			String testCase = "put the system in a state WHERE (" + prerequisite.trueWhen().get(i).get(0);
			for (int j = 1; j < prerequisite.trueWhen().get(i).size(); j++) {
				testCase = testCase + " AND " + prerequisite.trueWhen().get(i).get(j);
			}
			testCase = testCase + ") and check THAT " + effect;
			list.add(testCase);
		}
		return list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effect == null) ? 0 : effect.hashCode());
		result = prime * result + ((prerequisite == null) ? 0 : prerequisite.hashCode());
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
		Requirement other = (Requirement) obj;
		if (effect == null) {
			if (other.effect != null)
				return false;
		} else if (!effect.equals(other.effect))
			return false;
		if (prerequisite == null) {
			if (other.prerequisite != null)
				return false;
		} else if (!prerequisite.equals(other.prerequisite))
			return false;
		return true;
	}
}
