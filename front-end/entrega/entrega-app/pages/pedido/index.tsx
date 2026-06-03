import React from "react";
import {
  ScrollView,
  View,
  Text,
  Pressable,
} from "react-native";

import { ListProdutos } from "../../components";
import { useCarrinho } from "../../hooks/carrinho/useCarrinho";
import { styles } from "./style";

export default function Pedido() {
  const {
    carrinho,
    adicionarProduto,
    removerProduto,
    total,
    limparCarrinho,
  } = useCarrinho();

  return (
    <ScrollView
      style={styles.screen}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    >
      {/* HEADER */}
      <Text style={styles.title}>Fazer Pedido</Text>

      <Text style={styles.subtitle}>
        Escolha os produtos e finalize seu pedido.
      </Text>

      {/* LISTA DE PRODUTOS (controle aqui dentro) */}
      <ListProdutos onAdd={adicionarProduto} />

      {/* CARRINHO */}
      <View style={styles.section}>
        <Text style={styles.sectionTitle}>
          Seu Carrinho
        </Text>

        {carrinho.length === 0 ? (
          <Text style={styles.emptyText}>
            Nenhum produto selecionado
          </Text>
        ) : (
          carrinho.map((item) => (
            <View
              key={item.produto.idProduto}
              style={styles.cartCard}
            >
              <View>
                <Text style={styles.cartTitle}>
                  {item.produto.nome}
                </Text>

                <Text style={styles.cartPrice}>
                  R$ {item.produto.valor.toFixed(2)}
                </Text>
              </View>

              <View style={styles.cartActions}>
                <Pressable
                  style={styles.qtyButton}
                  onPress={() =>
                    removerProduto(item.produto.idProduto)
                  }
                >
                  <Text style={styles.qtyText}>-</Text>
                </Pressable>

                <Text style={styles.qtyValue}>
                  {item.quantidade}
                </Text>

                <Pressable
                  style={styles.qtyButton}
                  onPress={() =>
                    adicionarProduto(item.produto)
                  }
                >
                  <Text style={styles.qtyText}>+</Text>
                </Pressable>
              </View>
            </View>
          ))
        )}
      </View>

      {/* RESUMO */}
      <View style={styles.resumeCard}>
        <Text style={styles.resumeTitle}>
          Resumo do Pedido
        </Text>

        <View style={styles.resumeRow}>
          <Text style={styles.resumeLabel}>
            Total de itens
          </Text>

          <Text style={styles.resumeValue}>
            {carrinho.reduce(
              (acc, item) => acc + item.quantidade,
              0
            )}
          </Text>
        </View>

        <View style={styles.divider} />

        <View style={styles.resumeRow}>
          <Text style={styles.totalLabel}>
            Total
          </Text>

          <Text style={styles.totalValue}>
            R$ {total.toFixed(2)}
          </Text>
        </View>
      </View>

      {/* AÇÕES */}
      <View style={styles.actions}>
        <Pressable style={styles.confirmButton}>
          <Text style={styles.confirmButtonText}>
            CONFIRMAR PEDIDO
          </Text>
        </Pressable>
        <Pressable
          style={styles.clearButton}
          onPress={limparCarrinho}
        >
          <Text style={styles.clearButtonText}>
            LIMPAR
          </Text>
        </Pressable>
      </View>
    </ScrollView>
  );
}