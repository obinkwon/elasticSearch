package elasticSearch.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "thymeleaf/index";
    }

    @GetMapping("/save")
    public String save() {
        return "thymeleaf/save";
    }
}
