package mitras.test.teppi.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mitras.test.teppi.beans.RegistrationDTO;
import mitras.test.teppi.model.AjaxResponseBody;
import mitras.test.teppi.services.RegistrationService;

/**
 * Registration controller
 * 
 * @author TIEP KNIGHT
 *
 */
@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/registration")
	public ResponseEntity<AjaxResponseBody> saveRegistration(@Valid @RequestBody RegistrationDTO registration,
			BindingResult errors) {
		AjaxResponseBody result = null;

		if (errors.hasErrors()) {
			result = new AjaxResponseBody();
			result.setMsg(errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining("<br />")));
			return ResponseEntity.badRequest().body(result);
		}

		result = registrationService.addRegistration(registration);
		if (!StringUtils.isEmpty(result.getMsg())) {
			return ResponseEntity.badRequest().body(result);
		}

		return ResponseEntity.ok(result);
	}
}
