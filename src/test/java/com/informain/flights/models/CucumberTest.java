package com.informain.flights.models;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.UNDERSCORE;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        snippets = UNDERSCORE,
        features = "classpath:features",
        glue = "com.informain.flights.models"
)
public class CucumberTest {
}
