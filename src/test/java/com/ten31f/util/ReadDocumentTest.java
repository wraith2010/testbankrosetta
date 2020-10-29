package com.ten31f.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.junit.Test;

public class ReadDocumentTest {

	@Test
	public void test() {
		
		File file = null;
		WordExtractor extractor = null;
		try {

			URL resource = getClass().getClassLoader().getResource("chapter05.doc");
			file = new File(resource.toURI());
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			HWPFDocument document = new HWPFDocument(fis);
			extractor = new WordExtractor(document);
			String[] fileData = extractor.getParagraphText();
			for (int i = 0; i < fileData.length; i++) {
				if (fileData[i] != null)
					System.out.println(fileData[i]);
			}
		} catch (Exception exep) {
			exep.printStackTrace();
		}
	}

}
