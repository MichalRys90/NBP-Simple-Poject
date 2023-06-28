package com.nbp.NBP.service;

import com.nbp.NBP.client.NbpClient;
import com.nbp.NBP.domain.Computer;
import com.nbp.NBP.domain.Invoice;
import com.nbp.NBP.domain.Item;
import com.nbp.NBP.exception.RatesWithGivenDateDoesntExist;
import com.nbp.NBP.mapper.ComputerMapper;
import com.nbp.NBP.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NbpService {

    private final NbpClient nbpClient;
    private final ItemRepository itemRepository;
    private final ComputerMapper computerMapper;

    public double getRate(LocalDate date) throws RatesWithGivenDateDoesntExist {
        String strDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(strDate);
        String response = nbpClient.getUrl(strDate);
        log.info(response);
        return nbpClient.getDollarRate(strDate).getRates().get(0).getBid();
    }

    public List<Item> findForAnyString(String anyString) {
        return itemRepository.findForAnyString(anyString);
    }

    public List<Item> findAllByOrderByName() {
        return itemRepository.sortByName();
    }

    public List<Item> findAllByOrderByDate() {
        return itemRepository.sortByPostingDate();
    }

    public List<Item> findAllByDate(LocalDate date) {
        return itemRepository.findAllByPostingDate(date);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public void createXmlFile(Item... items) throws JAXBException {
        File file = new File("C:\\dev\\NBP\\src\\main\\resources\\Invoice.xml");
        JAXBContext context = JAXBContext.newInstance(Invoice.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Invoice invoice = new Invoice();
        for (Item item : items) {
            Computer computer = computerMapper.mapToComputer(item);
            invoice.getItemList().add(computer);
        }

        marshaller.marshal(invoice, file);
    }

    public void openXmlFile() throws JAXBException {
        File file = new File("C:\\dev\\NBP\\src\\main\\resources\\Invoice.xml");
        JAXBContext context = JAXBContext.newInstance(Invoice.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Invoice invoice = (Invoice) unmarshaller.unmarshal(file);
        System.out.println(invoice);
    }
}
