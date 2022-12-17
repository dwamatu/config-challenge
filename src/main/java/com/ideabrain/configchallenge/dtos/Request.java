package com.ideabrain.configchallenge.dtos;


import com.ideabrain.configchallenge.dtos.subdtos.Metadata;
import lombok.Data;

@Data
public class Request {

    public String name;
    public Metadata metadata;


}
