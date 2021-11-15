package com.cuongtien.controller;

import com.cuongtien.model.Book;
import com.cuongtien.model.Category;
import com.cuongtien.service.book.BookService;
import com.cuongtien.service.book.IBookService;
import com.cuongtien.service.category.CategoryService;
import com.cuongtien.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {
    private IBookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String action = request.getParameter("action");
if(action == null){
    action ="";
}
switch (action){
    case "create":
        showCreateForm(request,response);
        break;
    case "edit":
        showEditForm(request,response);
        break;
    case "delete":
        deleteBook(request,response);
        break;
    case "view":
        showBookInformation(request,response);
        break;
    default:
        listBooks(request,response);
        break;
}
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            bookService.delete(id);
            response.sendRedirect("/books");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showBookInformation(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/edit.jsp");
        request.setAttribute("book",book);
        request.setAttribute("cateListOfBook",book.getCategories());
        request.setAttribute("subList",categoryService.findAll());
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/list.jsp");
        request.setAttribute("bookList",bookService.findAll());
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
        request.setAttribute("categoryList",categoryService.findAll());
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create":
                createBook(request,response);
                break;
            case "edit":
                updateBook(request,response);
                break;
            default:
                listBooks(request,response);
                break;
        }

    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/edit.jsp");
        List<Category> categoryList = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String des = request.getParameter("des");
        String[] idString = request.getParameterValues("categories");
        int[] categories = new int[idString.length];
        for (int i = 0; i < idString.length; i++) {
            categories[i] = Integer.parseInt(idString[i]);
        }
        for (int a:categories
             ) {
            Category category = categoryService.findById(a);
            categoryList.add(category);
        }
        Book book = new Book(id,name,price,des);
        book.setCategories(categoryList);
        request.setAttribute("subList",categoryService.findAll());
        request.setAttribute("message","Book was updated!");
        request.setAttribute("book",book);

        try {
            bookService.update(book,categories);
            dispatcher.forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void createBook(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String des = request.getParameter("des") ;
        String[] categoriesStr = request.getParameterValues("categories");
        int[] categoriesInt = new int[categoriesStr.length];
        for (int i = 0; i < categoriesStr.length; i++) {
            categoriesInt[i] = Integer.parseInt(categoriesStr[i]);
        }
        Book book = new Book(name,price,des);
        try {
            bookService.insert(book,categoriesInt);
            request.setAttribute("message","Book was Created!");
            request.setAttribute("categoryList",categoryService.findAll());
            dispatcher.forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
