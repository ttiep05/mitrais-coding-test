package mitras.test.teppi;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import mitras.test.teppi.beans.RegistrationDTO;
import mitras.test.teppi.model.AjaxResponseBody;
import mitras.test.teppi.model.Registration;
import mitras.test.teppi.respository.RegistrationRepository;
import mitras.test.teppi.services.RegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegistrationSercieTest {

	@MockBean
	private RegistrationRepository regisRepo;

	@Autowired
	private RegistrationService service;

	@Test
	public void testSaveRegistrationValidateDb() {
		RegistrationDTO dto = mockRegistration();
		Registration regis = mock(Registration.class);
		when(regisRepo.findByPhoneNumber(any(String.class))).thenReturn(regis);
		when(regisRepo.findByEmail(any(String.class))).thenReturn(regis);
		AjaxResponseBody result = service.addRegistration(dto);
		assertEquals(true, result.getMsg().contains("Email should be unique."));
		assertEquals(true, result.getMsg().contains("Mobile number should be unique."));
	}

	@Test
	public void testSaveOk() {
		RegistrationDTO dto = mockRegistration();
		Registration regis = mock(Registration.class);
		when(regisRepo.findByPhoneNumber(any(String.class))).thenReturn(null);
		when(regisRepo.findByEmail(any(String.class))).thenReturn(null);
		when(regisRepo.save(any(Registration.class))).thenReturn(regis);
		AjaxResponseBody result = service.addRegistration(dto);
		assertEquals(null, result.getMsg());
		assertEquals(regis, result.getData());
	}

	private RegistrationDTO mockRegistration() {
		RegistrationDTO mock = new RegistrationDTO();
		mock.setPhoneNumber("0368129588");
		mock.setFirstName("Tiep");
		mock.setLastName("Tran");
		mock.setEmail("ttiep05@gmail.com");
		return mock;
	}

}
