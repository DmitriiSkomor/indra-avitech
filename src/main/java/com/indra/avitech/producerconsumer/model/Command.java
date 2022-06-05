package com.indra.avitech.producerconsumer.model;

import com.indra.avitech.producerconsumer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Command {
    private final CommandType type;
    private User user;

    public Command(CommandType type) {
        this.type = type;
    }
}
