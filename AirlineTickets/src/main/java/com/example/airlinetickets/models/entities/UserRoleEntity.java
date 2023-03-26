package com.example.airlinetickets.models.entities;

import com.example.airlinetickets.models.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  public UserRoleEntity() {
  }

  public UserRoleEntity(UserRoleEnum role) {
    this.role = role;
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public UserRoleEntity setRole(UserRoleEnum role) {
    this.role = role;
    return this;
  }
}
