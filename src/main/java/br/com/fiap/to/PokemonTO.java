package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class PokemonTO {
    private Long codigo;
    @NotBlank
    private String nome, categoria;
    @Positive
    private Double altura, peso;
    @PastOrPresent
    private LocalDate dataDeCaptura;

    public PokemonTO(){}

    public PokemonTO(Long codigo, String nome, String categoria, LocalDate dataDeCaptura, Double altura, Double peso) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.dataDeCaptura = dataDeCaptura;
        this.altura = altura;
        this.peso = peso;
    }

    public LocalDate getDataDeCaptura() {
        return dataDeCaptura;
    }


    public void setDataDeCaptura(LocalDate dataDeCaptura) {
        this.dataDeCaptura = dataDeCaptura;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
