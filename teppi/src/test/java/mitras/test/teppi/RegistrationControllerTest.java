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

import mitras.test.teppi.beans.RegistrationDTO;
import mitras.test.teppi.controller.RegistrationController;
import mitras.test.teppi.model.AjaxResponseBody;
import mitras.test.teppi.model.Registration;
import mitras.test.teppi.services.RegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegistrationControllerTest {

	@MockBean
	private RegistrationService regisService;

	@Autowired
	private RegistrationController controller;

	@Test
	public void testSaveRegistrationsOk() {
		BindingResult errors = mock(BindingResult.class);
		RegistrationDTO regis = mock(RegistrationDTO.class);
		AjaxResponseBody reponse = mockAjaxReponseBody();
		when(regisService.addRegistration(any(RegistrationDTO.class))).thenReturn(reponse);
		ResponseEntity<AjaxResponseBody> result = controller.saveRegistration(regis, errors);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void testSaveRegistrationValidateModel() {
		BindingResult errors = mock(BindingResult.class);
		RegistrationDTO regis = mock(RegistrationDTO.class);
		Mockito.when(errors.hasErrors()).thenReturn(true);
		Mockito.when(errors.getAllErrors()).thenReturn(new ArrayList<ObjectError>());
		ResponseEntity<?> result = controller.saveRegistration(regis, errors);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	private Registration mockRegistration() {
		Registration mock = new Registration();
		mock.setPhoneNumber("0368129588");
		mock.setFirstName("Tiep");
		mock.setLastName("Tran");
		mock.setEmail("ttiep05@gmail.com");
		return mock;
	}

	private AjaxResponseBody mockAjaxReponseBody() {
		AjaxResponseBody mock = new AjaxResponseBody();
		mock.setData(mockRegistration());
		return mock;
	}
}
