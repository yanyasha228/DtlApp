package dtl.DtlApp.GCPAuth;

import com.google.api.services.sheets.v4.model.Sheet;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleSpreadSheetService {
    void someDeals() throws IOException, GeneralSecurityException;

    Sheet getById(String sheetId);
}
