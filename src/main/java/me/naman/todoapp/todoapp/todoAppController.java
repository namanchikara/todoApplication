package me.naman.todoapp.todoapp;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class todoAppController {

    private ArrayList <todoListSchema> todoLists = new ArrayList < todoListSchema > ();
    private AtomicInteger listCount = new AtomicInteger();

    @RequestMapping("/")
    public String index() {
        return "Demo";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ResponseEntity < todoSchema > saveToDo(@RequestBody todoSchema payload) {
        System.out.println("Payload to save " + payload.getTask());

        return new ResponseEntity < todoSchema > (payload, HttpStatus.OK);
    }

    @RequestMapping(value = "/newList", method = RequestMethod.POST)
    public ResponseEntity < todoListSchema > newList(@RequestBody todoListSchema payload){
    
        todoLists.add(listCount.get(), new todoListSchema(listCount.getAndIncrement() , payload.getName()));
        return new ResponseEntity < todoListSchema > (todoLists.get(listCount.get()-1), HttpStatus.OK);
    }

}

