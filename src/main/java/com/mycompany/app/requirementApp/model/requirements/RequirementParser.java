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
		String workingPossibleBooleanExpression = possibleBooleanExpression;
		workingPossibleBooleanExpression = workingPossibleBooleanExpression.trim();

		boolean operatorPresent = false;
		for (int i = 0; i < booleanOperator.length; i++) {
			if (workingPossibleBooleanExpression.contains(booleanOperator[i])) {
				operatorPresent = true;
			}
		}

		if (operatorPresent == false) {
			while(startAndEndWithParentheses(workingPossibleBooleanExpression)) {
				workingPossibleBooleanExpression = removeFirstAndLastParenthesesAndTrim(workingPossibleBooleanExpression);
			}
			return new BooleanVariable(workingPossibleBooleanExpression);
		} else {
			return booleanOperator(workingPossibleBooleanExpression);
		}
	}

	private BooleanOperator booleanOperator(String workingPossibleBooleanExpression)
			throws NoGoodRequirementFormatException {
		int openParentheses = 0;
		int closedParentheses = 0;
		boolean toTrim = true;
		for (int i = 0; i < workingPossibleBooleanExpression.length(); i++) {
			if (workingPossibleBooleanExpression.charAt(i) == '(') {
				openParentheses++;
			}
			if (workingPossibleBooleanExpression.charAt(i) == ')') {
				closedParentheses++;
			}
			if (closedParentheses > openParentheses) {
				throw new NoGoodRequirementFormatException("no good parentheses format");
			}
			if ((closedParentheses == openParentheses) && i < workingPossibleBooleanExpression.length() - 1 && i > 0) {
				toTrim = false;
			}
		}

		if (toTrim) {
			workingPossibleBooleanExpression = removeFirstAndLastParenthesesAndTrim(workingPossibleBooleanExpression);
		}
		int secondRunOpen = 0;
		int secondRunClosed = 0;
		for (int i = 0; i < workingPossibleBooleanExpression.length(); i++) {
			if (workingPossibleBooleanExpression.charAt(i) == '(') {
				secondRunOpen++;
			}
			if (workingPossibleBooleanExpression.charAt(i) == ')') {
				secondRunClosed++;
			}
			if (secondRunClosed == secondRunOpen) {
				if (workingPossibleBooleanExpression.substring(i).startsWith(" AND ")) {
					return new AndOperator(booleanExpression(workingPossibleBooleanExpression.substring(0, i)),
							booleanExpression(workingPossibleBooleanExpression.substring(i + 5)));
				}
				if (workingPossibleBooleanExpression.substring(i).startsWith(" OR ")) {
					return new OrOperator(booleanExpression(workingPossibleBooleanExpression.substring(0, i)),
							booleanExpression(workingPossibleBooleanExpression.substring(i + 4)));
				}
				if (workingPossibleBooleanExpression.substring(i).startsWith(" XOR ")) {
					return new XorOperator(booleanExpression(workingPossibleBooleanExpression.substring(0, i)),
							booleanExpression(workingPossibleBooleanExpression.substring(i + 5)));
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
