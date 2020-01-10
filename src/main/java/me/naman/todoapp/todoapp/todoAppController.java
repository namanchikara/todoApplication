package me.naman.todoapp.todoapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class todoAppController {
    
    @RequestMapping("/index")
    public String index() {
      return "Demo";
    }


}
