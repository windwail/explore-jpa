package com.example.accessingdatajpa;

import lombok.*;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "author",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author book = (Author) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return 2002;
    }

}
