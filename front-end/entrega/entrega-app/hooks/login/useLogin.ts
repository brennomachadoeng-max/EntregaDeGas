import { LoginRequestDTO } from "../../pages/login/types";
import { isValidEmail, isValidCPF } from "../../util/validacao/inputValidar";
import {loginUsuario, loginEntregador} from "../../service/loginService";
import { useState } from "react";


export function useLogin() {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    async function login(from: LoginRequestDTO) {
        setLoading(true);
        setError("");
        try {
            let response;
            if(isValidEmail(from.login)) {
                response = await loginUsuario(from);
            }
            else if(isValidCPF(from.login)) {
                response = await loginEntregador(from)
            }
            else {
                setError("Digite um email ou CPF válido.");
                return false;
            }
            return true;
        }     
        catch (error) {
            setError("Login ou senha inválidos.");
            return false;
        } finally {
            setLoading(false);
        }
    }
    return {login, loading, error};
}

