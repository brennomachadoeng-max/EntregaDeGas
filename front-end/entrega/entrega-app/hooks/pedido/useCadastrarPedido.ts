import { useState } from "react";
import { Alert } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";

import {cadastrarPedido} from "../../service/pedidosService";
import {PedidoRequest} from "../../pages/pedido/types";

type UsuarioStorage = {
  id: number;
  nome: string;
  contato: string;
  tipo: string;
};

export function useCadastrarPedido() {
  const [loading, setLoading] = useState(false);

  async function cadastrar(
    pedido: Omit<PedidoRequest, "usuarioId">
  ) {
    setLoading(true);

    try {
      const usuarioStorage = await AsyncStorage.getItem("@entrega:user");

      if (!usuarioStorage) {
        Alert.alert("Erro", "Usuário não encontrado.");
        return false;
      }

      const usuario: UsuarioStorage = JSON.parse(usuarioStorage);

      await cadastrarPedido({
        ...pedido,
        usuarioId: usuario.id,
      });

      Alert.alert("Sucesso", "Pedido criado com sucesso!");
      return true;
    } catch (error) {
      console.log("erro cadastrar pedido", error);
      Alert.alert("Erro", "Erro ao criar pedido.");
      return false;
    } finally {
      setLoading(false);
    }
  }

  return {
    cadastrar,
    loading,
  };
}