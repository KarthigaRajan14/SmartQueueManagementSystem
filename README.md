# Smart Queue Management System

## Project Overview

Smart Queue Management System is a Java console application designed to manage people waiting for service. It uses a `PriorityQueue` to ensure that Priority users are served before Normal users while maintaining token numbers and estimated waiting times.

The application also supports queue statistics and file-based saving and loading of queue data.

## Features

- Add people to the queue
- Normal and Priority queue handling
- Automatic token number generation
- Estimated waiting time
- View current queue
- Serve people based on priority
- Queue statistics
- Save queue data to a file
- Load saved queue data
- Input validation and exception handling

## Technologies Used

- Java
- Java Collections Framework
- PriorityQueue
- File Handling
- Exception Handling
- IntelliJ IDEA

## How It Works

The system uses Java's `PriorityQueue` to manage people waiting for service.

People can join the queue as either:

- **Priority** — served before Normal users
- **Normal** — served after Priority users

Each person receives a unique token number. The system also estimates the waiting time based on the number of people who will be served before them.

The application provides a menu-driven interface for joining, viewing, serving, saving, and loading queue data.

## Menu Options

1. Join Queue
2. View Queue
3. Serve Queue
4. Queue Statistics
5. Save Queue
6. Load Queue
7. Exit

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA or any Java-supported IDE.
3. Make sure Java is installed and configured.
4. Run `Main.java`.
5. Use the menu options to manage the queue.

## Project Structure

```text
SmartQueueManagementSystem/
│
├── src/
│   ├── Main.java
│   └── Person.java
│
├── queue.txt
└── README.md
```
## File Storage

The application uses a `queue.txt` file to save and load queue information.

The saved data includes:

- Person name
- Token number
- Priority type

## Future Enhancements

- Graphical User Interface (GUI)
- Database integration
- Advanced queue analytics
- User authentication
- Improved waiting-time prediction

## Author

**Karthiga R**

B.Tech Information Technology Student