package com.nbp.NBP.databaseTest;

import com.nbp.NBP.controller.NbpController;
import com.nbp.NBP.domain.Item;
import com.nbp.NBP.dto.ItemDto;
import com.nbp.NBP.exception.RatesWithGivenDateDoesntExist;
import com.nbp.NBP.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class DatabaseTestSuite {

    @Autowired
    private NbpController nbpController;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void addingComputers() throws RatesWithGivenDateDoesntExist, JAXBException {
        ItemDto itemDto = new ItemDto("Dell", LocalDate.of(2023, 1, 3), 345);
        ItemDto itemDto2 = new ItemDto("Samsung", LocalDate.of(2023, 1, 3), 543);
        ItemDto itemDto3 = new ItemDto("Acer", LocalDate.of(2023, 1, 3), 346);

        ItemDto itemDto4 = new ItemDto("Dell", LocalDate.of(2023, 1, 10), 345);
        ItemDto itemDto5 = new ItemDto("Samsung", LocalDate.of(2023, 1, 10), 543);
        ItemDto itemDto6 = new ItemDto("Acer", LocalDate.of(2023, 1, 10), 346);

        nbpController.addItem(itemDto);
        nbpController.addItem(itemDto2);
        nbpController.addItem(itemDto3);
        nbpController.addItem(itemDto4);
        nbpController.addItem(itemDto5);
        nbpController.addItem(itemDto6);
    }

    @Test
    public void testForGetByAnyString() {
        //Given && When
        ResponseEntity<List<ItemDto>> list = nbpController.getByAnyName("e");
        for (ItemDto itemDto : Objects.requireNonNull(list.getBody())) {
            System.out.println(itemDto);
        }

        //Then
        assertEquals(200, list.getStatusCode().value());
        assertEquals(4, Objects.requireNonNull(list.getBody()).size());
    }

    @Test
    public void testForGetByDate() {
        //Given && When
        ResponseEntity<List<ItemDto>> list = nbpController.getByDate(LocalDate.of(2023, 1, 10));
        for (ItemDto itemDto : Objects.requireNonNull(list.getBody())) {
            System.out.println(itemDto);
        }

        //Then
        assertEquals(200, list.getStatusCode().value());
        assertEquals(3, Objects.requireNonNull(list.getBody()).size());
    }

    @Test
    public void testForSortByDate() throws RatesWithGivenDateDoesntExist, JAXBException {
        //Given
        ItemDto itemDto = new ItemDto("Lenovo", LocalDate.of(2015, 5, 12), 423);
        nbpController.addItem(itemDto);
        List<Item> list2 = (List<Item>) itemRepository.findAll();
        for (Item item : list2) {
            System.out.println(item);
        }

        //When
        ResponseEntity<List<ItemDto>> list = nbpController.sortByDate();
        for (ItemDto itemDtos : Objects.requireNonNull(list.getBody())) {
            System.out.println(itemDtos);
        }

        //Then
        assertEquals(200, list.getStatusCode().value());
        assertEquals(LocalDate.of(2015, 5, 12), Objects.requireNonNull(list.getBody()).get(0).getPostingDate());
    }

    @Test
    public void testForSortByName() {
        //Given
        List<Item> list2 = (List<Item>) itemRepository.findAll();
        for (Item item : list2) {
            System.out.println(item);
        }

        //When
        ResponseEntity<List<ItemDto>> list = nbpController.sortByName();
        for (ItemDto itemDtos : Objects.requireNonNull(list.getBody())) {
            System.out.println(itemDtos);
        }

        //Then
        assertEquals(200, list.getStatusCode().value());
        assertEquals(("Acer"), Objects.requireNonNull(list.getBody()).get(0).getName());
    }
}
