package gic.i4c.GicCafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import gic.i4c.GicCafe.model.Drink;
import gic.i4c.GicCafe.model.Drink_category;
import gic.i4c.GicCafe.service.CategoryService;

@RestController
public class categoryController {
    private CategoryService categoryService;

    public categoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @GetMapping("/new_drink")
    public String listCategory(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("drink", new Drink());
        return "addNewDrink";
    }

    @GetMapping("/new_drink/new_category")
    public String createCategory(Model model){
        Drink_category newCategory = new Drink_category();
        model.addAttribute("category", newCategory);
        return "AddCategories";
    }

    @PostMapping("/new_drink/save_category")
    public String saveCategory(@RequestParam("code") String code, @RequestParam("name") String name){
        Drink_category category = new Drink_category();
        category.setName(name);
        category.setCode(code);
        categoryService.saveCategory(category);
        return "redirect:/new_drink";
    }
}
