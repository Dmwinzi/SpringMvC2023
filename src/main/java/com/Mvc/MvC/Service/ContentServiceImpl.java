package com.Mvc.MvC.Service;

import com.Mvc.MvC.DTO.ContentDTO;
import com.Mvc.MvC.Exceptions.ContentNotFound;
import com.Mvc.MvC.Model.Content;
import com.Mvc.MvC.Repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService{

    private Repository repository;


    public ContentServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<String> createcontent(Content content) {
        try{
            repository.save(content);
            return ResponseEntity.ok("Saved succesfully");
        }catch (Exception e){
            return ResponseEntity.ok("Problem saving content"+e.getMessage().toString());
        }
    }

    @Override
    public List<ContentDTO> getall() {
        try{
            List<Content>  contents  = repository.findAll();
           return contents.stream().map(content -> maptoDTO(content)).collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage().toString());
        }
    }

    @Override
    public ContentDTO detail(int id) {
        Content  content  = repository.findById(id).orElseThrow( () -> new ContentNotFound("Content not found"));
        return maptoDTO(content);
    }

    @Override
    public ResponseEntity<String> updatecontent(int id,Content content) {
        Content updatecontent  = repository.findById(id).orElseThrow(() -> new ContentNotFound("Content not found!!"));
        updatecontent.setTitle(content.getTitle());
        updatecontent.setDescription(content.getDescription());
        updatecontent.setStatus(content.getStatus());
        updatecontent.setContentype(content.getContentype());
        updatecontent.setDatecreated(content.getDatecreated());
        updatecontent.setDateupdated(content.getDateupdated());
        updatecontent.setUrl(content.getUrl());
        try{
            repository.save(updatecontent);
             return ResponseEntity.ok("Content updated succesfully");
        }catch (Exception e){
            return ResponseEntity.ok("Problem updating content"+e.getMessage().toString());
        }
    }

    @Override
    public ResponseEntity<String> deletecontent(int id) {
        try{
            repository.deleteById(id);
            return ResponseEntity.ok("Content Deleted Succesfully");
        }catch (Exception e){
            return ResponseEntity.ok("Problem deleteing content"+e.getMessage().toString());
        }

    }


    private ContentDTO maptoDTO(Content content){
        ContentDTO contenttomap  = new ContentDTO();
        contenttomap.setTitle(content.getTitle());
        contenttomap.setDescription(content.getDescription());
        contenttomap.setStatus(content.getStatus());
        contenttomap.setContentype(content.getContentype());
        contenttomap.setDatecreated(content.getDatecreated());
        contenttomap.setDateupdated(content.getDateupdated());
        contenttomap.setUrl(content.getUrl());

        return contenttomap;
    }


    private Content  maptoEntity(ContentDTO contentDTO){
        Content  content  = new Content();
        content.setTitle(contentDTO.getTitle());
        content.setDescription(contentDTO.getDescription());
        content.setStatus(contentDTO.getStatus());
        content.setContentype(contentDTO.getContentype());
        content.setDatecreated(contentDTO.getDatecreated());
        content.setDateupdated(contentDTO.getDateupdated());
        content.setUrl(contentDTO.getUrl());
        return content;
    }

}
