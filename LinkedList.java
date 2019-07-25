import java.io.*;

// let's get started

public class LinkedList{
  Node head;

  static class Node{
      int data;
      Node next;

      Node(int d){
        data = d;
        next = null;
      }
  }
  public static LinkedList prepend(LinkedList list, int data){
    Node new_node = new Node(data);
    new_node.next = list.head;
    list.head = new_node;
    return list;
  }

  public static LinkedList insert(LinkedList list, int data){
    Node new_node = new Node(data);
    new_node.next = null;

    if (list.head == null){
      list.head = new_node;
    }
    else{
      Node last = list.head;
      while (last.next != null){
        last = last.next;
      }
      last.next = new_node;
    }
    return list;
  }

  public static LinkedList insert_after_k(LinkedList list, int data, int k){

    Node new_node = new Node(data);
    new_node.next = null;
    if (list.head == null){
      list.head = new_node;
    }
    else{
      Node last = list.head;
      for (int i = 1; i < k; i++){
        last = last.next;
      }
      new_node.next = last.next;
      last.next = new_node;
    }
    return list;
  }

public static void printlist(LinkedList list){
  Node counter_node = list.head;
  System.out.println(counter_node.data);

  while(counter_node.next != null) {
    counter_node = counter_node.next;

    System.out.println(counter_node.data);
  }
}

  public static void main(String[] args){
    LinkedList list = new LinkedList();

    list = insert(list, 100);
    list = insert(list, 202);
    list = insert(list, 303);

    list = insert_after_k(list, 250, 2);

    list = prepend(list, 1);
    printlist(list);
  }
}
