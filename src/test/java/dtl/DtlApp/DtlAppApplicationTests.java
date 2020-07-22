package dtl.DtlApp;


import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import dtl.DtlApp.FileUtils.CsvPassengersFileBuilder;
import dtl.DtlApp.Models.SheetPassenger;
import dtl.DtlApp.Models.Worker;
import dtl.DtlApp.RestDao.GoogleApiRestDao.SheetValuesRestDao;
import dtl.DtlApp.RestDao.GoogleApiRestDao.SpreadsheetRestDao;
import dtl.DtlApp.Services.PassengerSheetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

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
//        valueRange.setRange("23_Х-О (18:30)!D662:F671");
        valueRange.setRange("23_Х-О (18:30)!D2:F11");
//        List<List<Object>> values = new ArrayList<>();
//        List<Object> val = new ArrayList<>();
//        val.add("Pich");
//        val.add("Pich1");
//        val.add("Pich2");
//        values.add(val);


//        valueRange.setValues(values);
        spreadsheet.setSpreadsheetId("1Be4I1yOr9PqHpye6v0F3aAPZkedoUk7KiGsunEAyk6U");

//        sheetValuesRestDao.get(spreadsheet, valueRange);
//        passengerSheetService.getPassengersFromSheet(spreadsheet,"SAMPLE");
        Optional<SheetPassenger> shhetPass = passengerSheetService.getPassengerFromSpreadsheetInRange(spreadsheet,valueRange,"23_Х-О (18:30)");

        int i = 2;
    }

}
