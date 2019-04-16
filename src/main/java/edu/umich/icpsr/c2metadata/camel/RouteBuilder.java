package edu.umich.icpsr.c2metadata.camel;

import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.umich.icpsr.commons.config.Config;

@Component
public class RouteBuilder extends SpringRouteBuilder {

	private static final Logger LOG = Logger.getLogger(RouteBuilder.class);

	@Autowired
	private Config config;
	
	@Autowired
	private C2MetadataListener c2metadataListener;

	@Override
	public void configure() throws Exception {
		from(config.getValue("camel.c2metadata.queue")).bean(c2metadataListener);
		from("direct:c2metadata").to(config.getValue("camel.c2metadata.queue"));		
	}

}