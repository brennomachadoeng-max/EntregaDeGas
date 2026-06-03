import { useEffect, useState } from "react";
import * as Location from "expo-location";

export function useSaudacao() {
  const [cidade, setCidade] = useState("");
  const [loading, setLoading] = useState(true);

  function obterSaudacao() {
    const hora = new Date().getHours();

    if (hora < 12) {
      return "Bom dia";
    }

    if (hora < 18) {
      return "Boa tarde";
    }

    return "Boa noite";
  }

  useEffect(() => {
    carregarDados();
  }, []);

  async function carregarDados() {
    try {

      const { status } =
        await Location.requestForegroundPermissionsAsync();

      if (status !== "granted") {
        setCidade("Localização indisponível");
        return;
      }

      const posicao =
        await Location.getCurrentPositionAsync({});

      const endereco =
        await Location.reverseGeocodeAsync({
          latitude: posicao.coords.latitude,
          longitude: posicao.coords.longitude,
        });

      if (endereco.length > 0) {
        setCidade(
          endereco[0].city ||
          endereco[0].subregion ||
          "Cidade não encontrada"
        );
      }
    } catch (error) {
      console.error("Erro ao carregar saudação:", error);
    } finally {
      setLoading(false);
    }
  }

  return {
    cidade,
    saudacao: obterSaudacao(),
    loading,
  };
}