package cassandra.masteringCassandra2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

//02:20:04.099 [main] DEBUG com.datastax.driver.mapping.Mapper - Preparing query INSERT INTO weblog.blogs ("password",author,blog_name,email,id) VALUES (?,?,?,?,?);
//02:20:04.135 [main] DEBUG com.datastax.driver.mapping.Mapper - Preparing query INSERT INTO weblog.posts (blog_id,content,id,posted_on,tags,title) VALUES (?,?,?,?,?,?);
//Disconnected from the target VM, address: '127.0.0.1:53166', transport: 'socket'
//02:20:04.141 [main] DEBUG com.datastax.driver.core.Connection - Connection[localhost/127.0.0.1:9042-3, inFlight=0, closed=true] closing connection
//02:20:04.142 [main] DEBUG com.datastax.driver.core.Host.STATES - [localhost/127.0.0.1:9042] Connection[localhost/127.0.0.1:9042-3, inFlight=0, closed=true] closed, remaining = 1
//02:20:04.142 [main] ERROR cassandra.masteringCassandra2.CassandraConnection - Something went wrong, please look into the stacktrace here:
//    com.datastax.driver.core.exceptions.CodecNotFoundException: Codec not found for requested operation: [timestamp <-> long]

//UPDATED TO CASSANDRA 3

    @Test
    public void test() {
// POPULATE CASSANDRA, REQUIRES INSTANCE
//        Main.run();
    }
}