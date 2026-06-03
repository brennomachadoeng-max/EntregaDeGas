import React from "react";
import {
  View,
  Text,
  ScrollView,
  ActivityIndicator,
  Pressable,
} from "react-native";

import { styles } from "./style";
import { useListaPedidos } from "../../hooks/pedido/useListaPedidos";
import { useCarrinho } from "../../hooks/carrinho/useCarrinho";

export default function ListaPedidos() {
  const {
    pedidos,
    loading,
    error,
    carregarPedidos,
  } = useListaPedidos();
  const { adicionarProduto, carrinho } = useCarrinho();

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" />
      </View>
    );
  }

  if (error) {
    return (
      <View style={styles.emptyContainer}>
        <Text style={styles.emptyText}>
          {error}
        </Text>

        <Pressable
          style={styles.retryButton}
          onPress={carregarPedidos}
        >
          <Text style={styles.retryButtonText}>
            Tentar novamente
          </Text>
        </Pressable>
      </View>
    );
  }

  if (pedidos.length === 0) {
    return (
      <View style={styles.emptyContainer}>
        <Text style={styles.emptyText}>
          Nenhum pedido encontrado
        </Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>
        Histórico de Pedidos
      </Text>

      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={styles.list}
      >
        {pedidos.map((pedido) => (
          <View
            key={pedido.idPedido}
            style={styles.card}
          >
            <View style={styles.header}>
              <Text style={styles.pedido}>
                Pedido #{pedido.idPedido}
              </Text>
            </View>

            <Text style={styles.address}>
              {pedido.rua}, {pedido.numero}
            </Text>

            <Text style={styles.address}>
              {pedido.bairro} - {pedido.cidade}
            </Text>

            {pedido.produtos.map((produto, index) => (
              <Text
                key={index}
                style={styles.product}
              >
                🛢️ {produto.nomeProduto}
              </Text>
            ))}

            <View style={styles.footer}>
              <Text style={styles.date}>
                {new Date(
                  pedido.dataPedido
                ).toLocaleDateString("pt-BR")}
              </Text>

              <Text style={styles.price}>
                R$ {pedido.valorCompra.toFixed(2)}
              </Text>
            </View>
          </View>
        ))}
      </ScrollView>
    </View>
  );
}