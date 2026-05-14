import React, { useEffect, useRef } from "react";
import { Animated, View, Platform } from "react-native";

import { styles } from "./style";

type Props = {
  children: React.ReactNode;
};

export default function AnimatedCadastro({ children }: Props) {
  const fadeAnim = useRef(new Animated.Value(0)).current;
  const translateAnim = useRef(new Animated.Value(40)).current;

  const useNative = Platform.OS !== "web";

  useEffect(() => {
    Animated.parallel([
      Animated.timing(fadeAnim, {
        toValue: 1,
        duration: 800,
        useNativeDriver: useNative,
      }),

      Animated.timing(translateAnim, {
        toValue: 0,
        duration: 800,
        useNativeDriver: useNative,
      }),
    ]).start();
  }, []);

  return (
    <View style={styles.container}>
      <Animated.View
        style={[
          styles.wrapper,
          {
            opacity: fadeAnim,
            transform: [{ translateY: translateAnim }],
          },
        ]}
      >
        {children}
      </Animated.View>
    </View>
  );
}