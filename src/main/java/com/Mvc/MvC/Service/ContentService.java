package com.Mvc.MvC.Service;

import com.Mvc.MvC.DTO.ContentDTO;
import com.Mvc.MvC.DTO.ContentResponse;
import com.Mvc.MvC.Model.Content;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContentService {

    ResponseEntity<String> createcontent(Content content);

    ContentResponse getall(int pageno, int pagesize);

    ContentDTO detail(int id);

    ResponseEntity<String>  updatecontent  (int id,Content content);

    ResponseEntity<String> deletecontent (int id);

}
