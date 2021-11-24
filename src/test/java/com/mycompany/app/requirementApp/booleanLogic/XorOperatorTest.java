package com.mycompany.app.requirementApp.booleanLogic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class XorOperatorTest {
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
		XorOperator booleanExpression = new XorOperator(firstSentence, secondSentence);
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		expectedCombination.addAll(
				new ArrayList<ArrayList<String>>(Arrays.asList(new ArrayList<String>(Arrays.asList(firstSentenceText)),
						new ArrayList<String>(Arrays.asList(secondSentenceText)))));
		assertEquals("the text of the expression should be the same", "(the light is ON XOR the alarm is ON)",
				booleanExpression.booleanExpressionText());
		assertEquals("(A XOR B) is true when the first is true or the second is true and not both are true",
				expectedCombination, booleanExpression.trueWhen());
	}

	@Test
	public void testLessBasicCase() {
		XorOperator booleanExpression = new XorOperator(firstSentence, secondSentence);
		XorOperator lessBasicBooleanExpression = new XorOperator(booleanExpression, thirdSentence);
		assertEquals("the text of the expression should be the same",
				"((the light is ON XOR the alarm is ON) XOR the water is CLOSED)",
				lessBasicBooleanExpression.booleanExpressionText());
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		expectedCombination.addAll(
				new ArrayList<ArrayList<String>>(Arrays.asList(new ArrayList<String>(Arrays.asList(firstSentenceText)),
						new ArrayList<String>(Arrays.asList(thirdSentenceText)),
						new ArrayList<String>(Arrays.asList(secondSentenceText)))));
		assertEquals("((A XOR B) XOR C) is true when only one of them is true", expectedCombination,
				lessBasicBooleanExpression.trueWhen());
	}

	@Test
	public void testComplexCase() {
		XorOperator booleanExpression = new XorOperator(firstSentence, secondSentence);
		XorOperator secondBooleanExpression = new XorOperator(thirdSentence, fourthSentence);
		XorOperator complexBooleanExpression = new XorOperator(booleanExpression, secondBooleanExpression);
		assertEquals("the text of the expression should be the same",
				"((the light is ON XOR the alarm is ON) XOR (the water is CLOSED XOR the door is LOCKED))",
				complexBooleanExpression.booleanExpressionText());
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		expectedCombination.addAll(new ArrayList<ArrayList<String>>(Arrays.asList(
				new ArrayList<String>(Arrays.asList(firstSentenceText)),
				new ArrayList<String>(Arrays.asList(thirdSentenceText)),
				new ArrayList<String>(Arrays.asList(fourthSentenceText)),
				new ArrayList<String>(Arrays.asList(secondSentenceText)))));
		assertEquals("((A XOR B)XOR(C XOR D)) is true when only one of the variable is true", expectedCombination,
				complexBooleanExpression.trueWhen());
	}
}