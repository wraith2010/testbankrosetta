package com.ten31f.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ten31f.util.domain.DataType;
import com.ten31f.util.domain.Question;
import com.ten31f.util.logic.CSVWriter;
import com.ten31f.util.logic.DocReader;

public class App {

	public static void main(String[] args) {

		List<Question> questions = new ArrayList<>();
		Question question = new Question();

		DocReader docReader = new DocReader();

		File file = new File(args[0]);

		try {
			docReader.consume(file);

			for (String line : docReader.getFileData()) {
				switch (DataType.identify(line)) {
				case ANSWER:
					question.setAnswer(removeKey(line));
					break;
				case CHOICE:
					question.addChoice(removeKey(line));
					break;
				case QUESTION:
					question.setPrompt(removeKey(line));
					break;
				case SKILL:
					question.setSkill(removeKey(line));
					questions.add(question);
					question = new Question();
					break;
				case TOPIC:
					question.setTopic(removeKey(line));
					break;
				default:
					System.out.println(String.format("%s\t[%s]", DataType.identify(line), line));
					break;
				}

			}

			CSVWriter.write(questions, args[0] + ".csv");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String removeKey(String value) {
		if (value.contains(":")) {
			return value.substring(value.indexOf(":") + 1).trim();
		}

		return value;
	}

}
