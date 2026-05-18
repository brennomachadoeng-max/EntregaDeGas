import {EntregadorErrors} from "./types";

import {
  isValidCPF,
  isValidPhone,
  isValidSenha,
  validarNome
} from "../inputValidar";
import { EntregadorCadastroDTO } from "../../../pages/cadastro/entregador/types";

export function validarCadastroEntregador(form: EntregadorCadastroDTO): EntregadorErrors {

  return {
    nome: !validarNome(form.nome),
    cpf: !isValidCPF(form.cpf),
    telefone: !isValidPhone(form.telefone),
    senha: !isValidSenha(form.senha),
  };
}       