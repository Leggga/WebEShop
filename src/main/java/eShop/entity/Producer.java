package eShop.entity;

public class Producer extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 702021432096430060L;

	private String name;
	private int productCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	@Override
	public String toString() {
		return String.format("Producer [name=%s, productCount=%s]", name, productCount);
	}

}
