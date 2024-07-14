package com.huntercodexs.demo.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AwsSqsResponseDto {
    String file;
}
