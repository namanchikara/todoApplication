package me.naman.todoapp.todoapp;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "list/{id}/todo/", method = RequestMethod.POST)
    public ResponseEntity < todoListSchema > saveToDo(@RequestBody todoSchema payload, @PathVariable("id") int id) throws todoException {
        if(id > listCount.get()-1 || todoLists.get(id).isDeleted()){
            throw new todoException("List doesn't exist");
        }

        todoLists.get(id).addTask(payload.getTask());

        return new ResponseEntity < todoListSchema > (todoLists.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/new-list", method = RequestMethod.POST)
    public ResponseEntity < todoListSchema > newList(@RequestBody todoListSchema payload){
    
        todoLists.add(listCount.get(), new todoListSchema(listCount.getAndIncrement() , payload.getName()));
        return new ResponseEntity < todoListSchema > (todoLists.get(listCount.get()-1), HttpStatus.OK);
    }



}

