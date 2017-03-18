package qooj.jooq.nesst1;

public class Nest1 {
	private int code;
	private int nameId;
	private Address address;

	public Nest1(int code, int nameId, Address address) {
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
