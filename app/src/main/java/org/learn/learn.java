// package org.jyothishaastra;
package org.learn;

//import org.apache.tools.ant.types.resources.selectors.Compare;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static java.lang.Thread.sleep;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.Consumer;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// iw = Greatest sum of sub arrays
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {

    private static Integer sequence = 0;

    public static void main(String[] args) {
        var per1i = new Person("Fir1", "Las1");
        var cmp = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                o1.firstName().compareTo(o2.firstName());
                return 0;
            }
        };

        List<Person> perLis = new ArrayList();
			/*
		List<Person> perLis = new ArrayList(){
			public String toString(){
				var res = "";
				var len = this.size();
				while(len-- > 0){
					// res = res + it.toString();
					var it = this.iterator().next().toString() + "; ";
					System.out.println("Item is " + it);
					res = res + it;
				}
				return res;
			}
		};
			*/

        var per = new Person("Fir1", "Las2");
        perLis.add(per);
        var per1 = new Person("Fir1", "Las1");
        perLis.add(per1);
        perLis.add(new Person("Fir2", "Las2"));
        perLis.sort(cmp);
        System.out.println("Sorted list is " + perLis);

        BiFunction<Integer, Integer, Integer> biFunc = (p, q) -> p + q;
        // var biFunc = (p, q) -> p + q;
        BinaryOperator<Integer> meth = (p, q) -> p + q;
        System.out.printf("Res of BiFunc %d and BinaryOperator %d", biFunc.apply(4, 3), meth.apply(3, 4));

        calc((p, q) -> p + q, 3, 4);
        calc((p, q) -> p + q, "str1 ", "str2");
        calc((p, q) -> p / q, 6.0, 3.0);

/* 
		// perLis.sort(cmp);
		perLis.sort(new Comparator<Person>(){
			public int compare(Person p1, Person p2){
				return p1.getFirstName().compareTo(p2.getFirstName());
			}	
		});

*/
        perLis.sort(new FirLasComp<Person>() {
            public int compare(Person p1, Person p2) {
                int res = p1.firstName().compareTo(p2.firstName());
                return (res == 0 ? AnotherComp(p1, p2) : res);
            }

            public int AnotherComp(Person p1, Person p2) {
                return p1.lastName().compareTo(p2.lastName());
            }
        });
/* 
		// lamda on functional interfaces
		perLis.sort((p1, p2) -> {
				int res = p1.firstName().compareTo(p2.firstName());
				return (res == 0 ? p1.lastName().compareTo(p2.lastName()) : res);
		});
*/

        System.out.println("Sorted list is " + perLis);

        // int[] input = {24, 33, 16, 35, 15, 21, 33, 55, 77, 10, 75};

        int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int arr[] = {2, 3};

        for (int ele : arr) {
            System.out.println("Ele is " + ele);
        }


        // int[] ind = {0, 3, 4, 6, 7, 10};
        int[] ind = {0, 3, 3, 6, 6, 8};
        // -1, 1, -1, -4

        // ArraySum(input, ind);
        System.out.println("Result is " + ArraySum(input, ind));

        // if ind arr changes, someArr will change as well like C pointers
        // var someArr = Arrays.asList(2, 4, 6, 8);
        var someArr = Arrays.asList(ind);
        someArr.forEach(ele -> System.out.println("Ele is " + ele.toString()));
        var somArr = Arrays.asList(new double[]{2.4334, 4.4324}, new double[]{3.3334, 2.0324}, new double[]{4.3334, 2.9324});

        BiConsumer<Double, Double> op = ((p1, p2) -> System.out.printf("Item is lat %.3f lon %.3f\n", p1, p2));

        // cons func interf
        somArr.forEach(it -> op.accept(it[0], it[1]));
