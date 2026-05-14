import { useState } from "react";
import { View, Text, Alert, ActivityIndicator } from "react-native";
import { styles } from "./style";

import AnimatedCadastro from "../../../components/Animated/AnimatedCadastro";
import Input from "../../../components/input/inputCadastro";
import Botao from "../../../components/botao/botao";

import { UsuarioCadastroDTO } from "./types";
import { cadastrarUsuario } from "../../../service/usuarioService"; // Verifique se o caminho está correto

import {
  isValidCPF,
  isValidEmail,
  isValidPhone,
  isValidDate,
} from "../../../util/inputValidar";

export default function CadastroUsuario() {
  const [loading, setLoading] = useState(false); 

    const [form, setForm] = useState<UsuarioCadastroDTO>({
    nome: "",
    cpf: "",
    dataNascimento: "",
    telefone: "",
    email: "",
    senha: "",
  });

  const [errors, setErrors] = useState({
    nome: false,
    cpf: false,
    dataNascimento: false,
    telefone: false,
    email: false,
    senha: false,
  });

  async function executarCadastro(dados: UsuarioCadastroDTO) {
    setLoading(true);
    const cpfApenasNumeros = dados.cpf.replace(/\D/g, "");
    const telefoneApenasNumeros = dados.telefone.replace(/\D/g, "");
    const dataFormatada = dados.dataNascimento.split("/").reverse().join("-");

    // 3. MONTAGEM DO OBJETO FINAL
    const dadosParaEnviar = {
      ...dados,
      cpf: cpfApenasNumeros,
      telefone: telefoneApenasNumeros,
      dataNascimento: dataFormatada
    };

    console.log("🚀 Enviando para o Java:", dadosParaEnviar);

    try {
      const response = await cadastrarUsuario(dadosParaEnviar);
      
      Alert.alert(
        "Sucesso!", 
        `Usuário ${response.nome} cadastrado com sucesso!`
      );
      return true;
    } 
    catch (error: any) {
      // Se o erro persistir, o log do Java vai nos dizer qual outro campo está grande
      Alert.alert("Erro no cadastro", "Verifique os dados enviados.");
      console.error(error);
      return false;
    } 
    finally {
      setLoading(false);
    }
  }

  async function cadastrar() {
  const newErrors = {
    nome: form.nome.trim().length < 3,
    cpf: !isValidCPF(form.cpf),
    dataNascimento: !isValidDate(form.dataNascimento),
    telefone: !isValidPhone(form.telefone),
    email: !isValidEmail(form.email),
    senha: form.senha.length < 6,
  };
  setErrors(newErrors);

  const hasError = Object.values(newErrors).some(Boolean);
  if (hasError) {
    Alert.alert("Atenção", "Por favor, corrija os erros no formulário.");
    return;
  }

  await executarCadastro(form);
}

  return (
    <AnimatedCadastro>
      <View style={styles.card}>
        <Text style={styles.title}>Criar Conta Usuario</Text>
        <Text style={styles.subtitle}>Preencha os dados abaixo</Text>
        
        <View style={styles.form}>
          <Input
            placeholder="Nome completo"
            value={form.nome}
            error={errors.nome}
            onChangeText={(text) => setForm({ ...form, nome: text })}
          />
          
          <Input
            placeholder="CPF"
            type="cpf"
            value={form.cpf}
            error={errors.cpf}
            errorMessage="CPF deve conter 11 dígitos"
            onChangeText={(text) => setForm({ ...form, cpf: text })}
          />

          <Input
            placeholder="Data de nascimento"
            type="date"
            value={form.dataNascimento}
            error={errors.dataNascimento}
            errorMessage="Data de nascimento inválida"
            onChangeText={(text) => setForm({ ...form, dataNascimento: text })}
          />

          <Input
            placeholder="Telefone"
            type="phone"
            value={form.telefone}
            error={errors.telefone}
            errorMessage="Telefone inválido"
            onChangeText={(text) => setForm({ ...form, telefone: text })}
          />

          <Input
            placeholder="Email"
            type="email"
            value={form.email}
            error={errors.email}
            errorMessage="Email inválido"
            onChangeText={(text) => setForm({ ...form, email: text })}
          />

          <Input
            placeholder="Senha"
            secureTextEntry
            value={form.senha}
            error={errors.senha}
            errorMessage="Senha deve conter pelo menos 6 caracteres"
            onChangeText={(text) => setForm({ ...form, senha: text })}
          />

          {loading ? (
            <ActivityIndicator size="large" color="#0000ff" style={{ marginTop: 20 }} />
          ) : (
            <Botao title="Cadastrar" onPress={cadastrar} />
          )}
        </View>
      </View>
    </AnimatedCadastro>
  );
}

