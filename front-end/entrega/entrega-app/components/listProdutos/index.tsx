import React from "react";
import {
  View,
  Text,
  ActivityIndicator,
  Pressable,
} from "react-native";

import { styles } from "./style";
import { useListProdutos } from "../../hooks/produto/useListProduto";

type Props = {
  onAdd: (produto: any) => void;
};

export default function ListProdutos({ onAdd }: Props) {
  const { produtos, loading, semProdutos } = useListProdutos();

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#16A34A" />
      </View>
    );
  }

  if (semProdutos) {
    return (
      <View style={styles.emptyContainer}>
        <Text style={styles.emptyText}>
          Nenhum produto cadastrado no momento.
        </Text>
      </View>
    );
  }

  return (
    <View style={styles.section}>
      <Text style={styles.sectionTitle}>
        Escolha seu pedido
      </Text>

      {produtos.map((item) => (
        <Pressable
          key={item.idProduto}
          style={styles.card}
          onPress={() => onAdd(item)}
        >
          <View style={styles.iconBox}>
            <Text style={styles.icon}>🛢️</Text>
          </View>

          <View style={styles.content}>
            <Text style={styles.title}>{item.nome}</Text>

            <Text style={styles.price}>
              R$ {item.valor.toFixed(2)}
            </Text>

            <Text style={styles.description}>
              Toque para adicionar ao carrinho
            </Text>
          </View>

          <Text style={styles.arrow}>+</Text>
        </Pressable>
      ))}
    </View>
  );
}