package common.enums;

public enum enumCountry {
	CAN("CA", "CAN", "Canada"), 
	USA("US", "USA", "United States"),
	INDIA("INDIA","INDIA","India"),
	FIN("FI","FIN","Finland"),
    FRA("FR","FRA","France"),
    SIN("SI","SIN","Singapore");
	
	public String iso2code;
	public String iso3code;
	public String fullName;
	
	

	enumCountry(String iso2code, String iso3code, String fullName) {
		this.iso2code = iso2code;
		this.iso3code = iso3code;
		this.fullName = fullName;
	}
}
