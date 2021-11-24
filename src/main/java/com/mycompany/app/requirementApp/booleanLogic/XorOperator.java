package com.mycompany.app.requirementApp.booleanLogic;

import java.util.ArrayList;
import java.util.List;

public class XorOperator extends BooleanOperator {

	public XorOperator(BooleanExpression left, BooleanExpression right) {
		super(left, right);
		operator = "XOR";
	}

	@Override
	public List<ArrayList<String>> trueWhen() {
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < getLeftOperand().trueWhen().size(); i++) {
			for (int j = 0; j < getRightOperand().trueWhen().size(); j++) {

				if (!(list.contains(getLeftOperand().trueWhen().get(i)))) {
					list.add(getLeftOperand().trueWhen().get(i));
				}

				if (!(list.contains(getRightOperand().trueWhen().get(j)))) {
					list.add(getRightOperand().trueWhen().get(j));
				}

			}
		}
		return list;
	}

}