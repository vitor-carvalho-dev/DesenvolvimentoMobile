package com.example.cfp.persistencia;

import com.example.cfp.model.Receita;

import java.util.Collections;
import java.util.List;

public class ReceitaDAO implements ICrudDAO<Receita> {

    @Override
    public boolean salvar(Receita item) {
        return false;
    }

    @Override
    public boolean alterar(Receita item) {
        return false;
    }

    @Override
    public boolean deletar(Receita item) {
        return false;
    }

    @Override
    public List<Receita> listarTodos() {
        return Collections.emptyList();
    }

    @Override
    public Receita ListarPorId(int id) {
        return null;
    }
}
