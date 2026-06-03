import { LoginRequestDTO } from "../../pages/login/types";
import { isValidEmail, isValidCPF } from "../../util/validacao/inputValidar";
import {loginUsuario} from "../../service/loginService";
import { useState } from "react";
import { salvarUsuario } from "../../service/authStorage";


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
                response = await loginUsuario(from)
            }
            else {
                setError("Digite um email ou CPF válido.");
                return null;
            }
            await salvarUsuario(response);
            return response;
        }     
        catch (error) {
            setError("Login ou senha inválidos.");
            return null;
        } finally {
            setLoading(false);
        }
    }
    return {login, loading, error};
}

