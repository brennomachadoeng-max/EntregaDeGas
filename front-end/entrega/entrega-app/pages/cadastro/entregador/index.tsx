import { useState } from "react";
import { View, Text, Alert, ActivityIndicator } from "react-native";
import { styles } from "./style";

import AnimatedCadastro from "../../../components/Animated/AnimatedCadastro";
import Input from "../../../components/input/inputCadastro";
import Botao from "../../../components/botao/botao";

import { EntregadorCadastroDTO } from "./types";

import {
  isValidCPF,
  isValidPhone,
} from "../../../util/inputValidar";
import { cadastrarEntregador } from "../../../service/entregadorService";

export default function CadastroEntregador() {
  const [loading, setLoading] = useState(false); 

  const [form, setForm] = useState<EntregadorCadastroDTO>({
    nome: "",
    cpf: "",
    senha: "",
    telefone: "",
  });

  const [errors, setErrors] = useState({
    nome: false,
    cpf: false,
    telefone: false,
    senha: false,
  });

  async function cadastrar() {
    const newErrors = {
      nome: form.nome.trim().length < 3,
      cpf: !isValidCPF(form.cpf),
      telefone: !isValidPhone(form.telefone),
      senha: form.senha.length < 6,
    };
    setErrors(newErrors);

    const hasError = Object.values(newErrors).some(Boolean);
    if (hasError) {
        Alert.alert("Atenção", "Por favor, corrija os erros no formulário.");
        return;
    }
    setLoading(true);

    try {
      const response = await cadastrarEntregador(form);
      Alert.alert(
        "Sucesso!", 
        `Entregador ${response.nome} cadastrado com ID ${response.idEntregador}`,
        [{ text: "OK", onPress: () => console.log("Navegar para Login") }]
      );      
    } catch (error: any) {
      Alert.alert("Erro ao cadastrar", error.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <AnimatedCadastro>
      <View style={styles.card}>
        <Text style={styles.title}>Criar Conta Entregador</Text>
        <Text style={styles.subtitle}>
          Preencha os dados abaixo
        </Text>
        <View style={styles.form}>
          <Input
            placeholder="Nome completo"
            value={form.nome}
            error={errors.nome}
            onChangeText={(text) =>
              setForm({ ...form, nome: text })
            }
          />
          <Input
            placeholder="CPF"
            type="cpf"
            value={form.cpf}
            error={errors.cpf}
            errorMessage="CPF deve conter 11 dígitos"
            onChangeText={(text) =>
              setForm({ ...form, cpf: text })
            }
          />

          <Input
            placeholder="Telefone"
            type="phone"
            value={form.telefone}
            error={errors.telefone}
            errorMessage="Telefone inválido"
            onChangeText={(text) =>
              setForm({ ...form, telefone: text })
            }
          />

          <Input
            placeholder="Senha"
            secureTextEntry
            value={form.senha}
            error={errors.senha}
            errorMessage="Senha deve conter pelo menos 6 caracteres"
            onChangeText={(text) =>
              setForm({ ...form, senha: text })
            }
          />
          <Botao
            title="Cadastrar"
            onPress={cadastrar}
          />
      </View>
      </View>
    </AnimatedCadastro>
  );
}