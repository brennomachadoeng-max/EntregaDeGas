import { ProdutoPedidoDTO } from "../../components/listProdutos/types";

export interface ItemCarrinho {
  produto: ProdutoPedidoDTO;
  quantidade: number;
}