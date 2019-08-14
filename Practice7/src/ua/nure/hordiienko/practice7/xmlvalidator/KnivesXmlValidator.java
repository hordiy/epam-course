package ua.nure.hordiienko.practice7.xmlvalidator;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * Class validates the XML document according to the XSD schema.
 *
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 */

public class KnivesXmlValidator {

    /**
     * Validates the XML document according to the XSD schema.
     *
     * @param xsdSchema the filename of the XSD schema file
     * @param xmlFile   the XML document for validation
     */
    public String validate(String xsdSchema, String xmlFile) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdSchema));
            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlFile)));

        } catch (Exception e) {
            return ("File " + xmlFile + " is not valid!\n" + e.getMessage());

        }
        return ("File " + xmlFile + " is valid!");
    }
}
