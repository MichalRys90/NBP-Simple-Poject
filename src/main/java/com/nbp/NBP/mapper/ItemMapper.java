package com.nbp.NBP.mapper;

import com.nbp.NBP.domain.Item;
import com.nbp.NBP.dto.ItemDto;
import com.nbp.NBP.exception.RatesWithGivenDateDoesntExist;
import com.nbp.NBP.service.NbpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemMapper {

    private final NbpService nbpService;

    public ItemDto mapToItemDto(final Item item) {

        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getPostingDate(),
                item.getCostUsd(),
                item.getCostPln()
        );
    }

    public List<ItemDto> mapToItemDtoList(final List<Item> items) {
        return items.stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }

    public Item mapToItem(ItemDto itemDto) throws RatesWithGivenDateDoesntExist {

        double plnPrice = nbpService.getRate(itemDto.getPostingDate()) * itemDto.getCostUsd();
        DecimalFormat df = new DecimalFormat("####0.00");
        String temp = df.format(plnPrice).replaceAll(",", ".");
        double price = Double.parseDouble(temp);

        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getPostingDate(),
                itemDto.getCostUsd(),
                price
        );
    }
}
