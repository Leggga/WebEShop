package eShop.entity;

import eShop.model.CurrentAccount;

public class Account extends AbstractEntity<Integer> implements CurrentAccount {

	private static final long serialVersionUID = 5227982377116367762L;

	private String name;
	private String email;

	public Account(Integer id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Account(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	@Override
	public String getDescription() {
		return name + "(" + email + ")";
	}

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
