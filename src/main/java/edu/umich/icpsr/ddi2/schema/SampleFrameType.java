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
 * <p>Java class for sampleFrameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sampleFrameType">
 *   &lt;complexContent>
 *     &lt;extension base="{ddi:codebook:2_5}baseElementType">
 *       &lt;sequence>
 *         &lt;element ref="{ddi:codebook:2_5}sampleFrameName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}labl" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}txt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}validPeriod" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}custodian" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}useStmt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}universe" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}frameUnit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}referencePeriod" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}updateProcedure" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sampleFrameType", namespace = "ddi:codebook:2_5", propOrder = {
    "sampleFrameName",
    "labl",
    "txt",
    "validPeriod",
    "custodian",
    "useStmt",
    "universe",
    "frameUnit",
    "referencePeriod",
    "updateProcedure"
})
public class SampleFrameType
    extends BaseElementType
{

    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<StringType> sampleFrameName;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<LablType> labl;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<TxtType> txt;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<EventDateType> validPeriod;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<Custodian> custodian;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<UseStmtType> useStmt;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<UniverseType> universe;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<FrameUnitType> frameUnit;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<EventDateType> referencePeriod;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> updateProcedure;

    /**
     * Gets the value of the sampleFrameName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sampleFrameName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSampleFrameName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringType }
     * 
     * 
     */
    public List<StringType> getSampleFrameName() {
        if (sampleFrameName == null) {
            sampleFrameName = new ArrayList<StringType>();
        }
        return this.sampleFrameName;
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
     * Gets the value of the validPeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validPeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidPeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventDateType }
     * 
     * 
     */
    public List<EventDateType> getValidPeriod() {
        if (validPeriod == null) {
            validPeriod = new ArrayList<EventDateType>();
        }
        return this.validPeriod;
    }

    /**
     * Gets the value of the custodian property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custodian property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustodian().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Custodian }
     * 
     * 
     */
    public List<Custodian> getCustodian() {
        if (custodian == null) {
            custodian = new ArrayList<Custodian>();
        }
        return this.custodian;
    }

    /**
     * Gets the value of the useStmt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the useStmt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUseStmt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UseStmtType }
     * 
     * 
     */
    public List<UseStmtType> getUseStmt() {
        if (useStmt == null) {
            useStmt = new ArrayList<UseStmtType>();
        }
        return this.useStmt;
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
     * Gets the value of the frameUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frameUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrameUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FrameUnitType }
     * 
     * 
     */
    public List<FrameUnitType> getFrameUnit() {
        if (frameUnit == null) {
            frameUnit = new ArrayList<FrameUnitType>();
        }
        return this.frameUnit;
    }

    /**
     * Gets the value of the referencePeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referencePeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferencePeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventDateType }
     * 
     * 
     */
    public List<EventDateType> getReferencePeriod() {
        if (referencePeriod == null) {
            referencePeriod = new ArrayList<EventDateType>();
        }
        return this.referencePeriod;
    }

    /**
     * Gets the value of the updateProcedure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the updateProcedure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdateProcedure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getUpdateProcedure() {
        if (updateProcedure == null) {
            updateProcedure = new ArrayList<SimpleTextType>();
        }
        return this.updateProcedure;
    }

}
