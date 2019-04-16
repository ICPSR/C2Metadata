package edu.umich.icpsr.c2metadata.model;

public enum OutputType {

	SPSS ("spss"), 
	STATA ("stata"), 
	DDI ("ddi"), 
	HTML ("html");
	
	private final String label;

	OutputType(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
