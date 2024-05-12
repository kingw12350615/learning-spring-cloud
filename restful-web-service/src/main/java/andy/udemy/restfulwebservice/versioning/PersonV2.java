package andy.udemy.restfulwebservice.versioning;

public class PersonV2 {

	private Name name;


	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}


	public void setName(Name name) {
		this.name = name;
	}


	public static class Name{
		private String firstName;
		private String lastName;


		public Name(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	}

}
