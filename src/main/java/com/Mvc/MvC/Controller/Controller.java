package com.Mvc.MvC.Controller;

import com.Mvc.MvC.DTO.ContentDTO;
import com.Mvc.MvC.DTO.ContentResponse;
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
    public ResponseEntity<ContentResponse> getall(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
         ContentResponse content = contentServiceimpl.getall(pageNo, pageSize);
         return  ResponseEntity.ok(content);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ContentDTO> getcontent(@PathVariable int id){
        return ResponseEntity.ok(contentServiceimpl.detail(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> addContent(@Valid @RequestBody Content content){
         return contentServiceimpl.createcontent(content);
    }

     @PutMapping("/update/{id}")
     public ResponseEntity<String> update(@RequestBody Content content,@PathVariable Integer id){
        return contentServiceimpl.updatecontent(id,content);
     }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deletecontent (@PathVariable  Integer id){
        return contentServiceimpl.deletecontent(id);
    }



}
