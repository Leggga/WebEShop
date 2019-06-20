package eShop.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1536448334575182091L;
	
	private int idAccount;
	private List<OrderItem> items;
	private Timestamp created;
	
	

	public Order(Long id, int idAccount, Timestamp created) {
		this.id = id;
		this.idAccount = idAccount;
		this.created = created;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public BigDecimal getTotalCost() {
		BigDecimal cost = BigDecimal.ZERO;
		if (items != null) {
			for (OrderItem item : items) {
				cost = cost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCountProduct())));
			}
		}
		return cost;
	}
	
	
	@Override
	public String toString() {
		return String.format("Order [idAccount=%s, items=%s, created=%s]", idAccount, items, created);
	}

}
