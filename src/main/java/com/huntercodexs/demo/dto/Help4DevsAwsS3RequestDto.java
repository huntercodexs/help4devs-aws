package com.huntercodexs.demo.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Help4DevsAwsS3RequestDto {
    byte[] data;
    String filename;
}