package database.entities;


import database.utilities.OrderStatus;
import database.utilities.UserAddress;
import database.utilities.UserAddressConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Purchase_Orders")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    @Convert(converter = UserAddressConverter.class)
    private UserAddress address;

    @Column(name = "Order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "Purchased_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @ManyToOne(optional = true)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<Item> items;
}
