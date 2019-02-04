package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JacksonExamplesTest {


    @Test
    public void shouldProperlySerializeToJson() throws Exception {

        JacksonExamples.Dog dog = new JacksonExamples.Dog();
        dog.setAge(13);
        dog.setName("Tofik");

        String s = new ObjectMapper().writeValueAsString(dog);

        assertThat(s.contains("Tofik"), is(true));
        assertThat(s.contains("13"), is(true));
    }

    @Test
    public void shouldProperlyDeserializeFromJson() throws Exception {

        String json = "{\"age\":13,\"name\":\"Tofik\"}";
        JacksonExamples.Dog dog = new ObjectMapper().readValue(json, JacksonExamples.Dog.class);

        assertThat(dog.age, is(13));
        assertThat(dog.name, is("Tofik"));
    }

    @Test
    public void shouldProperlyPolymorphicSerializeToJson() throws Exception {

        Zoo zooWithDog = new Zoo();
        zooWithDog.animal = new Zoo.Dog();

        Zoo zooWithCat = new Zoo();
        zooWithCat.animal = new Zoo.Cat();


        String jsonDog = new ObjectMapper().writeValueAsString(zooWithDog);
        String jsonCat = new ObjectMapper().writeValueAsString(zooWithCat);

        assertThat(jsonDog.contains("\"type\":\"dog\""), is(true));
        assertThat(jsonCat.contains("\"type\":\"cat\""), is(true));
    }

    @Test
    public void shouldProperlyPolymorphicDeSerializeToJson() throws Exception {

        String json = "{\"animal\":{\"type\":\"dog\",\"name\":\"Tofik\",\"barkVolume\":5}};";
        Zoo zoo = new ObjectMapper().readValue(json, Zoo.class);

        assertThat(zoo.animal.name, is("Tofik"));
    }
}


