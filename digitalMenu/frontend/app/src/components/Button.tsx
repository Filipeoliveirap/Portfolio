import React from "react";
import { Text, TouchableOpacity } from "react-native";
type ButtonProps = {
  children: React.ReactNode;
  onPress: () => void;
  variant?: "primary" | "secondary";
  className?: string;
};
export function Button({
  children,
  onPress,
  variant = "primary",
  className,
}: ButtonProps) {
  const bgColor =
    variant === "primary"
      ? "bg-blue-600 active:bg-blue-700"
      : "bg-gray-200 border border-gray-300 active:bg-gray-300";
  const textColor = variant === "primary" ? "text-white" : "text-gray-800";

  return (
    <TouchableOpacity
      onPress={onPress}
      className={`w-full py-3 rounded-xl items-center justify-center ${bgColor} ${
        className ?? ""
      }`}
      activeOpacity={0.8}
    >
      <Text className={`font-semibold text-lg ${textColor}`}>{children}</Text>
    </TouchableOpacity>
  );
}

export default Button;
