package com.example.cfp.persistencia;

import java.util.List;

public interface ICrudDAO<T> {

    boolean salvar(T item);
    boolean alterar(T item);
    boolean deletar(T item);

    List<T> listarTodos();
    T ListarPorId(int id);
}
