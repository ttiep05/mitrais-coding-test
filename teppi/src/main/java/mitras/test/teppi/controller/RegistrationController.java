package mitras.test.teppi.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mitras.test.teppi.model.AjaxResponseBody;
import mitras.test.teppi.model.Registration;
import mitras.test.teppi.respository.RegistrationRepository;

/**
 * Registration controller
 * 
 * @author TIEP KNIGHT
 *
 */
@RestController
public class RegistrationController {

	@Autowired
	private RegistrationRepository registrationRepo;

	@PostMapping("/registration")
	public ResponseEntity<?> saveRegistration(@Valid @RequestBody Registration registration, BindingResult errors) {
		AjaxResponseBody result = new AjaxResponseBody();

		if (errors.hasErrors()) {
			result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining("<br />")));
			return ResponseEntity.badRequest().body(result);
		}

		if (registrationRepo.findByPhoneNumber(registration.getPhoneNumber()) != null) {
			result.addMsg("Mobile number should be unique.");
		}

		if (registrationRepo.findByEmail(registration.getEmail()) != null) {
			result.addMsg("Email should be unique.");
		}

		if (!StringUtils.isEmpty(result.getMsg())) {
			return ResponseEntity.badRequest().body(result);
		}

		Registration regis = registrationRepo.save(registration);
		result.setData(regis);

		return ResponseEntity.ok(result);
	}
}
