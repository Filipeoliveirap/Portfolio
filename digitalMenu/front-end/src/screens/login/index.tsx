import React from "react";
import { View, Text, Image, TextInput, TouchableOpacity } from "react-native";
import { styles } from "./styles";
import ProfileLogo from "../../assets/profileLogo.jpeg";
import { theme } from "../../theme/theme";
import { MaterialIcons } from "@expo/vector-icons";

export default function Login() {
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");

  const handleLoginPress = () => {
    console.log("login clicado: ", email, password);
  };
  return (
    <View style={styles.container}>
      <View style={styles.boxTop}>
        <Image style={styles.logo} source={ProfileLogo} />
        <Text style={styles.title}>Bem vindo de volta!</Text>
      </View>
      <View style={styles.boxMid}>
        <Text style={styles.titleInput}>ENDEREÇO DE E-MAIL</Text>
        <View style={styles.boxInput}>
          <TextInput
            style={styles.input}
            placeholder="Digite seu e-mail"
            placeholderTextColor={theme.colors.placeholder}
            autoCapitalize="none"
            value={email}
            onChangeText={(text) => setEmail(text)}
          />
          <MaterialIcons name="email" size={20} color={theme.colors.primary} />
        </View>
        <Text style={styles.titleInput}>SENHA</Text>
        <View style={styles.boxInput}>
          <TextInput
            style={styles.input}
            placeholder="Digite sua senha"
            placeholderTextColor={theme.colors.placeholder}
            secureTextEntry={true}
            value={password}
            onChangeText={(text) => setPassword(text)}
          />
          <MaterialIcons name="lock" size={20} color={theme.colors.primary} />
        </View>
      <Text style={styles.textMid}>Esqueceu sua senha ?</Text>
      </View>
      <View style={styles.boxBottom}>
        <TouchableOpacity style={styles.button} onPress={handleLoginPress}>
          <Text style={styles.buttonText}>ENTRAR</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.bottomTextContainer}>
        <Text style={styles.textBottom}>Não tem conta? </Text>
        <TouchableOpacity onPress={() => console.log("Navegar para criar conta")}>
          <Text style={styles.textBottomLink}>Criar agora!</Text>
        </TouchableOpacity>

      </View>
    </View>
  );
}
