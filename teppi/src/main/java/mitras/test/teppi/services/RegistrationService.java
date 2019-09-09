package mitras.test.teppi.services;

import mitras.test.teppi.beans.RegistrationDTO;
import mitras.test.teppi.model.AjaxResponseBody;

public interface RegistrationService {

	AjaxResponseBody addRegistration(RegistrationDTO registration);

}
