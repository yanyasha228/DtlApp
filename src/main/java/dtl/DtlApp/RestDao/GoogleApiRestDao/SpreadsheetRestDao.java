package dtl.DtlApp.RestDao.GoogleApiRestDao;




import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.UpdateSheetPropertiesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.util.Optional;

public interface SpreadsheetRestDao {
    Optional<Spreadsheet> getById(String spreadsheetId);

    void updateSheet(ValueRange valueRange);
}
