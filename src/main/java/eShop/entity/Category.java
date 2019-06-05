package eShop.entity;

public class Category extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 2805432901638314333L;

	private String name;
	private String url;
	private int productCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	@Override
	public String toString() {
		return String.format("Category [name=%s, url=%s, productCount=%s]", name, url, productCount);
	}

}
