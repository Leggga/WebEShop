package eShop.entity;

public class Account extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 5227982377116367762L;

	private String name;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Account [name=%s, email=%s]", name, email);
	}

}
