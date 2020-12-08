package com.ten31f.util.domain;

public enum DataType {

	QUESTION(null), TOPIC("Topic:"), ANSWER("Answer:"), SKILL("Skill:"), CHOICE(null), UNKOWN(null);

	private String key = null;

	private DataType(String key) {
		setKey(key);
	}

	public static DataType identify(String line) {

		String interestingPart = line.substring(0, line.length() / 4);

		DataType result = decided(interestingPart);

		System.out.println(String.format("%-10s: [%s] \"%s\"", result, interestingPart, line));
		return result;

	}

	private static DataType decided(String interestingPart) {

		for (DataType dataType : DataType.values()) {
			if (dataType.getKey() != null && interestingPart.startsWith(dataType.getKey())) {
				return dataType;
			}
		}

		if (interestingPart.contains(")")) {

			String number = interestingPart.substring(0, interestingPart.indexOf(")"));

			try {
				Integer.parseInt(number);
			} catch (NumberFormatException numberFormatException) {

				if (interestingPart.indexOf(")") == 1) {

					return CHOICE;
				} else {

					return UNKOWN;
				}
			}

			return QUESTION;
		}

		return UNKOWN;
	}

	private void setKey(String key) {
		this.key = key;
	}

	private String getKey() {
		return key;
	}

}
