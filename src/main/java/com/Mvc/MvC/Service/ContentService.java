package com.Mvc.MvC.Service;

import com.Mvc.MvC.DTO.ContentDTO;
import com.Mvc.MvC.Model.Content;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContentService {

    ResponseEntity<String> createcontent(Content content);

    List<ContentDTO> getall();

}
