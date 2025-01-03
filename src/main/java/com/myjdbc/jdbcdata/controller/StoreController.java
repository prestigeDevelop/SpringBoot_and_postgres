package com.myjdbc.jdbcdata.controller;

import com.myjdbc.jdbcdata.pcstore.entity.Printer;
import com.myjdbc.jdbcdata.service.PrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/printers")
public class StoreController {

    private final PrinterService printerService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Printer>> getAllPrinters() {
        // This method will return all the printers in the database
        List<Printer> printers = printerService.getAllPrinters();
        return new ResponseEntity<>(printers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Printer> getPrinter(@PathVariable Integer id) {
        //  This method will return the printer with the given id
        return printerService.getPrinterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Printer createPrinter(@RequestBody Printer printer) {
        return printerService.savePrinter(printer);
    }

    @PutMapping("/{model}")
    public ResponseEntity<Printer> updatePrinter(@PathVariable Integer model, @RequestBody Printer printerDetails) {
        try {
            return (ResponseEntity<Printer>) ResponseEntity.ok(printerService.updatePrinter(model, printerDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{model}")
    public ResponseEntity<Void> deletePrinter(@PathVariable Integer model) {
        printerService.deletePrinter(model);
        return ResponseEntity.noContent().build();
    }
}
