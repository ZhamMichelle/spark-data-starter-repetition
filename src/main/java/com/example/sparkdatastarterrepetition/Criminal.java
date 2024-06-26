package com.example.sparkdatastarterrepetition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
/**
 * @author zhamilya on 4/10/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Source("data/criminals.csv")
public class Criminal {
    private long id;
    private String name;
    private int number;
    @ForeignKeyName("criminalId")
    private List<Order> orders;
}
