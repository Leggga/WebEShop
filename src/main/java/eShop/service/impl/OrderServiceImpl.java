package eShop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import eShop.entity.Product;
import eShop.exception.ServerSqlException;
import eShop.exception.ValidationException;
import eShop.form.ProductForm;
import eShop.jdbc.JdbcUtils;
import eShop.jdbc.ResultSetHandler;
import eShop.jdbc.ResultSetHandlerFactory;
import eShop.model.ShoppingCart;
import eShop.service.OrderService;

class OrderServiceImpl implements OrderService {

	private static final ResultSetHandler<Product> productResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

	private final DataSource dataSource;

	public OrderServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void addProductToCart(ProductForm productForm, ShoppingCart cart) {
		try (Connection conn = dataSource.getConnection()) {
			String sqlSelect = "SELECT p.*, pr.producer, c.category " + "FROM product p "
					+ "JOIN producer pr ON p.id_producer = pr.id " + "JOIN category c ON p.id_category = c.id "
					+ "WHERE p.id = ? ";

			Product product = JdbcUtils.selectQuery(conn, sqlSelect, productResultSetHandler,
					productForm.getIdProduct());
			if (product != null) {
				cart.addProduct(product, productForm.getCount());
			} else {
				throw new ValidationException("Product not found by id = " + productForm.getIdProduct());
			}
		} catch (SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}
	
	@Override
	public void removeProductFromCart(ProductForm productForm, ShoppingCart cart) {
		try (Connection conn = dataSource.getConnection()) {
			String sqlSelect = "SELECT p.*, pr.producer, c.category " + "FROM product p "
					+ "JOIN producer pr ON p.id_producer = pr.id " + "JOIN category c ON p.id_category = c.id "
					+ "WHERE p.id = ? ";

			Product product = JdbcUtils.selectQuery(conn, sqlSelect, productResultSetHandler,
					productForm.getIdProduct());
			if (product != null) {
				cart.removeProduct(product, productForm.getCount());
			} else {
				throw new ValidationException("Product not found by id = " + productForm.getIdProduct());
			}
		} catch (SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}
}
