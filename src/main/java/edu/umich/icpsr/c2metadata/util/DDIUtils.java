package edu.umich.icpsr.c2metadata.util;

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import edu.umich.icpsr.ddi2.schema.CodeBookType;

public class DDIUtils {

	private static final Logger LOG = Logger.getLogger(DDIUtils.class);

	public static CodeBookType loadFromXml(File file){
		LOG.info("Executing loadFromXml.");
		CodeBookType codeBook = new CodeBookType();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CodeBookType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			codeBook = (CodeBookType) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(FileUtils.readFileToByteArray(file))));			
					} catch (Exception e) {  
	        LOG.error(e);  
	    } 
		return codeBook;
	}

	public static CodeBookType loadFromString(String input){
		LOG.info("Executing loadFromString.");
		CodeBookType codeBook = new CodeBookType();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CodeBookType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			codeBook = (CodeBookType) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(input.getBytes())));			
					} catch (Exception e) {  
	        LOG.error(e);  
	    } 
		return codeBook;
	}

}
