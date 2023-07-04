package com.Mvc.MvC.DTO;

import com.Mvc.MvC.Model.Status;
import com.Mvc.MvC.Model.Type;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContentDTO{

        @NotEmpty
        String title;
        String description;
        Status status;
        Type contentype;

        LocalDateTime datecreated;
        LocalDateTime dateupdated;
        String url;


}
