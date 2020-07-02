package dtl.DtlApp.GCPAuth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private Sheets sheetsService;
    private String APP_NAME = "DtlApp";
    private String SPREADSHEET_ID = "1XpI-UMvRjbOhmc9GvcGH0B_Ukr-0r71os2almAL96CM";
    @Value("${sheets.credentials.json.path}")
    private String credentialsFilePath;


    private Credential authorize()throws IOException, GeneralSecurityException{
        InputStream in = GoogleSheetsServiceImpl.class.getResourceAsStream(credentialsFilePath);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance() , new InputStreamReader(in));
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
//        GoogleAuthorizationCodeFlow flow = GoogleAuthorizationCodeFlow.Builder(
//                GoogleNetHttpTransport.newTrustedTransport() ,
//                JacksonFactory.getDefaultInstance(),
//                clientSecrets,
//                scopes)
    }

}






























