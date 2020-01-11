package me.naman.todoapp.todoapp;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/new-list", method = RequestMethod.POST)
    public ResponseEntity < todoListSchema > newList(@RequestBody todoListSchema payload){
    
        todoLists.add(listCount.get(), new todoListSchema(listCount.getAndIncrement() , payload.getName()));
        return new ResponseEntity < todoListSchema > (todoLists.get(listCount.get()-1), HttpStatus.OK);
    }

    @RequestMapping(value="list/{id}", method=RequestMethod.GET)
	public ResponseEntity<todoListSchema> getAllTodo(@PathVariable("id") int id) throws todoException{
        validateListId(id);
		return new ResponseEntity<todoListSchema>(todoLists.get(id) , HttpStatus.OK);
    }
    
    @RequestMapping(value = "list/{id}/todo/", method = RequestMethod.POST)
    public ResponseEntity < todoListSchema > saveToDo(@RequestBody todoSchema payload, @PathVariable("id") int id) throws todoException {
        
        validateListId(id);
        todoLists.get(id).addTask(payload.getTask());

        return new ResponseEntity < todoListSchema > (todoLists.get(id), HttpStatus.OK);
    }

    @RequestMapping(value="list/{Lid}/todo/{Tid}", method=RequestMethod.GET)
	public ResponseEntity<todoSchema> getTodo(@PathVariable("Lid") int lid, @PathVariable("Tid") int tid) throws todoException{
        validateListId(lid);

		return new ResponseEntity<todoSchema>(todoLists.get(lid).findTodoById(tid) , HttpStatus.OK);
    }
    
    @RequestMapping(value = "list/{Lid}/todo/{Tid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeTodoById(@PathVariable("Lid") int lid, @PathVariable("Tid") int tid) throws todoException{
        validateListId(lid);
        todoLists.get(lid).removeTodoById(tid);
        
        return new ResponseEntity<String>("ToDo has been deleted!", HttpStatus.OK);
    }
    
    @RequestMapping(value = "list/{Lid}/todo/{Tid}", method = RequestMethod.PATCH)
    public ResponseEntity<String> patchTodoById(@RequestBody todoSchema payload, @PathVariable("Lid") int lid, @PathVariable("Tid") int tid) throws todoException{
        validateListId(lid);
        todoLists.get(lid).patchTodo(tid, payload);
        return new ResponseEntity<String>("ToDo has been patched!", HttpStatus.OK);
    }

    @RequestMapping(value = "list/{Lid}/todo/{Tid}", method = RequestMethod.PUT)
    public ResponseEntity<String> putTodoById(@RequestBody todoSchema payload, @PathVariable("Lid") int lid, @PathVariable("Tid") int tid) throws todoException{
        validateListId(lid);
        todoLists.get(lid).putTodo(tid, payload);
        return new ResponseEntity<String>("PUT operation completed successfully!", HttpStatus.OK);
    }

    public boolean validateListId(int id) throws todoException {
        if(id > listCount.get()-1 || todoLists.get(id).isDeleted()){
            throw new todoException("List doesn't exist");
        }
        return true;
    }

}

