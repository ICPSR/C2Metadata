package edu.umich.icpsr.c2metadata.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import edu.umich.icpsr.commons.web.freemarker.LocalTemplateLoader;
import edu.umich.icpsr.commons.config.Config;
import edu.umich.icpsr.ddi2.schema.CodeBookType;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;

public class CodebookBuilder {

	private static final Logger LOG = Logger.getLogger(CodebookBuilder.class);

	@Autowired
	private Config config;

	@Autowired
	@Qualifier("freemarkerConfig")
	private FreeMarkerConfigurer freemarkerConfig;

	@Autowired
	@Qualifier("freemarkerConfigLocal")
	private FreeMarkerConfigurer freemarkerConfigLocal;

	public String buildCodebook(CodeBookType codebook) {
		LOG.info("Executing buildCodebook.");
		if(config.getValue("build.environment").equals("local")) {
			return buildCodebookLocally(codebook);
		}
		try {
			Map<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("codebook", codebook);
			Template template = null;
			Configuration cfg = freemarkerConfig.getConfiguration();
			if (config.getValue("freemarker.cache").equals("false")) {
				LOG.info("Clearing freemarker cache.");
				cfg.clearTemplateCache();
			}
			template = cfg.getTemplate(config.getValue("freemarker.template.url"));
			StringWriter out = new StringWriter();
			template.process(pageMap, out);
			String output = out.toString();
			out.close();
			return output;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} 
		return null;
	}

	public String buildCodebookLocally(CodeBookType codebook) {
		LOG.info("Executing buildCodebookLocally.");
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("codebook", codebook);
		Configuration cfg = freemarkerConfigLocal.getConfiguration();
		Template template = null;
		try {
			cfg.clearTemplateCache();
			template = cfg.getTemplate("c2m/default/htmlCodebook");
			StringWriter out = new StringWriter();
			template.process(pageMap, out);
			return out.toString();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	
}
