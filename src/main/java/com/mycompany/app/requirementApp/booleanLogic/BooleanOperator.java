package com.mycompany.app.requirementApp.booleanLogic;

public abstract class BooleanOperator implements BooleanExpression {
	private BooleanExpression leftOperand;
	private BooleanExpression rightOperand;
	protected String operator;

	public BooleanOperator(BooleanExpression left, BooleanExpression right) {
		leftOperand = left;
		rightOperand = right;
	}

	public BooleanExpression getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(BooleanExpression leftOperand) {
		this.leftOperand = leftOperand;
	}

	public BooleanExpression getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(BooleanExpression rightOperand) {
		this.rightOperand = rightOperand;
	}

	@Override
	public String booleanExpressionText() {
		return "(" + getLeftOperand().booleanExpressionText() + " " + operatorSign() + " "
				+ getRightOperand().booleanExpressionText() + ")";
	}

	private String operatorSign() {
		return operator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leftOperand == null) ? 0 : leftOperand.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((rightOperand == null) ? 0 : rightOperand.hashCode());
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
		BooleanOperator other = (BooleanOperator) obj;
		if (leftOperand == null) {
			if (other.leftOperand != null)
				return false;
		} else if (!leftOperand.equals(other.leftOperand))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (rightOperand == null) {
			if (other.rightOperand != null)
				return false;
		} else if (!rightOperand.equals(other.rightOperand))
			return false;
		return true;
	}
	
}
