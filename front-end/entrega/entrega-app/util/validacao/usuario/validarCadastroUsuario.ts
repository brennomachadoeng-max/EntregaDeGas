import { UsuarioErrors } from "./types";

import {
  isValidCPF,
  isValidEmail,
  isValidPhone,
  isValidDate,
  isValidSenha,
  validarNome
} from "../inputValidar";
import { UsuarioCadastroDTO } from "../../../pages/cadastro/usuario/types";

export function validarCadastroUsuario(form: UsuarioCadastroDTO): UsuarioErrors {

  return {
    nome: !validarNome(form.nome),
    cpf: !isValidCPF(form.cpf),
    dataNascimento: !isValidDate(form.dataNascimento),
    telefone: !isValidPhone(form.telefone),
    email: !isValidEmail(form.email),
    senha: !isValidSenha(form.senha),
  };
}