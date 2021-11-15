package com.cuongtien.service.book;

import com.cuongtien.config.ConnectMYSQL;
import com.cuongtien.model.Book;
import com.cuongtien.model.Category;
import com.cuongtien.service.category.CategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookService implements IBookService{
    public static final String INSERT_NEW_BOOK = "insert into book(name,price,description) value (?,?,?);";
    public static final String SELECT_ALL_FROM_BOOK = "select * from book;";
    public static final String INSERT_INTO_BOOK_CATEGORY_VALUE = "insert into book_category value (?,?);";
    public static final String UPDATE_BOOK_BY_ID = "update book set name=?,price=?, description = ? where id = ?";
    public static final String DELETE_FROM_BOOK_CATEGORY_BY_BOOK_ID = "delete from book_category where book_id =?;";
    public static final String INSERT_INTO_BOOK_CATEGORY_VALUE1 = "insert  into book_category value (?,?)";
    public static final String DELETE_FROM_BOOK_WHERE_ID = "delete from book where id = ?;";
    public static final String SELECT_FROM_BOOK_WHERE_ID = "select * from book where id=?;";
    public static final String SELECT_FROM_BOOK_CATEGORY_WHERE_BOOK_ID = "select*from book_category where book_id=?;";
    private CategoryService categoryService = new CategoryService();
    private String jdbcURL = "jdbc:mysql://localhost:3306/book_category_many_many?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Cuongtien1809";
//    private  Connection connection = getConnection();

//    protected Connection getConnection(){
//        Connection connection =null;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
Connection connection = ConnectMYSQL.getConnection();
    @Override
    public Book findById(int id) {
//        Connection connection = ;
        List<Category> categoryList = new ArrayList<>();
        Book book = null;

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_BOOK_WHERE_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String des = rs.getString("description");
                book = new Book(id,name,price,des);
            }
            PreparedStatement statement1 = connection.prepareStatement(SELECT_FROM_BOOK_CATEGORY_WHERE_BOOK_ID);
            statement1.setInt(1,id);
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()){
               int cate_id =  rs1.getInt("category_id");
               Category category = categoryService.findById(cate_id);
                categoryList.add(category);
            }
            book.setCategories(categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return  book;
    }

    @Override
    public List<Book> findAll() {
//        Connection connection = getConnection();
      List<Book> books = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_BOOK);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                List<Category> categories = new ArrayList<>();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String des = rs.getString("description");
                categories = categoryService.findAllCateOfBook(id);
                Book book = new Book(id,name,price,des,categories);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean insert(Book book,int[] categories) throws SQLException {
//        Connection connection = getConnection();
        boolean rowInserted;
        int book_id = 0;

        PreparedStatement statement = connection.prepareStatement(INSERT_NEW_BOOK,Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,book.getName());
        statement.setDouble(2,book.getPrice());
        statement.setString(3,book.getDes());

        rowInserted =statement.executeUpdate()>0;
//        hung id cua book
        ResultSet rs = statement.getGeneratedKeys();
        while (rs.next()){
            book_id = rs.getInt(1);
        }
        PreparedStatement statement1 = connection.prepareStatement(INSERT_INTO_BOOK_CATEGORY_VALUE);
        for (int cate : categories) {
            statement1.setInt(1,book_id);
            statement1.setInt(2,cate);
            statement1.executeUpdate();
        }
       return rowInserted;
    }

    @Override
    public boolean update(Book book,int[] categories) throws SQLException {

        boolean rowUpdated;
//        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_BY_ID);
        statement.setString(1,book.getName());
        statement.setDouble(2,book.getPrice());
        statement.setString(3,book.getDes());
        statement.setInt(4,book.getId());

        rowUpdated = statement.executeUpdate()>0;

        PreparedStatement statement1 = connection.prepareStatement(DELETE_FROM_BOOK_CATEGORY_BY_BOOK_ID);
        for (int a : categories
             ) {
            statement1.setInt(1,book.getId());
            statement1.executeUpdate();
        }

        PreparedStatement statement2 = connection.prepareStatement(INSERT_INTO_BOOK_CATEGORY_VALUE1);

        for (int cate : categories) {
            statement2.setInt(1,book.getId());
            statement2.setInt(2,cate);
            statement2.executeUpdate();
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
    boolean rowDeletedOfBook;
//        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(DELETE_FROM_BOOK_WHERE_ID);
        statement.setInt(1,id);
        rowDeletedOfBook  = statement.executeUpdate() >0;
return rowDeletedOfBook;
    }

    @Override
    public List<Book> sortByName() {
        return null;
    }
}
