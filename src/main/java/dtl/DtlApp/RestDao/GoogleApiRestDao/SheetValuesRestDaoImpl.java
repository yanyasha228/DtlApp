package dtl.DtlApp.RestDao.GoogleApiRestDao;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SheetValuesRestDaoImpl implements SheetValuesRestDao {

    @Value("${rest.sheets.api.v4.token}")
    private String token;

    @Value("${rest.sheets.api.v4.get.Spreadsheet.key.id}")
    private String getSpreadSheetUrl;

    @Value("${rest.sheets.api.v4.get.ValueRange.key.id.a1Notation}")
    private String getSheetValuesUrl;

    @Autowired
    private GoogleRestApiExchanger googleRestApiExchanger;


    @Override
    public void update(Spreadsheet spreadsheet, ValueRange valueRange) {

        ResponseEntity<ValueRange> resp = googleRestApiExchanger.exchange(String.format(getSheetValuesUrl, token, spreadsheet.getSpreadsheetId(), valueRange.getRange()), HttpMethod.PUT, valueRange, ValueRange.class);


    }

    @Override
    public Optional<ValueRange> get(Spreadsheet spreadsheet, ValueRange valueRange) {
        ResponseEntity<ValueRange> resp = googleRestApiExchanger.exchange(String.format(getSheetValuesUrl, token, spreadsheet.getSpreadsheetId(), valueRange.getRange()), HttpMethod.GET, valueRange, ValueRange.class);

        if(resp.getStatusCode() == HttpStatus.OK && !Objects.isNull(resp.getBody())){
            return Optional.of(resp.getBody());
        }
        return Optional.empty();
    }
}
