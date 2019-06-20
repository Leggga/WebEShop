package eShop;

public final class Constants {

	public static final String CURRENT_SHOPPING_CART = "CURRENT_SHOPPING_CART";
	public static final String USER_CURRENT_LOG = "USER_CERRENT_LOG";
	public static final String CURRENT_ACCOUNT = "CURRENT_ACCOUNT";
	public static final String CLIENT_MESSAGE = "CLIENT_MESSAGE";
	public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
	public static final String CATEGORY_LIST = "CATEGORY_LIST";
	public static final String PRODUCER_LIST = "PRODUCER_LIST";
	public static final String REFERER_PAGE = "REFERER_PAGE";
	public static final int MAX_SHOPPING_CART_ITEMS = 20;
	public static final int MAX_ITEMS_PER_PRODUCT = 10;
	public static final int MAX_PRODUCTS_PER_PAGE = 8;
	public static final int MAX_ORDERS_PER_PAGE = 5;
	
	public enum Cookie {
		SHOPPING_CART("iSCC", 60 * 60 * 24  * 365);
		
		private final String name;
		private final int ttl;
		
		private Cookie(String name , int ttl) {
			this.name = name;
			this.ttl = ttl;
		}
		
		public String getName() {
			return name;
		}
		
		public int getTtl() {
			return ttl;
		}
	}
	
}
