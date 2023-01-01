package com.rapmit.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String productCode;
    private BigDecimal price;
    private Integer quantity;

}
