package com.Mvc.MvC.Controller;

import com.Mvc.MvC.DTO.ContentDTO;
import com.Mvc.MvC.Model.Content;
import com.Mvc.MvC.Service.ContentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class Controller {


    private ContentServiceImpl contentServiceimpl;


    public Controller(ContentServiceImpl contentServiceimpl) {
        this.contentServiceimpl = contentServiceimpl;
    }

    @GetMapping("/")
    public String welcome(){
        return "Spring MVC Tutorial";
    }


    @GetMapping("/getall")
    public ResponseEntity<List<ContentDTO>> getall(){
         List<ContentDTO> content = contentServiceimpl.getall();
         return  ResponseEntity.ok(content);
    }


    @PostMapping("/create")
    public ResponseEntity<String> addContent(@Valid @RequestBody Content content){
         return contentServiceimpl.createcontent(content);
    }

     @PutMapping("/update/{id}")
     public ResponseEntity<String> update(@RequestBody Content content,@PathVariable Integer id){

        return ResponseEntity.ok("Updated Succesfully");
     }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deletecontent (@PathVariable  Integer id){
        return ResponseEntity.ok("Deleted Succesfully");
    }



}
