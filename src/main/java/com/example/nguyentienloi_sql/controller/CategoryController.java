package com.example.nguyentienloi_sql.controller;

import com.example.nguyentienloi_sql.entity.Book;
import com.example.nguyentienloi_sql.entity.Category;
import com.example.nguyentienloi_sql.services.BookService;
import com.example.nguyentienloi_sql.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private BookService bookService;
    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("title", "Categories List");
        return "category/list";
    }



    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }


    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "category/add";
        }
        categoryService.addCategory(category);

        return "redirect:/categories";
    }


    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") long id, Model model){
        Category editCategory = categoryService.getCategoryById(id);
        if(editCategory != null){
            model.addAttribute("category", editCategory);

            return "category/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book")Category updateCategory, BindingResult bindingResult ){
        if (bindingResult.hasErrors()){

            return "category/edit";
        }
        categoryService.getAllCategories().stream()
                .filter(category -> category.getId() == updateCategory.getId())
                .findFirst()
                .ifPresent(category -> {
                    categoryService.updateCategory(updateCategory);
                });
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping ("/delete/{id}")
    public String deleteBook (@PathVariable ("id") Long id)  {

        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/details/{id}")
    public String listByCategory(@PathVariable("id") long id, Model model){
        List<Book> list = bookService.getAllBooks().stream()
                .filter(book -> book.getCategory().getId() == id).toList();
               
                
        model.addAttribute("list",list);
        model.addAttribute("title", categoryService.getCategoryById(id).getName());
        return "category/details";
    }
    
}
