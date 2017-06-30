package com.rprakashg.sb.samples.services;

import com.rprakashg.sb.samples.exceptions.BackendServiceException;
import com.rprakashg.sb.samples.models.Response;
import org.springframework.stereotype.Service;

/**
 * Created by rprakashg on 6/28/17.
 */
@Service
public interface StockQuoteService {
    Response getStockQuote(String tickerSymbol) throws BackendServiceException;
}
