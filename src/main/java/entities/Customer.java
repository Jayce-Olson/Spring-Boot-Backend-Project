package entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts;

}

