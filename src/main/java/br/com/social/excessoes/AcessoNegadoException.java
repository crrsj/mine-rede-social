package br.com.social.excessoes;

public class AcessoNegadoException extends RuntimeException{
    public AcessoNegadoException(String mensagem){
        super(mensagem);
    }
}
