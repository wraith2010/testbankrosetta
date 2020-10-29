package com.ten31f.util.domain;

public enum DataType {

	QUESTION(null), TOPIC("Topic:"), ANSWER("Answer:"), SKILL("Skill:"), CHOICE(null), UNKOWN(null);

	private String key = null;

	private DataType(String key) {
		setKey(key);
	}

	public static DataType identify(String line) {

		for (DataType dataType : DataType.values()) {
			if (dataType.getKey() != null && line.startsWith(dataType.getKey()))
				return dataType;
		}

		if (line.contains(")")) {

			String number = line.substring(0, line.indexOf(")"));

			try {
				Integer.parseInt(number);
			} catch (NumberFormatException numberFormatException) {

				if (line.indexOf(")") == 1)
					return CHOICE;
				else
					return UNKOWN;
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
