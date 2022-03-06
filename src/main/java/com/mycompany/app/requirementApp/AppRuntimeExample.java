package com.mycompany.app.requirementApp;

import java.util.List;

import com.mycompany.app.requirementApp.model.requirements.NoGoodRequirementFormatException;
import com.mycompany.app.requirementApp.model.requirements.Requirement;
import com.mycompany.app.requirementApp.model.requirements.RequirementParser;

public class AppRuntimeExample {
	public static void main(String[] args) throws NoGoodRequirementFormatException {
		RequirementParser reqParser = new RequirementParser();
		long startingTime = System.currentTimeMillis();
		// Example requirements
		System.out.println("Example requirements");
		String[] requirements = {
				"When x is equal to 1 then y should be equal to 1",
				"When x is equal to 0 XOR x is equal to 2 then y should be different from 1"};
		for (int j = 0; j < requirements.length; j++) {
			Requirement req = reqParser.createRequirement(requirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		long endingTime = System.currentTimeMillis();
		System.out.println("test case produced in: " + (endingTime - startingTime) + " milliseconds");
	}
}
