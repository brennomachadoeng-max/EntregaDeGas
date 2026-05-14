import { TextInput, TextInputProps, View, Text } from "react-native";
import { styles } from "./style";

import {
  onlyNumbers,
  formatCPF,
  formatEmail,
  formatDate,
  formatPhone,
} from "../../../util/inputFormatters";

type Props = TextInputProps & {
  value: string;
  onChangeText: (text: string) => void;
  type?: "text" | "cpf" | "email" | "date" | "phone";
  error?: boolean;
  errorMessage?: string;
};

export default function Input({
  value,
  onChangeText,
  type = "text",
  error = false,
  errorMessage = "Campo inválido",
  ...rest
}: Props) {
  function handleChange(text: string) {
    if (type === "cpf") {
      const cleaned = onlyNumbers(text).slice(0, 11);
      onChangeText(formatCPF(cleaned));
      return;
    }

    if (type === "email") {
      onChangeText(formatEmail(text));
      return;
    }

    if (type === "date") {
      const cleaned = onlyNumbers(text).slice(0, 8);
      onChangeText(formatDate(cleaned));
      return;
    }

    if (type === "phone") {
      const cleaned = onlyNumbers(text).slice(0, 11);
      onChangeText(formatPhone(cleaned));
      return;
    }

    onChangeText(text);
  }

  return (
    <View>
      <TextInput
        {...rest}
        style={[
          styles.input,
          error && {
            borderColor: "#FF4D4F",
            borderWidth: 1.5,
          },
        ]}
        value={value}
        onChangeText={handleChange}
        placeholderTextColor="#999"
        autoCapitalize="none"
      />

      {error && (
        <Text style={styles.errorText}>
          {errorMessage}
        </Text>
      )}
    </View>
  );
}