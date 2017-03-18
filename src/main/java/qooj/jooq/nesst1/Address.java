package qooj.jooq.nesst1;

import org.jooq.Converter;

public class Address {
	private String value;

	public Address(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address)obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public static AddressConverter converter() {
		return new AddressConverter();
	}

	public static class AddressConverter implements Converter<String, Address> {
		private static final long serialVersionUID = 1L;

		@Override
		public Address from(String databaseObject) {
			return new Address(databaseObject);
		}

		@Override
		public String to(Address userObject) {
			return userObject.getValue();
		}

		@Override
		public Class<String> fromType() {
			return String.class;
		}

		@Override
		public Class<Address> toType() {
			return Address.class;
		}
	}
}
