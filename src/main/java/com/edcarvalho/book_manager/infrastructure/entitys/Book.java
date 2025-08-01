package com.edcarvalho.book_manager.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter                 // Lombok: gera automaticamente os métodos Getters e Setters para os campos da classe
@Setter
@AllArgsConstructor     // Lombok: gera respectivamente um construtor que aceita todos os campos da classe como parâmetros
@NoArgsConstructor      // e um construtor sem parâmetros
@Builder                // Lombok: gera automaticamente um builder para a classe permitindo a criação de objetos mais fluida e legível
@Table (name = "books") // JPA (Java Persistence API): specifica o nome da tabela no DB que corresponde à classe
@Entity                 // JPA: indica que a classe é uma entidade de dados, ou seja, uma tabela no DB

public class Book {

    @Id                 // Indica que o campo é uma chave primária para a tabela
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Indica que o valor da chave primária deve ser gerada automaticamente
    private Integer id;

    @Column (name = "title") //Especifica o nome das colunas no DB
    private String title;

    @Column (name = "author")
    private String author;
}
