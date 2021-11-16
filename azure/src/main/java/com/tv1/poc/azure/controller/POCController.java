package com.tv1.poc.azure.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class POCController {

  @GetMapping(value = "Admin", consumes = MediaType.ALL_VALUE)
  @ResponseBody
  @PreAuthorize("hasAuthority('APPROLE_Admin')")
  public String Admin() {
    return "Admin POC TV1 Compras Authorized";
  }
}

