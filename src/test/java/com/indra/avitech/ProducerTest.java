package com.indra.avitech;

import com.indra.avitech.producerconsumer.model.Command;
import com.indra.avitech.producerconsumer.model.CommandType;
import com.indra.avitech.producerconsumer.service.IProducer;
import com.indra.avitech.producerconsumer.service.Producer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerTest {

    private IProducer producer;

    private static final int MAX_QUEUE_CAPACITY = 5;

    private BlockingQueue<Command> blockingQueue;

    @BeforeEach
    public void init() {
        blockingQueue = new LinkedBlockingDeque<>(MAX_QUEUE_CAPACITY);
        producer = new Producer(blockingQueue);
    }


    @Test
    void add() throws InterruptedException {
        producer.add(1L, "A-1", "John Smith");
        producer.add(2L, "A-2", "Jay Potter");
        producer.add(3L, "A-3", "Alex Neumann");
        Assertions.assertEquals(CommandType.ADD, blockingQueue.take().getType());
        Assertions.assertEquals(2L, blockingQueue.take().getUser().getId());
        Assertions.assertEquals("A-3", blockingQueue.take().getUser().getGUID());
    }

    @Test
    void printAll() throws InterruptedException {
        producer.printAll();
        Assertions.assertEquals(CommandType.PRINT_ALL, blockingQueue.take().getType());
    }

    @Test
    void deleteAll() throws InterruptedException {
        producer.deleteAll();
        Assertions.assertEquals(CommandType.DELETE_ALL, blockingQueue.take().getType());
    }

}
