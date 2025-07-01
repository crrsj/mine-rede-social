package br.com.social.excessoes;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
