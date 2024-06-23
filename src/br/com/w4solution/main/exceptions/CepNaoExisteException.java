package br.com.w4solution.main.exceptions;

public class CepNaoExisteException extends RuntimeException {

    private String mensagem;

    public CepNaoExisteException(String mensagem) {
        this.mensagem = mensagem;
    }
    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
