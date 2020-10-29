package com.ten31f.util.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocReader {

	private List<String> fileData = null;

	public void consume(File file) throws IOException {

		WordExtractor extractor = null;

		FileInputStream fis = new FileInputStream(file.getAbsolutePath());
		HWPFDocument document = new HWPFDocument(fis);
		extractor = new WordExtractor(document);
		String[] fileDataArray = extractor.getParagraphText();

		List<String> fileDataList = Arrays.asList(fileDataArray).stream().map(String::trim)
				.collect(Collectors.toList());

		fileDataList.removeIf(String::isEmpty);

		setFileData(fileDataList);
	}

	public List<String> getFileData() {
		return fileData;
	}

	private void setFileData(List<String> fileData) {
		this.fileData = fileData;
	}

}
