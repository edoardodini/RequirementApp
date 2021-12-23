package com.mycompany.app.requirementApp;

import java.util.List;

import com.mycompany.app.requirementApp.requirements.NoGoodRequirementFormatException;
import com.mycompany.app.requirementApp.requirements.Requirement;
import com.mycompany.app.requirementApp.requirements.RequirementParser;

public class App {
	public static void main(String[] args) throws NoGoodRequirementFormatException {
		RequirementParser reqParser = new RequirementParser();
		long startingTime = System.currentTimeMillis();
		// 4.4.2 General Requirements
		System.out.println("4.4.2 General Requirements");
		String[] generalRequirements = {
				"When the desk is open then a clear indication of the ERTMS/ETCS mode shall be shown to the driver." };
		for (int j = 0; j < generalRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(generalRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.3 Isolation
		System.out.println();
		System.out.println("4.4.3 Isolation");
		String[] isolationRequirements = {
				"When in Isolation mode then there shall be a clear indication to the driver that the ERTMS/ETCS on-board equipment is isolated." };
		for (int j = 0; j < isolationRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(isolationRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.4 No Power
		System.out.println();
		System.out.println("4.4.4 No Power");
		String[] noPowerRequirements = {
				"When in No Power mode then the ERTMS/ETCS on-board equipment shall permanently command the emergency brake." };
		for (int j = 0; j < noPowerRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(noPowerRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.5 System Failure
		System.out.println();
		System.out.println("4.4.5 System Failure");
		String[] systemFailureRequirements = {
				"When there is a fault which affects safety then the ERTMS/ETCS on-board equipment shall switch to the System Failure mode.",
				"When in System Failure mode then the ERTMS/ETCS on-board equipment shall permanently command the Emergency Brakes." };
		for (int j = 0; j < systemFailureRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(systemFailureRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.6 Sleeping
		System.out.println();
		System.out.println("4.4.6 Sleeping ");
		String[] SleepingRequirements = {
				"When in Sleeping mode AND the engine is remote controlled by the leading engine then its ERTMS/ETCS on-board equipment shall not perform any train movement supervision.",
				"When in Sleeping mode then the ERTMS/ETCS on-board equipment shall perform the Train Position function; in particular, the front/rear end of the engine (i.e., not the train) shall be used to refer to train front/rear end.",
				"When in Sleeping mode then Sleeping mode shall be automatically detected on-board via the train interface.",
				"If in Sleeping mode AND a safety critical fault in a sleeping engine is present then if-possible, the train must not be stopped due to a safety critical fault in a sleeping engine. The ERTMS/ETCS on-board equipment should therefore try to memorise the occurrence of such fault(s), which should be handled \"when\" the engine leaves the Sleeping mode. The ERTMS/ETCS on-board equipment should also try to send an error information to the RBC.",
				"If in Sleeping AND (a desk of the sleeping engine is opened AND the train is running (this is an abnormal operation)) then the ERTMS/ETCS on-board equipment shall switch to Stand-By mode.",
				"If in Sleeping AND (the “sleeping input signal” is lost (no more detection of the remote control) AND the train is at standstill) then the switch to Stand-By mode shall be made.",
				"WHEN (the system is in one of all levels AND the order to contact the RBC is received) XOR ((the system is in level 2 XOR the system is in level 3) AND ((the system is entering Sleeping mode XOR exiting Sleeping mode) OR a safety critical fault of the ERTMS/ETCS on-board equipment occurs)) THEN the ERTMS/ETCS on-board equipment shall open a communication session with the RBC",
				"When in Sleeping AND there is a case of balise group message consistency error (refer to 3.16.2.4.4 and 3.16.2.5.1) then the ERTMS/ETCS onboard equipment shall not command the service brake.",
				"When in Sleeping AND ((the level is 2 XOR the level is 3) AND no compatible version has been established between the on-board equipment in Sleeping mode and the RBC) then the ERTMS/ETCS onboard equipment shall react as specified in 3.5.3.7 d) 2nd bullet but no driver’s indication shall be given."};
		for (int j = 0; j < SleepingRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(SleepingRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.7 Stand By
		System.out.println();
		System.out.println("4.4.7 Stand By");
		String[] standByRequirements = {
				"When in a state of the possible ones then Stand By cannot be selected by the driver (it is a default mode).",
				"When the ERTMS/ETCS on-board equipment awakes then it is in the Stand-By mode",
				"When in Stand By mode then Data for mission are collected (see SRS-chapter 5: “Start of Mission” procedure).",
				"When in Stand-By mode (where the desk of the engine can be open or closed) AND the desk is closed then no interaction with the driver shall be possible, except isolation of the ERTMS/ETCS on-board equipment.",
				"When in Stand By then the ERTMS/ETCS on-board equipment shall perform the Standstill Supervision."};
		for (int j = 0; j < standByRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(standByRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.8 Shunting
		System.out.println();
		System.out.println("4.4.8 Shunting");
		String[] shuntingRequirements = {
				"When in Shunting mode then The ERTMS/ETCS on-board equipment shall supervise the train movements against a ceiling speed: the shunting mode speed limit",
				"When in Shunting mode AND a balise group, not contained in the list, is passed then the train shall be tripped (\"When\" an empty list is sent, no balise group can be passed. \"When\" no list is sent, all balise groups can be passed)",
				"When in Shunting mode AND “stop 'if' in shunting mode” information is received from balise groups then the train is tripped.",
				
				"When in Shunting then no Train Data shall be required",
				
				"When in Shunting mode then the ERTMS/ETCS on-board equipment shall perform the Train Position function",
				
				"When in Shunting mode then the ERTMS/ETCS on-board shall not manage level transitions.",
				"When in Shunting mode AND (an immediate level transition order is received OR a conditional level transition is received) then the ERTMS/ETCS on-board shall store the order or conditional level transition.",
				"When the system move from Shunting AND ((during the Shunting mode an immediate level transition order has been received OR during the Shunting mode a conditional level transition order has been received) AND another mode than Shunting or Passive Shunting has been entered (i.e. 'when' the Shunting movement is terminated)) then the immediate level transition order or the conditional level transition order has be stored and shall be evaluated",
				
				"When in Shunting mode AND a communication session establishment order is received then the ERTMS/ETCS on-board shall not establish the communication session, but shall store the RBC ID/phone number.",
				
				"When in Shunting mode then the ERTMS/ETCS on-board shall not manage RBC-RBC hand-over, except for storing the RBC ID/phone number given at the RBC/RBC border.",
				
				"When (Shunting mode is selected by the driver AND the train is at standstill) XOR Shunting mode is ordered by the trackside then Shunting mode is accepted",
				
				"When in case of selection of Shunting mode by the driver AND in case of level 1 operations then the switch to shunting is always accepted by the on-board equipment",
				"When in case of selection of Shunting mode by the driver AND (in level 2 areas XOR in level 3 areas) then the on-board asks the trackside for an authorisation.",
				"When in case of selection of Shunting mode by the driver AND ((in level 2 areas XOR in level 3 areas) AND (the on-board has asked the trackside for an authorisation AND the auhtorisation has been received)) then the switch to shunting is possible.",
				
				"When there is an order to switch to Shunting mode from trackside AND the system is in level 1 then the order is given by a balise group (a list of balises, that the train is allowed to pass after the entry in Shunting, can be sent together with the order).",
				"When there is an order to switch to Shunting mode from trackside AND (the system is in level 2 XOR the system is in level 3) then the order is sent via radio (a list of balises, that the train is allowed to pass after the entry in Shunting, can be sent together with the order).",
				
				"When the switch to shunting is ordered by trackside then a driver acknowledgement is requested.",
				
				"When in Shunting mode then the ERTMS/ETCS on-board equipment shall display the train speed",
				"When in Shunting mode AND on driver request of showing the permitted speed then the ERTMS/ETCS on-board equipment shall display the train speed and the permitted speed.",
				"When in Shunting mode AND on driver request of stopping the show of the permitted speed then the display of the permitted shall be stopped."};
		for (int j = 0; j < shuntingRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(shuntingRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.9 Full Supervision
		System.out.println();
		System.out.println("4.4.9 Full Supervision");
		String[] fullSupervisionRequirements = {
				"When all train and track data, which is required for a complete supervision of the train, is available on board then the ERTMS/ETCS on-board equipment shall be in the Full Supervision mode.",
				"When all necessary conditions are fulfilled then Full supervision is entered automatically (cannot be selected by the driver).",
				"When in a state of the possible then Full supervision cannot be selected by the driver.",
				"When in Full Supervision mode then SSP and gradient are not required for the whole length of the train, but must be available at least from the FRONT END of the train.",
				"When in Full Supervision mode AND SSP and gradient are known for the whole length of the train then an indication “ENTRY IN FULL SUPERVISION” shall not be clearly displayed to the driver",
				"When in Full Supervision mode AND SSP and gradient are not known for the whole length of the train then an indication “ENTRY IN FULL SUPERVISION” shall be clearly displayed to the driver",
				"When in Full Supervision mode then the ERTMS/ETCS on-board equipment shall supervise train movements against a dynamic speed profile.",
				"When in Full Supervision mode then the ERTMS/ETCS on-board equipment shall display the train speed, the permitted speed, the target distance and the target speed to the driver (this list is not exhaustive – refer to chapter 4.7 “DMI depending on modes”)."};
		for (int j = 0; j < fullSupervisionRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(fullSupervisionRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.10 Unfitted
		System.out.println();
		System.out.println("4.4.10 Unfitted ");
		String[] unfittedRequirements = {
				"When in Unfitted mode then The ERTMS/ETCS on-board equipment shall supervise train movements against a ceiling speed: the lowest of the maximum train speed and the Unfitted mode speed limit for unfitted area (national value).",
				"When in Unfitted mode then the ERTMS/ETCS on-board equipment shall also supervise temporary speed restrictions.",
				"When in Unfitted mode then the ERTMS/ETCS on-board equipment shall display the train speed to the driver."};
		for (int j = 0; j < unfittedRequirements .length; j++) {
			Requirement req = reqParser.createRequirement(unfittedRequirements [j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.11 Staff Responsible
		System.out.println();
		System.out.println("4.4.11 Staff Responsible");
		String[] staffResponsibleRequirements = {
				"When in Staff Responsible mode then the ERTMS/ETCS on-board equipment shall supervise train movements against a ceiling speed: the staff responsible mode speed limit",
				"When in Staff Responsible mode then the ERTMS/ETCS on-board equipment shall supervise train movements against a given distance (regarding its origin location see 4.4.11.1.3.1). The ERTMS/ETCS on-board equipment shall supervise braking curves with a target speed of zero to the end of this distance.",
				"When in Staff Responsible mode AND the train overpasses a given distance then the ERTMS/ETCS on-board equipment shall trip the train",
				"When in Staff Responsible mode AND a list of expected balise groups has been sent by the RBC then the ERTMS/ETCS on-board equipment shall supervise train movements against a list of expected balise groups (the list that has been sent by the RBC)",
				"When in Staff Responsible mode AND (a list of expected balise groups has been sent by the RBC AND the train is over-passing a balise group that is not in the list ('When' an empty list is sent, no balise group can be passed)) then the train shall be tripped.",
				"When in Staff Responsible mode AND a list of expected balise groups has not been sent by the RBC then all balise groups can be passed",
				"When in Staff Responsible mode AND the train moves against balise groups giving the order ‘stop 'if' in SR’ then this order shall immediately trip the train",
				"When in Staff Responsible mode AND (the train moves against balise groups giving the order ‘stop 'if' in SR’ AND the over-passed balise group is included in a list of expected balises) then this order shall not immediately trip the train",
				"When in Staff Responsible mode then the ERTMS/ETCS on-board equipment shall supervise train movements against running in the direction opposite to the train orientation (reverse movement protection)",
				"When in Staff Responsible mode AND the National/Default value determines the max permitted distance to run in SR mode then the starting point of this distance shall refer to the estimated position of the train front 'when' SR mode was entered, or, already in Staff Responsible mode, 'when' Override was activated.",
				"When in Staff Responsible mode AND (the max permitted distance to run in SR mode is determined by the value transmitted by the RBC XOR the max permitted distance to run in SR mode is entered by the driver) then the start location of the distance shall refer to the estimated position of the train front 'when' the distance information is received or entered.",
				"When in Staff Responsible mode AND the max permitted distance to run in SR mode is determined by the value transmitted by EUROLOOP then the distance information transmitted by EUROLOOP shall be referred to one or more reference balise groups. On-board shall evaluate the distance to run in SR mode by matching the reference balise groups given with the LRBG. In case the LRBG is, due to a change of orientation, in front of the train 'when' the distance to run in SR mode is to be determined from the EUROLOOP information, the complete distance to run in SR mode shall be determined as the distance given by EUROLOOP plus the distance between the estimated train front end and the LRBG.",
				"When in Staff Responsible mode AND the train is at standstill then the ERTMS/ETCS on-board equipment shall give the possibility to the driver to modify the value of the SR mode speed limit and of the given distance.",
				
				"If the mode is Staff Responsible AND (a train movement is detected AND the driver is entering the SR speed/distance limits) then the ERTMS/ETCS on-board equipment shall trigger the brake command.",
				
				"If the mode is Staff Responsible AND ((the level is 2 XOR the level is 3) AND a communication session is open) then the driver shall have the possibility to request a new distance to run in Staff Responsible, by selecting \"Start\". This triggers an MA request.",
				"When entering SR mode then the value applicable for SR mode speed limit and the value applicable for SR distance shall be the corresponding National/Default values. Exception for SR distance: SR mode is authorised by RBC giving an SR distance.",
				"When in SR mode then the value applicable for the SR mode speed limit shall be, if-available, the last value entered by the driver.",
				"When in SR mode AND the last SR distance value received by the ERTMS/ETCS on-board equipment is entered by the driver then the value applicable for the SR distance shall be the distance to run in SR entered by the driver",
				"When in SR mode AND the last SR distance value received by the ERTMS/ETCS on-board equipment is given by the trackside then the value applicable for the SR distance shall be the distance to run in SR given by trackside.",
				"When in SR mode AND \"Override\" is selected then the SR mode speed limit value and the SR distance value previously entered by driver or given by trackside, if-any, shall be deleted. The corresponding National/Default values shall enter in force.",
				"If the train is in SR AND the train receives a new distance to run in SR mode from the RBC then the stored list of expected balise groups, if-any, shall be deleted or shall be replaced by the list of expected balise groups sent together with the distance to run in SR.",
				"If an ERTMS/ETCS on-board equipment in SR mode AND (it has previously received from EUROLOOP max permitted distance to run in SR mode information AND detects the main signal balise group being part of this information) then it shall ignore any new max permitted distance to run in SR mode information from that loop.",
				"When in SR mode AND (Override is active AND the driver requested to show permitted speed, target distance and the target speed) then the ERTMS/ETCS on-board equipment shall display the train speed and the override (permission to pass a signal at danger, trip inhibited).",
				"When in SR mode AND the driver requested to show permitted speed, target distance and the target speed then the ERTMS/ETCS on-board equipment shall display the train speed.",
				"When in SR mode AND ((Override is active XOR Override is not active) AND the driver requested to stop to display the permitted speed, target distance and the target speed) then the ERTMS/ETCS on-board equipment shall stop to show the train speed and the override (permission to pass a signal at danger, trip inhibited), 'if' selected.",
				"If the train is in SR mode AND it is receiving a \"track ahead free\" request from the RBC then the ERTMS/ETCS on-board equipment requests the driver to enter the \"track ahead free\" information."};
		for (int j = 0; j < staffResponsibleRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(staffResponsibleRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.12 On Sight
		System.out.println();
		System.out.println("4.4.12 On Sight");
		String[] onSightRequirements = {
				"When the On Sight mode is commanded by trackside AND all necessary conditions are fulfilled then On Sight mode shall be entered automatically.",
				"When in On Sight mode then the ERTMS/ETCS on-board equipment shall supervise train movements against a dynamic speed profile.",
				
				"When in On Sight mode then the ERTMS/ETCS on-board equipment shall display the train speed to the driver.",
				"When in On Sight mode AND the driver requested to show the permitted speed, target distance, target speed and release speed then the permitted speed, target distance, target speed and release speed (if-any) shall be displayed (this list is not exhaustive – refer to chapter 4.7 “DMI depending on modes”).",
				"When in On Sight mode AND the driver requested to not display the permitted speed, target distance, target speed and release speed then the permitted speed, target distance, target speed and release speed (if-any) shall not be displayed (this list is not exhaustive – refer to chapter 4.7 “DMI depending on modes”).",
				
				"If receiving a \"track ahead free\" request from the RBC then the ERTMS/ETCS on-board equipment requests the driver to enter the \"track ahead free\" information.",
				"When in On Sight mode then SSP and gradient are not required for the whole length of the train, but must be available at least from the FRONT END of the train.",
				"When in On Sight mode AND SSP and gradient are not known for the whole length of the train then an indication “ENTRY IN ON SIGHT” shall be clearly displayed to the driver until SSP and gradient are known for the whole length of the train."};
		for (int j = 0; j < onSightRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(onSightRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.13 Trip
		System.out.println();
		System.out.println("4.4.13 Trip");
		String[] tripRequirements = {
				"When in Trip mode then the ERTMS/ETCS on-board equipment shall command the emergency brakes (no brake release is possible in Trip mode).",
				"When in Trip mode then the ERTMS/ETCS on-board equipment shall indicate to the driver the reason of the train trip.",
				"When in Trip mode AND the train is at standstill then the ERTMS/ETCS on-board equipment shall request an acknowledgement from the driver (to allow the driver to acknowledge the train trip).",
				"When in Trip mode AND the desk is being closed then there is not a mode change but no interaction with the driver shall be possible as long as the desk is closed"};
		for (int j = 0; j < tripRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(tripRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.14 Post Trip
		System.out.println();
		System.out.println("4.4.14 Post Trip");
		String[] postTripRequirements = {
				"When in Trip mode AND the driver acknowledges the trip then the mode shall be Post Trip.",
				"When in post trip mode then the onboard equipment shall release the Command of the emergency brake.",
				"When in Post Trip mode then the ERTMS/ETCS on-board equipment shall keep on indicating to the driver the reason of the train trip.",
				"When in Post Trip mode then the train shall only be authorised to move backwards a given distance (national value). The ERTMS/ETCS on-board equipment shall supervise this national distance for reverse movements.",
				"When in Post Trip mode AND the given distance (national value) is over-passed then the train shall command the service brakes. The driver shall be informed about the reason for the brake application.",
				"When in Post Trip mode AND after the release of a brake command initiated due to an overpassed distance allowed for moving backwards in Post Trip mode then the ERTMS/ETCS on-board equipment shall command the service brake for any further movement in the direction opposite to the train orientation.",
				"When moving backwards AND in Post Trip mode then the train trip shall be inhibited.",
				"When in Post Trip mode AND (ERTMS/ETCS level is 1 AND the driver selects “Start”) then the onboard equipment proposes Staff Responsible.",
				"When in Post Trip mode AND (ERTMS/ETCS level is 2 XOR ERTMS/ETCS level is 3) then there is an MA Request to the RBC. It is the RBC responsibility to give an SR authorisation, or a Full Supervision MA or an On Sight/Shunting MA to an ERTMS/ETCS equipment that is in Post Trip mode.",
				"When in Post Trip mode AND in presence of balise group message consistency error (refer to 3.16.2.4.4 and 3.16.2.5.1) then the ERTMS/ETCS onboard equipment shall not command the service brake."};
		for (int j = 0; j < postTripRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(postTripRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.15 Non Leading
		System.out.println();
		System.out.println("4.4.15 Non Leading");
		String[] nonLeadingRequirements = {
				"When a “non leading input signal\" from the train interface is used then the ERTMS/ETCS on-board equipment shall use the “non leading input signal\" as a necessary condition to enter in Non-Leading mode.",
				"If in Non-Leading mode AND (the “non leading input signal\" is no longer present AND the train is at standstill) then the switch to Stand-By mode shall be made.",
				"If in Non-Leading mode AND (the “non leading input signal\" is no longer present AND the train is not at standstill) then the switch to Stand-By mode shall not be made.",
				"When in Non-Leading mode then the ERTMS/ETCS on-board equipment shall not perform any train movement supervision.",
				"When in Non-Leading mode then the ERTMS/ETCS on-board equipment shall perform the Train Position function; in particular, the front/rear end of the engine (i.e., not the train) shall be used to refer to train front/rear end.",
				"When in Non-Leading mode AND (level is 2 XOR level is 3) then the ERTMS/ETCS on-board equipment shall report its position to the RBC, according to the previously received parameters.",
				"When in Non-Leading mode AND there is a safety critical fault in a non-leading engine then if-possible the train must not be stopped due to a safety critical fault in a non-leading engine. The ERTMS/ETCS on-board equipment should therefore try to memorise the occurrence of such fault(s), which should be handled 'when' the engine leaves Non Leading mode. The ERTMS/ETCS on-board equipment should also try to send an error information to the RBC.",
				"When in Non-Leading mode then the ERTMS/ETCS on-board equipment shall display the train speed to the driver.",
				"When in Non-Leading mode then the supervision of linking consistency shall not be performed.",
				"When in Non-Leading mode AND In case of balise group message consistency error (refer to 3.16.2.4.4 and 3.16.2.5.1) then the ERTMS/ETCS onboard equipment shall not command the service brake."};
		for (int j = 0; j < nonLeadingRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(nonLeadingRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.17 National System
		System.out.println();
		System.out.println("4.4.17 National System");
		String[] nationalSystemRequirements = {
				"When In SN mode then according to the specific on-board implementation the National System may access the following resources via the ERTMS/ETCS on-board equipment: DMI, Juridical Recording interface, odometer, train interface and brakes. This can be achieved through the STM interface." };
		for (int j = 0; j < nationalSystemRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(nationalSystemRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.18 Reversing
		System.out.println();
		System.out.println("4.4.18 Reversing");
		String[] reversingRequirements = {
				"When in Reversing mode then the ERTMS/ETCS on-board equipment shall supervise train movements against a ceiling speed, the Reversing mode speed limit given from trackside, and a distance to run in the direction opposite to the train orientation, given from trackside.",
				"When in Reversing mode AND the distance to run in the direction opposite to the train orientation (given from trackside) then the emergency brake shall be commanded.",
				"When in Reversing mode AND (after the release of a brake command initiated due to an overpassed reversing distance AND while the reversing distance is still overpassed) then the ERTMS/ETCS on-board equipment shall command the emergency brake for any further movement in the direction opposite to the train orientation.",
				"When in Reversing mode then the ERTMS/ETCS on-board equipment shall display the train speed, the permitted speed and the remaining distance to run.",
				"When in Reversing mode AND the SBI supervision limit is exceeded (refer to chapter 3 table 5, triggering condition t4) then the ERTMS/ETCS on-board equipment shall command the emergency brake instead of the service brake. For the revocation of the brake command, refer to 3.13.10.2.4.",
				"When in Reversing mode then the position reports sent shall refer to the location of the driving cab (as before reversing).",
				"When in Reversing mode AND in presence of a balise group message consistency error (refer to 3.16.2.4.4 and 3.16.2.5.1) then the ERTMS/ETCS onboard equipment shall not command the service brake.",
				"When in Reversing mode AND there is an alarm reporting a malfunction for the onboard balise transmission function then the ERTMS/ETCS onboard equipment shall ignore this alarm.",
				"When in Reversing mode AND the ERTMS/ETCS system version number X transmitted by any balise is greater than the highest version X supported by the onboard equipment (refer to 3.17.3.5) then the information from this balise shall be ignored, the train shall not be tripped and the driver shall not be informed."};
		for (int j = 0; j < reversingRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(reversingRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.19 Limited Supervision
		System.out.println();
		System.out.println("4.4.19 Limited Supervision");
		String[] limitedSupervisionRequirements = {
				"When Limited supervision is commanded by trackside AND all necessary conditions are fulfilled then Limited Supervision shall be entered automatically.",
				"When in Limited Supervison then the ERTMS/ETCS on-board equipment shall supervise train movements against a dynamic speed profile.",
				"When in Limited Supervision then the ERTMS/ETCS on-board equipment shall display the train speed and the release speed, if-any (this list is not exhaustive – refer to chapter 4.7 “DMI depending on modes”).",
				"When in Limited Supervision AND (upon request by trackside (refer to clauses 4.4.19.1.4.2 to 4.4.19.1.4.6) AND (the generic LS function marker is stored on-board OR there are the conditions in clause 4.4.19.1.4.7 are fulfilled)) then the ERTMS/ETCS on-board equipment shall also display the lowest speed amongst the lowest MRSP element between the minimum safe front end of the train and the EOA/LOA and the target speed at the EOA/LOA.",
				"When in Limited Supervision AND upon an order to toggle on the LSSMA display then the ERTMS/ETCS on-board equipment shall start a delay timer for order received from RBC/RIU, at the value of the time stamp of the message including the order, for order received from balise group, at the time of passage over the first encountered balise of the balise group giving the order, and, as an exception, for order that has been stored in the level transition buffer (see section 4.8.3), at the time the level transition is performed.",
				"When in Limited Supervision AND the delay timer value becomes greater than the time-out value given by trackside then the ERTMS/ETCS on-board equipment shall display the LSSMA.",
				"When in Limited Supervision AND (it is received an order to toggle on the LSSMA display XOR it is received an order to toggle off the LSSMA display) then a toggle on order which has not been executed yet (because the on-board delay timer has not reached the delay time-out value) shall be deleted by the on-board equipment.",
				"If in Limited Supervision AND (the LSSMA display is already toggled on AND (the clause 4.4.19.1.4.3 is not immediately fulfilled upon reception of a new order to toggle on the LSSMA display OR an order to toggle off the LSSMA display is received)) then the ERTMS/ETCS on-board equipment shall toggle off the LSSMA display.",
				"When entering the Limited Supervision mode AND a toggle on order is not received then the LSSMA display shall be toggled off by the ERTMS/ETCS on-board equipment",
				"When entering the Limited Supervision mode AND a toggle on order is received then the order leads immediately to the display of the LSSMA as per clauses 4.4.19.1.4.2 and 4.4.19.1.4.3.",
				"When in Limited Supervisione AND (the generic LS function marker is not stored on-board AND (the target speed at the EOA/LOA is lower than the Limited Supervision mode speed limit AND the LSSMA is lower than the maximum train speed)) then the clauses 4.4.19.1.4.2 to 4.4.19.1.4.6 shall not apply and the LSSMA shall be displayed.",
				"When in Limited Supervision AND Limited Supervision mode profile has been received without the generic LS function marker in the same balise group message then the generic LS function marker shall be deleted by the ERTMS/ETCS on-board equipment.",
				"If in Limited Supervision mode AND receiving a \"track ahead free\" request from the RBC then the ERTMS/ETCS on-board equipment requests the driver to enter the \"track ahead free\" information.",
				"When in Limited Supervision mode then SSP and gradient are not required for the whole length of the train, but shall be at least available from the FRONT END of the train."};
		for (int j = 0; j < limitedSupervisionRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(limitedSupervisionRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}
		// 4.4.20 Passive Shunting
		System.out.println();
		System.out.println("4.4.20 Passive Shunting");
		String[] passiveShuntingRequirements = {
				"When in Passive Shunting mode then no information shall be shown on the desk (that must be closed) since there is no driver.",
				"When in Passive Shunting AND the engine is coupled to a leading engine then its ERTMS/ETCS on-board equipment shall not perform any train movement supervision.",
				"When in Passive Shunting mode then The ERTMS/ETCS on-board equipment shall perform Train Position function; in particular, the front/rear end of the engine (i.e., not the train) shall be used to refer to train front/rear end.",
				"When not in Shunting mode then it is not possible to enter Passive Shunting mode.",
				"When in Shunting mode then it is possible to enter Passive Shunting mode.",
				"When in Shunting mode then the driver shall have the possibility to enable the function “Continue Shunting on desk closure”.",
				"When the active desk is closed AND (the function “Continue Shunting on desk closure” is active AND the “passive shunting input signal” is received from the train interface) then the ERTMS/ETCS on-board equipment shall switch to Passive Shunting mode.",
				"When the active desk is closed AND (the function “Continue Shunting on desk closure” is not active XOR the “passive shunting input signal” is not present) then the ERTMS/ETCS on-board equipment shall switch to Stand-By mode.",
				
				"When the Shunting mode is left then the special function shall be inactive.",
				"When in Shunting mode then the special function “Continue Shunting on desk closure” shall allow one and only one transition from Shunting mode to Passive Shunting mode.",
				
				"When in Passive Shunting mode AND (a desk of the Passive Shunting engine is opened AND no “Stop Shunting on desk opening” information previously received from balise group is stored onboard) then the ERTMS/ETCS on-board equipment shall switch to Shunting mode.",
				"When in Passive Shunting mode AND a desk of the Passive Shunting engine is opened and “Stop Shunting on desk opening” information previously received from balise group is stored onboard then the ERTMS/ETCS on-board equipment shall switch to Stand By mode.",
				"When in passive Shunting mode AND a safety critical fault in a Passive Shunting engine is present then if-possible the train must not be stopped due to a safety critical fault in a Passive Shunting engine. The ERTMS/ETCS on-board equipment should therefore try to memorise the occurrence of such fault(s), which should be handled 'when' the engine leaves the Passive Shunting mode.",
				"When in Passive Shunting mode then the ERTMS/ETCS on-board shall not manage level transitions.",
				"When in Passive Shunting mode AND (an immediate level transition order is received OR a conditional level transition is received) then the ERTMS/ETCS on-board shall store the order or conditional level transition.",
				"When the system move from Passive Shunting AND ((during the Passive Shunting mode an immediate level transition order has been received OR during the Passive Shunting mode a conditional level transition order has been received) AND another mode than Shunting or Passive Shunting has been entered (i.e. 'when' the Shunting movement is terminated)) then the immediate level transition order or the conditional level transition order has be stored and shall be evaluated",
				"When in Passive Shunting mode AND a communication session establishment order is received then the ERTMS/ETCS on-board in Passive Shunting mode shall not establish the communication session, but shall store the RBC ID/phone number information.",
				"When in Passive Shunting mode then the ERTMS/ETCS on-board shall not manage RBC-RBC hand-over, except for storing the RBC ID/phone number information given at the RBC/RBC border.",
				"When in Passive Shunting mode AND In case of balise group message consistency error (refer to 3.16.2.4.4 and 3.16.2.5.1) then the ERTMS/ETCS onboard equipment shall not command the service brake."};
		for (int j = 0; j < passiveShuntingRequirements.length; j++) {
			Requirement req = reqParser.createRequirement(passiveShuntingRequirements[j]);
			List<String> testCase = req.requirementTestCase();
			System.out.println(req.requirementText());
			for (int i = 0; i < testCase.size(); i++) {
				System.out.println("test case n." + i + ": " + testCase.get(i));
			}
			System.out.println("");
		}

		// 4.4.6.1.10, 4.4.20.1.6, 4.8.5.4, 4.8.1.5, 4.8.1.6, 4.4.20.1.8
//		String[] requirements = {
//				"WHEN (the system is in one of all levels AND the order to contact the RBC is received) XOR (the system is in level 2/3 AND ((the system is entering Sleeping mode XOR exiting Sleeping mode) OR a safety critical fault of the ERTMS/ETCS on-board equipment occurs)) THEN the ERTMS/ETCS on-board equipment shall open a communication session with the RBC",
//				"When active desk is closed AND (the function “Continue Shunting on desk closure” is active AND the “passive shunting input signal” is received from the train interface) THEN the ERTMS/ETCS on-board equipment shall switch to Passive Shunting mode",
//				"When active desk is closed AND (the function “Continue Shunting on desk closure” is not active XOR (the function “Continue Shunting on desk closure” is active AND the “passive shunting input signal” is not present)) THEN the ERTMS/ETCS on-board equipment shall switch to Stand-By mode instead.",
//				"WHEN (the level transition order is deleted XOR the level transition order is overwritten by another level transition order for a different level) XOR ((the RBC transition order is deleted XOR the RBC transition order is overwritten by an order to switch to another Accepting RBC) XOR the communication session with the RBC that provided the stored information is terminated) THEN the sets of information stored in the transition buffer shall be deleted",
//				"If a message contains infill information then this latter (message) shall be evaluated considering all other non-infill information in that message.",
//				"When evaluating trackside information received by radio XOR re-evaluating a set of information released from the transition buffer then linking information, if-any, shall be evaluated prior to any other location related information.",
//				"If a desk of the Passive Shunting engine is opened AND no “Stop Shunting on desk opening” information previously received from balise group is stored onboard then the ERTMS/ETCS on-board equipment shall switch to Shunting mode."
//				};
//		for (int j = 0; j < requirements.length; j++) {
//			Requirement req = reqParser.createRequirement(requirements[j]);
//			List<String> testCase = req.requirementTestCase();
//			System.out.println(req.requirementText());
//			for (int i = 0; i < testCase.size(); i++) {
//				System.out.println("test case n." + i + ": " + testCase.get(i));
//			}
//			System.out.println("");
//		}
		long endingTime = System.currentTimeMillis();
		System.out.println("test case produced in: " + (endingTime - startingTime) + " milliseconds");
	}
}
