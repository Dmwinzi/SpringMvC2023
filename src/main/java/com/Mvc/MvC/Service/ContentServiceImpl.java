package com.Mvc.MvC.Service;

import com.Mvc.MvC.DTO.ContentDTO;
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
            return ResponseEntity.ok(e.getMessage().toString());
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
