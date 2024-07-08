package com.huntercodexs.demo.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AwsS3ResponseDto {
    String file;
}
