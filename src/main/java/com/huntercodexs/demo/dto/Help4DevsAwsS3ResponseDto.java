package com.huntercodexs.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Help4DevsAwsS3ResponseDto {
    String filename;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;
}
