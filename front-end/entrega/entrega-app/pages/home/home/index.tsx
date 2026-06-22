import React from 'react';
import { View, ActivityIndicator } from 'react-native';
import HomeUsuario from '../homeUsuario'; 
import HomeEntregador from '../homeEntregador';
import { useAuthContext } from '../../../hooks/auth/useAuthContext';

export default function Home() {
  const { user, loadingContext } = useAuthContext();

  if (loadingContext) {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <ActivityIndicator size="large" color="#0000ff" />
      </View>
    );
  }

  if (user?.tipo?.toUpperCase() === 'ENTREGADOR') {
    return <HomeEntregador />;
  }

  return <HomeUsuario />;
}