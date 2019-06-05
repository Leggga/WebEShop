package eShop.entity;

public class OrderItem extends AbstractEntity<Long> {

	private static final long serialVersionUID = -3475827384848848022L;

	private long idOrder;
	private Product product;
	private int countProduct;

	public long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCountProduct() {
		return countProduct;
	}

	public void setCountProduct(int countProduct) {
		this.countProduct = countProduct;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return String.format("OrderItems [idOrder=%s, idProduct=%s, countProduct=%s]", idOrder, product,
				countProduct);
	}

}
