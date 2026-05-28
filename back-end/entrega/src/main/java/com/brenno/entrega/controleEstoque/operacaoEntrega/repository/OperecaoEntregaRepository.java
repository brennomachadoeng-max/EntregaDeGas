package com.brenno.entrega.controleEstoque.operacaoEntrega.repository;

import com.brenno.entrega.controleEstoque.operacaoEntrega.modal.OperacaoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperecaoEntregaRepository  extends JpaRepository<OperacaoEntrega, Integer> {

}
