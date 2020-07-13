package dtl.DtlApp.RestDao.GoogleApiRestDao;

import com.google.api.client.json.GenericJson;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.Response;
import com.google.api.services.sheets.v4.model.UpdateSpreadsheetPropertiesRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface GoogleRestApiExchanger {

//    <REQ extends Request, RES extends Response> ResponseEntity<RES> exchangePost(String url,
//                                                                                 REQ requestEntity,
//                                                                                 Class<RES> responseEntityClass);

    <REQ, RES> ResponseEntity<RES> exchange(String url, HttpMethod method,
                                                                                 REQ requestEntity,
                                                                                 Class<RES> responseEntityClass);
//
//    <REQ extends Request, RES extends Response> ResponseEntity<RES> exchangePut(String url,
//                                                                                REQ requestEntity,
//                                                                                Class<RES> responseEntityClass);
//
//    <RES extends GenericJson> ResponseEntity<RES> exchangeGet(String url,
//                                                              Class<RES> responseEntityClass);
}
