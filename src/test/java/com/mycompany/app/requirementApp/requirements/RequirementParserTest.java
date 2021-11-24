package com.mycompany.app.requirementApp.requirements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mycompany.app.requirementApp.booleanLogic.AndOperator;
import com.mycompany.app.requirementApp.booleanLogic.BooleanVariable;
import com.mycompany.app.requirementApp.booleanLogic.OrOperator;

public class RequirementParserTest {

	@Test
	public void basicParserTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN x THEN y";
		Requirement expectedReq = new Requirement(new BooleanVariable("x"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
	}

	@Test
	public void basicParserWithParenthesesTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN (x) THEN y";
		Requirement expectedReq = new Requirement(new BooleanVariable("x"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
	}
	
	@Test
	public void basicParserWithDoubleParenthesesTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN ((x)) THEN y";
		Requirement expectedReq = new Requirement(new BooleanVariable("x"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
	}

	@Test
	public void basicParserWithParenthesesAndBlaksTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN    (  x   )    THEN    y    ";
		Requirement expectedReq = new Requirement(new BooleanVariable("x"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
	}

	@Test
	public void lessBasicBasicParserTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN (x AND y) THEN z";
		Requirement expectedReq = new Requirement(new AndOperator(new BooleanVariable("x"), new BooleanVariable("y")),
				"z");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
	}
	
//	@Test
//	public void lessBasicBasicParserDoubleParenthesesTest() throws NoGoodRequirementFormatException {
//		RequirementParser reqPars = new RequirementParser();
//		String req = "WHEN ((x AND y)) THEN z";
//		Requirement expectedReq = new Requirement(new AndOperator(new BooleanVariable("x"), new BooleanVariable("y")),
//				"z");
//		assertEquals("the requirement created by the parser should be as expected", expectedReq,
//				reqPars.createRequirement(req));
//	}

	@Test
	public void ParserTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN  x   AND (  y  OR  z ) THEN   k   ";
		Requirement expectedReq = new Requirement(new AndOperator(new BooleanVariable("x"),
				new OrOperator(new BooleanVariable("y"), new BooleanVariable("z"))), "k");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
	}
}
