package dtl.DtlApp.GCPAuth;


import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class GoogleSpreadSheetServiceImpl implements GoogleSpreadSheetService {

    private Sheets sheetsService;
    private String APP_NAME = "DtlApp";
    private String SPREADSHEET_ID = "1aFBDhLm16N12GpGscyugrziRtwB3d_r9xW7Gt2rL9_sMKn6yCavLCcYC";
    @Value("${sheets.credentials.json.path}")
    private String credentialsFilePath;

    @Value("${sheets.tokens.dir.path}")
    private String tokensDirPath;

    @Override
    public void someDeals() throws IOException, GeneralSecurityException {

    }

    @Override
    public Sheet getById(String sheetId) {
        return null;
    }


//    private Credential authorize()throws IOException, GeneralSecurityException{
//        InputStream in = new FileInputStream(credentialsFilePath);
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance() , new InputStreamReader(in));
//        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                GoogleNetHttpTransport.newTrustedTransport() ,
//                JacksonFactory.getDefaultInstance(),
//                clientSecrets,
//                scopes).setDataStoreFactory(new FileDataStoreFactory(new java.io.File(tokensDirPath)))
//                .setAccessType("offline").build();
//
//
//        Credential credential = new AuthorizationCodeInstalledApp(flow , new LocalServerReceiver()).authorize("116851287018793951062");
//
//        return credential;
//    }
//
//    public Sheets getSheetsService() throws IOException, GeneralSecurityException {
//        Credential credential = authorize();
//        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport() ,
//                JacksonFactory.getDefaultInstance(),credential).setApplicationName(APP_NAME).build();
//    }
//
//    public void someDeals() throws IOException, GeneralSecurityException {
//
//        sheetsService = getSheetsService();
//
//        String range = "CHECKLIST!Q1:Q10";
//
//        ValueRange resp = sheetsService.spreadsheets().values().get(SPREADSHEET_ID , range).execute();
//
//        List<List<Object>> values = resp.getValues();
//
//        int i = 0;
//
//    }


}






























