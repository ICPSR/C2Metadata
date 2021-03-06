//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.21 at 05:55:46 PM EDT 
//


package edu.umich.icpsr.ddi2.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for dataFingerprintType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataFingerprintType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="digitalFingerprintValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="algorithmSpecification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="algorithmVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="data"/>
 *             &lt;enumeration value="dataFile"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataFingerprintType", namespace = "ddi:codebook:2_5", propOrder = {
    "digitalFingerprintValue",
    "algorithmSpecification",
    "algorithmVersion"
})
public class DataFingerprintType {

    @XmlElement(namespace = "ddi:codebook:2_5", required = true)
    protected String digitalFingerprintValue;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected String algorithmSpecification;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected String algorithmVersion;
    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;

    /**
     * Gets the value of the digitalFingerprintValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigitalFingerprintValue() {
        return digitalFingerprintValue;
    }

    /**
     * Sets the value of the digitalFingerprintValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigitalFingerprintValue(String value) {
        this.digitalFingerprintValue = value;
    }

    /**
     * Gets the value of the algorithmSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithmSpecification() {
        return algorithmSpecification;
    }

    /**
     * Sets the value of the algorithmSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithmSpecification(String value) {
        this.algorithmSpecification = value;
    }

    /**
     * Gets the value of the algorithmVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithmVersion() {
        return algorithmVersion;
    }

    /**
     * Sets the value of the algorithmVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithmVersion(String value) {
        this.algorithmVersion = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
