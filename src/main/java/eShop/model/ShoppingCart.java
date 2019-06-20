package eShop.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import eShop.Constants;
import eShop.entity.Product;
import eShop.exception.ValidationException;

public class ShoppingCart {

	private Map<Integer, ShoppingCartItem> cart = new LinkedHashMap<>(
			Constants.MAX_SHOPPING_CART_ITEMS);

	private BigDecimal totalCost = BigDecimal.ZERO;
	private int totalCount = 0;

	public void addProduct(Product product, int count) throws ValidationException {
		checkInputData(product.getId(), count);

		if (!cart.containsKey(product.getId())) {
			cart.put(product.getId(), new ShoppingCartItem(product, count));
		} else {
			ShoppingCartItem item = cart.get(product.getId());
			item.setCount(item.getCount() + count);
		}
		refreshStatistics();
	}

	public void removeProduct(Product product, int count) {
		if (cart.containsKey(product.getId())) {
			ShoppingCartItem item = cart.get(product.getId());
			int diff = item.getCount() - count;

			if (diff > 0) {
				item.setCount(diff);
			} else {
				cart.remove(product.getId());
			}
		}
		refreshStatistics();
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Collection<ShoppingCartItem> getItems() {
		return cart.values();
	}

	private void refreshStatistics() {
		totalCost = BigDecimal.ZERO;
		totalCount = 0;

		for (ShoppingCartItem item : getItems()) {
			totalCount += item.getCount();
			totalCost = totalCost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
		}
	}

	private void checkInputData(int idProduct, int count) throws ValidationException {
		if (count <= 0 || count > Constants.MAX_ITEMS_PER_PRODUCT) {
			throw new ValidationException("Invalid 'count' parameter!");
		}

		if (cart.size() >= Constants.MAX_SHOPPING_CART_ITEMS) {
			throw new ValidationException("The shopping cart is fulled");
		}

		if (cart.containsKey(idProduct) && cart.get(idProduct).getCount() + count > Constants.MAX_ITEMS_PER_PRODUCT) {
			throw new ValidationException("Exceeded limit items per product");
		}
	}

	@Override
	public String toString() {
		return String.format("ShoppingCart [cart=%s, totalCost=%s, totalCount=%s]", cart, totalCost, totalCount);
	}

}
