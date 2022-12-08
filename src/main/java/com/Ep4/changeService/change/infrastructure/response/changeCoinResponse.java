package com.Ep4.changeService.change.infrastructure.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class changeCoinResponse {
   private String from;
   private String to;
   private Double TipoCambio;
}
