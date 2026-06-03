import { get } from "./http";
import {HistoricoRequestDTO,PedidoEntregaResponseDTO} from "../components/listaPedidos/types";

export function listaPedidos(request: HistoricoRequestDTO): Promise<PedidoEntregaResponseDTO[]> {
  return get(`/pedido/historico?usuarioId=${request.usuarioId}`);
}