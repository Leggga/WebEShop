package eShop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eShop.entity.Account;
import eShop.entity.Category;
import eShop.entity.Order;
import eShop.entity.OrderItem;
import eShop.entity.Producer;
import eShop.entity.Product;

public class ResultSetHandlerFactory {

	public final static ResultSetHandler<Product> PRODUCT_RESULT_SET_HANDLER = new ResultSetHandler<Product>() {

		@Override
		public Product handle(ResultSet rs) throws SQLException {
			Product p = new Product();
			p.setCategory(rs.getString("category"));
			p.setDescription(rs.getString("description"));
			p.setId(rs.getInt("id"));
			p.setImageLink(rs.getString("image_link"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getBigDecimal("price"));
			p.setProducer(rs.getString("producer"));

			return p;
		}

	};

	public final static ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER = new ResultSetHandler<Category>() {

		@Override
		public Category handle(ResultSet rs) throws SQLException {
			Category category = new Category();
			
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("category"));
			category.setUrl(rs.getString("url"));
			category.setProductCount(rs.getInt("product_count"));

			return category;
		}

	};
	
	public final static ResultSetHandler<Producer> PRODUCER_RESULT_SET_HANDLER = new ResultSetHandler<Producer>() {

		@Override
		public Producer handle(ResultSet rs) throws SQLException {
			Producer producer = new Producer();
			producer.setId(rs.getInt("id"));
			producer.setName(rs.getString("producer"));
			producer.setProductCount(rs.getInt("product_count"));
			
			return producer;
		}

	};
	
	public final static ResultSetHandler<Account> ACCOUNT_RESULT_SET_HANDLER = new ResultSetHandler<Account>() {
		
		@Override
		public Account handle(ResultSet rs) throws SQLException {
			return new Account(rs.getInt(1), rs.getString(2), rs.getString(3));
		}
	};
	
	public final static ResultSetHandler<Integer> INTEGER_ID_RESULT_SET_HANDLER = new ResultSetHandler<Integer>() {

		@Override
		public Integer handle(ResultSet rs) throws SQLException {
			return rs.getInt(1);
		}

	};
	
	public final static ResultSetHandler<Long> LONG_ID_RESULT_SET_HANDLER = new ResultSetHandler<Long>() {

		@Override
		public Long handle(ResultSet rs) throws SQLException {
			return rs.getLong(1);
		}

	};
	
	public final static ResultSetHandler<Integer> COUNT_ITEMS_RESULT_SET_HANDLER = new ResultSetHandler<Integer>() {

		@Override
		public Integer handle(ResultSet rs) throws SQLException {
				try{
					return rs.getInt(1);
				}catch(Exception e) {
					return 1;
				}
		}

	};
	
	public final static ResultSetHandler<Order> ORDER_RESULT_SET_HANDLER = new ResultSetHandler<Order>() {
		@Override
		public Order handle(ResultSet rs) throws SQLException {
			return new Order(rs.getLong(1), rs.getInt(2), rs.getTimestamp(3));
		}
	};
	
	public final static ResultSetHandler<OrderItem> ORDER_ITEM_RESULT_SET_HANDLER = new ResultSetHandler<OrderItem>() {
		
		@Override
		public OrderItem handle(ResultSet rs) throws SQLException {
			OrderItem orderItem = new OrderItem();
			orderItem.setId(rs.getLong(1));
			orderItem.setIdOrder(rs.getLong(2));
			orderItem.setCountProduct(rs.getInt(4));
			orderItem.setProduct(PRODUCT_RESULT_SET_HANDLER.handle(rs));
			
			return orderItem;
		}
	};

	public final static <T> ResultSetHandler<T> getSingleResultSetHandler(
			final ResultSetHandler<T> oneRowResultSetHandler) {
		return new ResultSetHandler<T>() {

			@Override
			public T handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return oneRowResultSetHandler.handle(rs);
				}
				return null;
			}

		};
	}

	public final static <T> ResultSetHandler<List<T>> getListResultSetHandler(
			final ResultSetHandler<T> oneRowResultSetHandler) {
		return new ResultSetHandler<List<T>>() {

			@Override
			public List<T> handle(ResultSet rs) throws SQLException {
				List<T> list = new ArrayList<>();
				while (rs.next()) {
					list.add(oneRowResultSetHandler.handle(rs));
				}

				return list;
			}
		};
	}

}