/* 
		var firstPt = somArr.get(0);
		// accept(op, firstPt[0], firstPt[1]);
		somArr.forEach(it -> accept(op, it[0], it[1]));
*/
        List<String> someLis = new ArrayList();
        someLis.addAll(List.of("mesha", "vrushaba", "mithuna", "karkataka", "simha", "kanya", "thula", "vrishika", "dhanusu", "makara", "kumbha", "meena"));

        // pred func interf
        someLis.removeIf(s -> s.startsWith("karka"));

        // func func interf
        someLis.replaceAll(it -> it.charAt(0) + " - " + it);
        // UnaryOperator u;

        someLis.forEach(it -> System.out.println("Item is " + it));

        var emptyStrs = new String[10];
        Arrays.fill(emptyStrs, "");

        // intfunc func interf is used
        // Arrays.setAll(emptyStrs, i -> i++ + ". " + switch(i) { case 0 -> "one"; case 2 -> "two"; default -> "";});
        Arrays.setAll(emptyStrs, i -> altStr(i));
        System.out.println(Arrays.toString(emptyStrs));
        String[] names = {"mesha", "vrushaba", "mithuna", "karkataka", "simha", "kanya", "thula", "vrishika", "dhanusu", "makara", "kumbha", "meena"};

        var emptyInts = new Integer[10];
        var rnd = new Random();
        Arrays.setAll(emptyInts, i -> rnd.nextInt(100));
        System.out.println(Arrays.toString(emptyInts));

        // Supplier<Integer> s;
        var res = randSelNames(25, names, () -> (new Random().nextInt(0, names.length)));
        System.out.println(Arrays.toString(res));

        if (someLis instanceof List ls) {
            System.out.printf("%s is of type %s\n", "someLis", ls.getClass().getSimpleName().toString());
        }

        // obj arr can be used to store any type of data

        Arrays.sort(emptyInts);
        System.out.println(Arrays.toString(emptyInts));
        Arrays.fill(emptyInts, 3);
        Integer ar[] = Arrays.copyOf(emptyInts, 3);

        // all methods in an interf w/o a body is an abstract method

        // streams
        Stream<String> str1 = someLis.stream().

                // stream pipelin .map.sorted .....
                        filter(ele -> ele.contains("t"))
                .map(String::toLowerCase)
                .sorted(Comparator.reverseOrder());
        // terminal operation forEach doesn't return Stream; intermediate operation identified by returning Stream
        // evalution first, no change until term operation has started/ exists
        // unlike chanining the op happens in sequence there
        // inter oper must be related like not incr no on a str list
        // .forEach(System.out::println);

        // Stream.of(emptyInts);
        var str3 = Stream.of("one", "two", "three");
        var str4 = Stream.of(2, 3, 5.5);

        // Comparator<ClassName> comp = Comparator.comparing(ClassName::getPropName).thenComparing(ClassName::getPropName);
        Comparator<ClassName> comp = Comparator.comparing(ClassName::getPropName);

        String format = "%s to %d".formatted("args", 3);

        // List.of(emptyStrs).stream().filter(co -> co.startsWith("b"))
        // .maptoInt();

        // str3.mapToInt()

        var str2 = someLis.stream()
                // .limit(3)
                .filter(it -> it.endsWith("a"))
                .map(String::toUpperCase)
                .sorted(Comparator.naturalOrder());
        // .sorted(comp);

        Stream.concat(str2, str4).forEach(System.out::println);

        System.out.printf("Stream generate\n");
        Stream.generate(() -> rnd.nextInt(2))
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(() -> "");

        System.out.printf("IntStream generate\n");
        // only if we know the start no
        // for loop syntax
        // IntStream.iterate(3, no -> no + 1)
        int nm = 33;
        IntStream.iterate(nm, i -> i < 43, no -> no + 1)
                .forEachOrdered(System.out::println);

        // var strA =
        // Stream.generate(() -> 1)
        var strA = Stream.iterate(1, i -> i <= 15, b -> b + 1)

                // .limit(15)
                // .forEach(System.out::println);
                .map(it -> "A-" + it);
//                .mapToInt(no -> no.length());

        var strB = Stream.iterate(16, b -> b + 1)
                // var strA = Stream.generate(() -> 1)
                .limit(15)
                .map(it -> "B-" + it);

        var strC = Stream.iterate(31, b -> b + 1)
                .limit(15)
                .map(it -> "C-" + it);

        var strD = Stream.iterate(46, b -> b + 1)
                .limit(15)
                .map(it -> "D-" + it);

        // var strE = Stream.iterate(51, b -> b + 1)
        // .limit(15)
        // .map(it -> "E-" + it);

        // sequence = 61;

        int seed = 61;
        // has Side effects has the var state is changed outside of the process
        var strE = Stream.generate(Main::getSequence)
                .limit(15)
                .map(it -> "E-" + (it + seed));
