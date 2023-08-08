package com.example.bankhw.ApiResonse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String response;
    private int status;
}
