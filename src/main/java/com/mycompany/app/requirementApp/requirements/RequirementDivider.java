package com.mycompany.app.requirementApp.requirements;

public class RequirementDivider {
	String[] prerequisiteStart;
	String[] effectStart;

	public RequirementDivider(String[] prerequisiteStart, String[] effectStart) {
		this.prerequisiteStart = prerequisiteStart;
		this.effectStart = effectStart;
	}

	public PrerequisiteAndEffectString getPrerequisiteAndEffectString(String requirementString)
			throws NoGoodRequirementFormatException {
		String requirementsWithoutStart = possibleRequirementWithoutRequirementStart(requirementString);

		int effectStartKeyword = 0;
		String trimmedPrerequisite = "";
		String trimmedEffect = "";
		for (int i = 0; i < effectStart.length; i++) {
			if (requirementsWithoutStart.contains(effectStart[i])) {
				effectStartKeyword++;
				int firstKeywordChar = requirementsWithoutStart.indexOf(effectStart[i]);
				trimmedPrerequisite = requirementsWithoutStart.substring(0, firstKeywordChar);
				trimmedEffect = requirementsWithoutStart.substring(firstKeywordChar);
				trimmedEffect = trimmedEffect.replace(effectStart[i], "");
			}
		}
		if (effectStartKeyword == 1) {
			return new PrerequisiteAndEffectString(trimmedPrerequisite, trimmedEffect);
		} else {
			throw new NoGoodRequirementFormatException(
					"the possible requirement contains too many effect keywords inside the text");
		}
	}

	private String possibleRequirementWithoutRequirementStart(String possibleRequirement)
			throws NoGoodRequirementFormatException {
		boolean startGood = false;
		String requirementToTrim = possibleRequirement;
		for (int i = 0; i < prerequisiteStart.length; i++) {
			if (requirementToTrim.startsWith(prerequisiteStart[i])) {
				startGood = true;
				requirementToTrim = requirementToTrim.replaceFirst(prerequisiteStart[i], "");
				break;
			}
		}
		if (startGood == false) {
			throw new NoGoodRequirementFormatException("the possible requirement starts badly");
		}
		for (int i = 0; i < prerequisiteStart.length; i++) {
			if (requirementToTrim.contains(prerequisiteStart[i])) {
				throw new NoGoodRequirementFormatException(
						"the possible requirement contains a start keyword inside the text");
			}
		}
		return requirementToTrim;
	}
}
