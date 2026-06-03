import AsyncStorage from "@react-native-async-storage/async-storage";
import { LoginResponseDTO } from "../pages/login/types";

const USER_KEY = "@entrega:user";
const CART_KEY = "@entrega:carrinho";

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
export async function salvarCarrinho(carrinho: any) {
  await AsyncStorage.setItem(
    CART_KEY,
    JSON.stringify(carrinho)
  );
}
export async function obterCarrinho() {
  const carrinho = await AsyncStorage.getItem(CART_KEY);

  if (!carrinho) {
    return [];
  }

  return JSON.parse(carrinho);
}
export async function removerCarrinho() {
  await AsyncStorage.removeItem(CART_KEY);
}