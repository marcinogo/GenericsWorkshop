package com.github.wojtechm;


import java.util.Arrays;
import java.util.List;

/**
 * Generic Inheritance!
 * <p>
 * Now. We have army of Singers!
 * Lets find out what we can do with them!
 * <p>
 * @author Wojciech Makiela
 */
public class Task5 {

    // Yeah. I know. Copy-paste development.
    // BUT! New methods are were introduced! Take a look
    interface Cloneable<T> {
        T clone();
    }
    static class Person {
        private String name;
    }
    static class Singer extends Person {
        private String genre;

        Singer(String genre) {
            this.genre = genre;
        }

        @Override
        public String toString() {
            return "Singer{" +
                    "genre='" + genre + '\'' +
                    '}';
        }
    }
    static class ElvisPresley extends Singer implements Cloneable<ElvisPresley> {
        private boolean isBestSinger = true;

        ElvisPresley(String genre) {
            super(genre);
        }

        @Override
        public ElvisPresley clone() {
            return new ElvisPresley(super.genre);
        }
    }

    public static void main(String[] args) {
        // Take a look at workWithSingers method. Uncomment lines 56 and 57 - see what happens
        List<Singer> singers = Arrays.asList(new Singer("Rock"), new Singer("Blues"));
        workWithSingers(singers);
//        List<ElvisPresley> presleyList = Arrays.asList(new ElvisPresley("Rock'n'Roll"));
//        workWithSingers(presleyList);

        /*
        So... Even though Elvis is a Singer, you cannot pass List<ElvisPresley> to workWithSingers method
        It's because List<Elvis> is NOT a subtype of List<Singer>. Why? Consider following code

            List<Integer> ints = Arrays.asList(1, 2);
            List<Number> nums = ints;
            nums.add(3.14);
            ints.toString().equals("1, 2, 3.14"); // whoops! A double in our its list! o.O

        Assigning list of ints to List<Number> variable is illegal, because this might lead to
        breaking initial list - list of ints having double in it.
        After all, you can add a Double to list of Numbers.
        In that example, List<Number> extends Object, and List<Integer> extends Object,
        thus our lists do not have parent-child relation - they are siblings.
        And as You already know, You can not assign variable of type A, to variable of type B
        unless A extends B.

        Same goes with List<ElvisPresley>. We don't want Lady Gaga in that list.

        Since You already know that inheritance in generics is not that easy, let's fix our 'workWithSingers'
        method, so it accepts any list of singers.
        To do that, you will use something called "wildcard". Start with changing declaration of our method to:
        private static void workWithSingers(List<?> singers)
        This question mark (?) is not something that You need to type in. It's valid java syntax
        Copy-paste and see.
        A wildcard type represents an unknown type.
        Here, we are specifying a list which could be of any type, as long as this type extends Singer.

         */
    }

    private static void workWithSingers(List<Singer> singers) {
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }
}
