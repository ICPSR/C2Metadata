//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.21 at 05:55:46 PM EDT 
//


package edu.umich.icpsr.ddi2.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xhtml:div xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns="ddi:codebook:2_5" xmlns:dc="http://purl.org/dc/terms/" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:saxon="http://xml.apache.org/xslt" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 * 					&lt;xhtml:h1 class="element_title"&gt;Conceptual Text Type&lt;/xhtml:h1&gt;
 * 					&lt;xhtml:div&gt;&lt;xhtml:h2 class="section_header"&gt;Description&lt;/xhtml:h2&gt;&lt;xhtml:div class="description"&gt;This type forms this basis for a textual element which may also provide for a conceptual (see concept) description of the element a longer description (see txt). If the concept and/or txt elements are used, then the element should contain no other child elements or text. Note that if elements from the PHRASE and FORM groups must not be used with elements from the xhtml:BlkNoForm.mix group; one can use either elements from xhtml:BlkNoForm.mix or elements from the PHRASE and FORM groups.&lt;/xhtml:div&gt;
 * 					&lt;/xhtml:div&gt;
 * 				&lt;/xhtml:div&gt;
 * </pre>
 *           
 *          
 * 
 * <p>Java class for conceptualTextType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conceptualTextType">
 *   &lt;complexContent>
 *     &lt;extension base="{ddi:codebook:2_5}abstractTextType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;group ref="{ddi:codebook:2_5}PHRASE"/>
 *           &lt;group ref="{ddi:codebook:2_5}FORM"/>
 *           &lt;element ref="{ddi:codebook:2_5}concept"/>
 *           &lt;element ref="{ddi:codebook:2_5}txt"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conceptualTextType", namespace = "ddi:codebook:2_5")
@XmlSeeAlso({
    NationType.class,
    TimeMethType.class,
    DataKindType.class,
    UniverseType.class,
    AnlyUnitType.class,
    ResInstruType.class
})
public class ConceptualTextType
    extends AbstractTextType
{


}