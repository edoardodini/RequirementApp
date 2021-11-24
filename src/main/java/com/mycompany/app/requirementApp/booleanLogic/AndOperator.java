package com.mycompany.app.requirementApp.booleanLogic;

import java.util.ArrayList;
import java.util.List;

public class AndOperator extends BooleanOperator {

	public AndOperator(BooleanExpression left, BooleanExpression right) {
		super(left, right);
		operator = "AND";
	}

	@Override
	public List<ArrayList<String>> trueWhen() {
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < getLeftOperand().trueWhen().size(); i++) {
			for (int j = 0; j < getRightOperand().trueWhen().size(); j++) {
				ArrayList<String> newList = new ArrayList<String>();
				newList.addAll(getLeftOperand().trueWhen().get(i));
				newList.addAll(getRightOperand().trueWhen().get(j));
				
				if (!(list.contains(newList))) {
					list.add(newList);
				}
			}
		}
		return list;
	}
	
}
