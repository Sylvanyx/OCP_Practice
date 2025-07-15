package Concurrency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/*
 * Concurrent Collection Classes
 * 
 * class                    collection framework            
 * 
 * ConcurrentHashMap        map
 * ConcurrentLinkedQueue    queue
 * 
 * ConcurrentSkipList       Concurrent/Sorted/Navigable-Map
 * ConcurrentSkipListSet    Sorted/Navigable-Set
 * 
 * CopyOnWriteArrayList     List
 * CopyOnWriteArraySet      Set
 * 
 * LinkedBlockingQueue      BlockingQueue
 * 
 * good practice to instantiate the concurrent collection by passing around nonconurrent interface when possible
 */
public class ConcurrentCollectionExp {

    public static void main(String[] args) {
        // Below Code Throws Concurrent Modification Exception
        // var animals = new HashMap<String, Integer>();
        // animals.put("rabbit", 1);
        // animals.put("deer", 2);
        // for (String key : animals.keySet()) {
        // animals.remove(key);
        // }

        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("rabbit", 1);
        map.put("deer", 2);
        System.out.println(map);
        for (String key : map.keySet()) {
            map.remove(key);
        }
        System.out.println(map);

        Queue<Integer> que = new ConcurrentLinkedQueue();
        que.offer(31);
        System.out.println(que.peek());
        System.out.println(que.poll());

        // concurrent version of TreeSet
        Set<String> skipset = new ConcurrentSkipListSet<>();
        skipset.add("gopher");
        skipset.add("groundhog");
        skipset.stream().collect(Collectors.joining(","));

        // concurrent version of TreeMap
        Map<String, String> skipmap = new ConcurrentSkipListMap<>();
        skipmap.put("bear", "honey");
        skipmap.entrySet().stream().forEach((e) -> System.out.println(e.getKey() + "-" + e.getValue()));

        // CopyOnWrite classes copy all ele's into another structure that is modified
        // The reference collection is not modified
        List<Integer> ls = List.of(1, 2, 3, 4, 5);
        List<Integer> favs = new CopyOnWriteArrayList<Integer>(ls);

        for (var n : favs) {
            System.out.println(n + " ");
            favs.add(9);
        }

        Set<Character> letters = new CopyOnWriteArraySet<Character>(Set.of('a', 'b', 'c', 'd', 'e'));
        for (char c : letters) {
            System.out.println(c + " ");
            letters.add('s');
        }
        System.out.println("Size: " + letters.size());

        try {
            var blockingQue = new LinkedBlockingQueue<Integer>();
            blockingQue.offer(39);
            blockingQue.offer(3, 4, TimeUnit.SECONDS);
            System.out.println(blockingQue.poll());
            System.out.println(blockingQue.poll(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            System.out.println("error with blocking queue");
        }
    }

}