/*
		// not recommended
		var strEe = Stream.generate(() -> rnd.nextInt(seed, seed + 15))
		.distinct()
		// .limit(15)
		.map(it -> "E-" + it)
		.sorted();
*/
        var strA_ = Stream.of(strA);

        // use iterate if you are using the initial value
        Stream.concat(Stream.concat(Stream.concat(Stream.concat(strA, strB), strC), strD), strE)
                .forEach((it) -> System.out.printf("%s ", it));

        class IntFuncExt implements ToIntFunction<String> {
            public int applyAsInt(String str) {
                return Integer.parseInt(str);
            }
        }
        ;

        var mapToInt = new IntFuncExt();

        // mapToInt maps any type Stream to primi int
        // Stream<Integer> strA_ =
        strA_
                // .mapToInt(mapToInt)
                // .mapToInt(in -> Integer.parseInt(3))
                .mapToInt(in -> 3)
                .forEach(System.out::println);
/* 
		// var strA__ = 
			strA_
			// .mapToInt(str -> str.length())
			// .mapToInt(str -> Integer.parseInt(str))
			// .mapToInt(String::length)
			.mapToInt(str -> 3)
			.forEach(System.out::println);
			; // takes str and returns int
*/
        // below affects the size of a stream
        // distinct(n), filter(pred), takeWhile(pred), skipWhile(pred), dropWhile(pred), limit(n) & skip(n)
        // intem operations are map(), peek() and sorted()
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                // .map(c -> Character.toUpperCase(c))
                .distinct()
                //                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                //                .takeWhile(i -> i < 'a')
                //                .skip(5)
                //                .filter(i -> Character.toUpperCase(i) > 'E')
                .forEach(d -> System.out.printf("%c ", d));

        int maxSeats = 100;
        int seatsInRow = 10;
        var stream =
                Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                        .map(i -> new Seat((char) ('A' + i / seatsInRow),
                                i % seatsInRow + 1))
                        // .mapToDouble(Seat::price);
                        // .mapToObj("%.2f"::formatted);
                        // .boxed()
                        // .map(new Comparator<String>(){}::toString)
                        .skip(5)
                        .limit(10)
                        .peek(s -> System.out.println("--> " + s)) // for debugging
                        // .boxed() // maps to an object
                        .sorted(Comparator.comparing(Seat::price)
                                .thenComparing(Seat::toString));
        //                        .mapToDouble(Seat::price)
        //                        .boxed()
        //                        .map("%.2f"::formatted);
        stream.forEach(System.out::println);
        // var objClass = Object.class;
    }

    public record Seat(char rowMarker, int seatNumber, double price) {

        public Seat(char rowMarker, int seatNumber) {
            this(rowMarker, seatNumber,
                    rowMarker > 'C' && (seatNumber <= 2 || seatNumber >= 9) ? 50 : 75);
        }

        @Override
        public String toString() {
            return "%c%03d %.0f".formatted(rowMarker, seatNumber, price);
        }
    }

    static Integer getSequence() {
        return sequence++;
    }

    static String altStr(Integer i) {
        return i++ + "- " + switch (i) {
            case 1 -> "one";
            case 2 -> "two";
            default -> "";
        };
    }

    // func inter are of Cons, Function, Pred and Supp
    public static <R> R calc(BinaryOperator<R> meth, R p1, R p2) {
        R res = meth.apply(p1, p2);
        System.out.println("Op of operation is " + res);
        return res;
    }

    // supp func interface
    public static String[] randSelNames(int count, String[] ip, Supplier<Integer> s) {
        String[] selVals = new String[count];
        for (int i = 0; i < count; i++) {
            selVals[i] = ip[s.get()];
        }
        return selVals;
    }

    // using custom func interf
    public static <R> R calce(Operation<R> meth, R p1, R p2) {
        R res = meth.operate(p1, p2);
        System.out.println("Op of operation is " + res);
        return res;
    }

    public static <S> void accept(BiConsumer<S, S> oper, S val1, S val2) {
        oper.accept(val1, val2);
    }

    interface Operation<I> {
        public I operate(I p1, I p2);
    }

    interface SomeInter {

        public static String someVar = "";
        // public String someVare;
        // ExtSome someCla;

        // has to have body
        private void SomeMeth() {
        }

        // no body here and is an abstract
        public void SomeMetho();

        // only these can have body
        public default void SomeMetho(String one) {
        }

        public default void SomMetho() {
        }

        public static void SomeMethod() {
        }

        private void SomeMet() {
        }
        // abstract record someRec(int one, String two){};
        // abstract enum Some{ one, two, three };
    }

    @Retention(RetentionPolicy.RUNTIME)
            // @Target({ElementType.METHOD})
    @interface AnnInterf {
        String name();

        int no() default 3;
    }

    @AnnInterf(name = "", no = 3)
    class ExtSome extends SomeCla {

        static String someVar;
        ExtSome someCla;

        @Override
        void Nobody() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    abstract class SomeCla {
        // abstract record someRec(int one, String two){};
        // abstract enum Some{ one, two, three };
        void SomeMeth() {
        }

        abstract void Nobody();

        // blah (foo [bar 'ba|z'])
    }

    // records are final fields and get like rec.lastName()
    // used instead of beans meaning class with only data in it
    public record SomeRec(String name, String lastName, String email, int id) {
        SomeRec(String name, String lastName, String email) {
            this(name, lastName, email, 3);
        }
    }

    public static int ArraySum(int[] input, int[] ind) {

        // int[] resSum = new int[ind.size()/2];
        // ind = {0, 6, 7, 15, 16, 26};
        int sum = 0;
        int sumLoop = 0;
        int st = 0;
        int end = 0;
        // int times = ind.length/2;

        // loops no. of sub array times
        for (int i = 0; i < ind.length; i = i + 2) {
            st = ind[i];
            end = ind[i + 1];
            System.out.println("St " + st);
            System.out.println("En " + end);

            // loop each sub array
            for (int y = st; y < end; ++y) {
                //for(int z = input[y]; z < input[end]; z++) {
                System.out.println("input[y] " + input[y]);

                sum = sum + input[y];
                //}
            }

            System.out.println("Res is " + sum);

            if (sum > sumLoop) {
                sumLoop = sum;
            }

            sum = 0;

        }

        return sumLoop;
    }
}

