package com.mycompany.app.requirementApp.model.requirements;

import com.mycompany.app.requirementApp.model.booleanLogic.AndOperator;
import com.mycompany.app.requirementApp.model.booleanLogic.BooleanExpression;
import com.mycompany.app.requirementApp.model.booleanLogic.BooleanOperator;
import com.mycompany.app.requirementApp.model.booleanLogic.BooleanVariable;
import com.mycompany.app.requirementApp.model.booleanLogic.OrOperator;
import com.mycompany.app.requirementApp.model.booleanLogic.XorOperator;

public class RequirementParser {
	private String[] prerequisiteStart = { "When ", "WHEN ", "when ", "If ", "IF ", "if " };
	private String[] effectStart = { " Then ", " THEN ", " then " };
	private String[] booleanOperator = { " AND ", " OR ", " XOR " };
	private RequirementDivider requirementDivider;

	public RequirementParser() {
		requirementDivider = new RequirementDivider(prerequisiteStart, effectStart);
	}

	public Requirement createRequirement(String possibleRequirement) throws NoGoodRequirementFormatException {
		PrerequisiteAndEffectString prereqAndEffect = requirementDivider
				.getPrerequisiteAndEffectString(possibleRequirement);
		return new Requirement(booleanExpression(prereqAndEffect.getPrerequisite()),
				prereqAndEffect.getEffect().trim());
	}

	private BooleanExpression booleanExpression(String possibleBooleanExpression)
			throws NoGoodRequirementFormatException {
		String workingPossibleBooleanExpression = possibleBooleanExpression.trim();

		if (isPossibleBooleanVariable(workingPossibleBooleanExpression)) {
			while (startAndEndWithParentheses(workingPossibleBooleanExpression)) {
				workingPossibleBooleanExpression = removeFirstAndLastParenthesesAndTrim(
						workingPossibleBooleanExpression);
			}
			return new BooleanVariable(workingPossibleBooleanExpression);
		} else {
			return booleanOperator(workingPossibleBooleanExpression);
		}
	}

	private boolean isPossibleBooleanVariable(String possibleBooleanVariable) throws NoGoodRequirementFormatException {
		if (!correctParentheses(possibleBooleanVariable)) {
			throw new NoGoodRequirementFormatException("no good parentheses format");
		}
		for (int i = 0; i < booleanOperator.length; i++) {
			if (possibleBooleanVariable.contains(booleanOperator[i])) {
				return false;
			}
		}
		return true;
	}

	private boolean externalParenthesesRemovable(String possibleBooleanExpression) {
		String expression = possibleBooleanExpression.trim();
		if (startAndEndWithParentheses(expression)) {
			if (expression.length() > 2) {
				if (correctParentheses(expression.substring(1, expression.length() - 1))) {
					return true;
				}
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean correctParentheses(String possibleBooleanExpression) {
		int openParentheses = 0;
		int closedParentheses = 0;
		for (int i = 0; i < possibleBooleanExpression.length(); i++) {
			if (possibleBooleanExpression.charAt(i) == '(') {
				openParentheses++;
			}
			if (possibleBooleanExpression.charAt(i) == ')') {
				closedParentheses++;
			}
			if (closedParentheses > openParentheses) {
				return false;
			}
		}
		if (closedParentheses == openParentheses) {
			return true;
		} else {
			return false;
		}
	}

	private BooleanOperator booleanOperator(String workingPossibleBooleanExpression)
			throws NoGoodRequirementFormatException {
		workingPossibleBooleanExpression.trim();
		if (!correctParentheses(workingPossibleBooleanExpression)) {
			throw new NoGoodRequirementFormatException("no good parentheses format");
		}
		while (externalParenthesesRemovable(workingPossibleBooleanExpression)) {
			workingPossibleBooleanExpression = removeFirstAndLastParenthesesAndTrim(workingPossibleBooleanExpression);
		}
		int openParentheses = 0;
		int closedParentheses = 0;
		for (int i = 0; i < workingPossibleBooleanExpression.length(); i++) {
			if (workingPossibleBooleanExpression.charAt(i) == '(') {
				openParentheses++;
			}
			if (workingPossibleBooleanExpression.charAt(i) == ')') {
				closedParentheses++;
			}
			if (openParentheses == closedParentheses && i > 0) {
				for (int operatorIndex = 0; operatorIndex < booleanOperator.length; operatorIndex++) {
					if (workingPossibleBooleanExpression.substring(i).startsWith(booleanOperator[operatorIndex])) {
						String leftOperand = workingPossibleBooleanExpression.substring(0, i);
						String rightOperand = workingPossibleBooleanExpression
								.substring(i + booleanOperator[operatorIndex].length());
						if (externalParenthesesRemovable(rightOperand)||isPossibleBooleanVariable(rightOperand)) {
							switch(booleanOperator[operatorIndex]) {
								case " AND ":
									return new AndOperator(booleanExpression(leftOperand), booleanExpression(rightOperand));
								case " OR ":
									return new OrOperator(booleanExpression(leftOperand), booleanExpression(rightOperand));
								case " XOR ":
									return new XorOperator(booleanExpression(leftOperand), booleanExpression(rightOperand));
								default:
									//remember to add the operator in che case if new operators are added
									throw new NoGoodRequirementFormatException("missing operator in the switch-case");
							}
						} else {
							throw new NoGoodRequirementFormatException("no good parentheses format");
						}
					}
				}
			}
		}
		throw new NoGoodRequirementFormatException("missing operator or wrong operator format");
	}

	private String removeFirstAndLastParenthesesAndTrim(String workingPossibleBooleanExpression) {
		return workingPossibleBooleanExpression.substring(1, workingPossibleBooleanExpression.length() - 1).trim();
	}

	private boolean startAndEndWithParentheses(String stringToCheck) {
		if (stringToCheck.startsWith("(") && stringToCheck.endsWith(")")) {
			return true;
		} else {
			return false;
		}
	}
}
