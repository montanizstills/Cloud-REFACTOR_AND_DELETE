package common.enums;

public enum enumCreditCard {
	TYPE("Visa"), NUMBER("4111111111111111"), NAME("Auto Test"), EXPIRY_MONTH("Dec (12)"), EXPIRY_YEAR("2022"),
	CODE("123");

	public String text;

	enumCreditCard(String text) {
		this.text = text;
	}
}
