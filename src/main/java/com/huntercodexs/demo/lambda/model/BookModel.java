package com.huntercodexs.demo.lambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {
    private int id;
    private String name;
    private int year;
}
