import { get } from "./http";
import { ProdutoPedidoDTO } from "../components/listProdutos/types";

export function listarProdutos(): Promise<ProdutoPedidoDTO[]> {
  return get("/produtos");
}