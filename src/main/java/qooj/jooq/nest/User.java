package qooj.jooq.nest;

public class User {
	private int code;
	private int nameId;
	private Address address;

	public User(int code, int nameId, Address address) {
		super();
		this.code = code;
		this.nameId = nameId;
		this.address = address;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getNameId() {
		return nameId;
	}

	public void setNameId(int nameId) {
		this.nameId = nameId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
