package com.mycompany.app.requirementApp;

import java.util.List;

import com.mycompany.app.requirementApp.requirements.NoGoodRequirementFormatException;
import com.mycompany.app.requirementApp.requirements.Requirement;
import com.mycompany.app.requirementApp.requirements.RequirementParser;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws NoGoodRequirementFormatException {
		RequirementParser reqParser = new RequirementParser();
		// 4.4.6.1.10, 4.4.20.1.6, 4.8.5.4, 4.8.1.5, 4.8.1.6, 4.4.20.1.8
		long startingTime = System.currentTimeMillis();
		String[] requirements = {
				"WHEN (the system is in one of all levels AND the order to contact the RBC is received) XOR (the system is in level 2/3 AND ((the system is entering Sleeping mode XOR exiting Sleeping mode) OR a safety critical fault of the ERTMS/ETCS on-board equipment occurs)) THEN the ERTMS/ETCS on-board equipment shall open a communication session with the RBC",
				"When active desk is closed AND (the function “Continue Shunting on desk closure” is active AND the “passive shunting input signal” is received from the train interface) THEN the ERTMS/ETCS on-board equipment shall switch to Passive Shunting mode",
				"When active desk is closed AND (the function “Continue Shunting on desk closure” is not active XOR (the function “Continue Shunting on desk closure” is active AND the “passive shunting input signal” is not present)) THEN the ERTMS/ETCS on-board equipment shall switch to Stand-By mode instead.",
				"WHEN (the level transition order is deleted XOR the level transition order is overwritten by another level transition order for a different level) XOR ((the RBC transition order is deleted XOR the RBC transition order is overwritten by an order to switch to another Accepting RBC) XOR the communication session with the RBC that provided the stored information is terminated) THEN the sets of information stored in the transition buffer shall be deleted",
				"If a message contains infill information then this latter (message) shall be evaluated considering all other non-infill information in that message.",
				"When evaluating trackside information received by radio XOR re-evaluating a set of information released from the transition buffer then linking information, if-any, shall be evaluated prior to any other location related information.",
				"If a desk of the Passive Shunting engine is opened AND no “Stop Shunting on desk opening” information previously received from balise group is stored onboard then the ERTMS/ETCS on-board equipment shall switch to Shunting mode."
				};
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
		System.out.println("test case produced in: "+(endingTime-startingTime)+" milliseconds");
	}
}
