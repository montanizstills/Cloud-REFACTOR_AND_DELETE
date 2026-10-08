package common.enums;

public enum enumAddress {

	Anaheim("1816 E Morava Ave", null, "1816 E Morava Ave", null, "Anaheim", enumState.CA, null, "92805", "92805-5433",
			enumCountry.USA),

	Bothell("20225 Bothell Everett Hwy", null, "20225 Bothell Everett Hwy", null, "Bothell", enumState.WA, null,
			"98012", "98012-8170", enumCountry.USA),

	Berwyn("1041 Old Cassatt Rd", null, null, null, "Berwyn", enumState.PA, null, "19312", "19312-1152",
			enumCountry.USA),

	Birmingham("2473 Hackworth Rd", "Suite 222", null, null, "Birmingham", enumState.AL, null, "35214", null,
			enumCountry.USA),

	Durham("4005 Durham-Chapel Hill Blvd", null, null, null, "Durham", enumState.NC, null, "27707", null,
			enumCountry.USA),

	Hammond("506 SW Railroad Ave", null, null, null, "Hammond", enumState.LA, null, "70403", "70403-4954",
			enumCountry.USA),

	Knoxville("550 W Main St", null, null, null, "Knoxville", enumState.TN, null, "37902", "37902-2515",
			enumCountry.USA),

	Miramar("1800 S University Dr", null, null, null, "Miramar", enumState.FL, null, "33025", "33025-2231",
			enumCountry.USA),

	Folsom("2495 Iron Point Rd #11", null, "2495 Iron Point Rd Ste 1230 # 11", null, "Folsom", enumState.CA, null,
			"95630", "95630-8710", enumCountry.USA),

	Illinois("5333 Prairie Stone Pkwy", null, "5333 Prairie Stone Pkwy", null, "Hoffman Estates", enumState.IL, null,
			"60192", "60192-3720", enumCountry.USA),

	GrandRapids("5120 28th St SE", null, null, null, "Grand Rapids", enumState.MI, null, "49512", null,
			enumCountry.USA),

	Edison("883 Route 1", null, "883 US Highway 1", null, "Edison", enumState.NJ, null, "08817", "08817-4677",
			enumCountry.USA),

	NewYork("45 Rockefeller Plaza", "1041 Old Cassatt Rd", "45 Rockefeller Plz", null, "New York", enumState.NY, null,
			"10111", "10111-0100", enumCountry.USA),

	Dallas("4505 Ridgeside Dr", null, null, null, "Dallas", enumState.TX, null, "75244", "75244-7524", enumCountry.USA),

	UniversalCity("100 Universal City Plaza", null, "100 Universal City Plz", null, "Universal City", enumState.CA,
			null, "91608", "91608-1002", enumCountry.USA),

	Tysons("1961 Chain Bridge Rd", null, null, null, "Tysons", enumState.VA, null, "22102", null, enumCountry.USA),

	PaloAlto("3075B Hansen Way", null, "3075B Hansen Way", null, "Palo Alto", enumState.CA, null, "94304", "94304-1000",
			enumCountry.USA),

	Washington("22833 NE 8th St", "Suite 100", null, null, "Sammamish", enumState.WA, null, "98074", null,
			enumCountry.USA),

	SanMateo("1530 3rd St", null, "1530 E 3rd Ave", null, "San Mateo", enumState.CA, null, "94401", "94401-2112",
			enumCountry.USA),
	
	Linfield("36 Railroad Street", null,  null, null, "Linfield", enumState.PA, null, "19468", "94401-2112",
			enumCountry.USA),
	
	Madhapur("4th and 5th floor Krishe Sapphire", null,  null, null, "Madhapur", enumState.TG, null, "50008", null,
			enumCountry.INDIA),
	

	VertexKOPAddress("2301 Renessaince Blvd", null, null, null, "King of Prussia", enumState.PA, null, "19406", null,
			enumCountry.USA),

	InvalidAddress("45 Rockefeller Plaza", null, null, null, "Hyderabad", enumState.CA, null, "99999", null,
			enumCountry.USA),
	

	Louisiana("6310 Clift Street", null, null, null, "Shreveport", enumState.LA, null, "71105", null,
			enumCountry.USA),
	
	Chesterbrook("1550 Liberty Ridget", null, null, null, "Chesterbrook", enumState.PA, null, "19087", null,
			enumCountry.USA),
	

	// Canada Addresses
	Quebec("119 St Jacques St", null, null, null, "Montreal", null, enumProvince.QC, "H2Y", "H2Y 1L6", enumCountry.CAN),
	
	Toronto("250 Yonge Street", "34th Floor", null, null, "Toronto", null, enumProvince.ON, "M5B 2L7", null,
			enumCountry.CAN),
	
	Vancouver("1055 Dunsmuir Street", "Suite 574", null, null, "Vancouver", null, enumProvince.BC, "V7X 1L3", null,
			enumCountry.CAN),
	Northbrook("12265 ON-41", null, null, null, "Northbrook", null, enumProvince.ON, "K0H 2G0", null, enumCountry.CAN);

	// @formatter:on

	public String addressLine1;
	public String addressLine2;
	public String city;
	public String zip5;
	public String cleansedAddressLine1;
	public String cleansedAddressLine2;
	public String zip9;
	public enumState state;
	public enumProvince province;
	public enumCountry country;

	enumAddress(String addressLine1, String addressLine2, String cleansedAddressLine1, String cleansedAddressLine2,
			String city, enumState state, enumProvince province, String zip5, String zip9, enumCountry country) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.cleansedAddressLine1 = cleansedAddressLine1;
		this.cleansedAddressLine2 = cleansedAddressLine2;
		this.city = city;
		this.state = state;
		this.province = province;
		this.zip5 = zip5;
		this.zip9 = zip9;
		this.country = country;
	}
}
