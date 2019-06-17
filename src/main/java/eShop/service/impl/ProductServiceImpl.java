package eShop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import eShop.entity.Category;
import eShop.entity.Producer;
import eShop.entity.Product;
import eShop.exception.ServerSqlException;
import eShop.form.SearchForm;
import eShop.jdbc.JdbcUtils;
import eShop.jdbc.ResultSetHandler;
import eShop.jdbc.ResultSetHandlerFactory;
import eShop.service.ProductService;

class ProductServiceImpl implements ProductService {

	private static final ResultSetHandler<List<Product>> productResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);
	private static final ResultSetHandler<List<Category>> categoryResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER);
	private static final ResultSetHandler<List<Producer>> producerResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.PRODUCER_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Integer> countItemsResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.COUNT_ITEMS_RESULT_SET_HANDLER);
	private final DataSource dataSource;
	
	

	public ProductServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}



	@Override
	public List<Product> allProductsList(int page, int limit) {
		try (Connection conn = dataSource.getConnection()) {
			int offset = (page - 1) * limit;

			String sqlSelect = "SELECT p.*, pr.producer, c.category " + "FROM product p "
					+ "JOIN producer pr ON p.id_producer = pr.id " + "JOIN category c ON p.id_category = c.id "
					+ "ORDER BY p.id "
					+ "LIMIT ? OFFSET ?";

			return JdbcUtils.selectQuery(conn, sqlSelect, productResultSetHandler, limit, offset);
		} catch (SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}


/**
 * @param categoryUrl must have format {products/xxx}
 */
	@Override
	public List<Product> productsByCategoryList(String categoryUrl, int page, int limit) {
		try(Connection conn = dataSource.getConnection()){
			int offset = (page - 1) * limit;
			
			String sqlSelect = "SELECT p.*, pr.producer, c.category " + 
					"FROM product p " + 
					"JOIN producer pr ON p.id_producer = pr.id " + 
					"JOIN category c ON p.id_category = c.id " + 
					"WHERE c.url = ? " + 
					"LIMIT ? OFFSET ?";
			return JdbcUtils.selectQuery(conn, sqlSelect, productResultSetHandler, categoryUrl, limit, offset);
		}catch(SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}



	@Override
	public List<Category> allCategoriesList() {
		try(Connection conn = dataSource.getConnection()){	
			
			String sqlSelect = "SELECT * " + 
					"FROM category " +
					"ORDER BY category";
			return JdbcUtils.selectQuery(conn, sqlSelect, categoryResultSetHandler);
		}catch(SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}



	@Override
	public List<Producer> allProducersList() {
		try(Connection conn = dataSource.getConnection()){	
			String sqlSelect = "SELECT * " + 
					"FROM producer "+ 
					"ORDER BY producer";
			return JdbcUtils.selectQuery(conn, sqlSelect, producerResultSetHandler);
		}catch(SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}
	
	@Override
	public int countAllProducts() {
		try(Connection conn = dataSource.getConnection()){	
			String sqlSelect = "SELECT COUNT(*) " + 
					"FROM product";
			return JdbcUtils.selectQuery(conn, sqlSelect, countItemsResultSetHandler);
		}catch(SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}

	@Override
	public int countProductsByCategory(String category) {
		try(Connection conn = dataSource.getConnection()){	
			String sqlSelect = "SELECT product_count " + 
					"FROM category " + 
					"WHERE url = ?";
			return JdbcUtils.selectQuery(conn, sqlSelect, countItemsResultSetHandler, category);
		}catch(SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}



	@Override
	public List<Product> productsBySearch(SearchForm searchForm, int page, int limit) {
		try (Connection conn = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			
			String sqlSelect = "SELECT p.*, pr.producer, c.category "
					+ "FROM product p "
					+ "JOIN producer pr ON p.id_producer = pr.id "
					+ "JOIN category c ON p.id_category = c.id "
					+ "WHERE (p.name like ? or p.description like ?)"
					+ convertListToStringWithInOperator(searchForm.getCategories(), "c.id")
					+ convertListToStringWithInOperator(searchForm.getProducers(), "pr.id")
					+ "ORDER BY p.id "
					+ "LIMIT ? OFFSET ?";

			return JdbcUtils.selectQuery(conn, sqlSelect, productResultSetHandler, "%" + searchForm.getQuery() + "%", 
					"%" + searchForm.getQuery() + "%", limit, offset);
		} catch (SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}



	@Override
	public int countProductsBySearchForm(SearchForm searchForm) {
		try(Connection conn = dataSource.getConnection()){
			
			String sqlSelect = "SELECT COUNT(*) "
					+ "FROM product p "
					+ "JOIN producer pr ON p.id_producer = pr.id "
					+ "JOIN category c ON p.id_category = c.id "
					+ "WHERE (p.name like ? or p.description like ?)" 
					+ convertListToStringWithInOperator(searchForm.getCategories(), "c.id")
					+ convertListToStringWithInOperator(searchForm.getProducers(), "pr.id");
			return JdbcUtils.selectQuery(conn, sqlSelect, countItemsResultSetHandler, "%" + searchForm.getQuery() + "%",
					"%" + searchForm.getQuery() + "%");
		}catch(SQLException e) {
			throw new ServerSqlException("Can't execute sql query!", e);
		}
	}
	
	private String convertListToStringWithInOperator(List<Integer> list, String field) {
		StringBuilder sb = new StringBuilder();
		
		if(list.isEmpty()) {
			return "";
		} else {
			sb.append(" AND ").append(field).append(" IN (").append(list.toString().replaceAll("(\\[|])", "")).append(") ");
			return sb.toString();
		}
	}
}
