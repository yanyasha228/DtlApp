package dtl.DtlApp.RestDao.GoogleApiRestDao;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.Response;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class GoogleRestApiExchangerImpl implements GoogleRestApiExchanger {
    @Autowired
    private RestTemplate restTemplate;

//    private RateLimiter rateLimiter = RateLimiter.create(1);

    @Autowired
    private JacksonFactory jacksonFactory;

//    private RateLimiter rateLimiter = RateLimiter.create(2);

//    @Override
//    public <REQ extends Request, RES extends Response> ResponseEntity<RES> exchangePut(String url, REQ requestEntity, Class<RES> responseEntityClass) {
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        String resStr = restTemplate.exchange(url, new HttpEntity<>(requestEntity, headers), String.class);
//
//        RES responseObject = null;
//
//        try {
//            JsonParser jsonParser = jacksonFactory.createJsonParser(resStr);
//            responseObject = jsonParser.parseAndClose(responseEntityClass);
//            int i = 0;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ResponseEntity<RES> responseEntity;
//        if (!Objects.isNull(responseObject)) {
//            responseEntity = new ResponseEntity<RES>(responseObject, HttpStatus.OK);
//        } else {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return responseEntity;
////        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestEntity, headers), responseEntityClass);
//    }
//
//    @Override
//    public <REQ extends Request, RES extends Response> ResponseEntity<RES> exchangePost(String url, REQ requestEntity, Class<RES> responseEntityClass) {
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        String resStr = restTemplate.postForObject(url, new HttpEntity<>(requestEntity, headers), String.class);
//
//        RES responseObject = null;
//
//        try {
//            JsonParser jsonParser = jacksonFactory.createJsonParser(resStr);
//            responseObject = jsonParser.parseAndClose(responseEntityClass);
//            int i = 0;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ResponseEntity<RES> responseEntity;
//        if (!Objects.isNull(responseObject)) {
//            responseEntity = new ResponseEntity<RES>(responseObject, HttpStatus.OK);
//        } else {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return responseEntity;
////        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestEntity, headers), responseEntityClass);
//    }

    @Override
    public <REQ, RES> ResponseEntity<RES> exchange(String url, HttpMethod method, REQ requestEntity, Class<RES> responseEntityClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        JsonParser jsonParser;
//        rateLimiter.acquire();
        ResponseEntity<String> resp = restTemplate.exchange(url, method, new HttpEntity<>(requestEntity, headers), String.class);
        RES responseObject = null;
        if (resp.getStatusCode() == HttpStatus.OK) {
            try {
                jsonParser = jacksonFactory.createJsonParser(resp.getBody());
                responseObject = jsonParser.parseAndClose(responseEntityClass);
                int i = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<RES>(responseObject, resp.getStatusCode());

    }

//    @Override
//    public <RES extends GenericJson> ResponseEntity<RES> exchangeGet(String url, Class<RES> responseEntityClass) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        ResponseEntity<Spreadsheet> res;
//        ResponseEntity<String> resStr = restTemplate.exchange(url, HttpMethod.GET, String.class);
//        RES responseObject = null;
//        try {
//            JsonParser jsonParser = jacksonFactory.createJsonParser(resStr);
//            responseObject = jsonParser.parseAndClose(responseEntityClass);
//            int i = 0;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ResponseEntity<RES> responseEntity;
//        if (!Objects.isNull(responseObject)) {
//            responseEntity = new ResponseEntity<RES>(responseObject, HttpStatus.OK);
//        } else {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return responseEntity;
//    }

}
