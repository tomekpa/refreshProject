package javavanila.junit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.rule.OutputCapture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommonRefreshTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Rule
    public FooBarRule fooBarRule = new FooBarRule();

    //Have to be before constructor, since it must be put into constructor
    @Mock
    ClassTwoInterface classTwoInterfaceMockAnnotated;

    @InjectMocks
    ClassMain classMain = new ClassMain(classTwoInterfaceMockAnnotated);

    //Can be after constructor, since its injected using InjectMocks
    @Mock
    ClassOneInterface classOneInterfaceMockAnnotated;

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass - im static");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass - im static");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void mockCreation() {
        ClassOneInterface coi2 = Mockito.mock(ClassOneInterface.class);
        Mockito.when(coi2.foo(any(Integer.class), any(Integer.class))).thenReturn(2);
        assertTrue(coi2.foo(99, 99) == 2);
    }

    @Test
    public void stubCreation() {
        ClassOneInterface coi3stub = new StubClassOne();
        assertTrue(coi3stub.foo(99, 99) == 3);
    }

    @Test
    public void verifyNumberOfCalls() {
        ClassOneInterface coi1 = new ClassOne();
        assertTrue(coi1.foo(99, 99) == 1);

        ClassOneInterface coi2 = Mockito.mock(ClassOneInterface.class);
        Mockito.when(coi2.foo(any(Integer.class), any(Integer.class))).thenReturn(2);

        assertTrue(coi2.foo(99, 99) == 2);
        verify(coi2, Mockito.times(1)).foo(any(Integer.class), any(Integer.class));

        coi2.foo(99, 99);
        coi2.foo(99, 99);
        verify(coi2, Mockito.times(3)).foo(any(Integer.class), any(Integer.class));
    }

    @Test
    public void consoleOutputTestUsingRule() {
        //Interesting, it ignores static init, maybe rule start to record just after test start
        //also ignores FooBarRule, so it ignores all befere @Before anootation
        //beforeClass - im static
        //FooBarRule
        //before
        //helloafter
        //afterClass - im static
        System.out.print("hello");
        assertEquals(outputCapture.toString(),
                "before\r\n" +
                        "hello");
    }

    @Test
    public void injectMocksTest() {

        Mockito.when(classOneInterfaceMockAnnotated.foo(any(Integer.class), any(Integer.class))).thenReturn(1111);
        Mockito.when(classTwoInterfaceMockAnnotated.bar(any(Integer.class), any(Integer.class))).thenReturn(2222);

        assertTrue(classMain.getOne() == 1111);
        assertTrue(classMain.getTwo() == 2222);

        verify(classOneInterfaceMockAnnotated, Mockito.times(1)).foo(11, 11);
        verify(classTwoInterfaceMockAnnotated, Mockito.times(1)).bar(22, 22);
    }

    static class StubClassOne implements ClassOneInterface {
        @Override
        public int foo(int a, int b) {
            return 3;
        }
    }
}