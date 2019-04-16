package edu.umich.icpsr.c2metadata;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.thread.DefaultThreadPools;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.DispatcherServlet;

import edu.umich.icpsr.commons.SpringContext;
import edu.umich.icpsr.commons.ThreadContextVariables;
import edu.umich.icpsr.commons.camel.EventLogger;
import edu.umich.icpsr.commons.dao.FedoraTransaction;
import edu.umich.icpsr.commons.model.sync.FedoraSyncHandler;
import edu.umich.icpsr.commons.security.SecurityKeys;
import edu.umich.icpsr.commons.security.model.User;

public class AppDispatcherServlet extends DispatcherServlet {

	private static final Logger LOG = Logger.getLogger(AppDispatcherServlet.class);
	private static final long serialVersionUID = -2450331553081180248L;

	@Override
	protected void initFrameworkServlet() throws ServletException {
		super.initFrameworkServlet();
		SpringContext.setContext(getWebApplicationContext());
		// startSeadMessageListener();

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FedoraTransaction.clear();
			String sessionKey = SecurityKeys.AUTH_USER.toString();
			User currUser = (User) request.getSession().getAttribute(sessionKey);
			if (currUser != null) {
				ThreadContextVariables.putSessionUser(currUser);
			}
			super.service(request, response);
		} finally {
			FedoraTransaction.clear();
			ThreadContextVariables.clear();
			FedoraSyncHandler.getCurrent().flush();
		}
	}

	@Override
	public void destroy() {
		try {
			super.destroy();
			PooledConnectionFactory pooledConnectionFactory = (PooledConnectionFactory) SpringContext.getBean(PooledConnectionFactory.class);
			if (pooledConnectionFactory != null) {
				pooledConnectionFactory.stop();
			}
			TaskRunnerFactory defaultTaskRunnerFactory = DefaultThreadPools.getDefaultTaskRunnerFactory();
			if (defaultTaskRunnerFactory != null) {
				defaultTaskRunnerFactory.shutdown();
			}
			DefaultThreadPools.shutdown();
			EventLogger.SYNC_THREAD_POOL.shutdownNow();
			FedoraSyncHandler.SYNC_THREAD_POOL.shutdown();
			SpringContext.setContext(null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
