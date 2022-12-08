package com.Ep4.changeService.change.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class coins {
  private Map<String,String> motd;
  private Map<String, String> query;
  private Map<String,Double>info;
  private Double result;
  private Boolean success;
  private String terms;
  private String privacy;
  private Boolean historical;
  private String date;
  private String source;
  private Long timestamp;
}
