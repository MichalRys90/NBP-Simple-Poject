package com.nbp.NBP.mapper;

import com.nbp.NBP.domain.Computer;
import com.nbp.NBP.domain.Item;
import org.springframework.stereotype.Service;

@Service
public class ComputerMapper {

    public Computer mapToComputer(Item item) {
        return new Computer(
                item.getName(),
                item.getPostingDate().toString(),
                item.getCostUsd(),
                item.getCostPln()
        );
    }
}
