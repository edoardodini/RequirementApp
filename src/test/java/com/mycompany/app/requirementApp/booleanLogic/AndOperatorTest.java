package com.mycompany.app.requirementApp.booleanLogic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AndOperatorTest {
	String firstSentenceText = "the light is ON";
	String secondSentenceText = "the alarm is ON";
	String thirdSentenceText = "the water is CLOSED";
	String fourthSentenceText = "the door is LOCKED";
	BooleanVariable firstSentence = new BooleanVariable(firstSentenceText);
	BooleanVariable secondSentence = new BooleanVariable(secondSentenceText);
	BooleanVariable thirdSentence = new BooleanVariable(thirdSentenceText);
	BooleanVariable fourthSentence = new BooleanVariable(fourthSentenceText);

	@Test
	public void testBasicCase() {
		AndOperator booleanExpression = new AndOperator(firstSentence, secondSentence);
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		ArrayList<String> validCombination = new ArrayList<String>(
				Arrays.asList(firstSentenceText, secondSentenceText));
		expectedCombination.add(validCombination);
		assertEquals("the text of the expression should be the same", "(the light is ON AND the alarm is ON)",
				booleanExpression.booleanExpressionText());
		assertEquals("(A&&B) is true when both are true", expectedCombination, booleanExpression.trueWhen());
	}

	@Test
	public void testLessBasicCase() {
		AndOperator booleanExpression = new AndOperator(firstSentence, secondSentence);
		AndOperator lessBasicBooleanExpression = new AndOperator(booleanExpression, thirdSentence);
		assertEquals("the text of the expression should be the same",
				"((the light is ON AND the alarm is ON) AND the water is CLOSED)",
				lessBasicBooleanExpression.booleanExpressionText());
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		ArrayList<String> validCombination = new ArrayList<String>(
				Arrays.asList(firstSentenceText, secondSentenceText, thirdSentenceText));
		expectedCombination.add(validCombination);
		assertEquals("((A&&B)&&C) is only true when all three are true", expectedCombination,
				lessBasicBooleanExpression.trueWhen());
	}

	@Test
	public void testComplexCase() {
		AndOperator booleanExpression = new AndOperator(firstSentence, secondSentence);
		AndOperator secondBooleanExpression = new AndOperator(thirdSentence, fourthSentence);
		AndOperator complexBooleanExpression = new AndOperator(booleanExpression, secondBooleanExpression);
		assertEquals("the text of the expression should be the same",
				"((the light is ON AND the alarm is ON) AND (the water is CLOSED AND the door is LOCKED))",
				complexBooleanExpression.booleanExpressionText());
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		ArrayList<String> validCombination = new ArrayList<String>(
				Arrays.asList(firstSentenceText, secondSentenceText, thirdSentenceText, fourthSentenceText));
		expectedCombination.add(validCombination);
		assertEquals("((A&&B)&&C) is only true when all three are true", expectedCombination,
				complexBooleanExpression.trueWhen());
	}
}
