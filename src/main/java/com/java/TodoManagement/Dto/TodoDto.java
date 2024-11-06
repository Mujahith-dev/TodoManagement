package com.java.TodoManagement.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
