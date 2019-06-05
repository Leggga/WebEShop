package eShop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import eShop.service.OrderService;
import eShop.service.ProductService;

public class ServiceManager {

	private final ProductService productService;
	private final OrderService orderService;
	private final Properties property;

	public static ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
		if (instance == null) {
			instance = new ServiceManager(context);
			context.setAttribute("SERVICE_MANAGER", instance);
		}
		return instance;
	}

	public void close() {
		// close resources
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public ProductService getProductService() {
		return productService;
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

	private ServiceManager(ServletContext context) {
		this.orderService = new OrderServiceImpl();
		this.productService = new ProductServiceImpl();
		this.property = new Properties();
		loadProperty();
	}

}
