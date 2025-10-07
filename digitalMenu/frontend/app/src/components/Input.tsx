import React from "react";
import { TextInput, View, Text, TextInputProps } from "react-native";

type InputProps = TextInputProps & {
  placeholder: string;
  value: string;
  onChangeText: (text: string) => void;
  secureTextEntry?: boolean;
  error?: string; 
};

export function Input({
  placeholder,
  value,
  onChangeText,
  secureTextEntry,
  error,
  ...rest
}: InputProps) {
  return (
    <View className="w-full mb-4">
      <TextInput
        className="w-full border border-gray-300 rounded-xl px-4 py-3 text-base bg-white text-gray-900"
        placeholder={placeholder}
        value={value}
        onChangeText={onChangeText}
        secureTextEntry={secureTextEntry}
        placeholderTextColor="#9CA3AF"
        {...rest} // passa qualquer outra prop do TextInput
      />
      {error && <Text className="text-red-500 mt-1">{error}</Text>}
    </View>
  );
}

export default Input;
