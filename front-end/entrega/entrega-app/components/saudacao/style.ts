import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
    container: {
    padding: 20,
    paddingBottom: 50,
  },

  header: {
    marginTop: 30,
    marginBottom: 25,

    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },

  location: {
    color: "#FBBF24",
    fontSize: 14,
    fontWeight: "700",
  },

  greeting: {
    color: "#FFFFFF",
    fontSize: 34,
    fontWeight: "800",
    marginTop: 8,
  },

  subtitle: {
    color: "#94A3B8",
    marginTop: 10,
    fontSize: 16,
    lineHeight: 24,
  },

  profile: {
    width: 58,
    height: 58,

    borderRadius: 29,

    backgroundColor: "#1E293B",

    justifyContent: "center",
    alignItems: "center",

    borderWidth: 2,
    borderColor: "#334155",
  },

  profileText: {
    color: "#FFFFFF",
    fontWeight: "800",
    fontSize: 18,
  },
});