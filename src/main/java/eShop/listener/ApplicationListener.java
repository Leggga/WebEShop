package eShop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import eShop.service.impl.ServiceManager;

@WebListener
public class ApplicationListener implements ServletContextListener {

	private static final Logger LOGGER = Logger.getLogger(ApplicationListener.class.getName());
	private ServiceManager serviceManager;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			serviceManager = ServiceManager.getInstance(sce.getServletContext());
		} catch (RuntimeException e) {
			LOGGER.error("Servlet context init failed!", e);
			throw e;
		}
		LOGGER.info("Servlet Context has been initialized!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		serviceManager.close();
		LOGGER.info("Servlet Context destroyed!");
	}

}
