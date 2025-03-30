package org.example.bluemoon.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.bluemoon.models.enumConverter.RoleConverter;
import org.example.bluemoon.models.enums.Role;

@Entity
@Table(name = "users", schema = "schema_duong")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @Column(name = "email")
    private String username;

    @Column(name = "password")
    private String password;

    @Convert(converter = RoleConverter.class)
    @Column(name = "role")
    private Role role;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}