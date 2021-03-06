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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for invalrngType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="invalrngType">
 *   &lt;complexContent>
 *     &lt;extension base="{ddi:codebook:2_5}baseElementType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element ref="{ddi:codebook:2_5}item"/>
 *           &lt;element ref="{ddi:codebook:2_5}range"/>
 *         &lt;/choice>
 *         &lt;element ref="{ddi:codebook:2_5}key" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "invalrngType", namespace = "ddi:codebook:2_5", propOrder = {
    "itemOrRange",
    "key",
    "notes"
})
public class InvalrngType
    extends BaseElementType
{

    @XmlElements({
        @XmlElement(name = "item", namespace = "ddi:codebook:2_5", type = ItemType.class),
        @XmlElement(name = "range", namespace = "ddi:codebook:2_5", type = RangeType.class)
    })
    protected List<BaseElementType> itemOrRange;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<TableAndTextType> key;
    @XmlElement(namespace = "ddi:codebook:2_5")
    protected List<NotesType> notes;

    /**
     * Gets the value of the itemOrRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemOrRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemOrRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemType }
     * {@link RangeType }
     * 
     * 
     */
    public List<BaseElementType> getItemOrRange() {
        if (itemOrRange == null) {
            itemOrRange = new ArrayList<BaseElementType>();
        }
        return this.itemOrRange;
    }

    /**
     * Gets the value of the key property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the key property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TableAndTextType }
     * 
     * 
     */
    public List<TableAndTextType> getKey() {
        if (key == null) {
            key = new ArrayList<TableAndTextType>();
        }
        return this.key;
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
