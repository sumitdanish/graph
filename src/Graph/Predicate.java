package Graph;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class Predicate {
    public static void main(String[] args) {
        Set<String> s  = new HashSet<>();
        s.add("/hello/world");
        s.add("/sari/akil");
        s.add("/fakirabad/alla");
        BiPredicate<String,String> biPredicate = (url,val) -> (url.contains("h") && val.startsWith("h"));
        boolean b = s.stream().anyMatch(a -> biPredicate.test(a,"h"));
        System.out.println(b);
    }
}
