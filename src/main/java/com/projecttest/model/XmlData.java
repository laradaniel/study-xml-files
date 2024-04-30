package com.projecttest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class XmlData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;
}
