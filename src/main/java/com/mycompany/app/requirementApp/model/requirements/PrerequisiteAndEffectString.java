package com.mycompany.app.requirementApp.model.requirements;

public class PrerequisiteAndEffectString {
	private String prerequisite;
	private String effect;
	public PrerequisiteAndEffectString(String prerequisite, String effect) {
		this.prerequisite = prerequisite;
		this.effect = effect;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public String getEffect() {
		return effect;
	}
}
