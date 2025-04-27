package com.despesas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.despesas.api.model.Despesa;
import com.despesas.api.service.PlanilhaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
    @Autowired
    private PlanilhaService planilhaService;
    
    @PostMapping
    public ResponseEntity<String> registrarDespesa(@RequestBody Despesa despesa) {
        planilhaService.registrarDespesa(despesa);
        return ResponseEntity.ok("Despesa registrada com sucesso");
    }
}
