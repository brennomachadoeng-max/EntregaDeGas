import AsyncStorage from "@react-native-async-storage/async-storage";
import { LoginResponseDTO } from "../pages/login/types";

const USER_KEY = "@entrega:user";

export async function salvarUsuario(usuario: LoginResponseDTO) {
    await AsyncStorage.setItem(
        USER_KEY,
        JSON.stringify(usuario)
    );
}
export async function obterUsuario() {
    const usuario = await AsyncStorage.getItem(USER_KEY);
    if (!usuario) {
        return null;
    }
    return JSON.parse(usuario);
}
export async function removerUsuario() {
    await AsyncStorage.removeItem(USER_KEY);
}