package ua.nure.hordiienko.practice7.controller;

import ua.nure.hordiienko.practice7.constants.Constants;
import ua.nure.hordiienko.practice7.constants.XML;
import ua.nure.hordiienko.practice7.entity.KnifeDesc;
import ua.nure.hordiienko.practice7.entity.KnifeVisual;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeBlade;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeHandle;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeHandle.Wood;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This is a DOM parser for the XML document containing a collection of knifes.
 *
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 */

public class DOMController {

    /**
     * Creates DOM model of the XML file.
     *
     * @param filename the input XML document
     * @return corresponding DOM document
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document getDocument(Iterable<KnifeDesc> knifes) throws
            ParserConfigurationException {

DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		dbf.setNamespaceAware(true);
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();
        // create root element
		Element dElement = document.createElement("p7:" + XML.KNIFES.value());
		
		// set attribute
		dElement.setAttribute("xmlns:p7", Constants.XSD_SCHEMA);
		dElement.setAttribute("xmlns:xsi", Constants.XML_XSI);
		dElement.setAttribute("xsi:schemaLocation", Constants.SCHEMA_LOCATION);
		
		// add root element
		document.appendChild(dElement);
		
		for (KnifeDesc knife : knifes) {
			Element knifeElement = document.createElement(XML.KNIFE.value());
			dElement.appendChild(knifeElement);
			
			if (knife.getName() != null) {
				knifeElement.setAttribute(XML.NAME.value(), knife.getName());
			}
			
			Element typeElement = document.createElement(XML.TYPE.value());
			typeElement.setTextContent(knife.getKnifeType());
			knifeElement.appendChild(typeElement);
			
			Element handyElement = document.createElement(XML.HANDY.value());
			handyElement.setTextContent(String.valueOf(knife.getKnifeHandy()));
			knifeElement.appendChild(handyElement);
			
			Element originElement = document.createElement(XML.ORIGIN.value());
			originElement.setTextContent(knife.getKnifeOrigin());
			knifeElement.appendChild(originElement);
			
			// add visuals element	
			Element visualElement = document.createElement(XML.VISUAL.value());
			knifeElement.appendChild(visualElement);
				
			Element bladeElement = document.createElement(XML.BLADE.value());
			visualElement.appendChild(bladeElement);		
	
			if (knife.getKnifeVisual().getKnifeBlade().getLength() != null) {
				bladeElement.setAttribute(XML.LENGTH.value(), String.valueOf(knife.getKnifeVisual().getKnifeBlade().getLength()));
			}
				
			if (knife.getKnifeVisual().getKnifeBlade().getWidth() != null) {
				bladeElement.setAttribute(XML.WIDTH.value(), String.valueOf(knife.getKnifeVisual().getKnifeBlade().getWidth()));
			}
				
			if (knife.getKnifeVisual().getKnifeBlade().getMetal() != null) {
				bladeElement.setAttribute(XML.METAL.value(), String.valueOf(knife.getKnifeVisual().getKnifeBlade().getMetal()));
			}
			
			
			Element handleElement = document.createElement(XML.HANDLE.value());
			visualElement.appendChild(handleElement);
			
			if (knife.getKnifeVisual().getKnifeHandle().getPlastic() != null) {
				Element plasticElement = document.createElement(XML.PLASTIC.value());
				handleElement.appendChild(plasticElement);
			}
			
			if (knife.getKnifeVisual().getKnifeHandle().getLeatherCoated() != null) {
			Element leatherCoatedElement = document.createElement(XML.LEATHERCOATED.value());
			handleElement.appendChild(leatherCoatedElement);
			}
			
			if (knife.getKnifeVisual().getKnifeHandle().getWood() != null) {
				Element woodElement = document.createElement(XML.WOOD.value());
				handleElement.appendChild(woodElement);
				
				if (knife.getKnifeVisual().getKnifeHandle().getWood().getType() != null) {
					woodElement.setAttribute(XML.WOODTYPE.value(), String.valueOf(
							knife.getKnifeVisual().getKnifeHandle().getWood().getType()));
				}
			}
				
			
			Element collectionElement = document.createElement(XML.COLLECTION.value());
			collectionElement.setTextContent(String.valueOf(knife.isCollection()));
			knifeElement.appendChild(collectionElement);
			
			
		}
        return document;
    }

    /**
     * Parses the XML document and creates the list of records.
     *
     * @param filename the input XML document
     * @return the list of knifes (records)
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     */
    public List<KnifeDesc> parseDocument(String filename) throws 
    	ParserConfigurationException, SAXException, IOException {
        
    	List<KnifeDesc> knifes = new ArrayList<>();
        KnifeDesc knife = null;
        Node currentNode = null;

        
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File(filename));
            NodeList records = doc.getDocumentElement().getElementsByTagName("knife");

