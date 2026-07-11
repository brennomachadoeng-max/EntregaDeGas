import { get, post } from "./http";
import { EnderecoCadastroDTO, EnderecoResponseDTO} from "../components/endereco/types";

export function cadastrarEndereco(
  endereco: EnderecoCadastroDTO
): Promise<EnderecoResponseDTO> {
  return post("/enderecos", endereco);
}

export function buscarEnderecosPorUsuario(
  idUsuario: number
): Promise<EnderecoResponseDTO[]> {
  return get(`/enderecos/usuario/${idUsuario}`);
}