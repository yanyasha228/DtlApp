package dtl.DtlApp.Services;

import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import dtl.DtlApp.Models.SheetPassenger;

import java.util.List;

public interface PassengerSheetService {

    List<SheetPassenger> getPassengersFromSheet(Spreadsheet spreadsheet , String sheetName);

    List<SheetPassenger> getPassengersFromSpreadsheet(Spreadsheet spreadsheet);

    List<SheetPassenger> getPassengersFromSpreadsheetInDateInterval(Spreadsheet spreadsheet , int dayBegin , int dayEnd);

}
