package com.Ep4.changeService.change.infrastructure.controller;

import com.Ep4.changeService.change.domain.entity.coins;
import com.Ep4.changeService.change.infrastructure.response.changeCoinResponse;
import com.Ep4.changeService.change.infrastructure.response.valueCoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1")
public class changeCoinsController {
    @Autowired
    private RestTemplate restTemplate;
    double cambio;
    @GetMapping
    @RequestMapping("/tipo-cambio")
    changeCoinResponse tipoCambio(@RequestParam String moneda1,@RequestParam String moneda2){
        String url="https://api.exchangerate.host/convert?from="+moneda1+"&to="+moneda2;
        coins response=restTemplate.getForObject(url,coins.class);
        changeCoinResponse changeCoinResponseObject = new changeCoinResponse();
        cambio=  Math.round(response.getResult()*100.0)/100.0;
        changeCoinResponseObject.setTipoCambio((double) cambio);
        changeCoinResponseObject.setFrom(response.getQuery().get("from"));
        changeCoinResponseObject.setTo(response.getQuery().get("to"));
          return changeCoinResponseObject;
    }
    @GetMapping
    @RequestMapping("/valor-cambio")
    valueCoinResponse valorCambio(@RequestParam float monto , @RequestParam String monedaOrig, @RequestParam String monedaDest){
        String url="https://api.exchangerate.host/convert?from="+monedaOrig+"&to="+monedaDest;
        coins response=restTemplate.getForObject(url,coins.class);
        valueCoinResponse valueCoinResponseObject = new valueCoinResponse();
        cambio=  Math.round(response.getResult()*100.0)/100.0;
        valueCoinResponseObject.setTipoCambio((double) cambio);
        valueCoinResponseObject.setTo(response.getQuery().get("to"));
        valueCoinResponseObject.setFrom(response.getQuery().get("from"));
        int newMonto= (int) Math.round(monto*valueCoinResponseObject.getTipoCambio());
        valueCoinResponseObject.setResultado(newMonto);
        return valueCoinResponseObject;
    }
}
