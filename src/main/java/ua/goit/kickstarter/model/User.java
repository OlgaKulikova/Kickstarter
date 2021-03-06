package ua.goit.kickstarter.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces.")
  @Column(name = "username")
  private String name;

  @Size(min = 6, max = 20, message = "The password must be at least 6 characters long.")
  private String password;

  @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message = "Invalid email address.")
  private String email;

  private boolean enable;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
  private UserRole userRole;

  public User() {
  }

  public User(String name, String password, String email, boolean enable) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.enable = enable;
  }

  public User(String name, String password, String email, boolean enable, UserRole userRole) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.enable = enable;
    this.userRole = userRole;
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }
}
