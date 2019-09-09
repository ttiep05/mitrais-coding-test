package mitras.test.teppi.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mitras.test.teppi.beans.RegistrationDTO;
import mitras.test.teppi.model.AjaxResponseBody;
import mitras.test.teppi.model.Registration;
import mitras.test.teppi.respository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationRepository registrationRepo;

	@Override
	public AjaxResponseBody addRegistration(RegistrationDTO registration) {

		AjaxResponseBody result = addRegistrationValidation(registration);

		if (!StringUtils.isEmpty(result.getMsg())) {
			return result;
		}

		Registration regis = registrationRepo.save(convertRegistrationFromDTO(registration));
		result.setData(regis);

		return result;
	}

	private AjaxResponseBody addRegistrationValidation(RegistrationDTO registration) {
		AjaxResponseBody result = new AjaxResponseBody();
		if (registrationRepo.findByPhoneNumber(registration.getPhoneNumber()) != null) {
			result.addMsg("Mobile number should be unique.");
		}

		if (registrationRepo.findByEmail(registration.getEmail()) != null) {
			result.addMsg("Email should be unique.");
		}

		return result;
	}

	private Registration convertRegistrationFromDTO(RegistrationDTO registration) {
		Registration model = new Registration();
		BeanUtils.copyProperties(registration, model);
		return model;
	}
}
