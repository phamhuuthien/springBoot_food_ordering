package com.IT.osahaneat.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private int id;
    private String nameCate;
    private List<MenuDTO> menu;
}
