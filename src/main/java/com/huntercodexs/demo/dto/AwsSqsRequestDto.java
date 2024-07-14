package com.huntercodexs.demo.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AwsSqsRequestDto {
    byte[] data;
    String filename;
}
