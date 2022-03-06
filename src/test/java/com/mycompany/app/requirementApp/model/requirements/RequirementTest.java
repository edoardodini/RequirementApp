package com.mycompany.app.requirementApp.model.requirements;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mycompany.app.requirementApp.model.booleanLogic.BooleanVariable;
import com.mycompany.app.requirementApp.model.booleanLogic.OrOperator;

public class RequirementTest {

	@Test
	public void basicRequirementTest() {
		String prerequisiteText = "prerequisiteText";
		String effect = "effect";
		Requirement requirement = new Requirement(new BooleanVariable(prerequisiteText), effect);
		String expectedRequirementText = "WHEN " + prerequisiteText + " THEN " + effect;

		assertEquals("the requirement text returned is as expected", expectedRequirementText,
				requirement.requirementText());
		List<String> expectedTestCases = new ArrayList<String>(
				Arrays.asList("put the system in a state WHERE (" + prerequisiteText + ") and check THAT " + effect));
		assertEquals("the test case are the same", expectedTestCases, requirement.requirementTestCase());
	}

	@Test
	public void lessBasicRequirementTest() {
		String firstCondition = "firstCondition";
		BooleanVariable firstVariable = new BooleanVariable(firstCondition);
		String secondCondition = "secondCondition";
		BooleanVariable secondVariable = new BooleanVariable(secondCondition);
		OrOperator prerequisite = new OrOperator(firstVariable, secondVariable);
		String effect = "effect";
		Requirement requirement = new Requirement(prerequisite, effect);
		String expectedRequirementText = "WHEN (" + firstCondition + " OR " + secondCondition + ") THEN " + effect;

		assertEquals("the requirement text returned is as expected", expectedRequirementText,
				requirement.requirementText());
		List<String> expectedTestCases = new ArrayList<String>(
				Arrays.asList("put the system in a state WHERE (" + firstCondition + ") and check THAT " + effect,
						"put the system in a state WHERE (" + secondCondition + ") and check THAT " + effect,
						"put the system in a state WHERE (" + firstCondition + " AND " + secondCondition
								+ ") and check THAT " + effect));
		assertEquals("the test case are the same", expectedTestCases, requirement.requirementTestCase());
	}
}
