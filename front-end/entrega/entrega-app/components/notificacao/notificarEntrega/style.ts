import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  container: {
    position: "absolute",
    top: 20,
    left: 16,
    right: 16,
    backgroundColor: "#FFFFFF",
    borderRadius: 12,
    padding: 16,
    zIndex: 999,
    elevation: 8,

    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 4,
    },
    shadowOpacity: 0.25,
    shadowRadius: 6,
  },

  titulo: {
    fontSize: 18,
    fontWeight: "700",
    color: "#1F2937",
    marginBottom: 8,
  },

  mensagem: {
    fontSize: 15,
    color: "#4B5563",
    marginBottom: 16,
  },

  botoes: {
    flexDirection: "row",
    justifyContent: "space-between",
    gap: 12,
  },

  botaoAceitar: {
    flex: 1,
    backgroundColor: "#22C55E",
    paddingVertical: 12,
    borderRadius: 8,
    alignItems: "center",
  },

  botaoRecusar: {
    flex: 1,
    backgroundColor: "#EF4444",
    paddingVertical: 12,
    borderRadius: 8,
    alignItems: "center",
  },

  textoBotao: {
    color: "#FFFFFF",
    fontSize: 15,
    fontWeight: "600",
  },
});