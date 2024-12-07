package com.poc.gerped.exception;

public class ServicosException extends Exception {


    private boolean erroNegocio;


    public ServicosException(String mensagem) {
        super(mensagem);
    }

    public ServicosException(String mensagem, boolean erroNegocio) {
        super(mensagem);
        this.erroNegocio = erroNegocio;
    }

    public ServicosException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }


    public ServicosException(String mensagem, Throwable causa, boolean erroNegocio) {
        super(mensagem, causa);
        this.erroNegocio = erroNegocio;
    }

    public boolean isErroNegocio() {
        return erroNegocio;
    }
}