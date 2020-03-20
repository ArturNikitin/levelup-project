package database.entities;


import database.utilities.ClothingStatus;
import database.utilities.ClothingType;
import database.utilities.Price;
import database.utilities.PriceConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private Order order;

}
