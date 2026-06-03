import React, { useEffect, useState } from "react";
import { View, ActivityIndicator } from "react-native";
import MapView from "react-native-maps";
import * as Location from "expo-location";
import { Localizacao } from "./types";

import { styles } from "./style";

export default function Mapa() {
  const [location, setLocation] = useState<Localizacao | null>(null);

  useEffect(() => {
    async function carregarLocalizacao() {
      const { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== "granted") return;
      const posicao = await Location.getCurrentPositionAsync({});
      setLocation({latitude: posicao.coords.latitude, longitude: posicao.coords.longitude,});
    }
    carregarLocalizacao();
  }, []);

  if (!location) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" />
      </View>
    );
  }

  return (
    <View style={styles.mapContainerMb}>
      <MapView
        style={styles.map}
        showsUserLocation
        followsUserLocation
        showsCompass
        showsTraffic
        showsBuildings
        loadingEnabled
        mapType="standard"
        initialRegion={{
          latitude: location.latitude,
          longitude: location.longitude,
          latitudeDelta: 0.01,
          longitudeDelta: 0.01,
        }}
      >
      </MapView>
    </View>
  );
}