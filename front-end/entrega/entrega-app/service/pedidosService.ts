import { get, post } from "./http";
import {HistoricoRequestDTO,PedidoEntregaResponseDTO} from "../components/listaPedidos/types";
import {PedidoRequest, PedidoResponseDTO} from "../pages/pedido/types";

export function listaPedidos(request: HistoricoRequestDTO): Promise<PedidoEntregaResponseDTO[]> {
  return get(`/pedido/historico?usuarioId=${request.usuarioId}`);
}

export function cadastrarPedido( pedido: PedidoRequest): Promise<PedidoResponseDTO> {
  return post("/pedido", pedido);
}