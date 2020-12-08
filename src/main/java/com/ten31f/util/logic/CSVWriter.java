package com.ten31f.util.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;

import com.ten31f.util.domain.Question;

public class CSVWriter {

	public static void write(List<Question> questions, String fileName) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

		for (Question question : questions) {
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(StringEscapeUtils.escapeCsv(question.getPrompt()));
			stringBuilder.append(",");

			List<String> keys = question.getChoices().keySet().stream().collect(Collectors.toList());

			Collections.sort(keys);

			for (String key : keys) {
				stringBuilder.append(StringEscapeUtils.escapeCsv(question.getChoices().get(key)));
				stringBuilder.append(",");
			}

			stringBuilder.append(StringEscapeUtils.escapeCsv(question.getAnswer()));
			stringBuilder.append(",");
			stringBuilder.append(StringEscapeUtils.escapeCsv(question.getTopic()));
			stringBuilder.append(",");
			stringBuilder.append(StringEscapeUtils.escapeCsv(question.getSkill()));
			stringBuilder.append(System.lineSeparator());

			writer.write(stringBuilder.toString());
		}

		writer.close();
	}

}
