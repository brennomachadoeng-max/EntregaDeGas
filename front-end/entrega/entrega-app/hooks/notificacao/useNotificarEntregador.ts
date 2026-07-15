import { useState } from "react";

import { notificarEntregador } from "../../service/notificacaoService";
import { SolicitacaoEntregaResponseDTO } from "../../service/notificacaoService";
import { obterUsuario } from "../../service/authStorage";

export function useNotificarEntregador() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [solicitacoes, setSolicitacoes] = useState<
    SolicitacaoEntregaResponseDTO[]
  >([]);

  async function buscarNotificacoes() {
    setLoading(true);
    setError("");

    try {
      const usuario = await obterUsuario();
      if (!usuario) {
        setError("Usuário não encontrado.");
        return [];
      }
      const response = await notificarEntregador(usuario.id);
      setSolicitacoes(response);
      return response;
    } catch (error) {
      setError("Erro ao buscar notificações.");
      return [];
    } finally {
      setLoading(false);
    }
  }

  return {
    buscarNotificacoes,
    solicitacoes,
    loading,
    error,
  };
}