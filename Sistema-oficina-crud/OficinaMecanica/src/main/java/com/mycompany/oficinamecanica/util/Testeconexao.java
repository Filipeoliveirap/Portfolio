package com.mycompany.oficinamecanica.util;

import com.mycompany.oficinamecanica.util.Conexao;
import java.sql.Connection;

public class Testeconexao {
    public static void main(String[] args){
        try (Connection conn = Conexao.getConnection()){
            System.out.println("Conexão bem-sucedida");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
