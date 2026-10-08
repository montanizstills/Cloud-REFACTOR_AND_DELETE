package common.enums;

public enum enumProvince {
	BC("British Columbia", "BC"), ON("Ontario", "ON"), QC("Quebec", "QC");

	public String fullName;
	public String abbreviation;

	enumProvince(String fullName, String abbreviation) {
		this.fullName = fullName;
		this.abbreviation = abbreviation;
	}
}
