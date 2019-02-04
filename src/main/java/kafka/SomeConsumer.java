package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class SomeConsumer {

    private static final Logger log = LoggerFactory.getLogger(SomeConsumer.class);

    private Consumer<String, String> createConsumer() {
        Properties kafkaProps = new Properties();
//        kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("group.id", "new1");
//        kafkaProps.put("group.id", "test");
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");


        kafkaProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //Jesli offset nie istnieje, zczytuj od poczatku "latest"
        kafkaProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); //Nie comituje offsetu,
        //Te dwa parametry powoduja, ze zawsze zczytujemy od poczatku wszystko, dla tej samej grupy


        return new KafkaConsumer<String, String>(kafkaProps);
    }

    /**
     * TODO: NOW IT LISTEN FOR LAST NOT READ OFFSET, HOW TO READ THEM ALL FROM BEGINNING?
     * ZAPISUJE PER CONSUMER GROUP, JAK ZMIENIMY NAZWE GRUPY, ZASSA WSZYSTKO OD NOWA
     * BO OFFSETY SA ZAPISYWANE PER GRUPA
     */

    void consumeWithAsyncCommit() {

        Consumer<String, String> consumer = createConsumer();
        consumer.subscribe(Collections.singletonList("test5"));

        try {
            for (int i = 0; i < 120; i++) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    log.info(
                            "\ntopic = {}, partition = {}, offset = {}, key = {}, value = {}\n",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value()
                    );
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.info("COMMIT SYNC/ASYNC: BEFORE");
                try {
//                    consumer.commitSync();

                    consumer.commitAsync(
                            new OffsetCommitCallback() {
                                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                                    if (exception != null) {
                                        log.info("COMMIT ASYNC: Callback error, offset:{}", offsets, exception);
                                    } else {
                                        log.info("COMMIT ASYNC: Callback success, offset:{}", offsets);
                                    }
                                }
                            });

                } catch (CommitFailedException e) {
                    log.error("commit failed", e);
                }
                log.info("COMMIT SYNC/ASYNC: AFTER");
            }
        } finally {
            consumer.close();
        }
    }

    void consumeWithIndividualCommits() {

        log.info("BEFORE POOL");
        Consumer<String, String> consumer = createConsumer();
        log.info("BEFORE POOL");
        consumer.subscribe(Collections.singletonList("test5"));

        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

        try {
            mainLoop:
            for (int i = 0; i < 120; i++) {
                log.info("BEFORE POOL");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                log.info("AFTER POOL");

                int breakCounter = 0;
                int maxBreakCounter = 5;

                log.info("Number of fetched records: " + records.count());

                for (ConsumerRecord<String, String> record : records) {
                    log.info(
                            "\ntopic = {}, partition = {}, offset = {}, key = {}, value = {}\n",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value()
                    );

                    currentOffsets.put(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset(), "no metadata")
                    );

                    consumer.commitAsync(currentOffsets, null);

                    breakCounter++;
                    if (breakCounter >= maxBreakCounter) {
                        break mainLoop;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            consumer.commitSync();
            consumer.close();
        }
    }

    void consumeNoCommits() {

        log.info("consumeNoCommits START");

        Consumer<String, String> consumer = createConsumer();
        log.info("createConsumer DONE");
        consumer.subscribe(Collections.singletonList("test"));
        log.info("consumer.subscribe DONE");

        try {
            for (int i = 0; i < 120; i++) {
                log.info("consumer.poll START");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                log.info("consumer.poll DONE, records: " + records.count());

                for (TopicPartition tp : consumer.assignment())
                    log.info("Consumer, Partition:" + tp.partition() + ", Offset: " + consumer.position(tp));

                for (ConsumerRecord<String, String> record : records) {
                    log.info(
                            "\ntopic = {}, partition = {}, offset = {}, key = {}, value = {}\n",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value()
                    );
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            consumer.close();
        }
    }


    void trivialTest() {
        log.info(
                "topic = {}, partition = {}",
                "asfasfasdf",
                5
        );
    }
}