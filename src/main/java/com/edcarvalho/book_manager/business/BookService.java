package com.edcarvalho.book_manager.business;

import com.edcarvalho.book_manager.infrastructure.BookDTO;
import com.edcarvalho.book_manager.infrastructure.entitys.Book;
import com.edcarvalho.book_manager.infrastructure.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/* Indica que a classe é um serviço que fornece uma lógica de negócios específica, distinta
de outras classes que podem ter propósitos diferentes */
@Service
public class BookService {
    private final BookRepository repository;

    /* Injeção de dependência feita manualmente, porém, pode ser feita automaticamente usando
     a anotação @Autowired no campo/atributo que representa o repositório */
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // Salva e encerra a conexão com o DB
    public Book saveBook(BookDTO bookDTO) {
        Book book = Book.builder()
                .title(bookDTO.title())
                .author(bookDTO.author())
                .build();

        repository.saveAndFlush(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    /* Por ser um retorno Optional<> que evita a NullPointerException, é necessário o lançamento
      de exceção para capturar o cenário onde o Objeto em questão não existe ou fornecer uma
      outra alternativa */
    public Book getBook(Integer id) {
        return repository.findBookById(id).orElseThrow(
                () -> new RuntimeException("Identificador fornecido é inválido.")
        );
    }

    public void updateBook(Integer id, BookDTO bookDTO) {
        Book bookEntity = repository.findBookById(id).orElseThrow(
                () -> new RuntimeException("Livro não encontrado")
        );
        Book updatedBook = Book.builder()
                .id(bookEntity.getId())
                .title(bookDTO.title() != null
                        ? bookDTO.title() : bookEntity.getTitle())
                .author(bookDTO.author() != null
                        ? bookDTO.author() : bookEntity.getAuthor())
                .build();

        repository.saveAndFlush(updatedBook);
    }

    public void deleteBook(Integer id) {
        repository.deleteBookById(id);
    }
}