public class Person implements Comparable<Person>{
    String name;
    int tokenNumber;
    String priority;

    Person(String name,int tokenNumber,String priority){
        this.name=name;
        this.tokenNumber=tokenNumber;
        this.priority=priority;
    }

    @Override
    public int compareTo(Person other) {
        if (this.priority.equals(other.priority)) {
            return Integer.compare(this.tokenNumber, other.tokenNumber);
        }

        if (this.priority.equals("Priority")) {
            return -1;
        }

        return 1;
    }
}
