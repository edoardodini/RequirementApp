package com.mycompany.app.requirementApp.model.booleanLogic;

import java.util.ArrayList;
import java.util.List;

public interface BooleanExpression {
	public List<ArrayList<String>> trueWhen();
	public String booleanExpressionText();
	public boolean equals(Object obj);
	public int hashCode();
}
