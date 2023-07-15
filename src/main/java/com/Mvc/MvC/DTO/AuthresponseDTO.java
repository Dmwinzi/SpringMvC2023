package com.Mvc.MvC.DTO;

import lombok.Data;

@Data
public class AuthresponseDTO {

    private String accesstoken;
    private String accesstype = "Bearer ";

    public AuthresponseDTO(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}
