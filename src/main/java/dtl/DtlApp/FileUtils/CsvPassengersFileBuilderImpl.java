package dtl.DtlApp.FileUtils;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import dtl.DtlApp.Models.SheetPassenger;
import dtl.DtlApp.Models.Worker;
import dtl.DtlApp.Services.PassengerSheetService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CsvPassengersFileBuilderImpl implements CsvPassengersFileBuilder {

    @Autowired
    private PassengerSheetService sheetPassengersService;

    private static String[] HEADERS = {"№", "Лист", "Место отправления", "Пункт прибытия", "Имя", "Номер телефона", "Номер билета", "Комментарий", "Менеджер", "Способ олпаты", "Стоимость"};

    @Value("${upload.csvStore.salary.path}")
    private String uploadCsvPath;

//    @Override
//    public String createCsv(Boolean availability) throws IOException {
//        return writeCsv(getActiveProductsStringRepresentation(availability));
//    }
//
//    @Override
//    public String createCsv(Group group, Boolean availability) throws IOException {
//        return writeCsv(getActiveProductsByGroupStringRepresentation(group, availability));
//    }
//

    @Override
    public String createCsvByWorker(Worker worker, Spreadsheet spreadsheet, int dateBegin, int dayEnd) throws IOException {
        return writeCsv(getSheetPassengersStringRepresentationByWorker(worker, spreadsheet, dateBegin, dayEnd), worker);
    }

    private String writeCsv(List<List<String>> productsStringRepresentation, Worker worker) throws IOException {

        String genFileName = uploadCsvPath + worker.getName() + "_" + LocalDate.now() + "_" + "ЗП" + ".csv";
        File nFile = new File(genFileName);
        FileWriter out = new FileWriter(nFile);

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            productsStringRepresentation.forEach(list -> {
                try {
                    printer.printRecord(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return genFileName;
    }

    //    private List<List<String>> getSheetPassengersStringRepresentationGroupedByWorkers() {
//
//        Map<Group, List<Product>> groupProductMap = new HashMap<>();
//
//        List<Group> allGroups = groupService.findAll();
//
//        for (Group gr : allGroups) {
//
//            List<Product> productsForMap;
//
//            if (Objects.isNull(availability)) {
//                productsForMap = productService.findAllByGroup(gr);
//            } else {
//                productsForMap = productService.findAllByGroupAndAvailability(gr, availability);
//            }
//
//            if (productsForMap.size() > 0) {
//                productsForMap.sort(Comparator.comparing(Product::getName));
//            }
//
//            groupProductMap.put(gr, productsForMap);
//
//        }
//
//
//        List<List<String>> productFieldRepresentation = new ArrayList<>();
//
//        int number = 1;
//        for (Map.Entry<Group, List<Product>> entry : groupProductMap.entrySet()) {
//            if (entry.getValue().size() == 0) continue;
//
//            List<String> title = new ArrayList<>();
//            title.add("---");
//            title.add("---------------------------" + entry.getKey().getName() + "---------------------------");
//            title.add("---");
//            title.add("---");
//            title.add("---");
//            title.add("----------------");
//
//            List<String> titleSpacing = new ArrayList<>();
//            titleSpacing.add("");
//            titleSpacing.add("");
//            titleSpacing.add("");
//            titleSpacing.add("");
//            titleSpacing.add("");
//            productFieldRepresentation.add(titleSpacing);
//            productFieldRepresentation.add(title);
//            productFieldRepresentation.add(titleSpacing);
//            for (Product prToRep : entry.getValue()) {
//                List<String> listToAdd = new ArrayList<>();
//                listToAdd.add(String.valueOf(number));
//                listToAdd.add(removeAllExChars(prToRep.getName()));
//                listToAdd.add(String.valueOf(prToRep.getAmount()));
//                listToAdd.add(" ");
//                listToAdd.add(" ");
//                listToAdd.add(String.valueOf(prToRep.getId()));
//                productFieldRepresentation.add(listToAdd);
//                number++;
//            }
//        }
//
//
//        return productFieldRepresentation;
//
//    }

    

    private List<List<String>> getSheetPassengersStringRepresentationByWorker(Worker worker, Spreadsheet spreadsheet, int dateBegin, int dateEnd) {

        List<SheetPassenger> passengerList = sheetPassengersService.getPassengersFromSpreadsheetInDateInterval(spreadsheet, dateBegin, dateEnd)
                .stream()
                .filter(sheetPassenger -> sheetPassenger.getManager().equalsIgnoreCase(worker.getName())).sorted(Comparator.comparing(SheetPassenger::getSheetTitle)).collect(Collectors.toList());


        List<List<String>> passengerFieldRepresentation = new ArrayList<>();

        int number = 1;
        for (SheetPassenger ps : passengerList) {
//            if (entry.getValue().size() == 0) continue;
            List<String> listToAdd = new ArrayList<>();
            listToAdd.add(String.valueOf(number));
            listToAdd.add(removeAllExChars(ps.getSheetTitle()));
            listToAdd.add(ps.getDeparturePoint());
            listToAdd.add(ps.getPlaceOfArrival());
            listToAdd.add(removeAllExChars(ps.getName()));
            listToAdd.add(ps.getPhoneNumber());
            listToAdd.add(removeAllExChars(ps.getTicketId()));
            listToAdd.add(removeAllExChars(ps.getComment()));
            listToAdd.add(ps.getManager());
            listToAdd.add(ps.getPaymentOption());
            listToAdd.add(ps.getPrice());
            passengerFieldRepresentation.add(listToAdd);
            number++;

        }


        return passengerFieldRepresentation;

    }

//    private List<List<String>> getActiveProductsByGroupStringRepresentation(Group group, Boolean availability) {
//
//        List<Product> productList;
//        if (Objects.isNull(availability)) {
//            productList = productService.findAllByGroup(group);
//        } else {
//            productList = productService.findAllByGroupAndAvailability(group, availability);
//        }
//
//        List<List<String>> productFieldRepresentation = new ArrayList<>();
//
//        if (productList.size() > 0) {
//            productList.sort(Comparator.comparing(Product::getName));
//        }
//
//        List<String> title = new ArrayList<>();
//        title.add("---");
//        title.add("---------------------------" + group.getName() + "---------------------------");
//        title.add("---");
//        title.add("---");
//        title.add("---");
//        title.add("----------------");
//        productFieldRepresentation.add(title);
//        int number = 1;
//        for (Product prToRep : productList) {
//            List<String> listToAdd = new ArrayList<>();
//            listToAdd.add(String.valueOf(number));
//            listToAdd.add(removeAllExChars(prToRep.getName()));
//            listToAdd.add(String.valueOf(prToRep.getAmount()));
//            listToAdd.add(" ");
//            listToAdd.add(" ");
//            listToAdd.add(String.valueOf(prToRep.getId()));
//            productFieldRepresentation.add(listToAdd);
//            number++;
//        }
//
//
//        return productFieldRepresentation;
//
//    }


    private boolean isNumber(String numberString) {
        try {
            Long.valueOf(numberString);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }


    private String removeAllExChars(String str) {
        String strToRet = str.replaceAll(",", " ");
        strToRet = strToRet.replaceAll(";", " ");

        return strToRet;
    }


    @Override
    public List<String> createCsv(Spreadsheet spreadsheet) {
        return null;
    }

    @Override
    public String createCsvAllPassengersInOneList(Spreadsheet spreadsheet) {
        return null;
    }
}
