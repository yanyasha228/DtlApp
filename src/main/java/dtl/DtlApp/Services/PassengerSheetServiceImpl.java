package dtl.DtlApp.Services;

import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import dtl.DtlApp.Models.SheetPassenger;
import dtl.DtlApp.RestDao.GoogleApiRestDao.SheetValuesRestDao;
import dtl.DtlApp.RestDao.GoogleApiRestDao.SpreadsheetRestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PassengerSheetServiceImpl implements PassengerSheetService {

    @Autowired
    private SheetValuesRestDao sheetValuesRestDao;

    private static final Logger logger = LoggerFactory.getLogger(PassengerSheetServiceImpl.class);

    @Autowired
    private SpreadsheetRestDao spreadsheetRestDao;

    private final String driverRangeA1 = "Z2";

    private final String autoRangeA1 = "Y2";

    private int possiblePlacesAmount = 80;

    private List<String> passengersColumnRanges = new ArrayList<>(Arrays.asList("D:F", "H:J", "L:N"));

    @Override
    public List<SheetPassenger> getPassengersFromSheet(Spreadsheet spreadsheet, String sheetName) {

        List<SheetPassenger> sheetPassengers = new ArrayList<>();
//        String sheetName = encodeRequestReservedSymbols(sheetName);
        ValueRange driverValueRange = new ValueRange();
        driverValueRange.setRange(sheetName + "!" + driverRangeA1);
        ValueRange autoValueRange = new ValueRange();
        autoValueRange.setRange(sheetName + "!" + autoRangeA1);

        Optional<ValueRange> valueRangeAutoOpt = sheetValuesRestDao.get(spreadsheet, autoValueRange);
        Optional<ValueRange> valueRangeDriveOpt = sheetValuesRestDao.get(spreadsheet, driverValueRange);
        if (valueRangeAutoOpt.isPresent() && valueRangeDriveOpt.isPresent()) {


            if (!Objects.isNull(valueRangeAutoOpt.get().getValues()) && !Objects.isNull(valueRangeDriveOpt.get().getValues())) {
                List<String> passA1s = generatePassengerRangesA1Notations();

                for (String passA1 : passA1s) {
                    String sheetPassA1 = sheetName + "!" + passA1;
                    ValueRange valueRange = new ValueRange();
                    valueRange.setRange(sheetPassA1);

                    Optional<ValueRange> valueRangeFromSheetOpt = sheetValuesRestDao.get(spreadsheet, valueRange);
                    logger.error("----" + passA1);
                    System.out.println("----" + passA1);
                    if (valueRangeFromSheetOpt.isPresent()) {
                        List<List<Object>> valuesArrRep = valueRangeFromSheetOpt.get().getValues();
                        if (Objects.isNull(valuesArrRep)) continue;
                        if (valuesArrRep.get(0).size() > 2 && valuesArrRep.get(1).size() == 1 && valuesArrRep.size() >= 9) {
                            SheetPassenger passenger = new SheetPassenger();
                            passenger.setDeparturePoint(valuesArrRep.get(0).get(0).toString());
                            passenger.setPlaceOfArrival(valuesArrRep.get(0).get(2).toString());
                            passenger.setPhoneNumber((valuesArrRep.get(1).size() < 1) ? "" : valuesArrRep.get(1).get(0).toString());
                            passenger.setPaymentOption((valuesArrRep.get(2).size() < 1) ? "" : valuesArrRep.get(2).get(0).toString());
                            passenger.setName((valuesArrRep.get(3).size() < 1) ? "" : valuesArrRep.get(3).get(0).toString());
                            passenger.setPrice((valuesArrRep.get(4).size() < 1) ? "" : valuesArrRep.get(4).get(0).toString());
                            passenger.setTicketId((valuesArrRep.get(5).size() < 1) ? "" : valuesArrRep.get(5).get(0).toString());
                            passenger.setComment((valuesArrRep.get(6).size() < 1) ? "" : valuesArrRep.get(6).get(0).toString());
                            passenger.setManager((valuesArrRep.get(7).size() < 1) ? "" : valuesArrRep.get(7).get(0).toString());
                            passenger.setSource((valuesArrRep.get(8).size() < 1) ? "" : valuesArrRep.get(8).get(0).toString());
                            passenger.setPromoId("");
                            passenger.setSheetTitle(sheetName);

                            sheetPassengers.add(passenger);
                        }

                    }
                }

            }
        }
        return sheetPassengers;
    }


    private List<String> generatePassengerRangesA1Notations() {
        int startRowIndex = 2;
        int endRowIndex = 11;
        int distanceBetweenIdentRows = 11;

        List<String> passengerRangesA1Notations = new ArrayList<>();

        for (int i = 0; i <= possiblePlacesAmount - 1; i++) {
            for (int c = 0; c <= passengersColumnRanges.size() - 1; c++) {
                String passengerColumnRange = passengersColumnRanges.get(c);
                String[] splitarr = passengerColumnRange.split(":");
                String firstColumnA1 = splitarr[0];
                String secondColumnA1 = splitarr[1];
                String rowIndexFirst = String.valueOf(startRowIndex + (distanceBetweenIdentRows * i));
                String rowIndexSecond = String.valueOf(endRowIndex + (distanceBetweenIdentRows * i));
                String finA1First = firstColumnA1 + rowIndexFirst;
                String finA1Second = secondColumnA1 + rowIndexSecond;
                String finA1 = finA1First + ":" + finA1Second;

                passengerRangesA1Notations.add(finA1);
            }
        }
        return passengerRangesA1Notations;
    }

    @Override
    public List<SheetPassenger> getPassengersFromSpreadsheet(Spreadsheet spreadsheet) {

        Optional<Spreadsheet> spreadFreshOpt = spreadsheetRestDao.getById(spreadsheet.getSpreadsheetId());
        List<SheetPassenger> passengersToRet = new ArrayList<>();
        if (spreadFreshOpt.isPresent()) {
            Spreadsheet spreadFresh = spreadFreshOpt.get();
            List<Sheet> sheetList = spreadFresh.getSheets();
            List<Sheet> filteredSheetList = sheetList.stream().filter(sheet -> {
                String sheetName = sheet.getProperties().getTitle();
                String[] splitedName = sheetName.split("_");
                if (splitedName.length > 1) {
                    return checkIfStringIsInteger(splitedName[0]);
                }
                return false;
            }).collect(Collectors.toList());

            for (Sheet sh : filteredSheetList) {
                passengersToRet.addAll(getPassengersFromSheet(spreadsheet, sh.getProperties().getTitle()));
            }

        }


        return passengersToRet;
    }

    @Override
    public List<SheetPassenger> getPassengersFromSpreadsheetInDateInterval(Spreadsheet spreadsheet, int dayBegin, int dayEnd) {
        Optional<Spreadsheet> spreadFreshOpt = spreadsheetRestDao.getById(spreadsheet.getSpreadsheetId());
        List<SheetPassenger> passengersToRet = new ArrayList<>();
        if (spreadFreshOpt.isPresent()) {
            Spreadsheet spreadFresh = spreadFreshOpt.get();
            List<Sheet> sheetList = spreadFresh.getSheets();
            List<Sheet> filteredSheetList = sheetList.stream().filter(sheet -> {
                String sheetName = sheet.getProperties().getTitle();
                String[] splitedName = sheetName.split("_");
                if (splitedName.length > 1) {
                    if (checkIfStringIsInteger(splitedName[0])) {
                        return checkIfNumberInInterval(Integer.parseInt(splitedName[0]), dayBegin, dayEnd);
                    }
                }
                return false;
            }).collect(Collectors.toList());

            for (Sheet sh : filteredSheetList) {
                System.out.println("---Sheet : " + sh.getProperties().getTitle());
                logger.error("---Sheet : " + sh.getProperties().getTitle());
                passengersToRet.addAll(getPassengersFromSheet(spreadsheet, sh.getProperties().getTitle()));
            }

        }
        return passengersToRet;
    }

    private boolean checkIfNumberInInterval(int num, int begin, int end) {
        return (num >= begin && num <= end);
    }

    private String encodeRequestReservedSymbols(String strToValidation) {
        String stringToRet = strToValidation.replaceAll("_", "%5F")
                .replaceAll("\\(", "%28")
                .replaceAll("\\)", "%29")
                .replaceAll("-", "%2D");

        return stringToRet;
    }

    private boolean checkIfStringIsInteger(String strForVal) {
        try {
            Integer.parseInt(strForVal);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
