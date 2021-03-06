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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nCubeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nCubeType">
 *   &lt;complexContent>
 *     &lt;extension base="{ddi:codebook:2_5}baseElementType">
 *       &lt;sequence>
 *         &lt;element ref="{ddi:codebook:2_5}location" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}labl" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}txt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}universe" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}imputation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}security" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}embargo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}respUnit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}anlysUnit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}verStmt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}purpose" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}dmns" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}measure" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}notes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sdatrefs" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="methrefs" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="pubrefs" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="access" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="dmnsQnty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cellQnty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nCubeType", namespace = "ddi:codebook:2_5", propOrder = {
    "location",
    "labl",
    "txt",
    "universe",
    "imputation",
    "security",
    "embargo",
    "respUnit",
    "anlysUnit",
    "verStmt",
    "purpose",
    "dmns",
    "measure",
    "notes"
})
public class NCubeType
    extends BaseElementType
{

    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<LocationType> location;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<LablType> labl;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<TxtType> txt;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<UniverseType> universe;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> imputation;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextAndDateType> security;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<EmbargoType> embargo;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> respUnit;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<ConceptualTextType> anlysUnit;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<VerStmtType> verStmt;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<PurposeType> purpose;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<DmnsType> dmns;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<MeasureType> measure;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<NotesType> notes;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "sdatrefs")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> sdatrefs;
    @XmlAttribute(name = "methrefs")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> methrefs;
    @XmlAttribute(name = "pubrefs")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> pubrefs;
    @XmlAttribute(name = "access")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> access;
    @XmlAttribute(name = "dmnsQnty")
    protected String dmnsQnty;
    @XmlAttribute(name = "cellQnty")
    protected String cellQnty;

    /**
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocationType }
     * 
     * 
     */
    public List<LocationType> getLocation() {
        if (location == null) {
            location = new ArrayList<LocationType>();
        }
        return this.location;
    }

    /**
     * Gets the value of the labl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LablType }
     * 
     * 
     */
    public List<LablType> getLabl() {
        if (labl == null) {
            labl = new ArrayList<LablType>();
        }
        return this.labl;
    }

    /**
     * Gets the value of the txt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the txt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTxt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TxtType }
     * 
     * 
     */
    public List<TxtType> getTxt() {
        if (txt == null) {
            txt = new ArrayList<TxtType>();
        }
        return this.txt;
    }

    /**
     * Gets the value of the universe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the universe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUniverse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UniverseType }
     * 
     * 
     */
    public List<UniverseType> getUniverse() {
        if (universe == null) {
            universe = new ArrayList<UniverseType>();
        }
        return this.universe;
    }

    /**
     * Gets the value of the imputation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imputation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImputation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getImputation() {
        if (imputation == null) {
            imputation = new ArrayList<SimpleTextType>();
        }
        return this.imputation;
    }

    /**
     * Gets the value of the security property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the security property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextAndDateType }
     * 
     * 
     */
    public List<SimpleTextAndDateType> getSecurity() {
        if (security == null) {
            security = new ArrayList<SimpleTextAndDateType>();
        }
        return this.security;
    }

    /**
     * Gets the value of the embargo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the embargo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmbargo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmbargoType }
     * 
     * 
     */
    public List<EmbargoType> getEmbargo() {
        if (embargo == null) {
            embargo = new ArrayList<EmbargoType>();
        }
        return this.embargo;
    }

    /**
     * Gets the value of the respUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getRespUnit() {
        if (respUnit == null) {
            respUnit = new ArrayList<SimpleTextType>();
        }
        return this.respUnit;
    }

    /**
     * Gets the value of the anlysUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anlysUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnlysUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptualTextType }
     * 
     * 
     */
    public List<ConceptualTextType> getAnlysUnit() {
        if (anlysUnit == null) {
            anlysUnit = new ArrayList<ConceptualTextType>();
        }
        return this.anlysUnit;
    }

    /**
     * Gets the value of the verStmt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verStmt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerStmt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerStmtType }
     * 
     * 
     */
    public List<VerStmtType> getVerStmt() {
        if (verStmt == null) {
            verStmt = new ArrayList<VerStmtType>();
        }
        return this.verStmt;
    }

    /**
     * Gets the value of the purpose property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the purpose property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPurpose().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PurposeType }
     * 
     * 
     */
    public List<PurposeType> getPurpose() {
        if (purpose == null) {
            purpose = new ArrayList<PurposeType>();
        }
        return this.purpose;
    }

    /**
     * Gets the value of the dmns property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmns property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmns().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DmnsType }
     * 
     * 
     */
    public List<DmnsType> getDmns() {
        if (dmns == null) {
            dmns = new ArrayList<DmnsType>();
        }
        return this.dmns;
    }

    /**
     * Gets the value of the measure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeasureType }
     * 
     * 
     */
    public List<MeasureType> getMeasure() {
        if (measure == null) {
            measure = new ArrayList<MeasureType>();
        }
        return this.measure;
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

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the sdatrefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sdatrefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSdatrefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getSdatrefs() {
        if (sdatrefs == null) {
            sdatrefs = new ArrayList<Object>();
        }
        return this.sdatrefs;
    }

    /**
     * Gets the value of the methrefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the methrefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMethrefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getMethrefs() {
        if (methrefs == null) {
            methrefs = new ArrayList<Object>();
        }
        return this.methrefs;
    }

    /**
     * Gets the value of the pubrefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pubrefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPubrefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getPubrefs() {
        if (pubrefs == null) {
            pubrefs = new ArrayList<Object>();
        }
        return this.pubrefs;
    }

    /**
     * Gets the value of the access property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the access property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAccess() {
        if (access == null) {
            access = new ArrayList<Object>();
        }
        return this.access;
    }

    /**
     * Gets the value of the dmnsQnty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDmnsQnty() {
        return dmnsQnty;
    }

    /**
     * Sets the value of the dmnsQnty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDmnsQnty(String value) {
        this.dmnsQnty = value;
    }

    /**
     * Gets the value of the cellQnty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellQnty() {
        return cellQnty;
    }

    /**
     * Sets the value of the cellQnty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellQnty(String value) {
        this.cellQnty = value;
    }

}
