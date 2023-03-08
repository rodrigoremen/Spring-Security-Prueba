package mx.edu.utez.sda.p1sp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerHome {
    @GetMapping("/drive")
    public String drive() {
        return "drive";
    }
}