            for (int i = 0; i < records.getLength(); ++i) {
                currentNode = records.item(i);

                // create new knife instance
                knife = new KnifeDesc();

                // get name
                knife.setName(((Element) currentNode).getAttribute("name"));

                // get child tags
                NodeList children = currentNode.getChildNodes();

                for (int j = 0; j < children.getLength(); ++j) {
                    Node node = children.item(j);

                    if (node instanceof Element) {
                    	getNode(node, knife);
                    }
                }

                knifes.add(knife);
            }

        return knifes;
    }
    
    public void getNode(Node node, KnifeDesc knife) {
        switch (node.getNodeName()) {
            case "knifeType":
                knife.setKnifeType(node.getTextContent());
                break;

            case "knifeHandy":
                knife.setKnifeHandy(Integer.parseInt(node.getTextContent()));
                break;

            case "knifeOrigin":
                knife.setKnifeOrigin(node.getTextContent());
                break;

            case "knifeVisual":
                knife.setKnifeVisual(new KnifeVisual());
                NodeList visualAttrs = node.getChildNodes();
                for (int k = 0; k < visualAttrs.getLength(); ++k) {
                    Node visualNode = visualAttrs.item(k);

                    if (visualNode instanceof Element) {
                    	getVisualNode(visualNode, knife);
                    }
                }

                break;


            case "collection":
                knife.setCollection(Boolean.parseBoolean(node.getTextContent()));
                break;

            default:
                knife = null;
                throw new IllegalArgumentException();
        }
    }
    
    public void getVisualNode(Node visualNode, KnifeDesc knife) {
        switch (visualNode.getNodeName()) {

            case "knifeBlade":

                KnifeBlade blade = new KnifeBlade();

                blade.setLength(new BigInteger(((Element) visualNode)
                		.getAttribute("length")));
                blade.setWidth(new BigInteger(((Element) visualNode)
                		.getAttribute("width")));
                blade.setMetal(((Element) visualNode)
                		.getAttribute("metal"));
                knife.getKnifeVisual().setKnifeBlade(blade);
                break;

            case "knifeHandle":
                knife.getKnifeVisual().setKnifeHandle(new KnifeHandle());

                // check wood
                NodeList woodNode = ((Element) visualNode).getElementsByTagName("wood");

                if (woodNode.getLength() > 0) {
                    Wood wood = new Wood();
                    wood.setType(((Element) woodNode.item(0))
                    		.getAttribute("type"));
                    knife.getKnifeVisual().getKnifeHandle().setWood(wood);
                    break;
                }

                // check leatherCoated
                if (((Element) visualNode)
                		.getElementsByTagName("leatherCoated").getLength() > 0) {
                    knife.getKnifeVisual()
                    	.getKnifeHandle().setLeatherCoated(new Object());
                    break;
                }

                // check plastic
                if (((Element) visualNode)
                		.getElementsByTagName("plastic").getLength() > 0) {
                    knife.getKnifeVisual().getKnifeHandle().setPlastic(new Object());
                    break;
                }
                break;

            default:
                knife = null;
                throw new IllegalArgumentException();
        }
    }
    
    /**
	 * Saves Knifes object to XML file.
	 * 
	 * @param knifes
	 * Knifes object to be saved.
	 * @param xmlFileName
	 * Output XML file name.
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
     * @throws SAXException 
     * @throws IOException 
	 */
	
	public static void saveToXML(List<KnifeDesc> knifes, String xmlFileName) throws 
		ParserConfigurationException, TransformerException {
		
		saveToXML(getDocument(knifes), xmlFileName);
		
	}
	
	/**
	 * Save DOM to XML
	 * 
	 * @param document
	 * DOM to be saved.
	 * @param xmlFileName
	 * Output XML file name.
	 * @throws TransformerException 
	 */
	
	public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
		
		StreamResult result = new StreamResult(new File(xmlFileName));
		
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		
		t.transform(new DOMSource(document), result);
	}
}