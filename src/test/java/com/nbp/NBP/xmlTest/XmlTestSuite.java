package com.nbp.NBP.xmlTest;

import com.nbp.NBP.domain.Item;
import com.nbp.NBP.dto.ItemDto;
import com.nbp.NBP.exception.RatesWithGivenDateDoesntExist;
import com.nbp.NBP.mapper.ItemMapper;
import com.nbp.NBP.service.NbpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;

@SpringBootTest
public class XmlTestSuite {

    @Autowired
    private NbpService nbpService;
    @Autowired
    private ItemMapper itemMapper;

    @Test
    void testCreatingXmlFileFor20231003() throws JAXBException, RatesWithGivenDateDoesntExist {
        //Given
        ItemDto itemdto = new ItemDto(1, "Acer", LocalDate.of(2023, 1, 3), 345);
        ItemDto itemdto2 = new ItemDto(2, "Acer", LocalDate.of(2023, 1, 3), 543);
        ItemDto itemdto3 = new ItemDto(3, "Acer", LocalDate.of(2023, 1, 3), 346);
        Item item = itemMapper.mapToItem(itemdto);
        Item item2 = itemMapper.mapToItem(itemdto2);
        Item item3 = itemMapper.mapToItem(itemdto3);

        //When
        nbpService.createXmlFile(item, item2, item3);

        //Then
        nbpService.openXmlFile();
    }

    @Test
    void testCreatingXmlFileFor20231010() throws JAXBException, RatesWithGivenDateDoesntExist {
        //Given
        ItemDto itemdto = new ItemDto(1, "Acer", LocalDate.of(2023, 1, 10), 345);
        ItemDto itemdto2 = new ItemDto(2, "Acer", LocalDate.of(2023, 1, 10), 543);
        ItemDto itemdto3 = new ItemDto(3, "Acer", LocalDate.of(2023, 1, 10), 346);
        Item item = itemMapper.mapToItem(itemdto);
        Item item2 = itemMapper.mapToItem(itemdto2);
        Item item3 = itemMapper.mapToItem(itemdto3);

        //When
        nbpService.createXmlFile(item, item2, item3);

        //Then
        nbpService.openXmlFile();
    }
}
