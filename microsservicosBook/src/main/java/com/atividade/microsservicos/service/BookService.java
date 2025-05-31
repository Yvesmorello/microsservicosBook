package com.atividade.microsservicos.service;

import com.atividade.microsservicos.model.Book;
import com.atividade.microsservicos.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    public Book updateBook(Long id, Book book) {
        Book bookFound = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        bookFound.setTitle(book.getTitle());
        bookFound.setAuthor(book.getAuthor());
        bookFound.setIsbn(book.getIsbn());

        return repository.save(bookFound);
    }

    public Book updateStatus(Long id, String status) {
        Book bookFound = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        bookFound.setStatus(status);

        return repository.save(bookFound);
    }



}
