package ua.goit.kickstarter.factory;

import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.dao.*;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.*;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

  public static Controller createReadCategoryController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class, ProjectService.class);
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(categoryService, projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createCategoryController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class);
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      controller = constructor.newInstance(categoryService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createCreateProjectController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class, ProjectService.class,
                  CommentService.class, BlogPostService.class);
      ProjectService projectService = getProjectService(getProjectDao(connection));
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      CommentService commentService = getCommentService(getCommentDao(connection));
      BlogPostService blogPostService = getBlogPostService(getBlogPostDao(connection));
      controller = constructor.newInstance(categoryService, projectService, commentService, blogPostService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createReadProjectController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(ProjectService.class, CommentService.class,
          BlogPostService.class);
      ProjectService projectService = getProjectService(getProjectDao(connection));
      CommentService commentService = getCommentService(getCommentDao(connection));
      BlogPostService blogPostService = getBlogPostService(getBlogPostDao(connection));
      controller = constructor.newInstance(projectService, commentService, blogPostService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createProjectController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class, ProjectService.class);
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(categoryService, projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createReadAllProjectsController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(ProjectService.class);
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createCommentController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CommentService.class, ProjectService.class);
      CommentService commentService = getCommentService(getCommentDao(connection));
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(commentService, projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createBlogPostController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(ProjectService.class, BlogPostService.class,
          CommentService.class);
      ProjectService projectService = getProjectService(getProjectDao(connection));
      BlogPostService blogPostService = getBlogPostService(getBlogPostDao(connection));
      CommentService commentService = getCommentService(getCommentDao(connection));
      controller = constructor.newInstance(projectService, blogPostService, commentService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createErrorController(Class<? extends
      Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor =
          clazz.getConstructor();
      controller = constructor.newInstance();
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static CategoryDao getCategoryDao(Connection connection) {
    return new CategoryDaoImpl(connection);
  }

  public static ProjectDao getProjectDao(Connection connection) {
    return new ProjectDaoImpl(connection);
  }

  public static CommentDao getCommentDao(Connection connection) {
    return new CommentDaoImpl(connection);
  }

  public static BlogPostDao getBlogPostDao(Connection connection) {
    return new BlogPostDaoImpl(connection);
  }

  public static UserDao getUserDao(Connection connection) {
    return new UserDaoImpl(connection);
  }

  public static CategoryService getCategoryService(CategoryDao dao) {
    return new CategoryServiceImpl(dao);
  }

  public static ProjectService getProjectService(ProjectDao dao) {
    return new ProjectServiceImpl(dao);
  }

  public static CommentService getCommentService(CommentDao dao) {
    return new CommentServiceImpl(dao);
  }

  public static BlogPostService getBlogPostService(BlogPostDao dao) {
    return new BlogPostServiceImpl(dao);
  }

  public static Connection getConnection() {
    Connection connection;
    ProjectProperties prop = new ProjectProperties();
    String database = prop.getDatabase();

    try {
      connection = DriverManager.getConnection(database);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }
}
