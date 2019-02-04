package kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class KafkaCustomerSerializer implements Serializer<KafkaCustomer> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String s, KafkaCustomer kafkaCustomer) {
        try {
            byte[] serializedName;
            int stringSize;
            if (kafkaCustomer == null) {
                return null;
            } else {
                if (kafkaCustomer.getName() != null) {
                    serializedName = kafkaCustomer.getName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(kafkaCustomer.getID());
            buffer.putInt(stringSize);
            buffer.put(serializedName);
            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Customer to byte[]" + e);
        }
    }

    @Override
    public void close() {

    }
}
