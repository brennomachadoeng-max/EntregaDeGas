import {createNativeStackNavigator} from '@react-navigation/native-stack';
import CadastroEntregador from '../../pages/cadastro/entregador';
import CadastroUsuario from '../../pages/cadastro/usuario';

const Stack = createNativeStackNavigator();


export default function StackNavigation(){
  return(
    <Stack.Navigator screenOptions={{headerShown: false}}>
      <Stack.Screen name='CadastroUsuario' component={CadastroUsuario} />
      <Stack.Screen name='CadastroEntregador' component={CadastroEntregador} />
    </Stack.Navigator>
  );
}
