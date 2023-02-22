package ru.practicum.stats.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Builder
@Entity
@Table(name = "stats", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Size(max = 30)
    String app;
    @Size(max = 250)
    String uri;
    @Size(max = 50)
    String ip;
    LocalDateTime timestamp;
}
