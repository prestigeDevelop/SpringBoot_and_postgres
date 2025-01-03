package com.myjdbc.jdbcdata.service;

import org.springframework.stereotype.Service;

@Service
public class PrinterService1 {

//    private final PrinterRepository printerRepository;
//
//    @Autowired
//    public PrinterService(PrinterRepository printerRepository) {
//        this.printerRepository = printerRepository;
//    }
//
//    public List<Printer> getAllPrinters() {
//        return printerRepository.findAll();
//    }
//
//    public Optional<Printer> getPrinterById(Integer model) {
//        return printerRepository.findById(model);
//    }
//
//    public Printer savePrinter(Printer printer) {
//        return printerRepository.save(printer);
//    }
//
//    public Printer updatePrinter(Integer model, Printer printerDetails) {
//        return printerRepository.findById(model).map(printer -> {
//            printer.setColor(printerDetails.getColor());
//            printer.setType(printerDetails.getType());
//            printer.setPrice(printerDetails.getPrice());
//            return printerRepository.save(printer);
//        }).orElseThrow(() -> new RuntimeException("Printer not found with model: " + model));
//    }
//
//    public void deletePrinter(Integer model) {
//        printerRepository.deleteById(model);
//    }
}
