package com.spring_data_jpa.event_manager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "host")
public class Host implements Serializable {

    private static final long serialVersionUid = 1L;

    public Host(UUID id, String name, String cpf, Set<Event> events) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.events = events;
    }

    public Host() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true, columnDefinition = "char(11)", length = 11)
    @Size(min = 11, max = 11)
    private String cpf;

    @OneToMany(mappedBy = "host")
    @JsonIgnore
    private Set<Event> events = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
