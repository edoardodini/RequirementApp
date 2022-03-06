package com.mycompany.app.requirementApp.model.booleanLogic;

import java.util.ArrayList;
import java.util.List;

public class OrOperator extends BooleanOperator {

	public OrOperator(BooleanExpression left, BooleanExpression right) {
		super(left, right);
		operator = "OR";
	}

	@Override
	public List<ArrayList<String>> trueWhen() {
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < getLeftOperand().trueWhen().size(); i++) {
			for (int j = 0; j < getRightOperand().trueWhen().size(); j++) {
				ArrayList<String> newList = new ArrayList<String>();
				newList.addAll(getLeftOperand().trueWhen().get(i));
				newList.addAll(getRightOperand().trueWhen().get(j));

				if (!(list.contains(getLeftOperand().trueWhen().get(i)))) {
					list.add(getLeftOperand().trueWhen().get(i));
				}

				if (!(list.contains(getRightOperand().trueWhen().get(j)))) {
					list.add(getRightOperand().trueWhen().get(j));
				}

				if (!(list.contains(newList))) {
					list.add(newList);
				}
			}
		}
		return list;
	}

}
