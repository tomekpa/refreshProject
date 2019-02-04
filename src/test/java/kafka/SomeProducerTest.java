//package kafka;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class SomeProducerTest {
//
//    @Test
//    public void fireAndForgetTest() throws Exception {
//        SomeProducer someProducer = new SomeProducer();
//        someProducer.fireAndForgetSend();
//    }
//
//    @Test
//    public void synchronousSendTest() throws Exception {
//        SomeProducer someProducer = new SomeProducer();
//        someProducer.synchronousSend();
//    }
//
//    @Test
//    public void asynchronousSendTest() throws Exception {
//        SomeProducer someProducer = new SomeProducer();
//        someProducer.asynchronousSend();
//    }
//
//    @Test
//    public void customSerializerSendTest() throws Exception {
//        SomeProducer someProducer = new SomeProducer();
//        someProducer.customSerializerSend();
//    }
//}