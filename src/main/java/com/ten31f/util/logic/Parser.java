package com.ten31f.util.logic;

import java.util.ArrayList;
import java.util.List;

import com.ten31f.util.domain.DataType;
import com.ten31f.util.domain.Question;

public class Parser {

	public static List<Question> parse(List<String> data) {

		List<Question> questions = new ArrayList<>();
		Question question = new Question();

		for (String line : data) {
			switch (DataType.identify(line)) {
			case ANSWER:
				question.setAnswer(removeKey(line));
				break;
			case CHOICE:
				question.addChoice(removeKey(line));
				break;
			case QUESTION:
				if (question.getPrompt() != null) {
					questions.add(question);
					question = new Question();
				}
				question.setPrompt(removeKey(line));
				break;
			case SKILL:
				question.setSkill(removeKey(line));
				break;
			case TOPIC:
				question.setTopic(removeKey(line));
				break;
			default:
				break;
			}

		}

		questions.add(question);

		return questions;

	}

	private static String removeKey(String value) {

		if (value.contains(":")) {
			return value.substring(value.indexOf(":") + 1).trim();
		}

		return value;
	}

}
