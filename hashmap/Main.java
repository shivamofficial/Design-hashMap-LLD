package hashmap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // Add key-value pairs
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4); // triggers resize (capacity doubles from 4 → 8)

        // Retrieve values
        System.out.println("A => " + map.get("A")); // 1
        System.out.println("B => " + map.get("B")); // 2
        System.out.println("C => " + map.get("C")); // 3
        System.out.println("D => " + map.get("D")); // 4
        System.out.println("E => " + map.get("E")); // null (not present)

        // Update a value
        map.put("B", 20);
        System.out.println("B => " + map.get("B")); // 20

        // Test resizing by adding more keys
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 7);
        map.put("H", 8); // triggers another resize

        System.out.println("After adding more keys:");
        System.out.println("E => " + map.get("E")); // 5
        System.out.println("H => " + map.get("H")); // 8
    }
}
