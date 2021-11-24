package com.mycompany.app.requirementApp.booleanLogic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class OperatorsTest {
	String firstSentenceText = "the level is one of the levels";
	String secondSentenceText = "the order to contact RBC is received";
	String thirdSentenceText = "the level is 2/3";
	String fourthSentenceText = "the system is entering sleeping mode";
	String fifthSentenceText = "the system is exiting sleeping mode";
	String sixthSentenceText = "there is a fault";
	BooleanVariable firstSentence = new BooleanVariable(firstSentenceText);
	BooleanVariable secondSentence = new BooleanVariable(secondSentenceText);
	BooleanVariable thirdSentence = new BooleanVariable(thirdSentenceText);
	BooleanVariable fourthSentence = new BooleanVariable(fourthSentenceText);
	BooleanVariable fifthSentence = new BooleanVariable(fifthSentenceText);
	BooleanVariable sixthSentence = new BooleanVariable(sixthSentenceText);

	ArrayList<String> oneTwoCombination = new ArrayList<String>(Arrays.asList(firstSentenceText, secondSentenceText));
	ArrayList<String> threeFourCombination = new ArrayList<String>(
			Arrays.asList(thirdSentenceText, fourthSentenceText));
	ArrayList<String> threeFiveCombination = new ArrayList<String>(Arrays.asList(thirdSentenceText, fifthSentenceText));
	ArrayList<String> threeSixCombination = new ArrayList<String>(Arrays.asList(thirdSentenceText, sixthSentenceText));
	ArrayList<String> threeFourSixCombination = new ArrayList<String>(
			Arrays.asList(thirdSentenceText, fourthSentenceText, sixthSentenceText));
	ArrayList<String> threeFiveSixCombination = new ArrayList<String>(
			Arrays.asList(thirdSentenceText, fifthSentenceText, sixthSentenceText));

	AndOperator firstCase = new AndOperator(firstSentence, secondSentence);
	XorOperator sleepingCase = new XorOperator(fourthSentence, fifthSentence);
	OrOperator optionsCase = new OrOperator(sleepingCase, sixthSentence);
	AndOperator secondCase = new AndOperator(thirdSentence, optionsCase);
	XorOperator prerequisite = new XorOperator(firstCase, secondCase);

	@Test
	public void textTest() {
		assertEquals("the returned text should be equal",
				"((the level is one of the levels AND the order to contact RBC is received) XOR (the level is 2/3 AND ((the system is entering sleeping mode XOR the system is exiting sleeping mode) OR there is a fault)))",
				prerequisite.booleanExpressionText());
	}

	@Test
	public void CasesTest() {
		List<ArrayList<String>> expectedCombination = new ArrayList<ArrayList<String>>();
		expectedCombination
				.addAll(new ArrayList<ArrayList<String>>(Arrays.asList(oneTwoCombination, threeFourCombination,
						threeSixCombination, threeFourSixCombination, threeFiveCombination, threeFiveSixCombination)));

		assertEquals("the returned cases should be equal", expectedCombination, prerequisite.trueWhen());
	}
}
