package com.edcarvalho.book_manager.infrastructure.repository;

import com.edcarvalho.book_manager.infrastructure.entitys.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Implementação da interface JPARepository precisa da entidade de dados e o tipo da chave primária da mesma
// Convenção: a interface de repositório deve estar no mesmo pacote que a entidade de dados ou em um pacote relacionado
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookById(Integer id);

    // Garante o rollback da transação em caso de falhas revertendo alterações, evitando inconsistências no DB
    @Transactional
    void deleteBookById(Integer id);
}