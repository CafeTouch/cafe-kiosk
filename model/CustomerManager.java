package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    private static final String DATA_FILE = "customer_data.txt";
    private static Map<String, Customer> customerMap = new HashMap<>();

    static {
        loadReadable();  // 프로그램 실행 시 자동 불러오기
    }

    public static Customer getOrCreate(String phone) {
        return customerMap.computeIfAbsent(phone, k -> new Customer());
    }

    public static void saveReadable() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Map.Entry<String, Customer> entry : customerMap.entrySet()) {
                String phone = entry.getKey();
                Customer c = entry.getValue();
                writer.write(phone + ":" + c.getStampCount() + "," + c.getCouponCount());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadReadable() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String phone = parts[0];
                String[] values = parts[1].split(",");
                int stamp = Integer.parseInt(values[0]);
                int coupon = Integer.parseInt(values[1]);

                Customer c = new Customer();
                for (int i = 0; i < stamp; i++) c.addStamp();
                for (int i = 0; i < coupon; i++) c.addCoupon();

                customerMap.put(phone, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Customer> getCustomerMap() {
        return customerMap;
    }
}
