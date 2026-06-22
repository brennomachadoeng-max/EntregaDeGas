import { NavigationContainer } from '@react-navigation/native';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, View } from 'react-native';
import StackNavigation from './navigation/stack/StackNavigation'; // Verifique se o caminho está correto
import { AuthProvider } from './hooks/auth/useAuthContext';

export default function App() {
  return (
    <AuthProvider>
      <View style={styles.container}>
        <NavigationContainer>
          <StackNavigation />
        </NavigationContainer>
        <StatusBar style="auto" />
      </View>
    </AuthProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
});