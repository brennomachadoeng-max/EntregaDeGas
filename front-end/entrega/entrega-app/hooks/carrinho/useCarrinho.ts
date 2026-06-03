import { useEffect, useState } from "react";
import { ProdutoPedidoDTO } from "../../components/listProdutos/types";
import {
  salvarCarrinho,
  obterCarrinho,
} from "../../service/authStorage";
import { ItemCarrinho } from "../../feature/carrinho/types";

export function useCarrinho() {
  const [carrinho, setCarrinho] = useState<ItemCarrinho[]>([]);

  useEffect(() => {
    async function carregar() {
      const data = await obterCarrinho();
      setCarrinho(data);
    }
    carregar();
  }, []);

  useEffect(() => {
    salvarCarrinho(carrinho);
  }, [carrinho]);

  function adicionarProduto(produto: ProdutoPedidoDTO) {
    setCarrinho((prev) => {
      const itemExistente = prev.find(
        (item) => item.produto.idProduto === produto.idProduto
      );

      if (itemExistente) {
        return prev.map((item) =>
          item.produto.idProduto === produto.idProduto
            ? {
                ...item,
                quantidade: item.quantidade + 1,
              }
            : item
        );
      }

      return [...prev, { produto, quantidade: 1 }];
    });
  }

  function removerProduto(idProduto: number) {
    setCarrinho((prev) =>
      prev
        .map((item) =>
          item.produto.idProduto === idProduto
            ? {
                ...item,
                quantidade: item.quantidade - 1,
              }
            : item
        )
        .filter((item) => item.quantidade > 0)
    );
  }

  function limparCarrinho() {
    setCarrinho([]);
  }

  const total = carrinho.reduce((acc, item) => {
    return acc + item.produto.valor * item.quantidade;
  }, 0);

  return {
    carrinho,
    adicionarProduto,
    removerProduto,
    limparCarrinho,
    total,
  };
}