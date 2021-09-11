package edu.ncsu.csc216.pack_scheduler.user;

/**
 * User class abstracts shared information from Student and Registrar
 * @author magolden
 * @author gmalbarr
 * @author kbmille6
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's hash password */
	private	 String hashPW;

	/**
	 * User Constructor. Encapsulates firstName, lastName, id, email, and password for Student and Registrar
	 * @param firstName first name of user
	 * @param lastName last name of user
	 * @param id id of user
	 * @param email email of user
	 * @param password password of user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Gets Student's first name
	 * @return firstName
	 */
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	/**
	 * Gets student's last name
	 * @return lastName
	 */
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	/**
	 * Gets student's id
	 * @return id
	 */
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * Gets student email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets student email
	 * @param email the email to set
	 * @throws IllegalArgumentException with the message "Invalid email" if email
	 * is null, empty, doesn't have an '@', doesn't have a '.', or the last '.'
	 * is not after the '@' 
	 */
	public void setEmail(String email) {
		
		if(email == null || "".equals(email) || email.indexOf("@") < 0 || email.indexOf(".") < 0 || email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email"); 
		}
		this.email = email;
	}

	/**
	 * Gets student's hash password
	 * @return the hashPW
	 */
	public String getPassword() {
		return hashPW;
	}

	/**
	 * Sets student's hashPW
	 * @param hashPW the hashPW to set
	 * @throws IllegalArgumentException with the message "Invalid password"
	 * if the password is null or empty
	 */
	public void setPassword(String hashPW) {
		if(hashPW == null || "".equals(hashPW)) {
			throw new IllegalArgumentException("Invalid password"); 
		}
		this.hashPW = hashPW;
	}

	/**
	 * Sets the student's first name
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException with the message "Invalid first name"
	 * if the first name is null or empty
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name"); 
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException with the message "Invalid last name"
	 * if the last name is null or empty
	 */
	public void setLastName(String lastName) {
		if(lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name"); 
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the student's id
	 * @param id the id to set
	 * @throws IllegalArgumentException with the message "Invalid id"
	 * if the id is null or empty
	 */
	public void setId(String id) {
		if(id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id"); 
		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashPW == null) ? 0 : hashPW.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hashPW == null) {
			if (other.hashPW != null)
				return false;
		} else if (!hashPW.equals(other.hashPW))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

}