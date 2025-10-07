import { useNavigation } from "@react-navigation/native";
import { MotiView } from "moti";
import React from "react";
import { Image, Text, TouchableOpacity, View } from "react-native";
import { NativeStackNavigationProp } from '@react-navigation/native-stack';
import { RootStackParamList } from '../navigation/types';

type LoginScreenNavigationProp = NativeStackNavigationProp<RootStackParamList>;

export default function WelcomeScreen() {
  const navigation = useNavigation<LoginScreenNavigationProp>();

  return (
    <View className="flex-1 bg-gradient-to-b from-amber-100 to-orange-50 justify-center items-center px-6">
      <MotiView
        from={{ opacity: 0, translateY: 40 }}
        animate={{ opacity: 1, translateY: 0 }}
        transition={{ type: "timing", duration: 800 }}
        className="w-full items-center"
      >
        <Image
          source={require("../../../assets/images/food-illustration.png")}
          className="w-64 h-64 mb-10"
          resizeMode="contain"
        />

        <Text className="text-3xl font-bold text-amber-800 mb-4 text-center">
          Bem-vindo ao Digital Menu
        </Text>

        <Text className="text-base text-amber-600 text-center mb-10">
          Peça com facilidade, rapidez e praticidade direto do seu celular.
        </Text>

        <TouchableOpacity
          onPress={() => navigation.navigate("Login")}
          className="bg-amber-500 w-full py-4 rounded-2xl mb-4"
        >
          <Text className="text-white text-center font-semibold text-lg">
            Entrar
          </Text>
        </TouchableOpacity>

        <TouchableOpacity
          onPress={() => navigation.navigate("Signup")}
          className="border border-amber-500 w-full py-4 rounded-2xl"
        >
          <Text className="text-amber-600 text-center font-semibold text-lg">
            Criar conta
          </Text>
        </TouchableOpacity>
      </MotiView>
    </View>
  );
}
