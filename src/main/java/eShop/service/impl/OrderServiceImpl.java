package eShop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import eShop.entity.Account;
import eShop.entity.Order;
import eShop.entity.OrderItem;
import eShop.entity.Product;
import eShop.exception.AccessDeniedExceptioin;
import eShop.exception.InternalServerException;
import eShop.exception.ResourceNotFoundException;
import eShop.exception.ValidationException;
import eShop.form.ProductForm;
import eShop.jdbc.JdbcUtils;
import eShop.jdbc.ResultSetHandler;
import eShop.jdbc.ResultSetHandlerFactory;
import eShop.model.CurrentAccount;
import eShop.model.ShoppingCart;
import eShop.model.ShoppingCartItem;
import eShop.model.SocialAccount;
import eShop.service.OrderService;

class OrderServiceImpl implements OrderService {

	private final Logger LOGGER = Logger.getLogger(getClass());

	private static final ResultSetHandler<Product> productResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);
	private static final ResultSetHandler<eShop.entity.Account> accountResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Integer> accountIdResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.INTEGER_ID_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Long> orderIdResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.LONG_ID_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Order> orderResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);
	private static final ResultSetHandler<List<Order>> ordeListResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);
	private static final ResultSetHandler<List<OrderItem>> orderItemsResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.ORDER_ITEM_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Integer> countOrderResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.INTEGER_ID_RESULT_SET_HANDLER);

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
			throw new InternalServerException("Can't execute sql query!", e);
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
			throw new InternalServerException("Can't execute sql query!", e);
		}
	}

	public String shoppingCartToString(ShoppingCart shoppingCart) {
		StringBuilder sb = new StringBuilder();

		for (ShoppingCartItem item : shoppingCart.getItems()) {
			sb.append(item.getProduct().getId() + "&" + item.getCount() + "/");
		}

		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public ShoppingCart shoppingCartFromString(String cookieValue) throws ValidationException {
		String[] arr = cookieValue.split("/");
		ShoppingCart cart = new ShoppingCart();

		for (String str : arr) {
			try {
				String[] product = str.split("&");
				int idProduct = Integer.parseInt(product[0]);
				int count = Integer.parseInt(product[1]);

				addProductToCart(new ProductForm(idProduct, count), cart);
			} catch (RuntimeException e) {
				LOGGER.error("Can't deserialize the shopping cart!!!", e);
			}
		}
		return cart.getItems().isEmpty() ? null : cart;
	}

	@Override
	public CurrentAccount authentificate(SocialAccount socialAccount) {
		try (Connection conn = dataSource.getConnection()) {
			String select = "SELECT * " + "FROM account " + "WHERE email = ?";
			Account account = JdbcUtils.selectQuery(conn, select, accountResultSetHandler, socialAccount.getEmail());
			if (account == null) {
				account = new Account(socialAccount.getName(), socialAccount.getEmail());
				account.setId(JdbcUtils.insertQuery(conn, "INSERT INTO account(name, email) values (? ,?)",
						accountIdResultSetHandler, account.getName(), account.getEmail()));
				conn.commit();
			}
			return account;
		} catch (SQLException e) {
			throw new InternalServerException("Can't execute sql query!", e);
		}
	}

	@Override
	public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
		if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
			throw new InternalServerException("Can't make an order. Shopping cart is null or empty");
		}
		try (Connection conn = dataSource.getConnection()) {
			Long orderID = JdbcUtils.insertQuery(conn, "INSERT INTO `order` (id_account, date_created) VALUES (?, ?);",
					orderIdResultSetHandler, currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
			JdbcUtils.insertBatch(conn, "INSERT INTO `order_item` (id_order, id_product, count_product) VALUES (?,?,?)",
					toOrderItemParameters(orderID, shoppingCart.getItems()));
			conn.commit();
			return orderID;
		} catch (SQLException e) {
			throw new InternalServerException("Can't execute sql query!", e);
		}
	}

	@Override
	public Order getOrderById(long id, CurrentAccount account) {
		try (Connection conn = dataSource.getConnection()) {
			Order order = JdbcUtils.selectQuery(conn, "Select * FROM `order` WHERE id = ?", orderResultSetHandler, id);
			if (order == null ) {
				throw new ResourceNotFoundException("Not found order by id = " + id);
			}
			if ( account == null) {
				throw new AccessDeniedExceptioin("You don't have permissions view this order!");
			}
			if (order.getIdAccount() != account.getId()) {
				throw new AccessDeniedExceptioin(
						"This account with id " + account.getId() + " isn't owner an order id " + order.getId());
			}

			String sql = "SELECT o.*, p.*, c.category, pr.producer "
					+ "FROM `order_item` o, `product` p, `category` c, `producer` pr "
					+ "WHERE pr.id = p.id_producer and c.id = p.id_category and o.id_product = p.id and o.id_order = ?";
			List<OrderItem> orderList = JdbcUtils.selectQuery(conn, sql, orderItemsResultSetHandler, id);
			order.setItems(orderList);

			return order;
		} catch (SQLException e) {
			throw new InternalServerException("Can't execute sql query!", e);
		}
	}

	@Override
	public List<Order> listMyOrders(CurrentAccount account, int page, int limit) {
		try (Connection conn = dataSource.getConnection()) {
			if (account == null) {
				throw new InternalServerException("Account is NULL");
			}

			int offset = (page - 1) * limit;
			String sql = "Select * FROM `order` WHERE id_account = ? ORDER BY date_created DESC LIMIT ? OFFSET ?";

			List<Order> order = JdbcUtils.selectQuery(conn, sql, ordeListResultSetHandler, account.getId(), limit,
					offset);
			return order;
		} catch (SQLException e) {
			throw new InternalServerException("Can't execute sql query!", e);
		}
	}

	@Override
	public int countMyOrders(CurrentAccount account) {
		try (Connection conn = dataSource.getConnection()) {
			if (account == null) {
				throw new InternalServerException("Account is NULL");
			}
			String sql = "Select COUNT(*) FROM `order` WHERE id_account = ?";
			return JdbcUtils.selectQuery(conn, sql, countOrderResultSetHandler, account.getId());
		} catch (SQLException e) {
			throw new InternalServerException("Can't execute sql query!", e);
		}
	}

	private List<Object[]> toOrderItemParameters(Long idOrder, Collection<ShoppingCartItem> cartItems) {
		List<Object[]> list = new LinkedList<>();
		for (ShoppingCartItem item : cartItems) {
			list.add(new Object[] { idOrder, item.getProduct().getId(), item.getCount() });
		}
		return list;
	}
}
