package com.nbp.NBP.controller;

import com.nbp.NBP.domain.Item;
import com.nbp.NBP.dto.ItemDto;
import com.nbp.NBP.exception.RatesWithGivenDateDoesntExist;
import com.nbp.NBP.mapper.ItemMapper;
import com.nbp.NBP.service.NbpService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/nbp")
@RequiredArgsConstructor
public class NbpController {

    private final NbpService nbpService;
    private final ItemMapper itemMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) throws RatesWithGivenDateDoesntExist, JAXBException {
        Item item = itemMapper.mapToItem(itemDto);
        nbpService.saveItem(item);
        nbpService.createXmlFile(item);
        return ResponseEntity.ok(itemMapper.mapToItemDto(item));
    }

    @GetMapping(value = "getByAny")
    public ResponseEntity<List<ItemDto>> getByAnyName(@RequestParam String any) {
        List<Item> items = nbpService.findForAnyString(any);
        return ResponseEntity.ok(itemMapper.mapToItemDtoList(items));
    }

    @GetMapping(value = "getByDate")
    public ResponseEntity<List<ItemDto>> getByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Item> items = nbpService.findAllByDate(date);
        return ResponseEntity.ok(itemMapper.mapToItemDtoList(items));
    }

    @GetMapping(value = "sortByDate")
    public ResponseEntity<List<ItemDto>> sortByDate() {
        List<Item> items = nbpService.findAllByOrderByDate();
        return ResponseEntity.ok(itemMapper.mapToItemDtoList(items));
    }

    @GetMapping(value = "sortByName")
    public ResponseEntity<List<ItemDto>> sortByName() {
        List<Item> items = nbpService.findAllByOrderByName();
        return ResponseEntity.ok(itemMapper.mapToItemDtoList(items));
    }
}
