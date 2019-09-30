package com.cleverlance.airlabs.entity.airlabs;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AIRPORT")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @ApiModelProperty(notes = "The Airport code")
    @Id
    private String code;
    @ApiModelProperty(notes = "The Airport name")
    private String name;

    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
