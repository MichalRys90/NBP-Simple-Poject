package com.nbp.NBP.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQuery(
        name = "Item.findForAnyString",
        query = "From Item where name LIKE concat('%', :ANYSTRING, '%')"
)
@NamedQuery(
        name = "Item.sortByName",
        query = "From Item Order By name"
)
@NamedQuery(
        name = "Item.sortByPostingDate",
        query = "From Item Order By postingDate"
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ITEM_ID", unique = true)
    private long id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "POSTING_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate postingDate;

    @Column(name = "COST_USD")
    private double costUsd;

    @Column(name = "COST_PLN")
    private double costPln;
}
