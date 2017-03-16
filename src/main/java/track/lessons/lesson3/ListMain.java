package track.lessons.lesson3;



public class ListMain {

    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList();
        MyLinkedList linkedList = new MyLinkedList();
        for (int i = 0; i < 10; ++i) {
            arrayList.add(i);
            linkedList.add(i);
        }
        arrayList.remove(0);
        linkedList.remove(1);
        linkedList.add(10);
        System.out.println("arr");
        for (int i = 0; i < arrayList.size(); ++i) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println("");
        System.out.println("link");
        for (int i = 0; i < linkedList.size(); ++i) {
            System.out.print(linkedList.get(i) + " ");
        }
        linkedList.push(5);
        System.out.println("");
        System.out.println("link");
        for (int i = 0; i < linkedList.size(); ++i) {
            System.out.print(linkedList.get(i) + " ");
        }
        linkedList.pop();
        System.out.println("");
        System.out.println("link");
        for (int i = 0; i < linkedList.size(); ++i) {
            System.out.print(linkedList.get(i) + " ");
        }
        linkedList.dequeue();
        System.out.println("");
        System.out.println("link");
        for (int i = 0; i < linkedList.size(); ++i) {
            System.out.print(linkedList.get(i) + " ");
        }
        linkedList.enqueue(7);
        System.out.println("");
        System.out.println("link");
        for (int i = 0; i < linkedList.size(); ++i) {
            System.out.print(linkedList.get(i) + " ");
        }
    }
}
