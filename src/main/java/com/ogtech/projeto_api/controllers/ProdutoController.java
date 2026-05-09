package com.ogtech.projeto_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogtech.projeto_api.models.Produto;
import com.ogtech.projeto_api.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(produto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto Removido");

    }
}
