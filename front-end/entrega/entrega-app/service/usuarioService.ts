import { post } from "./http";
import { UsuarioCadastroDTO, UsuarioResponseDTO } from "../pages/cadastro/usuario/types";

export function cadastrarUsuario(usuario: UsuarioCadastroDTO): Promise<UsuarioResponseDTO> {
    return post("/usuarios/cadastro", usuario);
}