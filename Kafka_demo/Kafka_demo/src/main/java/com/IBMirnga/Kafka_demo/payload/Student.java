package com.IBMirnga.Kafka_demo.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student {

    private int id;
    private String firstname;
    private String lastname;

}
