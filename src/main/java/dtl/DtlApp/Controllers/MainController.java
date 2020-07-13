package dtl.DtlApp.Controllers;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import dtl.DtlApp.FileUtils.CsvPassengersFileBuilder;
import dtl.DtlApp.Models.Worker;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private CsvPassengersFileBuilder csvPassengersFileBuilder;

    @GetMapping(value = "/importSalaryCsv",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    byte[] importFile(@RequestParam String spreadSheetId , @RequestParam String workerName , @RequestParam int dayBegin , @RequestParam int dayEnd) throws IOException {
        Worker worker = new Worker();
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setSpreadsheetId(spreadSheetId);
        worker.setName(workerName);
        InputStream in = new FileInputStream(csvPassengersFileBuilder.createCsvByWorker(worker,spreadsheet,dayBegin , dayEnd));
        return IOUtils.toByteArray(in);
    }
}
