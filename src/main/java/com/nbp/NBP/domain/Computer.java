package com.nbp.NBP.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlType(propOrder = {"name", "postingDate", "costUsd", "costPln"})
public class Computer {

    private String name;
    private String postingDate;
    private double costUsd;
    private double costPln;
}
