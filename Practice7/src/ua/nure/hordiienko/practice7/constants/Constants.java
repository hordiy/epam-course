package ua.nure.hordiienko.practice7.constants;

/**
 * 
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 * 
 * This class holds application constants.
 *
 */

public final class Constants {
	
	private Constants() {
		
	}
	
	public static final String XSD_FILE = "input.xsd";
	public static final String XML_FILE = "input.xml";
	
	public static final String XML_XSI = "http://www.w3.org/2001/XMLSchema-instance";
	public static final String XSD_SCHEMA = "http://nure.ua/hordiienko/practice7";
	public static final String SCHEMA_LOCATION = XSD_SCHEMA + " " + XSD_FILE;
	
	public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
	public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";
}
