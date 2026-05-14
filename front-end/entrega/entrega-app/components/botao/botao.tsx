import { View, Text, TouchableOpacity, ActivityIndicator } from "react-native";
import { styles } from "./style";

type Props = {
  title: string;
  onPress: () => void;
  loading?: boolean;
  disabled?: boolean;
};

export default function Botao({
  title,
  onPress,
  loading = false,
  disabled = false,
}: Props) {
  return (
    <TouchableOpacity
      style={[
        styles.button,
        (disabled || loading) && { opacity: 0.6 },
      ]}
      activeOpacity={0.8}
      onPress={onPress}
      disabled={disabled || loading}
    >
      {loading ? (
        <ActivityIndicator color="#FFF" />
      ) : (
        <Text style={styles.buttonText}>
          {title}
        </Text>
      )}
    </TouchableOpacity>
  );
}