package dtl.DtlApp.FileUtils;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import dtl.DtlApp.Models.SheetPassenger;
import dtl.DtlApp.Models.Worker;

import java.io.IOException;
import java.util.List;

public interface CsvPassengersFileBuilder {

    String createCsvByWorker(Worker worker , Spreadsheet spreadsheet , int dateBegin , int dayEnd) throws IOException;

    List<String> createCsv(Spreadsheet spreadsheet);

    String createCsvAllPassengersInOneList(Spreadsheet spreadsheet);

}
