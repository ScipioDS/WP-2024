package com.webprog.demo1.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "CategoryServlet", urlPatterns = "/cat/messy")
public class MessyCategoryServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public MessyCategoryServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    //In-Memory Categories
    @Getter
    @Setter
    @AllArgsConstructor
    class Category {
        private String name;
        private String description;
    }
    private List<Category> categories = null;
    private void addCategory(String name, String description){
        if(name.isEmpty() || name == null || description.isEmpty() || description == null){
            return;
        }
        Category cat = new Category(name, description);
        this.categories.add(cat);
    }

    //Initialize
    public void init() throws ServletException {
        categories = new ArrayList<>();

        addCategory("Movies", "Movies category");
        addCategory("Books", "Books category");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext wb = new WebContext(webExchange);

        wb.setVariable("ipAddress", req.getRemoteAddr());
        wb.setVariable("userAgent", req.getHeader("user-agent"));
        wb.setVariable("categories", categories);

        springTemplateEngine.process("categories.html", wb, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        addCategory(name, description);

        resp.sendRedirect("/cat");
    }

}
