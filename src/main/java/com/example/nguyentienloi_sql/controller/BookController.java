package com.example.nguyentienloi_sql.controller;

import com.example.nguyentienloi_sql.entity.Book;
import com.example.nguyentienloi_sql.services.BookService;
import com.example.nguyentienloi_sql.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBook(Model model) {

        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("title", "Book List");
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> editBook = bookService.getAllBooks().stream().filter(book -> book.getId().equals(id)).findFirst();
        if (editBook.isPresent()) {
            model.addAttribute("book", editBook.get());
            return "book/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book updatedBook) {
       /* var optionalBook = books.stream()
                .filter(book -> book.getId() == updatedBook.getId())
                .findFirst();
        optionalBook.ifPresent(book -> books.set(books.indexOf(book), updatedBook));*/
        bookService.updateBook(updatedBook);

        return "redirect:/books";
    }

    @GetMapping ("/delete/{id}")
    public String deleteBook (@PathVariable ("id") Long id)  {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
