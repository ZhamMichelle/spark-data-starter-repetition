package com.example.sparkdatastarterrepetition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhamilya on 4/30/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Source("data/orders.csv")
public class Order {
    private String name;
    private String desc;
    private int price;
    private long criminalId;
}
