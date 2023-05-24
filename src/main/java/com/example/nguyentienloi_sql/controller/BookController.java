package com.example.nguyentienloi_sql.controller;

import com.example.nguyentienloi_sql.entity.Book;
import com.example.nguyentienloi_sql.services.BookService;
import com.example.nguyentienloi_sql.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

/*    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }*/

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            //model.addAttribute("book", new Book());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }


    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        Book editBook = bookService.getBookById(id);
        if(editBook != null){
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book")Book updateBook, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        bookService.getAllBooks().stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {
                    bookService.updateBook(updateBook);
                });
        return "redirect:/books";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping ("/delete/{id}")
    public String deleteBook (@PathVariable ("id") Long id)  {

        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
