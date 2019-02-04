package kafka;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

class SomeProducer {

    private static final Logger log = LoggerFactory.getLogger(SomeProducer.class);

    private Producer<String, String> producer;
    private Producer<String, KafkaCustomer> customerProducer;

    SomeProducer() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(kafkaProps);

        Properties kafkaCustomerProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "kafka.KafkaCustomerSerializer");
        customerProducer = new KafkaProducer<String, KafkaCustomer>(kafkaProps);
    }

    void flush() {
        //TEST CHYBA SIE URYWAL I NIE ROBIL FLUSHA, JAk SIE ODCZEKA PARE ms to jest ok
        producer.flush();
    }

    private void waitDueToTestEnd(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void fireAndForgetSend() {
        ProducerRecord<String, String> record =
                new ProducerRecord<>(
                        "test",
                        "test fireAndForget");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitDueToTestEnd();
    }

    void synchronousSend() {
        ProducerRecord<String, String> record =
                new ProducerRecord<>(
                        "test",
                        "test synchronousSend");
        try {
            producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitDueToTestEnd();
    }

    void asynchronousSend() {
        ProducerRecord<String, String> record =
                new ProducerRecord<>(
                        "test",
                        "test asynchronousSend");
        try {
            producer.send(record, new SomeProducerAsynchronousCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("asynchronousSend START");
        waitDueToTestEnd();
    }

    private class SomeProducerAsynchronousCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            log.info("asynchronousSend COMPLETE");
            if (e != null) {
                e.printStackTrace();
            }
        }
    }

    void customSerializerSend() {
        KafkaCustomer kafkaCustomer = new KafkaCustomer(65, "Customer 65");
        ProducerRecord<String, KafkaCustomer> record =
                new ProducerRecord<>(
                        "test",
                        "customerKey",
                        kafkaCustomer);
        try {
            customerProducer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitDueToTestEnd();
    }
}