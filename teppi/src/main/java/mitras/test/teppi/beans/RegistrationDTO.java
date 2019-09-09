package mitras.test.teppi.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationDTO {
	@NotBlank(message = "Mobile number is required.")
	@Pattern(regexp = "[0-9\\.\\-\\s+\\/()]+", message = "Mobile number should validate valid phone number.")
	@Size(min = 10, message = "Mobile number should be at least 10 characters")
	private String phoneNumber;

	@NotBlank(message = "First name is required.")
	private String firstName;

	@NotBlank(message = "Last name is required.")
	private String lastName;

	private String dateOfBirth;

	private String gender;

	@NotBlank(message = "Email is required.")
	@Email
	private String email;

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