class ClassName {
    private String propName = "";

    public String getPropName() {
        return propName;
    }
}

// @FunctionalInterface
interface FirLasComp<S> extends Comparator<S> {

    public int AnotherComp(S per1, S per2);
}

/* 
   class Compare<Person> implements Comparator<Person> {

   public int compare(Person arg0, Person arg1) {
// getting undef sym err
// return arg0.getFirstName().compareTo(arg1.getFirstName());
arg0 = new Person("fir", "las");
arg0.getFirstName();
return 1;
   }
   }
   class Person{
   private String firstName;

   private String lastName;

   public Person(String fir, String las){
   firstName = fir;
   lastName = las;
   }

   public String getFirstName() {
   return firstName;
   }

   public void setFirstName(String firstName) {
   this.firstName = firstName;
   }

   public String getLastName() {
   return lastName;
   }

   public void setLastName(String lastName) {
   this.lastName = lastName;
   }

   public String toString(){
   return firstName + " " + lastName;
   }
   }
   */
public class learn {

    public void CopyStream() {
        List<String> strings = List.of("one", "two", "three", "four");
        Map<String, String> map = Map.of("key", "val", "2", "val2");
        Function<String, Integer> toLength = String::length;
        List<Integer> ints = strings.stream()
                .map(toLength)
                .collect(Collectors.toList());

        var ints_ = strings.stream()
                .mapToInt(String::length)
                .summaryStatistics();

        System.out.println("List is " + ints);

    }

    public void CallableFuture() {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<?> f = es.submit(new Callable<Void>() {
            public Void call() {
                return null;
            }
        });

        Future<String> fS = es.submit(new Callable<String>() {
            public String call() {
                return "";
            }
        });

        try {
            f.get();
            System.err.println("Future Res is " + fS.get());
        } catch (InterruptedException ex) {
        } catch (ExecutionException ex) {
        }
    }

    public void StreamsMapFilterReduce() {
        String fil = "/tmp/so", str = "rama";
        try (var strm = Files.lines(Path.of(fil))) {
            long count = strm.filter(line -> line.contains(str)).count();

            // var c = strm.takeWhile(bo -> bo.contains('bo'));
            System.out.println("Found " + count + " of " + str);
        } catch (IOException ex) {
            System.err.println("Exception is " + ex.toString());
        }

        IntStream.range(0, 7)
                // .forEach(ic -> System.err.println("Ele is " + ic));
                .takeWhile(no -> no > 2)
                .forEach(System.out::println);

        // IntStream.iterate(0, 8);
        List<String> list = List.of("one", "two", "three");
        list
                .stream()
                .filter(pr -> pr.contains("pr"))
                .forEach(cr -> System.out.println("" + cr));

        list
                .stream()
                // .map(ele -> ele.toUpperCase())
                .filter(pr -> pr.length() == 3)
                .map(String::toUpperCase)
                // .forEach(ele -> System.out.println(ele));
                .forEach(System.out::println);
    }

