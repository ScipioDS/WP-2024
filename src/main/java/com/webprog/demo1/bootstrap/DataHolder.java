package com.webprog.demo1.bootstrap;
import com.webprog.demo1.model.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = null;

    // On application startup, initialize the categories list
    // On each startup, the list will be initialized with the same values and the previous values will be lost
    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Books", "Books category"));
    }

}
