package com.venkat.fp;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Course {
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    /*public int getReviewScore() {
        return reviewScore;
    }

    public Course(){}

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", reviewScore=" + reviewScore +
                ", noOfStudents=" + noOfStudents +
                '}';
    }

    public static void main(String[] args) {
        List<Course> courses = getCourses();

        Predicate<Course> coursePredicateGT64 = course -> course.getReviewScore() > 64;
        System.out.println(courses.stream()
                .allMatch(coursePredicateGT64));

        Predicate<Course> coursePredicateGT95 = course -> course.getReviewScore() > 95;
        System.out.println(courses.stream()
                .allMatch(coursePredicateGT95));

        boolean match = courses.stream()
                .noneMatch(course -> course.getReviewScore() > 98);

        System.out.println("non match :" + match);

    }

    static Optional<Course> getCourseByPosition(int position){
        //var courseList = getCourses();
        List<Course> courseList = getCourses();
        Optional<Course> course = Optional.empty();

        if(position >= 0 && position < courseList.size()){
            course = Optional.of(courseList.get(position));
        }
        return course;
    }

    static Optional<Course> getCourseByPosition2(int position){
        //var courseList = getCourses();
        List<Course> courseList = getCourses();
        Course course = null;

        if(position >= 0 && position < courseList.size()){
            course =courseList.get(position);
        }
        return Optional.ofNullable(course);
    }*/

    /*private static List<Course> getCourses() {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 13400),
                new Course("API", "Microservices", 97, 12280),
                new Course("Microservices", "Microservices", 96, 10000),
                new Course("FullStack", "FullStack", 91, 19000),
                new Course("AWS", "Cloud", 74, 17000),
                new Course("Azure", "Cloud", 87, 17000),
                new Course("Docker", "Cloud", 87, 20000),
                new Course("Kubernetes", "Cloud", 65, 20000)
        );
        return courses;
    }*/
}
