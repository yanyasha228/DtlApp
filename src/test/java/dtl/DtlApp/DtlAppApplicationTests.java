package dtl.DtlApp;


import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import dtl.DtlApp.FileUtils.CsvPassengersFileBuilder;
import dtl.DtlApp.Models.Worker;
import dtl.DtlApp.RestDao.GoogleApiRestDao.SheetValuesRestDao;
import dtl.DtlApp.RestDao.GoogleApiRestDao.SpreadsheetRestDao;
import dtl.DtlApp.Services.PassengerSheetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootTest
class DtlAppApplicationTests {

    @Autowired
    private SpreadsheetRestDao spreadSheetRestDao;

    @Autowired
    private SheetValuesRestDao sheetValuesRestDao;

    @Autowired
    private PassengerSheetService passengerSheetService;

    @Autowired
    private CsvPassengersFileBuilder csvPassengersFileBuilder;

    @Test
    void contextLoads() throws IOException, GeneralSecurityException {
        Spreadsheet spreadsheet = new Spreadsheet();
        Worker worker = new Worker();
        worker.setName("Почтар Галина");
        ValueRange valueRange = new ValueRange();
//        List<List<Object>> values = new ArrayList<>();
//        List<Object> val = new ArrayList<>();
//        val.add("Pich");
//        val.add("Pich1");
//        val.add("Pich2");
//        values.add(val);


//        valueRange.setValues(values);
        spreadsheet.setSpreadsheetId("1rGAPbX3TOsaeoQpI4XDaHgWtNpCEA-YC6Lniz5X-VGQ");

//        sheetValuesRestDao.get(spreadsheet, valueRange);
//        passengerSheetService.getPassengersFromSheet(spreadsheet,"SAMPLE");
        String urlToRet = csvPassengersFileBuilder.createCsvByWorker(worker,spreadsheet,1,1);

        int i = 2;
    }

}
