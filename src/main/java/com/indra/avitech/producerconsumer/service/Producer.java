package com.indra.avitech.producerconsumer.service;

import com.indra.avitech.producerconsumer.entity.User;
import com.indra.avitech.producerconsumer.model.Command;
import com.indra.avitech.producerconsumer.model.CommandType;
import lombok.AllArgsConstructor;
import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class Producer implements IProducer {
    private final BlockingQueue<Command> blockingQueue;

    @Override
    public void add(Long userId, String userGUID, String userName) {
        try {
            User user = new User();
            user.setId(userId);
            user.setGUID(userGUID);
            user.setName(userName);
            Command command = new Command(CommandType.ADD, user);
            blockingQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAll() {
        try {
            blockingQueue.put(new Command(CommandType.PRINT_ALL));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            blockingQueue.put(new Command(CommandType.DELETE_ALL));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
