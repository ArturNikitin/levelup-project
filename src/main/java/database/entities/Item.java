package database.entities;


import database.utilities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    @Convert(converter = PriceConverter.class)
    private Price price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothingType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothingStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothingSize size;

    @Column(name = "Image")
    @Convert(converter = FileConverter.class)
    private File file;

    @Column(name = "Item_Description")
    private String description;


    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private Order order;

    public Item(String name, Price price, ClothingType type, ClothingSize size, User user) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.size = size;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                type == item.type &&
                status == item.status &&
                size == item.size &&
                Objects.equals(file, item.file) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, type, status, size, file, description, order);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", status=" + status +
                ", size=" + size +
                ", file=" + file +
                ", description='" + description + '\'' +
                ", order=" + order +
                '}';
    }
}
