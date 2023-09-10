import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product p1;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        p1 = new Product("Tets", "TestDescript","2234556",25.67);
    }

    @org.junit.jupiter.api.Test
    void setName() {

        p1.setName("Test");
        assertEquals("Test",p1.getName());

    }

    @org.junit.jupiter.api.Test
    void setDescription() {
        p1.setDescription("TestTest");
        assertEquals("TestTest",p1.getDescription());
    }

    @org.junit.jupiter.api.Test
    void setID() {
        p1.setID("123456");
        assertEquals("123456",p1.getID());
    }

    @org.junit.jupiter.api.Test
    void setCost() {
        p1.setCost(26.50);
        assertEquals(26.50,p1.getCost());
    }

    @org.junit.jupiter.api.Test
    void toCSV() {
        p1.toCSV();
    }
}