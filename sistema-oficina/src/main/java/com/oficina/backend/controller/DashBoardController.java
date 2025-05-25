package com.oficina.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oficina.backend.service.ServicoService;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/servicos-por-mes")
    public Map<String, Integer> getServicosPorMes() {
        Map<String, Integer> dados = new LinkedHashMap<>();
        dados.put("Janeiro", 5);
        dados.put("Fevereiro", 8);
        dados.put("Março", 4);
        dados.put("Abril", 10);
        return dados;
    }
}
