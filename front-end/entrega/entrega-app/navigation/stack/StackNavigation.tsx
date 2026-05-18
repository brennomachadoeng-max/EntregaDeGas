import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {CadastroEntregador, CadastroUsuario, Login} from '../../pages';

const Stack = createNativeStackNavigator();


export default function StackNavigation(){
  return(
    <Stack.Navigator screenOptions={{headerShown: false}}>
      <Stack.Screen name='Login' component={Login} />
      <Stack.Screen name='CadastroEntregador' component={CadastroEntregador} />
      <Stack.Screen name='CadastroUsuario' component={CadastroUsuario} />
    </Stack.Navigator>
  );
}
