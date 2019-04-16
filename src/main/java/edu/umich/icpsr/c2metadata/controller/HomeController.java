package edu.umich.icpsr.c2metadata.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

import edu.umich.icpsr.c2metadata.service.C2MetadataService;
import edu.umich.icpsr.commons.ServerException;
import edu.umich.icpsr.commons.SpringContext;
import edu.umich.icpsr.commons.ThreadContextVariables;
import edu.umich.icpsr.commons.config.Config;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;
import edu.umich.icpsr.commons.security.SecurityKeys;
import edu.umich.icpsr.commons.security.SecurityService;
import edu.umich.icpsr.commons.security.model.User;
import edu.umich.icpsr.commons.web.util.URLService;

@Controller
public class HomeController {

	private static final Logger LOG = Logger.getLogger(HomeController.class);

	@Autowired
	private Config config;

	@Autowired
	@Qualifier("archonnexDAO")
	private BusinessObjectDAO archonnexDAO;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private URLService urlService;

	@Autowired
	@Qualifier("c2metadataService")
	private C2MetadataService c2metadataService;

	@RequestMapping({"/","/index","/home"})
	public ModelAndView home() {
		return new ModelAndView("index");
	}

	@RequestMapping("/static/{page}")
	public ModelAndView staticPage(@PathVariable String page, HttpServletRequest req) {
		return new ModelAndView(page);
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest httpReq, @RequestParam(value = "request_uri") String request_uri) {
		LOG.info("Executing login method.");
		urlService.checkTrustedURL(request_uri);
		return "redirect:" + request_uri;
	}

	@RequestMapping("/logout")
	public void logout(@RequestParam(required = false) String reqUri, HttpServletRequest httpReq,
			HttpServletResponse response) {
		Config config = SpringContext.getBean(Config.class);
		httpReq.getSession().invalidate();
		try {
			response.sendRedirect(config.getValue("deposit.server.url") + "/oauth/logout?reqUri="
					+ config.getUrlValue("app.url", httpReq));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ServerException(e.getMessage());
		}
	}

	@RequestMapping("/rpxCallback")
	public void rpxCallback(@RequestParam String token, HttpServletRequest httpReq, HttpServletResponse response) {
		EngageService engageService = EngageServiceFactory.getEngageService(janRainConfig());
		UserDataResponse userDataResponse;
		try {
			userDataResponse = engageService.authInfo(token);
			Profile profile = userDataResponse.getProfile();
			String identifier = profile.getIdentifier();
			Map<String, Object> criteriaMap = new HashMap<String, Object>();
			criteriaMap.put("socialId", identifier);
			User user = archonnexDAO.findFirstByCriteria(User.class, criteriaMap);
			if (user == null) {
				user = new User();
				user.copyFrom(profile);
				archonnexDAO.save(user);
			}
			HttpSession session = httpReq.getSession();
			session.setAttribute(SecurityKeys.AUTH_USER.toString(), user);
			ThreadContextVariables.putSessionUser(user);
			session.setAttribute(SecurityKeys.AUTH_USER_JWOT.toString(), securityService.createJWT(user));
			String reqUrl = session.getAttribute("reqUrlPath").toString();
			urlService.checkTrustedURL(reqUrl);
			LOG.info("Redirecting to " + reqUrl);
			response.sendRedirect(reqUrl);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public com.googlecode.janrain4j.conf.Config janRainConfig() {
		com.googlecode.janrain4j.conf.Config janrainCfg = com.googlecode.janrain4j.conf.Config.Builder.build()
				.apiKey(config.getValue("janrain.apiKey")).applicationID(config.getValue("janrain.applicationID"))
				.applicationDomain(config.getValue("janrain.applicationDomain"));
		return janrainCfg;
	}


}
