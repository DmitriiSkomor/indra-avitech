package com.indra.avitech.producerconsumer;

import com.indra.avitech.producerconsumer.model.Command;
import com.indra.avitech.producerconsumer.service.Consumer;
import com.indra.avitech.producerconsumer.service.IConsumer;
import com.indra.avitech.producerconsumer.service.IProducer;
import com.indra.avitech.producerconsumer.service.Producer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    private static final int MAX_QUEUE_CAPACITY = 5;

    public static void main(String[] args) {
        demoSingleConsumer();
        demoMultipleConsumers();
        System.exit(0);
    }

    public static void demoSingleConsumer() {
        BlockingQueue<Command> blockingQueue = new LinkedBlockingDeque<>(MAX_QUEUE_CAPACITY);
        IProducer producer = new Producer(blockingQueue);
        producer.add(1L, "a1", "Robert");
        producer.add(2L, "a2", "Martin");
        producer.printAll();
        producer.deleteAll();
        producer.printAll();
        IConsumer consumer = new Consumer(blockingQueue);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void demoMultipleConsumers() {
        BlockingQueue<Command> blockingQueue = new LinkedBlockingDeque<>(MAX_QUEUE_CAPACITY);
        IProducer producer = new Producer(blockingQueue);
        producer.add(1L, "a1", "Robert");
        producer.add(2L, "a2", "Martin");
        producer.printAll();
        producer.deleteAll();
        producer.printAll();
        IConsumer consumer = new Consumer(blockingQueue);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(consumer);
            threads.add(thread);
            thread.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
