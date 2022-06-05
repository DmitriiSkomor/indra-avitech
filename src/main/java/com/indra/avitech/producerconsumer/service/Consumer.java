package com.indra.avitech.producerconsumer.service;

import com.indra.avitech.producerconsumer.entity.User;
import com.indra.avitech.producerconsumer.factory.EntityManagerProvider;
import com.indra.avitech.producerconsumer.model.Command;
import com.indra.avitech.producerconsumer.model.CommandType;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements IConsumer {

    private final BlockingQueue<Command> blockingQueue;

    private EntityManager em = EntityManagerProvider.createOrGetEntityManager();

    public Consumer(BlockingQueue<Command> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        consume();
    }

    @Override
    public void consume() {
        while (true) {
            try {
                Command command = blockingQueue.take();
                // Consume value
                CommandType commandType = command.getType();
                if (CommandType.ADD.equals(commandType)) {
                    add(command.getUser());
                } else if (CommandType.PRINT_ALL.equals(commandType)) {
                    printAll();
                } else if (CommandType.DELETE_ALL.equals(commandType)) {
                    deleteAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private synchronized void add(User user) {
        em.getTransaction().begin();
        em.createNativeQuery("INSERT INTO SUSERS(USER_ID, USER_GUID, USER_NAME) values(:id,:GUID, :name)")
                .setParameter("id", user.getId())
                .setParameter("GUID", user.getGUID())
                .setParameter("name", user.getName())
                .executeUpdate();
        em.getTransaction().commit();
    }

    private synchronized void printAll() {
        System.out.println("LIST OF USERS:");
        List<User> users = em.createNativeQuery("SELECT * FROM SUSERS", User.class).getResultList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private synchronized void deleteAll() {
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM SUSERS").executeUpdate();
        em.getTransaction().commit();
    }
}
