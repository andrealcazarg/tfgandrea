package com.example.prueba.model;

import lombok.Data;

@Data

public class PayPal {

    private String currency;
    private String method;
    private String intent;
    private String description;
}