    public void Semaphore(Semaphore s) {
        try {
            s.acquire();
            s.release();
            s.wait();
            s.acquire();
            s.release();

        } catch (InterruptedException ex) {
        }
    }

    public void Semaphore_(Semaphore s) {
        // Semaphore s = new Semaphore(1, true);
        try {
            s.acquire();

            // wake up waiting thrds
            s.notify();

            s.release();

        } catch (InterruptedException ex) {
        }
    }

    public void useThr() {
        // Can be used for thread safe operations without synchronized
        // ArrayBlockingQueue abq = new ArrayBlockingQueue(10);
        SortedSet<Integer> sortedSet = null;
        // sortedSet.add(1);

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2) {
                return -1;
            }
        });

		/*
		   Thread1 thread2 = new Thread1();
		   thread2.start();
		   */
		/*
		   Thread thr1 = new Thread(new Runnable1());
		   thr1.start();

		// thr1.interrupt();

		Thread thr2 = new Thread(new Runnable1());
		thr2.start();

		Thread thr3 = new Thread(new Runnable() {
		public void run() {
		try {
		sleep(100);
		} catch (InterruptedException ex) {
		}
		}
		});
		thr2.start();

*/
    }

    public static int[] arrayOfMultiples(int num, int length) {

        int[] arr = new int[length];
        arr[0] = 7;
        // int arrr[] = { 3, 34, 5};

        for (int i = 0; i < length; i++) {
            // i++;
            arr[i] = num * (i + 1);
        }

        return arr;
    }

    public static int sum(int[] arr) {

        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        }

        int no = 0;
        int i = 0;

        while (i > 0) {
            no = no + arr[i++];
            sum(arr);
        }

        return no;
    }

    public static int sum_(int[] arr) {

        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        }

        int[] no = new int[arr.length - 1];
        no[arr.length - 2] = arr[arr.length - 1] + arr[arr.length - 2];
        return sum_(no);

        // return no[0];
    }

    public static void StreamSample() {
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        // stream = Stream.of("a", "b", "c");

        HashMap<String, String> rec;
        // SortedMap<String, String> rec;

        ArrayList<String> al = new ArrayList<String>();
        al.add("one");
        al.add("two");
        stream = al.stream();

        al.parallelStream().forEach(ele -> System.out.println(ele));

        long len = al.stream().distinct().count();
        al.stream().distinct();

        boolean isExist = al.stream().anyMatch(element -> element == "a");

        Stream<String> stream_ = al.stream().filter(element -> element == "d");

        // String str = () -> System.out.println("");
        new Thread(() -> {
            System.out.println("New thread created");
        }).start();

        var queue = new ArrayBlockingQueue(3);
        queue.add("len");
        queue.add("two");
        queue.add("three");
        queue.remove();
        try {
            // wait until queue is empty
            queue.put("len");
        } catch (InterruptedException ex) {
        }
        try {
            // wait until queue has atleast one item
            queue.take();
        } catch (InterruptedException ex) {
        }

        Iterator<String> it = queue.iterator();
        it.next(); // gives len?
        it.next(); // gives two?
        it.remove(); // no add here

        LinkedList list = new LinkedList<String>();
        list.add("e");
        list.add("w");
        list.add("o");
        ListIterator<String> listIterator = list.listIterator();
        listIterator.next();
        listIterator.add("new"); // add and rem here
        listIterator.remove();

        // can go back as well
        listIterator.previous();

        // var sdf = new ImplIter();
        // ArrayList<String> dfd = sdf.al;
        // dfd.iterator().next();
    }

    public void Comparator_() {

        ArrayList<String> al = new ArrayList<String>();

        // sort in natural order
        Collections.sort(al);

        // Collections.sort(al, new SampleClass());
        // anonymous class
        Collections.sort(al, new Comparator<String>() {
            public int compare(String t, String t1) {
                return -t.compareTo(t1); // reverse alpha
                // throw new UnsupportedOperationException("Not supported yet.");
            }

        });
    }

    public class ImplIter implements Iterable<String> {

        public LinkedList<String> link = new LinkedList();

        class ImplIterat implements Iterator<String> {

            int index = 0;

            public boolean hasNext() {
                return link.size() > index;
            }

            public String next() {
                if (hasNext())
                    return link.get(index);
                else
                    throw new UnsupportedOperationException("No 'next' ele ");
            }
        }

        public Iterator<String> iterator() {
            return new ImplIterat();
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'iterator'");
        }
    }

    public class SampleClass implements Comparator<String> {

        //@Override
        public int compare(String t, String t1) {
            // throw new UnsupportedOperationException("Not supported yet.");
            // for t < t1
            // return 1;
            // else ie for equal
            // return 0;
            if (t.length() < t.length())
                return -1;
            else
                return 1;

            // return 0;
        }

    }

    public static void var(String par) {
        System.out.println("" + par);
        String[] arr = new String[]{"a", "b", "c", "d", "e"};
        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        Arrays.stream(arr, 1, 3);

        Sum s = (int x, int y) -> x + y;

        System.out.println("Sum is " + s.calc(3, 9));

        Cons cs = (Double in) -> System.out.println("Input processed is " + in);
        cs.accept(2.3);

        Pre pr = (double in) -> in > 3;
        System.out.println("Pred res is " + pr.test(2.2));

        Funct func = (Integer w) -> w + 1;
        System.out.println("Func res is " + func.apply(2));

        // Pro pro = () -> 2;
        // System.out.println("Pro res is " + pro.get());

        Ltd ltd = (long val) -> val * 1.0;
        ltd.applyAsDouble(6);


        // FileOutputStream
        // ObjectOutputStream
    }

    interface Ltd extends java.util.function.LongToDoubleFunction {

        double applyAsDouble(long value);
    }

    interface Cons extends Consumer<Double> {

        @Override
        public void accept(Double in);
    }

    // interface Pre extends Predicate<Double> {
    interface Pre extends DoublePredicate {

        @Override
        public boolean test(double in);
    }

    interface Funct extends Function<Integer, Integer> {

        @Override
        public Integer apply(Integer d);
    }

    interface Pro extends Supplier<Integer> {

        @Override
        default Integer get() {
            return new Random().nextInt();
        }

        ;
    }

    // functional interface
    public interface Sum {

        public int calc(int x, int y);
        // public String calc(int y);
    }
}

