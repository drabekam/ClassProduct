
public class Product {

    private String name;
    private String description;
    private String ID;
    private double cost;

    public Product(String name, String description, String ID, double cost) {
        this.name = formatName(name);
        this.description = formatDescription(description);
        this.ID = formatID(ID);
        this.cost = cost;
    }

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = formatName(name);
    }

    public String getDescription() {
        return description.trim();
    }

    public void setDescription(String description) {
        this.description = formatDescription(description);
    }

    public String getID() {
        return ID.trim();
    }

    public void setID(String ID) {
        this.ID = formatID(ID);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private String formatName(String name) {
        return String.format("%-35s", name);
    }

    private String formatDescription(String description) {
        return String.format("%-75s", description);
    }

    private String formatID(String ID) {
        return String.format("%-6s", ID);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name.trim() + '\'' +
                ", description='" + description.trim() + '\'' +
                ", ID='" + ID.trim() + '\'' +
                ", cost=" + cost +
                '}';
    }
    public String toCSV() {
        return name + "," + description + "," + ID + "," + cost;
    }
}
