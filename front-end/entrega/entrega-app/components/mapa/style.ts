import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  mapContainer: {
    height: 380,
    overflow: "hidden",
    borderRadius: 32,
    marginTop: 20,
    borderWidth: 1,
    borderColor: "#1E293B",
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 12,
    },
    shadowOpacity: 0.2,
    shadowRadius: 20,
    elevation: 12,
  },
    mapContainerMb: {
    height: 180,
    overflow: "hidden",
    borderRadius: 32,
    marginTop: 20,
    borderWidth: 1,
    borderColor: "#1E293B",
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 12,
    },
    shadowOpacity: 0.2,
    shadowRadius: 20,
    elevation: 12,
  },

  map: {
    width: "100%",
    height: "100%",
  },

  loadingContainer: {
    height: 380,
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 32,
    backgroundColor: "#172033",
    marginTop: 20,
  },

  mapText: {
    color: "#94A3B8",
    fontSize: 16,
    fontWeight: "600",
  },
});