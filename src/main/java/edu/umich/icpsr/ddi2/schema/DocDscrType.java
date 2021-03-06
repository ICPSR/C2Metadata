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
 * <p>Java class for docDscrType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="docDscrType">
 *   &lt;complexContent>
 *     &lt;extension base="{ddi:codebook:2_5}baseElementType">
 *       &lt;sequence>
 *         &lt;element ref="{ddi:codebook:2_5}citation" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}guide" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}docStatus" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}docSrc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}controlledVocabUsed" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}notes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "docDscrType", namespace = "ddi:codebook:2_5", propOrder = {
    "citation",
    "guide",
    "docStatus",
    "docSrc",
    "controlledVocabUsed",
    "notes"
})
public class DocDscrType
    extends BaseElementType
{

    @XmlElement(namespace = "ddi:codebook:2_5")
    protected CitationType citation;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> guide;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> docStatus;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<DocSrcType> docSrc;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<ControlledVocabUsedType> controlledVocabUsed;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<NotesType> notes;

    /**
     * Gets the value of the citation property.
     * 
     * @return
     *     possible object is
     *     {@link CitationType }
     *     
     */
    public CitationType getCitation() {
        return citation;
    }

    /**
     * Sets the value of the citation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CitationType }
     *     
     */
    public void setCitation(CitationType value) {
        this.citation = value;
    }

    /**
     * Gets the value of the guide property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guide property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGuide().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getGuide() {
        if (guide == null) {
            guide = new ArrayList<SimpleTextType>();
        }
        return this.guide;
    }

    /**
     * Gets the value of the docStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getDocStatus() {
        if (docStatus == null) {
            docStatus = new ArrayList<SimpleTextType>();
        }
        return this.docStatus;
    }

    /**
     * Gets the value of the docSrc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docSrc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocSrc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocSrcType }
     * 
     * 
     */
    public List<DocSrcType> getDocSrc() {
        if (docSrc == null) {
            docSrc = new ArrayList<DocSrcType>();
        }
        return this.docSrc;
    }

    /**
     * Gets the value of the controlledVocabUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the controlledVocabUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getControlledVocabUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ControlledVocabUsedType }
     * 
     * 
     */
    public List<ControlledVocabUsedType> getControlledVocabUsed() {
        if (controlledVocabUsed == null) {
            controlledVocabUsed = new ArrayList<ControlledVocabUsedType>();
        }
        return this.controlledVocabUsed;
    }

    /**
     * Gets the value of the notes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NotesType }
     * 
     * 
     */
    public List<NotesType> getNotes() {
        if (notes == null) {
            notes = new ArrayList<NotesType>();
        }
        return this.notes;
    }

}
