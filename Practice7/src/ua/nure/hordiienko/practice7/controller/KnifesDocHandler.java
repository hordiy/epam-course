package ua.nure.hordiienko.practice7.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.hordiienko.practice7.entity.KnifeDesc;
import ua.nure.hordiienko.practice7.entity.Knifes;
import ua.nure.hordiienko.practice7.entity.KnifeVisual;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeBlade;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeHandle;
import ua.nure.hordiienko.practice7.entity.KnifeVisual.KnifeHandle.Wood;

/**
 * Class - document handler for SAX parser.
 *
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 */


public class KnifesDocHandler extends DefaultHandler {
	
    private String currentElem;
    private KnifeDesc currentKnife;
    private List<KnifeDesc> knifes;
    private StringBuilder builder = new StringBuilder();

    public List<KnifeDesc> getKnifes() {
        return new ArrayList<>(knifes);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        String data = null;
        currentElem = localName;
            switch (currentElem) {
                case "knifes":
                    knifes = new Knifes().getKnife();
                    break;

                case "knife":
                    currentKnife = new KnifeDesc();

                    data = attributes.getValue("name");
                    currentKnife.setName(data);                
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
                    data = attributes.getValue("length");
                    blade.setLength(new BigInteger(data));
                    data = attributes.getValue("width");
                    blade.setWidth(new BigInteger(data));
                    data = attributes.getValue("metal");
                    blade.setMetal(data);
                    currentKnife.getKnifeVisual().setKnifeBlade(blade);
                    break;

                case "knifeHandle":
                    currentKnife.getKnifeVisual().setKnifeHandle(new KnifeHandle());
                    break;

                case "wood":
                    Wood wood = new Wood();

                    data = attributes.getValue("type");
                    wood.setType(data);
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
                    throw new SAXException("Unknown element " + qName);
            }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if ("knifeType".equals(currentElem) ||
        		"knifeHandy".equals(currentElem) ||
        		"knifeOrigin".equals(currentElem) ||
        		"collection".equals(currentElem)) {

            builder.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        String data = builder.toString().trim();

        switch (qName) {

            case "knife":
                knifes.add(currentKnife);
                break;

            case "knifeType":
                currentKnife.setKnifeType(data);
                break;

            case "knifeHandy":
                currentKnife.setKnifeHandy(Integer.parseInt(data));
                break;

            case "knifeOrigin":
                currentKnife.setKnifeOrigin(data);
                break;

            case "collection":
                currentKnife.setCollection(Boolean.parseBoolean(data));
                break;

            default:
                break;
        }

        builder.setLength(0);
    }
}

