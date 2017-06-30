package com.rprakashg.sb.samples.controllers;

import com.rprakashg.sb.samples.CollectMetrics;
import com.rprakashg.sb.samples.exceptions.BackendServiceException;
import com.rprakashg.sb.samples.models.Quote;
import com.rprakashg.sb.samples.models.Response;
import com.rprakashg.sb.samples.services.StockQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rprakashg on 6/28/17.
 */
@RestController
@RequestMapping("/")
public class DemoServiceController {
    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceController.class);

    @Autowired
    private StockQuoteService service;

    @CollectMetrics(true)
    @RequestMapping(value = "/quotes/{tickerSymbol}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<Quote> getStockQuote(@PathVariable("tickerSymbol") String tickerSymbol)
            throws BackendServiceException{
        Response r = service.getStockQuote(tickerSymbol);

        return new ResponseEntity<>(r.getQuery().getResults().getQuote(), HttpStatus.OK);
    }
}
