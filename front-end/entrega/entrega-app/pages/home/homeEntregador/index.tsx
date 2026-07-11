import React, { useState } from "react";
import { ScrollView, View, Text, Pressable, Platform, Alert } from "react-native";

import { styles } from "./style";
import { ListPedidos, Saudacao } from "../../../components";
import { useTrocaAtivoEntregador } from "../../../hooks/entregador/useTrocarAtivoEntregador";

export default function HomeEntregador() {
  const [isOnline, setIsOnline] = useState(false);

  const { trocarStatus, carregando } = useTrocaAtivoEntregador();

  async function handleTrocarStatus() {
    try {
      const entregadorAtualizado = await trocarStatus();
      setIsOnline(entregadorAtualizado.ativo);
    } catch (error) {
      Alert.alert("Erro", "Não foi possível alterar seu status.");
    }
  }

  return (
    <ScrollView
      style={styles.screen}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    >
      {Platform.OS !== "web" && <Saudacao />}

      <View style={[styles.heroCard, isOnline && styles.heroCardOnline]}>
        <Text style={styles.heroIcon}>
          {isOnline ? "🚗" : "💤"}
        </Text>

        <Text style={styles.heroTitle}>
          {isOnline ? "Você está Online" : "Você está Offline"}
        </Text>

        <Text style={styles.heroDescription}>
          {isOnline
            ? "Aguarde novas solicitações de gás na sua região. Mantenha o app aberto."
            : "Fique online para começar a receber pedidos e realizar entregas hoje."}
        </Text>

        <Pressable
          style={[styles.heroButton, isOnline && styles.heroButtonOffline]}
          onPress={handleTrocarStatus}
          disabled={carregando}
        >
          <Text style={[styles.heroButtonText, isOnline && styles.heroButtonTextOffline]}>
            {carregando
              ? "ALTERANDO..."
              : isOnline
                ? "FICAR OFFLINE"
                : "FICAR ONLINE AGORA"}
          </Text>
        </Pressable>
      </View>

      <Text style={styles.sectionTitle}>
        Seu Desempenho (Hoje)
      </Text>

      <View style={styles.features}>
        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>💰</Text>
          <Text style={styles.featureText}>R$ 0,00</Text>
          <Text style={styles.featureSubText}>Ganhos</Text>
        </View>

        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>📦</Text>
          <Text style={styles.featureText}>0</Text>
          <Text style={styles.featureSubText}>Entregas</Text>
        </View>

        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>⭐</Text>
          <Text style={styles.featureText}>5.0</Text>
          <Text style={styles.featureSubText}>Avaliação</Text>
        </View>
      </View>

      <Text style={styles.sectionTitle}>
        Próximas Entregas / Histórico
      </Text>

      <ListPedidos />
    </ScrollView>
  );
}