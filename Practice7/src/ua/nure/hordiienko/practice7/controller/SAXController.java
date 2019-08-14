package ua.nure.hordiienko.practice7.controller;

import ua.nure.hordiienko.practice7.constants.Constants;
import ua.nure.hordiienko.practice7.entity.KnifeDesc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * This is a SAX parser for the XML document containing a collection of knifes.
 *
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 */

public class SAXController {

    /**
     * Parses the XML document and creates the list of records.
     *
     * @param filename the input XML document
     * @return the list of knifes (records)
     * @throws ParserConfigurationException 
     * @throws IOException 
     */
    public List<KnifeDesc> parseDocument(String filename) throws 
    	SAXException, ParserConfigurationException, IOException {
        
    	SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
	    factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
		factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
            		
		SAXParser parser = factory.newSAXParser();
	    KnifesDocHandler docHandler = new KnifesDocHandler();

       	parser.parse(new File(filename), docHandler);
       	return docHandler.getKnifes();
        }
}