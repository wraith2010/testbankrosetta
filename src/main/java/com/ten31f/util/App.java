package com.ten31f.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ten31f.util.domain.Question;
import com.ten31f.util.logic.CSVWriter;
import com.ten31f.util.logic.DocReader;
import com.ten31f.util.logic.Parser;

public class App {

	public static void main(String[] args) {

		String outputFileName = args[0].substring(0, args[0].lastIndexOf(".")) + "csv";

		File file = new File(args[0]);

		try {
			List<String> data = DocReader.consume(file);

			List<Question> questions = Parser.parse(data);

			CSVWriter.write(questions, outputFileName);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
