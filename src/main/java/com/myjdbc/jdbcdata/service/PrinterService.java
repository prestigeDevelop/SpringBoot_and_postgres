package com.myjdbc.jdbcdata.service;

import com.myjdbc.jdbcdata.pcstore.entity.Printer;
import com.myjdbc.jdbcdata.pcstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrinterService {
    @Autowired
    private StoreRepository storeRepository;

    public PrinterService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Printer savePrinter(Printer printer) {
        storeRepository.save(printer);
        return printer;
    }

    public Optional<Printer> getPrinterById(Integer id) {
        return storeRepository.findById(id);
    }

    public void deletePrinter(int id) {
        storeRepository.deleteById(id);
    }

    public Printer updatePrinter(Integer id, Printer printer) {

        return storeRepository.save(printer);
    }

    /* get all printers list */
    public List<Printer> getAllPrinters() {
        return storeRepository.findAll();
    }
}
