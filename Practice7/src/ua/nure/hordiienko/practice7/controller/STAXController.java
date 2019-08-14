package ua.nure.hordiienko.practice7.controller;

import ua.nure.hordiienko.practice7.constants.XML;
import ua.nure.hordiienko.practice7.entity.KnifeDesc;
import ua.nure.hordiienko.practice7.entity.KnifeVisual;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeBlade;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeHandle;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeHandle.Wood;
import ua.nure.hordiienko.practice7.entity.Knifes;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

/**
 * This is a StAX parser for the XML document containing a collection of knifes.
 *
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 */


public class STAXController {
	    
    private String currentElement;
    private KnifeDesc currentKnife;
    private List<KnifeDesc> knifes;

    public List<KnifeDesc> getKnifes() {
        return new ArrayList<>(knifes);
    }

    public List<KnifeDesc> parseDocument(String filename) throws XMLStreamException {
            // get the default factory instance
            XMLInputFactory factory = XMLInputFactory.newInstance();
            
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
            
            XMLEventReader reader = factory.createXMLEventReader(new StreamSource(filename));

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
    				continue;
    			}
                
                if (event.isStartElement()) {
                	
                	StartElement startElement = event.asStartElement();
    				currentElement = startElement.getName().getLocalPart();
	             
	                
    				switch (currentElement) {
                    case "knifes":
                        knifes = new Knifes().getKnife();
                        break;

                    case "knife":
                        currentKnife = new KnifeDesc();
                        Attribute attribute = startElement.getAttributeByName(new QName(XML.NAME.value()));
                        if (attribute != null) {
                        	currentKnife.setName(attribute.getValue());
                        }
                        break;

                    case "knifeType":
                    case "knifeHandy":
                    case "knifeOrigin":
                        break;

                    case "knifeVisual":
                        currentKnife.setKnifeVisual(new KnifeVisual());
                        break;

                    case "knifeBlade":
                        KnifeBlade blade = new KnifeBlade();
                        
                        // set blade attributes
                        attribute = startElement.getAttributeByName(new QName(XML.LENGTH.value()));
                        if (attribute != null) {
                        	blade.setLength(new BigInteger(attribute.getValue()));
                        }
                        
                        attribute = startElement.getAttributeByName(new QName(XML.WIDTH.value()));
                        if (attribute != null) {
                        	blade.setWidth(new BigInteger(attribute.getValue()));
                        }
                        
                        attribute = startElement.getAttributeByName(new QName(XML.METAL.value()));
                        if (attribute != null) {
                        	blade.setMetal(attribute.getValue());
                        }
                        currentKnife.getKnifeVisual().setKnifeBlade(blade);
                        break;

                    case "knifeHandle":
                        currentKnife.getKnifeVisual().setKnifeHandle(new KnifeHandle());
                        break;

                    case "wood":
                        Wood wood = new Wood();
                        
                        attribute = startElement.getAttributeByName(new QName(XML.WOODTYPE.value()));
                        if (attribute != null) {
                        	wood.setType(attribute.getValue());
                        }
                        currentKnife.getKnifeVisual().getKnifeHandle().setWood(wood);
                        break;

                    case "leatherCoated":
                        currentKnife.getKnifeVisual().getKnifeHandle().setLeatherCoated(new Object());
                        break;

                    case "plastic":
                        currentKnife.getKnifeVisual().getKnifeHandle().setPlastic(new Object());
                        break;

                    case "collection":
                        break;

                    default:

                        // element not found
                        currentKnife = null;
                }
                }
                
                if (event.isCharacters()) {
    				Characters characters = event.asCharacters();
    				
    				if (XML.TYPE.equalsTo(currentElement)) {
    					currentKnife.setKnifeType(characters.getData());
    					continue;
    				}
    				
    				if (XML.HANDY.equalsTo(currentElement)) {
    					currentKnife.setKnifeHandy(Integer.valueOf(characters.getData()));
    					continue;
    				}
    				
    				if (XML.ORIGIN.equalsTo(currentElement)) {
    					currentKnife.setKnifeOrigin(characters.getData());
    					continue;
    				}
    				
    				if (XML.COLLECTION.equalsTo(currentElement)) {
    					currentKnife.setCollection(Boolean.valueOf(characters.getData()));
    					continue;
    				}
    			
                }    
           
                
                if (event.isEndElement()) {
                	
    				EndElement endElement = event.asEndElement();
    				String localName = endElement.getName().getLocalPart();
    				
    				if (XML.KNIFE.equalsTo(localName)) {
    					knifes.add(currentKnife);
    				}
    			
                }
            }
            reader.close();
            return this.getKnifes();
    }
}