class Spliterator_ implements Spliterator<String> {

    @Override
    public boolean tryAdvance(Consumer<? super String> cnsmr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Spliterator<String> trySplit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long estimateSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int characteristics() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

class ProCons2 {

    public List<Integer> list = new LinkedList<Integer>();

    private Object lock = new Object();

    private Random r = new Random(100);

    public synchronized void produce() throws InterruptedException {
        System.out.println("Starting Producer");

        do {
            synchronized (lock) {
                list.add(r.nextInt());

                do {
                    if (list.size() == 10) {
                        wait();
                    }
                    Thread.sleep(100);
                } while (true);
            }
        } while (true);
    }

    public synchronized void consumer() throws InterruptedException {
        System.out.println("Starting Consumer");
        do {
            synchronized (lock) {
                do {
                    // list.remove();
                    // System.out.println("sdfds sdfd");
                    if (list.size() == 0) {
                        notify();
                    }
                } while (true);
            }
        } while (true);
    }
}

class ProCons {

    public synchronized void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Starting Producer");

            Thread.sleep(1000);

            wait();
            System.out.println("Resuming");
        }
    }

    public synchronized void consumer() throws InterruptedException {
        System.out.println("Starting Consumer");
        Thread.sleep(1000);
        synchronized (this) {
            Scanner s = new Scanner(System.in);
            s.nextLine();
            notify();

            Thread.sleep(1000);

        }
    }
}

/*
 * a-->a-->a-->b-->d-->e-->e-->h
 * output: a-->3-->b-->1-->d-->1-->e-->2-->h-->1
 * Number of consecutive chars <= 9
 class ListNodeChar
 {
 char val;
 ListNodeChar next;
 }
 public class ConsNos {

// ListNodeChar listNodeChar;

public ListNodeChar Result(ListNodeChar listNodeChar) {

var listNodeChar_ = listNodeChar_;
listNodeChar_.next = null;

ListNodeChar prev;
char a = listNodeChar_.val;
int count = 0;

while(listNodeChar.next != null) {

if(listNodeChar.val != a)
var d = new ListNodeChar();
d.val = count;
listNodeChar_.next = d;

d = new ListNodeChar();
d.val = listNodeChar.val;
listNodeChar_.next = d;
count = 0;

}
count++;

listNodeChar = listNodeChar.next;

}

return ListNodeChar_;
 }
 public static void Main() {

 ConsNos res = new ConsNos();
 ListNodeChar lis = new ListNodeChar();
 var re = res.result(lis);

 while(re.next != null)
 {
 System.out.println("Element or count is " + re.val);
 }

 }
 */


