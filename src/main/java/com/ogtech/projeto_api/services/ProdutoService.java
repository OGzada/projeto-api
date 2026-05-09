package com.ogtech.projeto_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogtech.projeto_api.models.Produto;
import com.ogtech.projeto_api.repositories.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
