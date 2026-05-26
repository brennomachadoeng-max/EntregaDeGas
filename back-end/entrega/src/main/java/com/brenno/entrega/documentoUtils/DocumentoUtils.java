package com.brenno.entrega.documentoUtils;

public final class DocumentoUtils {

    private DocumentoUtils() {}

    public static String somenteNumeros(String valor) {
        return valor == null ? null : valor.replaceAll("\\D", "");
    }

    public static boolean possuiTamanhoCpf(String cpf) {
        return somenteNumeros(cpf).length() == 11;
    }

    public static boolean possuiTamanhoCnpj(String cnpj) {
        return somenteNumeros(cnpj).length() == 14;
    }

    public static boolean possuiTamanhoTelefone(String telefone) {
        int tamanho = somenteNumeros(telefone).length();
        return tamanho == 11;
    }

    public static boolean verificarEmail(String email) {
        return email != null && email.contains("@") ;
    }
}
