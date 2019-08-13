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

  public static LinkedList insertArray(int[] intarr, LinkedList orig_list){
    LinkedList list = copyList(orig_list);
    for(int val: intarr){
      list = insert(list, val);
    }
    return list;
  }

  public static LinkedList prepend(LinkedList orig_list, int data){
    /* adds a new element to the beginning of the list */
    LinkedList list = copyList(orig_list);
    Node new_node = new Node(data);
    new_node.next = list.head;
    list.head = new_node;
    return list;
  }

  public static LinkedList insert(LinkedList orig_list, int data){
    // add element to the end of the list
    LinkedList list = copyList(orig_list);
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

  public static LinkedList insert_after_k(LinkedList orig_list, int data, int k){
  LinkedList list = copyList(orig_list);
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
  System.out.print(counter_node.data);

  while(counter_node.next != null) {
    counter_node = counter_node.next;
    System.out.print(" -> ");
    System.out.print(counter_node.data);

  }
  System.out.println("");
}

public static LinkedList removeFirst(LinkedList orig_list){
LinkedList list = copyList(orig_list);
  list.head = list.head.next;
  return list;
}

public static LinkedList removeLast(LinkedList orig_list){
LinkedList list = copyList(orig_list);
  Node counter_node = list.head;
  while(counter_node.next.next != null) {
    counter_node = counter_node.next;
  }
  counter_node.next = null;
  return list;
}

public static LinkedList removeNth(LinkedList orig_list, int n){
LinkedList list = copyList(orig_list);
  if(n == 1){
list = removeFirst(list);
  } else {
    Node counter_node = list.head;
    for (int i = 1; i < n - 1; i++){
      if(counter_node.next != null){
        counter_node = counter_node.next;
      }
    }
    counter_node.next = counter_node.next.next;
  }
  return list;
}

public static LinkedList reverseList(LinkedList orig_list){
  LinkedList list = copyList(orig_list);
  Node counter_node = list.head;
  LinkedList reversed = new LinkedList();
  reversed = prepend(reversed, counter_node.data);
  while(counter_node.next != null){
    counter_node = counter_node.next;
    reversed = prepend(reversed, counter_node.data);

  }
  return(reversed);

}

public static LinkedList removeNthFromEnd(LinkedList orig_list, int n){
  /* removal of nth element from end assumes the linkedlist's length is unknown
  If however you wanted to have an instance attribute list.length then all the methods mutating
  the list need to mutate this instance attribute as well.

  Implementation is done by reversing the linkedlist, removing the nth element
  from the beginning of the linkedlist, and then reversing it again before finally
  returning it
  */
  LinkedList list = copyList(orig_list);
  list = reverseList(list);
  list = removeNth(list, n);
  return reverseList(list);
}

public static LinkedList copyList(LinkedList list){
  /* this is necessary because the methods that "return" a list actually mutate the same list in place as well
  Therefore, when trying to take the return of a mutating method and assign it to a new list, the two lists are actually
  pointing to the same linkedlists. This method is called before any mutating methods, to ensure that modifications
  are applied only to the copy of the list, and not the original list.
  */
LinkedList newlist = new LinkedList();
if(list.head != null){

  Node counter_node = list.head;
  newlist.head = new Node(counter_node.data);
  Node new_counter = newlist.head;

  while(counter_node.next != null){
    counter_node = counter_node.next;
    new_counter.next = new Node(counter_node.data);
    new_counter = new_counter.next;
  }
}
  return(newlist);
}

public static boolean isPalinodrome(LinkedList orig_list){
  LinkedList list = copyList(orig_list);
  LinkedList reversed = reverseList(list);
  Node list_counter = list.head;
  Node reversed_counter = reversed.head;
  boolean flag = true;
  while(list_counter.next != null){
    if(list_counter.data != reversed_counter.data){
      flag = false;
      break;
    }
    list_counter = list_counter.next;
    reversed_counter = reversed_counter.next;
  }
  return flag;
}


  public static void main(String[] args){
    LinkedList list = new LinkedList();
    list = insertArray(new int[] {45, 32, 15, 9, -3, 0, 2, -16}, list);
    list = insert_after_k(list, 250, 2);
    list = prepend(list, 1);
    System.out.println("Original List:");
    printlist(list);

    System.out.println("Reversed List:");
    LinkedList reversed = reverseList(list);
    printlist(reversed);

    System.out.println("Removing 3rd element from beginning, then removing first and last (test 3 methods)");
    LinkedList testlist = removeNth(list, 3);
    testlist = removeFirst(testlist);
    testlist = removeLast(testlist);
    printlist(testlist);

    System.out.println("Removing 4th element from end of linked list (original order)");
    printlist(removeNthFromEnd(list, 4));

    LinkedList list2 = insertArray(new int[] {1, 2, -4, 2, 1}, new LinkedList());
    System.out.println("Testing palinodrome: " + isPalinodrome(list2));
    printlist(list2);

    LinkedList list3 = insertArray(new int[] {1, 2, 0, 3, 2, 1}, new LinkedList());
    System.out.println("Testing palinodrome: " + isPalinodrome(list3));
    printlist(list3);



  }
}
