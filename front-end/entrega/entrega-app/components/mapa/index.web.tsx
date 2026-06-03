import React from "react";
import { View, Text } from "react-native";
import { styles } from "./style";

export default function Mapa() {
  return (
    <View style={styles.mapContainer}>
      <Text style={styles.mapText}>
        📍 Mapa da sua localização
      </Text>
    </View>
  );
}