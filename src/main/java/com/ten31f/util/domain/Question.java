package com.ten31f.util.domain;

import java.util.HashMap;
import java.util.Map;

public class Question {

	private String prompt = null;
	private String answer = null;
	private String topic = null;
	private String skill = null;

	private Map<String, String> choices = null;

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {

		if (prompt.contains(")")) {
			System.out.println("prompt: " + prompt);
			prompt = prompt.substring(prompt.indexOf(")") + 2, prompt.length());
		}

		this.prompt = prompt;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public void addChoice(String value) {
		if (getChoices() == null)
			setChoices(new HashMap<>());

		String key = value.substring(0, value.indexOf(")"));
		String answer = value.substring(value.indexOf(")") + 1);

		getChoices().put(key, answer);

	}

	public Map<String, String> getChoices() {
		return choices;
	}

	public void setChoices(Map<String, String> choices) {
		this.choices = choices;
	}

}
