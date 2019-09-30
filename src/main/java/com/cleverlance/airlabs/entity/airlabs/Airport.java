package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

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
    @Id
    private String code;
    private String name;
    @JsonProperty("country_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Nullable
    private String countryName;

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
