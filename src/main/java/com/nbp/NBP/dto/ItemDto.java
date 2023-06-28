package com.nbp.NBP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private long id;
    private String name;
    private LocalDate postingDate;
    private double costUsd;
    private double costPln;

    public ItemDto(long id, String name, LocalDate postingDate, double costUsd) {
        this.id = id;
        this.name = name;
        this.postingDate = postingDate;
        this.costUsd = costUsd;
    }

    public ItemDto(String name, LocalDate postingDate, double costUsd) {
        this.name = name;
        this.postingDate = postingDate;
        this.costUsd = costUsd;
    }
}
