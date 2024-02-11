package guru.qa.files.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.files.utils.Book;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BookTests {

    @Test
    public void jsonTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File bookJsonfile = new File("src/test/resources/files/book.json");
        Book book = objectMapper.readValue(bookJsonfile, Book.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(book.getTitle()).as("title").isEqualTo("The Lord of the Rings");
        softly.assertThat(book.getAuthor()).as("author").isEqualTo("J. R. R. Tolkien");
        softly.assertThat(book.getPublished()).as("published").isEqualTo("July 29, 1954");
        softly.assertThat(book.getPrecededBy()).as("precededBy").isEqualTo("The Hobbit");
        softly.assertThat(book.getPages()).as("pages").isEqualTo(876);
        softly.assertThat(book.getCharacters()).as("characters")
                .isEqualTo(Arrays.asList("Aragorn", "Gollum", "Gandalf", "Sauron", "Frodo Baggins"));
        softly.assertAll();
    }
}
