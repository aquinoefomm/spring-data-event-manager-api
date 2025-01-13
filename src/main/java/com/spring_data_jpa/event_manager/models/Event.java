package com.spring_data_jpa.event_manager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false, unique = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = true)
    private Host host;

    @ManyToMany
    @JoinTable(
            name = "event_guest",  // The join table that will hold the many-to-many relationship
            joinColumns = @JoinColumn(name = "event_id"),  // The foreign key column in the join table for Event
            inverseJoinColumns = @JoinColumn(name = "guest_id")  // The foreign key column in the join table for Guest
    )
    @JsonIgnore
    private Set<Guest> guests = new HashSet<>();

}
