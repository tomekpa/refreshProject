//package kafka;
//
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.Produced;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.Properties;
//import java.util.regex.Pattern;
//
//public class KafkaStreamsTest {
//
//    @Test
//    public void wordCountTest() throws Exception {
//
//        Properties streamsConfiguration  = new Properties();
//        streamsConfiguration .put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
//        streamsConfiguration .put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        streamsConfiguration .put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        streamsConfiguration .put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//
//        final StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String> source = builder.stream("test");
//
//        final Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
////
////        final KTable<String, Long> wordCounts = source.flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
////                .map((key, value) -> new KeyValue<Object, Object>(value, value))
////                .filter((key, value) -> (!value.equals("the")))
//////                .groupByKey()
////                .groupBy((key, word) -> word)
////                .count();
//////                .count("aaa").mapValues(value -> Long.toString(value)).toStream();
////        counts.to("test-output");
//
//
//
////        KStream counts = source
//        final KTable<String, Long> wordCounts = source
//                // Split each text line, by whitespace, into words.  The text lines are the record
//                // values, i.e. we can ignore whatever data is in the record keys and thus invoke
//                // `flatMapValues()` instead of the more generic `flatMap()`.
//                .flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
//                // Count the occurrences of each word (record key).
//                //
//                // This will change the stream type from `KStream<String, String>` to `KTable<String, Long>`
//                // (word -> count).  In the `count` operation we must provide a name for the resulting KTable,
//                // which will be used to name e.g. its associated state store and changelog topic.
//                //
//                // Note: no need to specify explicit serdes because the resulting key and value types match our default serde settings
//                .groupBy((key, word) -> word)
////                .groupByKey()
////                .count().toStream();;
//                .count();
//
//        final Serde<String> stringSerde = Serdes.String();
//        final Serde<Long> longSerde = Serdes.Long();
//
//        wordCounts.toStream().to("test-output", Produced.with(stringSerde, longSerde));
//
////        counts.to("test-output");
//
//        final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
//        streams.cleanUp();
//        streams.start();
//
//        Thread.sleep(5000L);
//        streams.close();
//
//    }
//}
