package tp.web.mvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tp.entity.Compte;
import tp.service.CompteService;

@Controller 
public class HelloWorldCtrl {
	
	@RequestMapping("/hello-world")
	@ResponseBody //si @ResponseBody , génération directe de la réponse
	String say_hello() {
	    return "Hello World!";
	}
}
