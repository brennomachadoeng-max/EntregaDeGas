import { ScrollView, View, Text, Pressable } from "react-native";
import { styles } from "./style";

export default function Home() {
  return (
    <ScrollView
      style={styles.screen}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    >
      <View style={styles.mapContainer}>
        <Text style={styles.mapText}>
          📍 Mapa da sua localização
        </Text>
      </View>

      <View style={styles.header}>
        <View>
          <Text style={styles.location}>
            📍 Feira de Santana
          </Text>

          <Text style={styles.greeting}>
            Boa noite, Brenno
          </Text>

          <Text style={styles.subtitle}>
            Seu gás está a poucos minutos de você.
          </Text>
        </View>

        <View style={styles.profile}>
          <Text style={styles.profileText}>
            BM
          </Text>
        </View>
      </View>

      <Pressable style={styles.heroCard}>
        <Text style={styles.heroIcon}>
          🛢️
        </Text>

        <Text style={styles.heroTitle}>
          Pedir Gás
        </Text>

        <Text style={styles.heroDescription}>
          Solicite um entregador próximo e acompanhe sua entrega em tempo real.
        </Text>

        <View style={styles.heroButton}>
          <Text style={styles.heroButtonText}>
            PEDIR AGORA
          </Text>
        </View>
      </Pressable>

      <View style={styles.activityCard}>
        <Text style={styles.activityTitle}>
          Região ativa
        </Text>

        <Text style={styles.activityText}>
          Existem entregadores disponíveis próximos da sua localização neste momento.
        </Text>
      </View>

      <Text style={styles.sectionTitle}>
        Recursos
      </Text>

      <View style={styles.features}>
        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>⚡</Text>
          <Text style={styles.featureText}>Entrega rápida</Text>
        </View>

        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>📍</Text>
          <Text style={styles.featureText}>Rastreamento</Text>
        </View>

        <View style={styles.featureCard}>
          <Text style={styles.featureIcon}>🛡️</Text>
          <Text style={styles.featureText}>Segurança</Text>
        </View>
      </View>

      <Text style={styles.sectionTitle}>
        Último pedido
      </Text>

      <View style={styles.lastOrder}>
        <Text style={styles.lastOrderTitle}>
          Botijão GLP 13kg
        </Text>

        <Text style={styles.lastOrderStatus}>
          ✓ Entregue com sucesso
        </Text>

        <Text style={styles.lastOrderDate}>
          30/05/2026 às 18:30
        </Text>
      </View>
    </ScrollView>
  );
}