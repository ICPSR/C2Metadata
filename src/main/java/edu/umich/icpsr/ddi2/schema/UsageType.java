//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.21 at 05:55:46 PM EDT 
//


package edu.umich.icpsr.ddi2.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{ddi:codebook:2_5}selector"/>
 *           &lt;element ref="{ddi:codebook:2_5}specificElements"/>
 *         &lt;/choice>
 *         &lt;element ref="{ddi:codebook:2_5}attribute" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usageType", namespace = "ddi:codebook:2_5", propOrder = {
    "selector",
    "specificElements",
    "attribute"
})
public class UsageType {

    @XmlElement(namespace = "ddi:codebook:2_5")
    protected String selector;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected SpecificElementsType specificElements;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected String attribute;

    /**
     * Gets the value of the selector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelector() {
        return selector;
    }

    /**
     * Sets the value of the selector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelector(String value) {
        this.selector = value;
    }

    /**
     * Gets the value of the specificElements property.
     * 
     * @return
     *     possible object is
     *     {@link SpecificElementsType }
     *     
     */
    public SpecificElementsType getSpecificElements() {
        return specificElements;
    }

    /**
     * Sets the value of the specificElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecificElementsType }
     *     
     */
    public void setSpecificElements(SpecificElementsType value) {
        this.specificElements = value;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Sets the value of the attribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute(String value) {
        this.attribute = value;
    }

}