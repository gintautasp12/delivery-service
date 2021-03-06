package lt.vu.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`ORDER`")
@NamedQueries({
    @NamedQuery(name = "Order.findAll", query = "select o from Order o order by o.createdAt desc"),
    @NamedQuery(name = "Order.findNew", query = "select o from Order o where o.status = 0 and o.createdAt <= :date"),
    @NamedQuery(name = "Order.findBySender", query = "select o from Order o where o.sender = :sender or o.senderInfo.email = :email order by o.createdAt desc"),
    @NamedQuery(name = "Order.findOneById", query = "select o from Order o where o.id = :id"),
    @NamedQuery(name = "Order.findByRecipient", query = "select o from Order o where o.recipientInfo.email = :email order by o.createdAt desc"),
})
@Getter @Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CREATED_AT", nullable = false)
    private final Date createdAt = new Date();

    @Column(name = "STATUS", nullable = false)
    private OrderStatus status;

    @Column(name = "PICKUP_DATE_TIME", nullable = false)
    private Date pickupDateTime;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "PACKAGE_OPTION_ID", nullable = false)
    private PackageOption packageOption;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_INFO_ID", nullable = false)
    private UserInfo senderInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPIENT_INFO_ID", nullable = false)
    private UserInfo recipientInfo;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User sender;

    @ManyToMany
    @JoinTable(
        name = "ORDER_ATTRIBUTE",
        joinColumns = { @JoinColumn(name = "ORDER_ID") },
        inverseJoinColumns = { @JoinColumn(name = "ATTRIBUTE_ID") }
    )
    private List<Attribute> attributes;

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version;

    public Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;

        return order.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
