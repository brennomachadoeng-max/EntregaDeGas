import { patch, post } from "./http";
import { EntregadorCadastroDTO, EntregadorResponseDTO } from "../pages/cadastro/entregador/types";

export function cadastrarEntregador(entregador: EntregadorCadastroDTO): Promise<EntregadorResponseDTO> {
    return post("/entregadores/cadastro", entregador);
}

export function alternarStatusEntregador(idEntregador: number): Promise<EntregadorResponseDTO> {
  return patch(`/entregadores/${idEntregador}/status`);
}