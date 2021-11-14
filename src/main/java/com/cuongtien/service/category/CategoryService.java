package com.cuongtien.service.category;

import com.cuongtien.model.Book;
import com.cuongtien.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/book_category_many_many?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Cuongtien1809";
    private  Connection connection = getConnection();
    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public Category findById(int id) {
        Category category = null;
        try {
//           /lay thong tin cua book
            PreparedStatement statement = connection.prepareStatement("select * from category where id = ?;");
       statement.setInt(1,id);
       ResultSet rs = statement.executeQuery();
       while (rs.next()){
           String name = rs.getString("name");
           String des = rs.getString("description");
           category = new Category(id,name,des);
       }
//
        } catch (SQLException e) {
            e.printStackTrace();
        }

return category;
    }
    public List<Category> findAllCateOfBook(int book_id){
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select *from book_category where book_id = ?;");
            statement.setInt(1,book_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int cate_id = rs.getInt("category_id");
                Category category = findById(cate_id);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;

    }
    public List<Category> subList(List<Category> categories){
        List<Category>categoryListSun = new ArrayList<>();
        List<Category> categoryListFather = findAll();
        for (Category c: categoryListFather
             ) {
            boolean check = categories.contains(c);
            if(check == false){
                categoryListSun.add(c);
            }
        }
        return categoryListSun;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories= new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from category;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String des = rs.getString("description");
                categories.add(new Category(id,name,des));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean insert(Category category,int[]arr) throws SQLException {
        boolean rowInserted;
        PreparedStatement statement = connection.prepareStatement("insert into book (name,description)value (?,?);");
        statement.setString(1,category.getName());
        statement.setString(2,category.getDes());
        rowInserted = statement.executeUpdate()>0;
        return rowInserted;
    }

    @Override
    public boolean update(Category category,int[] arr) throws SQLException {
        boolean rowUpdated ;
        PreparedStatement statement = connection.prepareStatement("update category set name = ?, description = ? where id = ?");
        statement.setString(1,category.getName());
        statement.setString(2,category.getDes());
        statement.setInt(3,category.getId());
        rowUpdated = statement.executeUpdate() >0;
        return  rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        PreparedStatement statement = connection.prepareStatement("delete category where  id = ?;");
        statement.setInt(1,id);
       rowDeleted = statement.executeUpdate()>0;
       return rowDeleted;
    }

    @Override
    public List<Category> sortByName() {
        return null;
    }
}
