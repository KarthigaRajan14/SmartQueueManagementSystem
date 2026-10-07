import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        PriorityQueue<Person> queue=new PriorityQueue<>();
        System.out.println("Smart Queue Management System");
        int choice;
        int tokenNumber=1;
        int serviceTime=5;
        while(true) {

            System.out.println("1.Join queue");
            System.out.println("2.view queue");
            System.out.println("3.serve queue");
            System.out.println("4.Queue Statistics");
            System.out.println("5.Save Queue");
            System.out.println("6.Load Queue");
            System.out.println("7.Exit");

            System.out.println("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            if (choice == 1) {
                System.out.println("Enter your name: ");
                String name = sc.nextLine();

                String priority;

                while (true) {
                    System.out.println("Enter priority (Normal/Priority): ");
                    priority = sc.nextLine();

                    if (priority.equalsIgnoreCase("Normal")) {
                        priority = "Normal";
                        break;
                    } else if (priority.equalsIgnoreCase("Priority")) {
                        priority = "Priority";
                        break;
                    }

                    System.out.println("Invalid priority! Please enter Normal or Priority.");
                }

                Person person = new Person(name, tokenNumber, priority);
                int waitingTime = 0;

                PriorityQueue<Person> tempQueue = new PriorityQueue<>(queue);
                tempQueue.add(person);

                while (!tempQueue.isEmpty()) {
                    Person p = tempQueue.poll();

                    if (p.tokenNumber == person.tokenNumber) {
                        break;
                    }

                    waitingTime += serviceTime;
                }
                queue.add(person);

                System.out.println(name + " joined the queue!");
                System.out.println("Your Token Number: " + tokenNumber);
                System.out.println("Estimated Waiting Time: " + waitingTime + " minutes");
                tokenNumber++;
            }

            else if(choice == 2) {
                PriorityQueue<Person> tempQueue = new PriorityQueue<>(queue);
                int waitingTime=0;

                System.out.println("<<<<<<<<Current Queue>>>>>>>>");

                while (!tempQueue.isEmpty()) {
                    Person p = tempQueue.poll();

                    System.out.println(p.name + " | Token: " + p.tokenNumber
                            + " | Priority: " + p.priority
                            + " | Estimated Waiting Time: " + waitingTime + " minutes");

                    waitingTime += serviceTime;
                }

                if (queue.isEmpty()) {
                    System.out.println("Queue is currently empty");
                }
                System.out.println("______________________");
            }

            else if(choice == 3) {
                if (queue.isEmpty()) {
                    System.out.println("No one is waiting in the queue!");
                } else {

                    Person servedPerson = queue.poll();
                    System.out.println("Serving Token: " + servedPerson.tokenNumber);
                    System.out.println("Name: " + servedPerson.name);
                    System.out.println(servedPerson.name + " has been served successfully!");
                }
            }
            else if(choice==4){
                System.out.println("===== QUEUE STATISTICS =====");

                int totalPeople = queue.size();
                int priorityPeople = 0;

                for (Person person : queue) {
                    if (person.priority.equals("Priority")) {
                        priorityPeople++;
                    }
                }
                int normalPeople = totalPeople - priorityPeople;
                System.out.println("Total People: " + totalPeople);
                System.out.println("Priority People: "+priorityPeople);
                System.out.println("Normal People: " + normalPeople);
            }
            else if(choice==5){
                saveQueue(queue);
            }
            else if(choice==6){
                tokenNumber = readQueueFile(queue, tokenNumber);
            }
            else if(choice == 7) {
                System.out.println("Thank You!");
                break;
            }
            else{
                System.out.println("Invalid choice! Please enter the choice from 1-7.");
            }
        }
    }
    public static void saveQueue(PriorityQueue<Person> queue) {

        try {
            FileWriter writer = new FileWriter("queue.txt");

            for (Person person : queue) {
                writer.write(person.name + " | Token: "
                        + person.tokenNumber + " | "
                        + person.priority + "\n");
            }

            writer.close();

            System.out.println("Queue saved successfully!");

        } catch (IOException e) {
            System.out.println("Unable to save queue. File error occurred.");
        }
    }

    public static int readQueueFile(PriorityQueue<Person> queue, int tokenNumber) {

        int maxToken = 0;

        try {
            Scanner reader = new Scanner(new java.io.File("queue.txt"));

            queue.clear();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length < 3) {
                    continue;
                }

                String name = parts[0].trim();

                int loadedToken = Integer.parseInt(
                        parts[1].replace("Token:", "").trim()
                );

                String priority = parts[2].trim();

                if (loadedToken > maxToken) {
                    maxToken = loadedToken;
                }

                Person person = new Person(name, loadedToken, priority);
                queue.add(person);
            }

            reader.close();

            System.out.println("Queue loaded successfully!");

            return maxToken + 1;

        } catch (IOException e) {
            System.out.println("Queue file not found. Please save the queue first.");
            return tokenNumber;
        }
    }
}