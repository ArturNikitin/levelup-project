package database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false, length = 100)
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany
    private List<PurchaseOrder> orders;

    @OneToMany
    private List<Item> items;
}
