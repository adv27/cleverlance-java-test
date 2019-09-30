package com.cleverlance.airlabs.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlabsResponse<T> {
    private Object request;
    private T response;
}
