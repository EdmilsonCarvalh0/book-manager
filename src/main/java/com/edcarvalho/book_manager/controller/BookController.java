package com.edcarvalho.book_manager.controller;

import com.edcarvalho.book_manager.business.BookService;
import com.edcarvalho.book_manager.infrastructure.BookDTO;
import com.edcarvalho.book_manager.infrastructure.entitys.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // Indica que a classe é um controlador REST.
                    // O Spring reconhece que os métodos devem ser tratados como handlers de requisições HTTP.
@RequestMapping("/book") // Especifica a URL base para os métodos da classe
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) { // Injeta o valor de uma variável de caminho nos métodos. {id}
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping
    // Injeta o corpo da requisição nos métodos. Recebe dados de formulários ou objetos Json. {book}
    public ResponseEntity<Book> saveBook(@RequestBody BookDTO bookDTO) {
        var book = bookService.saveBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(@RequestParam Integer id, @RequestBody BookDTO bookDTO) {
        bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBookById(@RequestParam Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}