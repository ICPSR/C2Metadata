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
 * <p>Java class for dataCollType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataCollType">
 *   &lt;complexContent>
 *     &lt;extension base="{ddi:codebook:2_5}baseElementType">
 *       &lt;sequence>
 *         &lt;element ref="{ddi:codebook:2_5}timeMeth" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}dataCollector" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}collectorTraining" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}frequenc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}sampProc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}sampleFrame" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}targetSampleSize" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}deviat" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}collMode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}resInstru" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}instrumentDevelopment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}sources" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}collSitu" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}actMin" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}ConOps" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}weight" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{ddi:codebook:2_5}cleanOps" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCollType", namespace = "ddi:codebook:2_5", propOrder = {
    "timeMeth",
    "dataCollector",
    "collectorTraining",
    "frequenc",
    "sampProc",
    "sampleFrame",
    "targetSampleSize",
    "deviat",
    "collMode",
    "resInstru",
    "instrumentDevelopment",
    "sources",
    "collSitu",
    "actMin",
    "conOps",
    "weight",
    "cleanOps"
})
public class DataCollType
    extends BaseElementType
{

    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<TimeMethType> timeMeth;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<DataCollectorType> dataCollector;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<CollectorTrainingType> collectorTraining;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<FrequencType> frequenc;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<ConceptualTextType> sampProc;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SampleFrameType> sampleFrame;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<TargetSampleSizeType> targetSampleSize;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> deviat;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<ConceptualTextType> collMode;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<ResInstruType> resInstru;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<InstrumentDevelopmentType> instrumentDevelopment;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected SourcesType sources;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> collSitu;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> actMin;
    @XmlElement(name = "ConOps", namespace = "ddi:codebook:2_5")
    protected List<ConOpsType> conOps;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<SimpleTextType> weight;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<CleanOpsType> cleanOps;

    /**
     * Gets the value of the timeMeth property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeMeth property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeMeth().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeMethType }
     * 
     * 
     */
    public List<TimeMethType> getTimeMeth() {
        if (timeMeth == null) {
            timeMeth = new ArrayList<TimeMethType>();
        }
        return this.timeMeth;
    }

    /**
     * Gets the value of the dataCollector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataCollector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataCollector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataCollectorType }
     * 
     * 
     */
    public List<DataCollectorType> getDataCollector() {
        if (dataCollector == null) {
            dataCollector = new ArrayList<DataCollectorType>();
        }
        return this.dataCollector;
    }

    /**
     * Gets the value of the collectorTraining property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the collectorTraining property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCollectorTraining().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CollectorTrainingType }
     * 
     * 
     */
    public List<CollectorTrainingType> getCollectorTraining() {
        if (collectorTraining == null) {
            collectorTraining = new ArrayList<CollectorTrainingType>();
        }
        return this.collectorTraining;
    }

    /**
     * Gets the value of the frequenc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frequenc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrequenc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FrequencType }
     * 
     * 
     */
    public List<FrequencType> getFrequenc() {
        if (frequenc == null) {
            frequenc = new ArrayList<FrequencType>();
        }
        return this.frequenc;
    }

    /**
     * Gets the value of the sampProc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sampProc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSampProc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptualTextType }
     * 
     * 
     */
    public List<ConceptualTextType> getSampProc() {
        if (sampProc == null) {
            sampProc = new ArrayList<ConceptualTextType>();
        }
        return this.sampProc;
    }

    /**
     * Gets the value of the sampleFrame property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sampleFrame property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSampleFrame().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SampleFrameType }
     * 
     * 
     */
    public List<SampleFrameType> getSampleFrame() {
        if (sampleFrame == null) {
            sampleFrame = new ArrayList<SampleFrameType>();
        }
        return this.sampleFrame;
    }

    /**
     * Gets the value of the targetSampleSize property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetSampleSize property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetSampleSize().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TargetSampleSizeType }
     * 
     * 
     */
    public List<TargetSampleSizeType> getTargetSampleSize() {
        if (targetSampleSize == null) {
            targetSampleSize = new ArrayList<TargetSampleSizeType>();
        }
        return this.targetSampleSize;
    }

    /**
     * Gets the value of the deviat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deviat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeviat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getDeviat() {
        if (deviat == null) {
            deviat = new ArrayList<SimpleTextType>();
        }
        return this.deviat;
    }

    /**
     * Gets the value of the collMode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the collMode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCollMode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptualTextType }
     * 
     * 
     */
    public List<ConceptualTextType> getCollMode() {
        if (collMode == null) {
            collMode = new ArrayList<ConceptualTextType>();
        }
        return this.collMode;
    }

    /**
     * Gets the value of the resInstru property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resInstru property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResInstru().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResInstruType }
     * 
     * 
     */
    public List<ResInstruType> getResInstru() {
        if (resInstru == null) {
            resInstru = new ArrayList<ResInstruType>();
        }
        return this.resInstru;
    }

    /**
     * Gets the value of the instrumentDevelopment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instrumentDevelopment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstrumentDevelopment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstrumentDevelopmentType }
     * 
     * 
     */
    public List<InstrumentDevelopmentType> getInstrumentDevelopment() {
        if (instrumentDevelopment == null) {
            instrumentDevelopment = new ArrayList<InstrumentDevelopmentType>();
        }
        return this.instrumentDevelopment;
    }

    /**
     * Gets the value of the sources property.
     * 
     * @return
     *     possible object is
     *     {@link SourcesType }
     *     
     */
    public SourcesType getSources() {
        return sources;
    }

    /**
     * Sets the value of the sources property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourcesType }
     *     
     */
    public void setSources(SourcesType value) {
        this.sources = value;
    }

    /**
     * Gets the value of the collSitu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the collSitu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCollSitu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getCollSitu() {
        if (collSitu == null) {
            collSitu = new ArrayList<SimpleTextType>();
        }
        return this.collSitu;
    }

    /**
     * Gets the value of the actMin property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actMin property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActMin().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getActMin() {
        if (actMin == null) {
            actMin = new ArrayList<SimpleTextType>();
        }
        return this.actMin;
    }

    /**
     * Gets the value of the conOps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conOps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConOps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConOpsType }
     * 
     * 
     */
    public List<ConOpsType> getConOps() {
        if (conOps == null) {
            conOps = new ArrayList<ConOpsType>();
        }
        return this.conOps;
    }

    /**
     * Gets the value of the weight property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weight property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeight().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleTextType }
     * 
     * 
     */
    public List<SimpleTextType> getWeight() {
        if (weight == null) {
            weight = new ArrayList<SimpleTextType>();
        }
        return this.weight;
    }

    /**
     * Gets the value of the cleanOps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cleanOps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCleanOps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CleanOpsType }
     * 
     * 
     */
    public List<CleanOpsType> getCleanOps() {
        if (cleanOps == null) {
            cleanOps = new ArrayList<CleanOpsType>();
        }
        return this.cleanOps;
    }

}
