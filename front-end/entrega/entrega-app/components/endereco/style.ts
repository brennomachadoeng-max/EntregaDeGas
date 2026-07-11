import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  container: {
    gap: 10,
  },

  title: {
    fontSize: 18,
    fontWeight: "700",
    color: "#222",
  },

  newAddressCard: {
    padding: 14,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: "#2e7d32",
    backgroundColor: "#eaf6ec",
  },

  newAddressText: {
    fontSize: 15,
    fontWeight: "700",
    color: "#2e7d32",
  },

  form: {
    gap: 10,
  },

  input: {
    height: 46,
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 8,
    paddingHorizontal: 12,
    backgroundColor: "#fff",
  },

  inputError: {
    borderColor: "#d32f2f",
  },

  saveButton: {
    height: 46,
    borderRadius: 8,
    backgroundColor: "#2e7d32",
    alignItems: "center",
    justifyContent: "center",
  },

  saveButtonText: {
    color: "#fff",
    fontWeight: "700",
  },

  addressCard: {
    padding: 14,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: "#ddd",
    backgroundColor: "#fff",
  },

  addressCardSelected: {
    borderColor: "#2e7d32",
    backgroundColor: "#eaf6ec",
  },

  addressRow: {
    flexDirection: "row",
    alignItems: "center",
    gap: 12,
  },

  addressIdBox: {
    width: 42,
    height: 42,
    borderRadius: 8,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#f1f1f1",
  },

  addressIdText: {
    fontSize: 16,
    fontWeight: "700",
    color: "#333",
  },

  addressInfo: {
    flex: 1,
  },

  addressTitle: {
    fontSize: 15,
    fontWeight: "700",
    color: "#222",
  },

  addressText: {
    marginTop: 3,
    fontSize: 14,
    color: "#666",
  },

  emptyText: {
    fontSize: 14,
    color: "#777",
  },
});