package com.pimmpo.passwordmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pimmpo.passwordmanager.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private UserRole name;
}
