package dtl.DtlApp.RestDao.GoogleApiRestDao;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class SpreadsheetRestDaoImpl implements SpreadsheetRestDao {

    @Value("${rest.sheets.api.v4.token}")
    private String token;

    @Value("${rest.sheets.api.v4.get.Spreadsheet.key.id}")
    private String getSpreadSheetUrl;

    @Autowired
    private GoogleRestApiExchanger googleRestApiExchanger;

    @Override
    public Optional<Spreadsheet> getById(String spreadsheetId) {
        if(Objects.isNull(spreadsheetId) || spreadsheetId.isBlank()) return Optional.empty();

        ResponseEntity<Spreadsheet> response = googleRestApiExchanger.exchange(String.format(getSpreadSheetUrl,token,spreadsheetId), HttpMethod.GET , null,Spreadsheet.class);

        Spreadsheet spread = response.getBody();
        String spId = spread.getSpreadsheetId();
        int i = 0;
        return Optional.ofNullable(spread);
    }

    @Override
    public void updateSheet(ValueRange valueRange) {

    }
}
