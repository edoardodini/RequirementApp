package com.mycompany.app.requirementApp.model.booleanLogic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class OrOperatorTest {
	String firstSentenceText = "the light is ON";
	String secondSentenceText = "the alarm is ON";
	String thirdSentenceText = "the water is CLOSED";
	String fourthSentenceText = "the door is LOCKED";
	BooleanVariable firstSentence = new BooleanVariable(firstSentenceText);
	BooleanVariable secondSentence = new BooleanVariable(secondSentenceText);
	BooleanVariable thirdSentence = new BooleanVariable(thirdSentenceText);
	BooleanVariable fourthSentence = new BooleanVariable(fourthSentenceText);
	ArrayList<String> oneTwoCombination = new ArrayList<String>(Arrays.asList(firstSentenceText, secondSentenceText));
	ArrayList<String> oneThreeCombination = new ArrayList<String>(Arrays.asList(firstSentenceText, thirdSentenceText));
	ArrayList<String> oneFourCombination = new ArrayList<String>(Arrays.asList(firstSentenceText, fourthSentenceText));
	ArrayList<String> twoThreeCombination = new ArrayList<String>(Arrays.asList(secondSentenceText, thirdSentenceText));
	ArrayList<String> twoFourCombination = new ArrayList<String>(Arrays.asList(secondSentenceText, fourthSentenceText));
	ArrayList<String> threeFourCombination = new ArrayList<String>(
			Arrays.asList(thirdSentenceText, fourthSentenceText));
	ArrayList<String> oneTwoThreeCombination = new ArrayList<String>(
			Arrays.asList(firstSentenceText, secondSentenceText, thirdSentenceText));
	ArrayList<String> oneTwoFourCombination = new ArrayList<String>(
			Arrays.asList(firstSentenceText, secondSentenceText, fourthSentenceText));
	ArrayList<String> oneThreeFourCombination = new ArrayList<String>(
			Arrays.asList(firstSentenceText, thirdSentenceText, fourthSentenceText));
	ArrayList<String> twoThreeFourCombination = new ArrayList<String>(
			Arrays.asList(secondSentenceText, thirdSentenceText, fourthSentenceText));
	ArrayList<String> oneTwoThreeFourCombination = new ArrayList<String>(
			Arrays.asList(firstSentenceText, secondSentenceText, thirdSentenceText, fourthSentenceText));

	@Test
	public void testBasicCase() {
		OrOperator booleanExpression = new OrOperator(firstSentence, secondSentence);
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		expectedCombination.addAll(
				new ArrayList<ArrayList<String>>(Arrays.asList(new ArrayList<String>(Arrays.asList(firstSentenceText)),
						new ArrayList<String>(Arrays.asList(secondSentenceText)), oneTwoCombination)));
		assertEquals("the text of the expression should be the same", "(the light is ON OR the alarm is ON)",
				booleanExpression.booleanExpressionText());
		assertEquals("(A||B) is true when the first is true or the second is true or both are true",
				expectedCombination, booleanExpression.trueWhen());
	}

	@Test
	public void testLessBasicCase() {
		OrOperator booleanExpression = new OrOperator(firstSentence, secondSentence);
		OrOperator lessBasicBooleanExpression = new OrOperator(booleanExpression, thirdSentence);
		assertEquals("the text of the expression should be the same",
				"((the light is ON OR the alarm is ON) OR the water is CLOSED)",
				lessBasicBooleanExpression.booleanExpressionText());
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		expectedCombination.addAll(
				new ArrayList<ArrayList<String>>(Arrays.asList(new ArrayList<String>(Arrays.asList(firstSentenceText)),
						new ArrayList<String>(Arrays.asList(thirdSentenceText)), oneThreeCombination,
						new ArrayList<String>(Arrays.asList(secondSentenceText)), twoThreeCombination,
						oneTwoCombination, oneTwoThreeCombination)));
		assertEquals("((A&&B)&&C) is true when the boolean rules are respected", expectedCombination,
				lessBasicBooleanExpression.trueWhen());
	}

	@Test
	public void testComplexCase() {
		OrOperator booleanExpression = new OrOperator(firstSentence, secondSentence);
		OrOperator secondBooleanExpression = new OrOperator(thirdSentence, fourthSentence);
		OrOperator complexBooleanExpression = new OrOperator(booleanExpression, secondBooleanExpression);
		assertEquals("the text of the expression should be the same",
				"((the light is ON OR the alarm is ON) OR (the water is CLOSED OR the door is LOCKED))",
				complexBooleanExpression.booleanExpressionText());
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		expectedCombination.addAll(new ArrayList<ArrayList<String>>(Arrays.asList(
				new ArrayList<String>(Arrays.asList(firstSentenceText)),
				new ArrayList<String>(Arrays.asList(thirdSentenceText)), oneThreeCombination,
				new ArrayList<String>(Arrays.asList(fourthSentenceText)), oneFourCombination, threeFourCombination,
				oneThreeFourCombination, new ArrayList<String>(Arrays.asList(secondSentenceText)), twoThreeCombination,
				twoFourCombination, twoThreeFourCombination, oneTwoCombination, oneTwoThreeCombination,
				oneTwoFourCombination, oneTwoThreeFourCombination)));
		assertEquals("((A&&B)&&(C&&D)) is true when the boolean rules are respected", expectedCombination,
				complexBooleanExpression.trueWhen());
	}
}