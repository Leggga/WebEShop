package eShop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import eShop.service.OrderService;
import eShop.service.ProductService;
import eShop.service.SocialService;

public class ServiceManager {

	private final Logger LOGGER = Logger.getLogger(getClass());

	private final ProductService productService;
	private final SocialService socialService;
	private final BasicDataSource dataSource;
	private final OrderService orderService;
	private final Properties property;

	private ServiceManager(ServletContext context) {
		this.property = new Properties();
		loadProperty();
		this.dataSource = initDataSource();
		this.orderService = new OrderServiceImpl(dataSource);
		this.productService = new ProductServiceImpl(dataSource);
		this.socialService = new FacebookSocialServiceImpl(this);
	}

	public static ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
		if (instance == null) {
			instance = new ServiceManager(context);
			context.setAttribute("SERVICE_MANAGER", instance);
		}
		return instance;
	}

	public void close() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			LOGGER.error("Can't close dataSource!", e);
		}
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public ProductService getProductService() {
		return productService;
	}
	
	public SocialService getSocialService() {
		return socialService;
	}

	public String getAppProperty(String key) {
		return property.getProperty(key);
	}

	private void loadProperty() {
		try (InputStream input = ServiceManager.class.getResourceAsStream("/application.properties")) {
			property.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Error! Property file hasn't read.");
		}
	}

	private BasicDataSource initDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDefaultAutoCommit(false);
		dataSource.setRollbackOnReturn(true);
		dataSource.setDriverClassName(getAppProperty("db.driver"));
		dataSource.setUrl(getAppProperty("db.url"));
		dataSource.setUsername(getAppProperty("db.username"));
		dataSource.setPassword(getAppProperty("db.password"));
		dataSource.setInitialSize(Integer.parseInt(getAppProperty("db.pool.initSize")));
		dataSource.setMaxTotal(Integer.parseInt(getAppProperty("db.pool.maxSize")));

		return dataSource;
	}

}
