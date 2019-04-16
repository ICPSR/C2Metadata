//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.21 at 05:55:46 PM EDT 
//


package edu.umich.icpsr.ddi2.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sourcesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sourcesType">
 *   &lt;complexContent>
 *     &lt;extension base="{ddi:codebook:2_5}baseElementType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{ddi:codebook:2_5}dataSrc" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{ddi:codebook:2_5}sourceCitation" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{ddi:codebook:2_5}srcOrig" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{ddi:codebook:2_5}srcChar" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{ddi:codebook:2_5}srcDocu" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{ddi:codebook:2_5}sources" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sourcesType", namespace = "ddi:codebook:2_5", propOrder = {
    "dataSrc",
    "sourceCitation",
    "srcOrig",
    "srcChar",
    "srcDocu",
    "sources"
})
public class SourcesType
    extends BaseElementType
{

    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> dataSrc;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<CitationType> sourceCitation;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<ConceptualTextType> srcOrig;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> srcChar;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> srcDocu;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SourcesType> sources;

    /**
     * Gets the value of the dataSrc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSrc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSrc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getDataSrc() {
        if (dataSrc == null) {
            dataSrc = new ArrayList<SimpleTextType>();
        }
        return this.dataSrc;
    }

    /**
     * Gets the value of the sourceCitation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceCitation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceCitation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CitationType }
     * 
     * 
     */
    public List<CitationType> getSourceCitation() {
        if (sourceCitation == null) {
            sourceCitation = new ArrayList<CitationType>();
        }
        return this.sourceCitation;
    }

    /**
     * Gets the value of the srcOrig property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the srcOrig property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSrcOrig().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptualTextType }
     * 
     * 
     */
    public List<ConceptualTextType> getSrcOrig() {
        if (srcOrig == null) {
            srcOrig = new ArrayList<ConceptualTextType>();
        }
        return this.srcOrig;
    }

    /**
     * Gets the value of the srcChar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the srcChar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSrcChar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getSrcChar() {
        if (srcChar == null) {
            srcChar = new ArrayList<SimpleTextType>();
        }
        return this.srcChar;
    }

    /**
     * Gets the value of the srcDocu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the srcDocu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSrcDocu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getSrcDocu() {
        if (srcDocu == null) {
            srcDocu = new ArrayList<SimpleTextType>();
        }
        return this.srcDocu;
    }

    /**
     * Gets the value of the sources property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sources property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSources().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SourcesType }
     * 
     * 
     */
    public List<SourcesType> getSources() {
        if (sources == null) {
            sources = new ArrayList<SourcesType>();
        }
        return this.sources;
    }

}