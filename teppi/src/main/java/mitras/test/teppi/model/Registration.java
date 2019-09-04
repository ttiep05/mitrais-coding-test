package mitras.test.teppi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "registration")
public class Registration extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "registration_generator")
	@SequenceGenerator(name = "registration_generator", sequenceName = "registration_sequence", initialValue = 1000)
	private Integer id;

	@NotBlank(message = "Mobile number is required.")
	@Pattern(regexp = "[0-9\\.\\-\\s+\\/()]+", message = "Mobile number should validate valid phone number.")
	@Size(min = 10, message = "Mobile number should be at least 10 characters")
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;

	@NotBlank(message = "First name is required.")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "Last name is required.")
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@NotBlank(message = "Email is required.")
	@Column(name = "email", unique = true)
	@Email
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}