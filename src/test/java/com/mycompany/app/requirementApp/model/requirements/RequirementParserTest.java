package com.mycompany.app.requirementApp.model.requirements;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mycompany.app.requirementApp.model.booleanLogic.AndOperator;
import com.mycompany.app.requirementApp.model.booleanLogic.BooleanVariable;
import com.mycompany.app.requirementApp.model.booleanLogic.OrOperator;

public class RequirementParserTest {

	@Test
	public void basicParserTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN x THEN y";
		Requirement expectedReq = new Requirement(new BooleanVariable("x"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
		req = "WHEN the light is on THEN y";
		expectedReq = new Requirement(new BooleanVariable("the light is on"), "y");
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
	public void basicParserWithFirtParenthesesOnlyTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN (x THEN y";
		assertThatThrownBy(() -> {
			reqPars.createRequirement(req);
		}).isInstanceOf(NoGoodRequirementFormatException.class).hasMessageContaining("no good parentheses format");
	}

	@Test
	public void basicParserWithLastParenthesesOnlyTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN x) THEN y";
		assertThatThrownBy(() -> {
			reqPars.createRequirement(req);
		}).isInstanceOf(NoGoodRequirementFormatException.class).hasMessageContaining("no good parentheses format");
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
	public void basicParserWithParenthesesAndBlanksTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN    (  x   )    THEN    y    ";
		Requirement expectedReq = new Requirement(new BooleanVariable("x"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
		req = "WHEN    x       THEN    y    ";
		expectedReq = new Requirement(new BooleanVariable("x"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
		req = "WHEN    ( the light is on )       THEN    y    ";
		expectedReq = new Requirement(new BooleanVariable("the light is on"), "y");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
		req = "WHEN     the light is on       THEN    y    ";
		expectedReq = new Requirement(new BooleanVariable("the light is on"), "y");
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

	@Test
	public void lessBasicBasicParserDoubleParenthesesTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN ((x AND y)) THEN z";
		Requirement expectedReq = new Requirement(new AndOperator(new BooleanVariable("x"), new BooleanVariable("y")),
				"z");
		assertEquals("the requirement created by the parser should be as expected", expectedReq,
				reqPars.createRequirement(req));
	}

	@Test
	public void lessBasicBasicParserNoParenthesesTest() throws NoGoodRequirementFormatException {
		RequirementParser reqPars = new RequirementParser();
		String req = "WHEN x AND y AND z THEN w";
		assertThatThrownBy(() -> {
			reqPars.createRequirement(req);
		}).isInstanceOf(NoGoodRequirementFormatException.class).hasMessageContaining("no good parentheses format");
	}

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
