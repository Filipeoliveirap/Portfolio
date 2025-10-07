import { zodResolver } from "@hookform/resolvers/zod";
import React from "react";
import { Controller, useForm } from "react-hook-form";
import { Text, View } from "react-native";
import { z } from "zod";

import Button from "../components/Button";
import Input from "../components/Input";

const loginSchema = z.object({
  email: z.string().email({ message: "E-mail inválido" }),
  password: z
    .string()
    .min(6, { message: "Senha deve ter pelo menos 6 caracteres" }),
});

type LoginFormData = z.infer<typeof loginSchema>;

export default function LoginScreen() {
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginFormData>({
    resolver: zodResolver(loginSchema),
  });

  const onSubmit = (data: LoginFormData) => {
    console.log("Dados do login:", data);
    // Aqui você chamaria seu serviço que faz login via API
  };

  return (
    <View className="flex-1 justify-center px-6 bg-background">
      <Text className="text-3xl font-bold text-primary mb-6">Login</Text>

      <Controller
        control={control}
        name="email"
        render={({ field: { value, onChange } }) => (
          <Input
            placeholder="E-mail"
            value={value}
            onChangeText={onChange}
            error={errors.email?.message}
            keyboardType="email-address"
          />
        )}
      />

      <Controller
        control={control}
        name="password"
        render={({ field: { value, onChange } }) => (
          <Input
            placeholder="Senha"
            value={value}
            onChangeText={onChange}
            error={errors.password?.message}
            secureTextEntry
          />
        )}
      />

      <Button onPress={handleSubmit(onSubmit)} className="mt-4">
        Entrar
      </Button>
    </View>
  );
}
