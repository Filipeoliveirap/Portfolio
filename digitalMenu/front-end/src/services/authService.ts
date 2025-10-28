import { api } from "./api";
import { UsersEndpoints } from "../constants/api";

interface LoginRequest {
  email: string;
  password: string;
}

interface LoginResponse {
  token: string;
  user: {
    id: number;
    name: string;
    email: string;
    role: string;
  };
}

export async function loginUser(data: LoginRequest): Promise<LoginResponse> {
  try {
    const response = await api.post<LoginResponse>(UsersEndpoints.LOGIN, data);
    return response.data;
  } catch (error: any) {
    if (error.response) {
      throw new Error(
        error.response.data.message || "Ocorreu um erro ao fazer login."
      );
    } else {
      throw new Error("Erro de conexão com o servidor.");
    }
  }
}
