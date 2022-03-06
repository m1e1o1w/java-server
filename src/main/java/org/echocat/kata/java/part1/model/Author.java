package org.echocat.kata.java.part1.model;

import java.util.Objects;

public class Author {
    private final int userId;
    private final int id;
    private final String title;

    public Author(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return userId == author.userId && id == author.id && title.equals(author.title);
    }

    @Override
    public String toString() {
        return "Author{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title);
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
