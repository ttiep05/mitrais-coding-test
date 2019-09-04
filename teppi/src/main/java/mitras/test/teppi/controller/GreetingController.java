package mitras.test.teppi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main Controller Mapping request localhost:8080
 * 
 * @author TIEP KNIGHT
 *
 */

@Controller
public class GreetingController {

	@GetMapping("/")
	public String getMain() {
		return "main";
	}
}
