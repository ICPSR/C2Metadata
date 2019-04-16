package edu.umich.icpsr.c2metadata.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.umich.icpsr.c2metadata.service.PseudocodeServiceImpl;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;

@Controller
public class PseudocodeController {

	private static final Logger LOG = Logger.getLogger(PseudocodeController.class);

	@Autowired
	private PseudocodeServiceImpl pseudocodeServiceImpl;
		
	@Autowired
	@Qualifier("archonnexDAO")
	private BusinessObjectDAO archonnexDAO;

	@RequestMapping(value="/pseudocode", method = RequestMethod.GET)
	public ModelAndView parseSdtl() {
		LOG.info("Executing parseSdtl.");
		ModelAndView mv = new ModelAndView("readable");
		return mv;
	}		

	@RequestMapping(value="/pseudocode", method = RequestMethod.POST)
	public ModelAndView parseSdtl(
			@RequestParam (required = false) MultipartFile sdtlFile,
			HttpServletRequest req, 
			HttpServletResponse resp
			) {
		LOG.info("Executing parseSdtl.");
		ModelAndView mv = new ModelAndView("readable");
		try {
			String sdtlAsString = new String(sdtlFile.getBytes());
			String humanText = pseudocodeServiceImpl.generate(sdtlAsString);
			mv.addObject("input",sdtlAsString);
			mv.addObject("output",humanText);
		} catch(Exception e){ 
			LOG.error(e);
			mv.addObject("message","Unable to parse SDTL into human-readable text.");
		}				
		return mv;
	}		
	
}
