import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  section: {
    marginTop: 20,
  },

  sectionTitle: {
    fontSize: 18,
    fontWeight: "800",
    color: "#0F172A",
    marginBottom: 12,
  },

  loadingContainer: {
    padding: 20,
    alignItems: "center",
    justifyContent: "center",
  },

  emptyContainer: {
    padding: 20,
    alignItems: "center",
    justifyContent: "center",
  },

  emptyText: {
    color: "#64748B",
    fontSize: 14,
    textAlign: "center",
  },

  card: {
    flexDirection: "row",
    alignItems: "center",

    backgroundColor: "#FFFFFF",
    padding: 16,

    borderRadius: 18,
    marginBottom: 12,

    borderWidth: 1,
    borderColor: "#E2E8F0",

    shadowColor: "#000",
    shadowOpacity: 0.05,
    shadowRadius: 10,
    elevation: 2,
  },

  iconBox: {
    width: 48,
    height: 48,
    borderRadius: 14,
    backgroundColor: "#F1F5F9",
    justifyContent: "center",
    alignItems: "center",
    marginRight: 12,
  },

  icon: {
    fontSize: 22,
  },

  content: {
    flex: 1,
  },

  title: {
    fontSize: 16,
    fontWeight: "700",
    color: "#0F172A",
  },

  price: {
    marginTop: 2,
    fontSize: 14,
    fontWeight: "600",
    color: "#16A34A",
  },

  description: {
    marginTop: 4,
    fontSize: 12,
    color: "#64748B",
  },

  arrow: {
    fontSize: 24,
    color: "#94A3B8",
    marginLeft: 8,
  },
});