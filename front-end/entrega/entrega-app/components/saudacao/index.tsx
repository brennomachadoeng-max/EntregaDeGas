import React from "react";
import { View, Text, ActivityIndicator } from "react-native";

import { styles } from "./style";
import { useSaudacao } from "../../hooks/saudacao/useSaudacao";

export default function Saudacao() {
  const {cidade, saudacao, loading,} = useSaudacao();

  if (loading) {
    return <ActivityIndicator />;
  }

  return (
    <View style={styles.header}>
      <View>
        <Text style={styles.location}>
          📍 {cidade}
        </Text>

        <Text style={styles.greeting}>
          {saudacao}
        </Text>

        <Text style={styles.subtitle}>
          Seu gás está a poucos minutos de você.
        </Text>
      </View>
    </View>
  );
}