package hashmap;
import java.util.*;

public class MyHashMap<K, V> {   // ✅ declare K, V here

    // Inner Node class
    public class Node {
        K key;
        V value;
        Node next;   //

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Object[] buckets;
    private float loadFactor;
    private int capacity;
    private int size;


    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.capacity = 4;
        this.loadFactor = 0.75f;
        this.buckets = new Object[capacity];  // ✅ Cast fixes error
        this.size = 0;
    }


    private Node getNode(int index)
    {
        return (Node)buckets[index];
    }
    private void setNode(int index,Node node)
    {
         buckets[index]=node;
    }

    // Hash function: convert key -> index
    private int getBucketIndex(K key) {
        int hashcode = key.hashCode();
        return Math.abs(hashcode) % capacity;
    }

    // Example put method
public void put(K key,V value)
{
    int index=getBucketIndex(key);
    Node head=getNode(index);

    // Check if key exists
    while(head!=null)
    {
        if(head.key.equals(key))
        {
            head.value=value;
            return;
        }
        head=head.next;
    }

    //insert new node

    Node newNode=new Node(key,value);
    newNode.next=getNode(index);
    setNode(index,newNode);
    size++;

    // Check load factor
    if ((float) size / capacity >= loadFactor) {
        resize();
    }
}

    private void resize() {
        int newCapacity = capacity * 2;
        Object[] newBuckets = new Object[newCapacity];

        for (int i = 0; i < capacity; i++) {
            Node head = getNode(i);
            while (head != null) {
                Node next = head.next;

                int newIndex = Math.abs(head.key.hashCode()) % newCapacity;
                head.next = (Node) newBuckets[newIndex];
                newBuckets[newIndex] = head;

                head = next;
            }
        }

        buckets = newBuckets;
        capacity = newCapacity;
    }

    // Example get method
    public V get(K key)
    {
        int index=getBucketIndex(key);
        Node head=getNode(index);

        while(head !=null)
        {
            if(head.key.equals(key))
            {
                return head.value;
            }
            head=head.next;
        }
        return null;
    }


}
