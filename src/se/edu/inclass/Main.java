package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

//        printData(tasksData);
//        System.out.println("Printing deadlines");
//        printDeadlines(tasksData);

//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printSortedDeadlines(tasksData);


        for (Task t : filterByString(tasksData, "11")) {
            System.out.println(t);
        }

//        printDataUsingStreams(tasksData);
//        printDeadlinesUsingStreams(tasksData);
        System.out.println("total deadline is " + countDeadlinesUsingStreams(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static int countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Calculating deadlines using streams --------------------------------");
        int count;
        //casting is required as count() returns a long value
        count = (int) tasksData.stream()
                .filter(t -> t instanceof Deadline)
                .count();

        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    //printing data using streams
    public static void printDataUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing Data using streams--------------------------");
        //converting collection into stream
        tasksData.stream()
                .forEach(System.out::println);

        System.out.println("Printing Data using streams--------------------------");

    }


    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }


    public static void printSortedDeadlines(ArrayList<Task> tasksData) {
        System.out.println("printing sorted deadlines-------------------");
        tasksData.stream()
                .filter(s -> s instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))//requires a comparator as a parameter, so i create one on the fly
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredTaskList;
        filteredTaskList = (ArrayList<Task>) tasksData.stream() //casting as toList returns a list object not arraylist
                //taking task object and takes its description if the description contains filterString then it will collect it and put into a list
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());

        return filteredTaskList;


    }
}
