package com.moriawe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public String start() {
        return "Start Page";
    }

    // Returns it as a Json Object - FasterXML Jackson
    @GetMapping("/greet")
    public GreetResponse greet() {
        //return "Hello World!";
        GreetResponse response = new GreetResponse(
                "Tjipp!",
                List.of("Java", "Kotlin", "Swift", "JavaScript", "Dart"),
                new Person("Alex", 23, 30_000.0)
        );
        return response;
    }

    record Person(String name, int age, double savings) {}

    record GreetResponse(
            String greet,
            List<String> favProgrammingLanguages,
            Person person
    ) { }

    // This class can be replaced by record GreetResponse()
//    class GreetResponse {
//        private final String greet;
//
//        GreetResponse(String greet) {
//            this.greet = greet;
//        }
//
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public String toString() {
//            return "GreetResponse{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (GreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hashCode(greet);
//        }
//    }

}
