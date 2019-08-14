package ua.nure.hordiienko.practice7;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ua.nure.hordiienko.practice7.constants.Constants;
import ua.nure.hordiienko.practice7.controller.DOMController;
import ua.nure.hordiienko.practice7.controller.SAXController;
import ua.nure.hordiienko.practice7.controller.STAXController;
import ua.nure.hordiienko.practice7.entity.KnifeDesc;
import ua.nure.hordiienko.practice7.util.Sorter;
import ua.nure.hordiienko.practice7.xmlvalidator.KnivesXmlValidator;

/**
 * This is runner.
 * It's run all commands, to demonstrate, that is works properly:
 * 1. XML validation
 * 2. XML parsing with DOM
 * 3. XML parsing with SAX
 * 4. XML parsing with STAX
 *
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 */

public class Main {

    /**
     * Runs all tasks.
     *
     * @param args command line arguments
     */
	
public static void usage() {
		
		System.out.println("Usage:\njava ua.kharkov.knure.hordiienko.practice7.Main xmlFileName");
	}
	
    public static void main(String[] args) throws 
    	SAXException, ParserConfigurationException, IOException, XMLStreamException {
        
       
        if (args.length != 1) {
			usage();
			return;
		}
        
        try {
			output(args[0]);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
			System.err.println("Incorrect XML file: " + args[0] + " " + ex.getMessage());
		}
    }
    
    public static void output(String name) throws 
    	ParserConfigurationException, TransformerException, IOException, SAXException, XMLStreamException {
    	
    	String xmlFileName = name;
    	System.out.println("Output ==> " + xmlFileName);
    	
		////////////////////////////////////////////////////////
		// 					DOMController					  //
		////////////////////////////////////////////////////////

    	List<KnifeDesc> knifesDom = new DOMController().parseDocument(xmlFileName);
    	Sorter.sortKnifesByName(knifesDom);
    	
    	String outputXmlFile = "output.dom.xml";
    	DOMController.saveToXML(knifesDom, outputXmlFile);
    	System.out.println("Output ==> " + outputXmlFile);
    	new KnivesXmlValidator().validate(Constants.XSD_FILE, outputXmlFile);
    	

		////////////////////////////////////////////////////////
		// 					SAXController					  //
		////////////////////////////////////////////////////////
    	
    	List<KnifeDesc> knifeSax = new SAXController().parseDocument(xmlFileName);
    	Sorter.sortKnifesByType(knifeSax);
    	
    	outputXmlFile = "output.sax.xml";
    	DOMController.saveToXML(knifeSax, outputXmlFile);
    	System.out.println("Output ==> " + outputXmlFile);
    	new KnivesXmlValidator().validate(Constants.XSD_FILE, outputXmlFile);

		////////////////////////////////////////////////////////
		// 					STAXController					  //
		////////////////////////////////////////////////////////
    	
    	List<KnifeDesc> knifeStax = new STAXController().parseDocument(xmlFileName);
    	Sorter.sortKnifesByOrigin(knifeStax);
    	
    	outputXmlFile = "output.stax.xml";
    	DOMController.saveToXML(knifeStax, outputXmlFile);
    	System.out.println("Output ==> " + outputXmlFile);
    	new KnivesXmlValidator().validate(Constants.XSD_FILE, outputXmlFile);
    }
}
