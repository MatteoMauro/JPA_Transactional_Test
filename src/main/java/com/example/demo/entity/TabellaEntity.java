package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tabella")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TabellaEntity implements Serializable
{
    @Id
    private UUID id;
    private String name;
    private String description;
}
