package com.rprakashg.sb.samples.services;

import com.rprakashg.sb.samples.exceptions.BackendServiceException;
import com.rprakashg.sb.samples.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by rprakashg on 6/29/17.
 */
@Service
public class StockQuoteServiceImpl implements StockQuoteService {
    private static final Logger LOG = LoggerFactory.getLogger(StockQuoteServiceImpl.class);
    private static final String yahooApiEndPoint = "https://query.yahooapis.com/v1/public/yql?q={q}&format={format}&env={env}";
    private static final String query = "select * from yahoo.finance.quotes where symbol in (\"%s\")";

    @Override
    public Response getStockQuote(String tickerSymbol) throws BackendServiceException {

        try{
            String q = String.format(query, tickerSymbol);
            RestTemplate rt = new RestTemplate();
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("format", "json");
            uriVariables.put("env", "store://datatables.org/alltableswithkeys");
            uriVariables.put("q", q);
            ResponseEntity<Response> response = rt.getForEntity(yahooApiEndPoint, Response.class, uriVariables);
            return response.getBody();

        }catch (final HttpClientErrorException e){
            throw new BackendServiceException(e.getStatusCode().value(), e.getStatusText());
        }catch(Exception e){
            throw new BackendServiceException(500, e.getMessage());
        }
    }
}
