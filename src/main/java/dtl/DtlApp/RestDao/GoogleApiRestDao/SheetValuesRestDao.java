package dtl.DtlApp.RestDao.GoogleApiRestDao;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.util.Optional;


public interface SheetValuesRestDao {
    void update(Spreadsheet spreadsheet ,ValueRange valueRange);
    Optional<ValueRange> get(Spreadsheet spreadsheet , ValueRange valueRange);
}
