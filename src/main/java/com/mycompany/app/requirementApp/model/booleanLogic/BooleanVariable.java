package com.mycompany.app.requirementApp.model.booleanLogic;

import java.util.ArrayList;
import java.util.List;

public class BooleanVariable implements BooleanExpression {
	private String booleanVariableText;

	public List<ArrayList<String>> trueWhen() {
		List<ArrayList<String>> listOfList = new ArrayList<ArrayList<String>>();
		ArrayList<String> listOfVariable = new ArrayList<String>();
		listOfVariable.add(booleanVariableText);
		listOfList.add(listOfVariable);
		return listOfList;
	}

	public String booleanExpressionText() {
		return booleanVariableText;
	}

	public String getBooleanVariableText() {
		return booleanVariableText;
	}

	public void setBooleanVariableText(String booleanVariableText) {
		this.booleanVariableText = booleanVariableText;
	}

	public BooleanVariable(String booleanVariableText) {
		this.booleanVariableText = booleanVariableText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booleanVariableText == null) ? 0 : booleanVariableText.hashCode());
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
		BooleanVariable other = (BooleanVariable) obj;
		if (booleanVariableText == null) {
			if (other.booleanVariableText != null)
				return false;
		} else if (!booleanVariableText.equals(other.booleanVariableText))
			return false;
		return true;
	}
}
