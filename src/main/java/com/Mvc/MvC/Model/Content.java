package com.Mvc.MvC.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Usercontent")
public class Content {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;
        @NotEmpty
        String title;
        String description;
        Status status;
        Type contentype;

        LocalDateTime datecreated;
        LocalDateTime dateupdated;
        String url;

}
