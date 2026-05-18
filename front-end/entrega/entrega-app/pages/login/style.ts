import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({

  keyboard: {
    flex: 1,
    backgroundColor: "#EEF2F7",
  },

  scroll: {
    flexGrow: 1,

    width: "100%",

    justifyContent: "center",
    alignItems: "center",

    padding: 25,
  },

  form: {
    width: "100%",
    display: "flex",
    gap: 20,
  },

  card: {
    width: "100%",
    maxWidth: 480,

    backgroundColor: "#FFFFFF",

    borderRadius: 32,

    paddingVertical: 40,
    paddingHorizontal: 28,

    shadowColor: "#000",

    shadowOffset: {
      width: 0,
      height: 20,
    },

    shadowOpacity: 0.12,

    shadowRadius: 30,

    elevation: 12,

    borderWidth: 1,
    borderColor: "#F1F3F5",
  },

  title: {
    fontSize: 36,

    fontWeight: "800",

    color: "#111827",

    textAlign: "center",

    marginBottom: 10,

    letterSpacing: -1,
  },

  subtitle: {
    fontSize: 16,

    color: "#434750",

    textAlign: "center",

    marginBottom: 35,

    lineHeight: 24,
  },

  errorText: {
    color: "#DC2626",

    fontSize: 14,

    textAlign: "center",

    marginTop: -10,
  },
  linksContainer: {
    marginTop: 25,
    gap: 14,
    alignItems: "center",
  },
  link: {
    color: "#747474",
    fontSize: 15,
    fontWeight: "600",
  },
  linkHover:{
    color: "#00184b",
  },
});