package edu.umich.icpsr.c2metadata.service;

import java.io.IOException;


import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingInterceptor implements ClientHttpRequestInterceptor  {
	private static final Logger LOG = Logger.getLogger(LoggingInterceptor.class);
	
	
	 @Override
	    public ClientHttpResponse intercept(
	      HttpRequest request, 
	      byte[] body, 
	      ClientHttpRequestExecution execution) throws IOException {
	  
	       // 
	        
	        LOG.debug(request);
	        LOG.debug("The request body is"+body);
	        request.getHeaders().add("Accept", "application/json");
			request.getHeaders().add("Content-Type", "application/x-www-form-urlencoded");
			ClientHttpResponse response = execution.execute(request, body);
			 LOG.debug("The response body is"+response.getBody());
	       // LOG.debug("The response body is"+response.getBody());
	        ///response.getHeaders().add("Foo", "bar");
	        return null;
	    }

}
