package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class Runner {
@Before
public void setup(){
    System.out.println("setup");
}

@After
public void clean(){
    System.out.println("clean");
}
}
