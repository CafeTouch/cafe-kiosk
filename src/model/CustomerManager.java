package model;

import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    private static Map<String, Customer> customerMap = new HashMap<>();

    public static Customer getOrCreate(String phone) {
        return customerMap.computeIfAbsent(phone, k -> new Customer());
    }
}
