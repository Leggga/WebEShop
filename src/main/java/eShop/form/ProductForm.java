package eShop.form;

public class ProductForm {

	private int idProduct;
	private int count;

	public ProductForm(int idProduct, int count) {
		this.idProduct = idProduct;
		this.count = count;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
