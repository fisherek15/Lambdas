package best.programming;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //1.1 Runnable example
        new Thread(new CodeToRun()).start();

        //1.2 Runnable example
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from the anonymous Runnable class");
            }
        }).start();

        //1.3 Runnable example
        new Thread(() -> System.out.println("Printing from the lambda Runnable")).start();

        //1.3a Runnable example
        new Thread(() -> {
            System.out.println("Printing from the expanded lambda Runnable");
            System.out.println("This is line II");
            System.out.format("This is line %d\n", 3);
        }).start();

        Employee tom = new Employee("Tom Trimmer");
        Employee lisa = new Employee("Lisa Adele");
        Employee adrian = new Employee("Adrian Hummer");
        Employee andrea = new Employee("Andrea Still");

        List<Employee> employees = new ArrayList<>();
        employees.add(tom);
        employees.add(lisa);
        employees.add(adrian);
        employees.add(andrea);

        //2.1 Comparator example
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getName().compareTo(e2.getName());
            }
        });

        //2.2 Comparator example
        Collections.sort(employees, (e1, e2) ->
                e1.getName().compareTo(e2.getName()));

        //2.3 Comparator example
        Collections.sort(employees,
                Comparator.comparing(Employee::getName));

        //2.4 Compare example
        employees.sort(Comparator.comparing(Employee::getName));

        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }

        UpperConcat uc = (s1, s2) -> {
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };
        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);

        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();
        System.out.println(s);
    }

    public static final String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

class CodeToRun implements Runnable {

    @Override
    public void run() {
        System.out.println("Printing from the Runnable");
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass {
    public String doSomething(){

        UpperConcat uc = (s1, s2) -> {
            System.out.println("The lambda expression's class is " + getClass().getSimpleName());
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };

        System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());
        printValue();
        return Main.doStringStuff(uc, "String1", "String2");
    }

    public void printValue(){
        int number = 25;
        Runnable r = () -> {
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("The value is " + number);
        };

        new Thread(r).start();
    }
}