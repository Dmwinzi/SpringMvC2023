package com.Mvc.MvC.DTO;

import com.Mvc.MvC.Model.Content;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.util.List;

@Data
public class ContentResponse {

    private List<ContentDTO> content;
    private int pageno;
    private int pagesize;
    private long totalelements;
    private long totalpages;
    private boolean last;

}
