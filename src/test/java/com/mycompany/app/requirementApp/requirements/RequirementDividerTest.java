package com.mycompany.app.requirementApp.requirements;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class RequirementDividerTest {
	private static final String TOO_MANY_EFFECT_KEYWORDS = "the possible requirement contains too many effect keywords inside the text";
	private static final String BAD_START = "the possible requirement starts badly";
	private static final String TOO_MANY_START_KEYWORDS = "the possible requirement contains a start keyword inside the text";
	String[] prerequisiteStart = { "When ", "WHEN ", "when ", "If ", "IF ", "if " };
	String[] effectStart = { " Then ", " THEN ", " then " };

	@Test
	public void basicRequirementTest() throws NoGoodRequirementFormatException {
		RequirementDivider reqDiv = new RequirementDivider(prerequisiteStart, effectStart);
		String requirement = "when something happen then something else happen too";
		PrerequisiteAndEffectString split = reqDiv.getPrerequisiteAndEffectString(requirement);
		String expectedPrereq = "something happen";
		String expectedEffect = "something else happen too";
		assertEquals("the prerequisite is as expected", expectedPrereq, split.getPrerequisite());
		assertEquals("the effect is as expected", expectedEffect, split.getEffect());
	}

	@Test
	public void tooManyEffectKeywordTest() throws NoGoodRequirementFormatException {
		RequirementDivider reqDiv = new RequirementDivider(prerequisiteStart, effectStart);
		String requirement = "when something happen then something else happen THEN too";
		assertThatThrownBy(() -> {
			reqDiv.getPrerequisiteAndEffectString(requirement);
		}).isInstanceOf(NoGoodRequirementFormatException.class).hasMessageContaining(TOO_MANY_EFFECT_KEYWORDS);
	}

	@Test
	public void badStartTest() throws NoGoodRequirementFormatException {
		RequirementDivider reqDiv = new RequirementDivider(prerequisiteStart, effectStart);
		String requirement = " when something happen then something else happen too";
		assertThatThrownBy(() -> {
			reqDiv.getPrerequisiteAndEffectString(requirement);
		}).isInstanceOf(NoGoodRequirementFormatException.class).hasMessageContaining(BAD_START);
	}

	@Test
	public void tooManyStartKeyywordsTest() throws NoGoodRequirementFormatException {
		RequirementDivider reqDiv = new RequirementDivider(prerequisiteStart, effectStart);
		String requirement = "WHEN something happen then something else happen if too";
		assertThatThrownBy(() -> {
			reqDiv.getPrerequisiteAndEffectString(requirement);
		}).isInstanceOf(NoGoodRequirementFormatException.class).hasMessageContaining(TOO_MANY_START_KEYWORDS);
	}

}
