package database.entities;


import database.utilities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Convert(converter = PriceConverter.class)
    private Price price;

    @Column
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


    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private Order order;

}
