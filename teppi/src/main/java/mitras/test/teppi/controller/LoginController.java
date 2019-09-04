package mitras.test.teppi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Login Controller
 * 
 * @author TIEP KNIGHT
 *
 */
@Controller
public class LoginController {
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
}
