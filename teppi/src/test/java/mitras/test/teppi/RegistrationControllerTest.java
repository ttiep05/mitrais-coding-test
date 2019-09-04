package mitras.test.teppi;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import mitras.test.teppi.controller.RegistrationController;
import mitras.test.teppi.model.AjaxResponseBody;
import mitras.test.teppi.model.Registration;
import mitras.test.teppi.respository.RegistrationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegistrationControllerTest {

	@MockBean
	private RegistrationRepository regisRepository;

	@Autowired
	private RegistrationController controller;

	@Test
	public void testSaveRegistrationsOk() {
		BindingResult errors = mock(BindingResult.class);
		Registration regis = mockRegistration();
		when(regisRepository.save(any(Registration.class))).thenReturn(regis);
		ResponseEntity<?> result = controller.saveRegistration(regis, errors);
		AjaxResponseBody bodyResult = (AjaxResponseBody) result.getBody();
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(bodyResult.getData(), regis);
	}

	@Test
	public void testSaveRegistrationValidateModel() {
		BindingResult errors = mock(BindingResult.class);
		Registration regis = mockRegistration();
		Mockito.when(errors.hasErrors()).thenReturn(true);
		Mockito.when(errors.getAllErrors()).thenReturn(new ArrayList<ObjectError>());
		ResponseEntity<?> result = controller.saveRegistration(regis, errors);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testSaveRegistrationValidateDb() {
		BindingResult errors = mock(BindingResult.class);
		Registration regis = mockRegistration();
		when(regisRepository.findByPhoneNumber(any(String.class))).thenReturn(regis);
		when(regisRepository.findByEmail(any(String.class))).thenReturn(regis);
		ResponseEntity<?> result = controller.saveRegistration(regis, errors);
		AjaxResponseBody bodyResult = (AjaxResponseBody) result.getBody();
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(bodyResult.getMsg().contains("Email should be unique."), true);
		assertEquals(bodyResult.getMsg().contains("Mobile number should be unique."), true);
	}

	private Registration mockRegistration() {
		Registration mock = new Registration();
		mock.setPhoneNumber("0368129588");
		mock.setFirstName("Tiep");
		mock.setLastName("Tran");
		mock.setEmail("ttiep05@gmail.com");
		return mock;
	}
}
