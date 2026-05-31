import {post} from "./http";
import {LoginRequestDTO, LoginResponseDTO} from "../pages/login/types";

export function loginUsuario(loginData: LoginRequestDTO): Promise<LoginResponseDTO> {
    return post("/auth/login", loginData);
}
