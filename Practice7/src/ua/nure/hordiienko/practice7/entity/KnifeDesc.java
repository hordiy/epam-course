package ua.nure.hordiienko.practice7.entity;

import java.util.ArrayList;
import java.util.List;

/**
* @author V.Hordiienko
* @version 1.0  June 16, 2019.
*/


public class KnifeDesc {

    protected String knifeType;
    protected int knifeHandy;
    protected String knifeOrigin;
    protected KnifeVisual knifeVisual;
    protected boolean collection;
    protected String name;
    protected List<KnifeVisual> visuals;
    
    public List<KnifeVisual> getVisuals() {
        if (visuals == null) {
        	visuals = new ArrayList<>();
        }
        return visuals;
    }

    /**
     * Gets the value of the knifeType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getKnifeType() {
        return knifeType;
    }

    /**
     * Sets the value of the knifeType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKnifeType(String value) {
        this.knifeType = value;
    }

    /**
     * Gets the value of the knifeHandy property.
     */
    public int getKnifeHandy() {
        return knifeHandy;
    }

    /**
     * Sets the value of the knifeHandy property.
     */
    public void setKnifeHandy(int value) {
        this.knifeHandy = value;
    }

    /**
     * Gets the value of the knifeOrigin property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getKnifeOrigin() {
        return knifeOrigin;
    }

    /**
     * Sets the value of the knifeOrigin property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKnifeOrigin(String value) {
        this.knifeOrigin = value;
    }

    /**
     * Gets the value of the knifeVisual property.
     *
     * @return possible object is
     * {@link KnifeDesc.KnifeVisual }
     */
    public KnifeVisual getKnifeVisual() {
        return knifeVisual;
    }

    /**
     * Sets the value of the knifeVisual property.
     *
     * @param value allowed object is
     *              {@link KnifeDesc.KnifeVisual }
     */
    public void setKnifeVisual(KnifeVisual value) {
        this.knifeVisual = value;
    }

    /**
     * Gets the value of the collection property.
     */
    public boolean isCollection() {
        return collection;
    }

    /**
     * Sets the value of the collection property.
     */
    public void setCollection(boolean value) {
        this.collection = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }
}
