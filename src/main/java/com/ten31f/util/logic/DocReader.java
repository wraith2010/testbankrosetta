package com.ten31f.util.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocReader {

	public static List<String> consume(File file) throws IOException {

		List<String> fileDataList = getStringData(file).stream().map(String::trim).collect(Collectors.toList());

		fileDataList.removeIf(String::isEmpty);

		return fileDataList;
	}

	private static List<String> getStringData(File file) throws IOException {

		FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());

		if (file.getName().endsWith(".docx")) {

			List<String> data = new ArrayList<>();
			XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);

			for (XWPFParagraph xwpfParagraph : xwpfDocument.getParagraphs()) {

				if (xwpfParagraph.getNumLevelText() != null) {
					data.add(xwpfParagraph.getNumLevelText() + " " + xwpfParagraph.getText());
				} else {
					data.add(xwpfParagraph.getText());
				}

			}

			xwpfDocument.close();

			return data;
		}

		HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);
		WordExtractor wordExtractor = new WordExtractor(hwpfDocument);
		return Arrays.asList(wordExtractor.getText().split("\n"));
	}
}
