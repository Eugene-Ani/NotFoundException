package ru.netology.manager;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import ru.netology.domain.Book;
        import ru.netology.domain.Product;
        import ru.netology.domain.TShirt;
        import ru.netology.repository.ProductRepository;
        import ru.netology.domain.NotFoundException;

        import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repo = new ProductRepository();

    private final Book firstBook = new Book(1, "Мир на Земле", 800, "Станислав Лем", 250, 1985);
    private final Book secondBook = new Book(2, "Проклятые экономики", 1500, "Андрей Мовчан", 450, 2020);
    private final Book thirdBook = new Book(3, "Норвежский лес", 1050, "Харуки Мураками", 460, 2000);
    private final TShirt firstTShort = new TShirt (4, "Puma", 1200, "Blue", "XL");
    private final TShirt secondTShort = new TShirt(5, "Adidas", 1300, "Yellow", "XXL");

    @BeforeEach
    public void setUp() {
        repo.save(firstBook);
        repo.save(secondBook);
        repo.save(thirdBook);
        repo.save(firstTShort);
        repo.save(secondTShort);
         }

    @Test
    void shouldDeleteIfExists() {
        repo.removeById(2);
        Product[] expected = new Product[]{firstBook, thirdBook, firstTShort, secondTShort};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowException() {
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(7);
        });
    }
}