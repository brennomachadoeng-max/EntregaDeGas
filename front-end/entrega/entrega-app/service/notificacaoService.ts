import { get, post } from "./http";

export function listarSolicitacoesPedido(
  pedidoId: number
): Promise<SolicitacaoEntregaResponseDTO[]> {
  return get(`/solicitacoes/pedido/${pedidoId}`);
}

export function notificarEntregador(
  idEntregador: number
): Promise<SolicitacaoEntregaResponseDTO[]> {
  return get(`/solicitacoes/notificar/entragador/${idEntregador}`);
}

export function expirarSolicitacao(
  idSolicitacao: number
): Promise<string> {
  return post(`/solicitacoes/${idSolicitacao}/expirar`);
}

export function recusarSolicitacao(
  idSolicitacao: number
): Promise<string> {
  return post(`/solicitacoes/${idSolicitacao}/recusar`);
}

export function aceitarSolicitacao(
  idSolicitacao: number
): Promise<PedidoEntregaResponseDTO> {
  return post(`/solicitacoes/${idSolicitacao}/aceitar`);
}

export function solicitarEntregador(
  pedidoId: number
): Promise<string> {
  return post(`/solicitacoes/${pedidoId}/solicitarEntregador`);
}

export type ProdutoItemPedidoDTO = {
  nome: string;
  quantidade: number;
  valorUnitario: number;
};

export type PedidoEntregaResponseDTO = {
  idPedido: number;
  nomeCliente: string;
  telefoneCliente: string;
  rua: string;
  numero: string;
  bairro: string;
  cidade: string;
  valorCompra: number;
  produtos: ProdutoItemPedidoDTO[];
  dataPedido: string;
};

export type SolicitacaoEntregaResponseDTO = {
  idSolicitacao: number;
  idPedido: number;
  idEntregador: number;
  status: string;
  dataSolicitacao: string;
};