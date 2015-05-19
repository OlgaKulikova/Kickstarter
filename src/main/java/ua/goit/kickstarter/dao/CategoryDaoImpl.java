package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

  public CategoryDaoImpl(Connection connection) {
    super(connection);
  }

  @Override
  public Category getById(Integer id) {
    Category category;
    String sqlSelect = "SELECT * FROM categories WHERE id = " + id + ";";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      if (rs.next()) {
        String name = rs.getString("name");
        category = new Category(name);
        category.setId(id);
      } else {
        category = null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return category;
  }

  @Override
  public List<Category> getAll() {
    List<Category> categoryList = new ArrayList<>();
    String sqlSelect = "SELECT * FROM categories";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Category category = new Category(name);
        category.setId(id);
        categoryList.add(category);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return categoryList;
  }

  @Override
  public Category add(Category category) {
    int categoryId;
    String sqlInsert = "INSERT INTO categories (name) VALUES ( ? )";
    try {
      PreparedStatement statement = connection.prepareStatement(sqlInsert);
      statement.setString(1, category.getName());

      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Creating user failed, no rows affected.");
      }
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          categoryId = generatedKeys.getInt(1);
          category = getById(categoryId);
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return category;
  }

  @Override
  public void delete(Category category) {
    String query = "DELETE FROM categories WHERE id = " +
            category.getId() + ";";
    executeUpdate(query);
  }

  @Override
  public Category update(Category element) {
    String query = "UPDATE categories " +
            " SET name = '" + element.getName() + "'" +
            " WHERE id = " + element.getId() + ";";
    executeUpdate(query);
    return element;
  }
}
