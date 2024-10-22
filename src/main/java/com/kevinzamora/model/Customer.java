package com.kevinzamora.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Id
@GeneratedValue
public class Customer {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;

}
