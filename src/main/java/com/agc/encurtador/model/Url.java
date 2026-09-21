package com.agc.encurtador.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// A annotation @Entity indica que a classe deve ser mapeada e convertida numa tabela no DB.
@Entity
@Table(name = "urls")
public class Url {

    // A annotation @Id indica que o id é a primary key da tabela. O @GeneratedValue gera o ID automaticamente por auto-incremento.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A annotation @Column dita as regras para a coluna na tabela.
    // nullable -> Dita se o atributo pode ser nulo ou não na tabela.
    // length -> Dita o tamanho máximo de caracteres do dado.
    // unique -> Dita o se o atributo deve ser único ou não na tabela.
    @Column(nullable = false, length = 2048)
    private String urlOriginal;

    @Column(nullable = false, unique = true, length = 10)
    private String codigoCurto;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private int contadorCliques = 0;

    // Construtor vazio necessário para consultas no DB.
    public Url() {
    }

    public Url(String urlOriginal, String codigoCurto) {
        this.urlOriginal = urlOriginal;
        this.codigoCurto = codigoCurto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public String getCodigoCurto() {
        return codigoCurto;
    }

    public void setCodigoCurto(String codigoCurto) {
        this.codigoCurto = codigoCurto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getContadorCliques() {
        return contadorCliques;
    }

    public void setContadorCliques(int contadorCliques) {
        this.contadorCliques = contadorCliques;
    }
}
