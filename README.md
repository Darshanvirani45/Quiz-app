# Quiz-app
---------------------------------------------------------------------------------------------------------------------------
A place where  you  can explore your self 

#DB Design:
----------------------------------------------------------------------------------------------------------------------------
![image](https://github.com/user-attachments/assets/08a5c2d2-7581-4364-b63f-a6adb98c234b)

#Description:
____________________________________________________________________________________________________________________________
This project is a Quiz Application built using Spring Boot, which provides API endpoints for managing quiz sessions. It allows users to engage with quizzes, answer questions, and track their performance. The application is designed to handle multiple-choice questions stored in a database and offers various functionalities to create a smooth and interactive user experience.

#How to run:
------------------------------------------------------------------------------------------------------------------------------
##to run the quiz there some Prerequisites:
1)JDK 11 or higher
2)Maven
3)Postman
4)Spring boot

#Access the H2 Database Console:
-------------------------------------------------------------------------------------------------------------------------------
1) URL: http://localhost:8080/h2-console
2) JDBC URL: jdbc:h2:mem:testdb
3) Username: sa
4) Password: (leave blank)

#Project Structure:
-----------------------------------------------------------------------------------------------------------------------------
1)Controllers  : handles http request of applications.
2)Services     : carry on all the bussiness logic og application.
3)Dao          : data access object. this is layer which intrect with Database.
4)Entities     : represent data models or DataBase tables.

#Technologies Use:
------------------------------------------------------------------------------------------------------------------------------
1)Maven
2)Spring boot
3)H2 db
4>Spring Data Jpa

#Key Features:
-------------------------------------------------------------------------------------------------------------------------------
1)Start a New Quiz Session:

  =>Users can initiate a new quiz session through an API endpoint.
  =>endpoint:http://localhost:8080/quiz/session (http-verb:post)
  =>RequestBody Sample : {
                            "userId":1,
                            "totalQuestions":10,
                            "category":"Os"
                          }
  =>ResponseBody Sample : {
                            "sessionId": 2,
                            "numberOfQuestions": 10,
                            "sessionUser": {
                                "userId": 1,
                                "name": "Rohit",
                                "email": "rohit@gmail.com"
                                            }
                        }
                        
2)Get a Random Multiple Choice Question:

=>This endpoint will return set of random question based on selecetd category
=>endpoint : http://localhost:8080/quiz/random-questions (http-verb:get)
=> RequestHeader Format : sessionId -> id of session which is created previously
=> ResponseBody Sample : 
        [
    {
        "questionText": "Explain the concept of process and thread in an operating system.",
        "answerOptionPairs": {
            "A": "A process is a program in execution; a thread is the smallest unit of a process",
            "B": "A thread is a program in execution; a process is the smallest unit of a thread",
            "C": "Both are the same",
            "D": "None of the above"
        }
    },
    {
        "questionText": "Explain the concept of deadlock in operating systems.",
        "answerOptionPairs": {
            "A": "A situation where processes are stuck in a loop waiting for resources",
            "B": "A situation where memory is full",
            "C": "A situation where CPU is fully utilized",
            "D": "None of the above"
        }
    },
    {
        "questionText": "What is the purpose of an interrupt in an operating system?",
        "answerOptionPairs": {
            "A": "To handle events that need immediate attention",
            "B": "To allocate memory",
            "C": "To manage files",
            "D": "None of the above"
        }
    },
    {
        "questionText": "What is the difference between multitasking and multiprocessing?",
        "answerOptionPairs": {
            "A": "Multitasking allows running multiple applications; multiprocessing uses multiple CPUs",
            "B": "Multiprocessing allows running multiple applications; multitasking uses multiple CPUs",
            "C": "Both are the same",
            "D": "None of the above"
        }
    },
    {
        "questionText": "What are the different types of operating systems?",
        "answerOptionPairs": {
            "A": "Batch, Time-sharing, Distributed",
            "B": "Single-user, Multi-user",
            "C": "Embedded, Real-time",
            "D": "All of the above"
        }
    },
    {
        "questionText": "What is the purpose of a semaphore in an operating system?",
        "answerOptionPairs": {
            "A": "To manage access to shared resources",
            "B": "To increase memory",
            "C": "To speed up the CPU",
            "D": "None of the above"
        }
    },
    {
        "questionText": "Explain the concept of paging and segmentation.",
        "answerOptionPairs": {
            "A": "Paging divides memory into fixed-size pages; segmentation divides memory into variable-sized segments",
            "B": "Segmentation divides memory into fixed-size pages; paging divides memory into variable-sized segments",
            "C": "Both are the same",
            "D": "None of the above"
        }
    },
    {
        "questionText": "What is the role of a device driver in an operating system?",
        "answerOptionPairs": {
            "A": "To control hardware devices",
            "B": "To manage memory",
            "C": "To increase CPU speed",
            "D": "None of the above"
        }
    },
    {
        "questionText": "How does a shell work in an operating system?",
        "answerOptionPairs": {
            "A": "Provides an interface for users to interact with the OS",
            "B": "Manages hardware devices",
            "C": "Allocates memory",
            "D": "None of the above"
        }
    },
    {
        "questionText": "Explain the concept of process synchronization.",
        "answerOptionPairs": {
            "A": "To coordinate access to shared resources",
            "B": "To increase memory",
            "C": "To speed up the CPU",
            "D": "None of the above"
        }
    }
]

3)Submit an Answer:
   =>Using this endpoint user's answer will go for verification
   =>endpoint :  http://localhost:8080/quiz/submit-answer  (http-verb:post)
   =>RequestBody Sample:
     {
	"sessionId":1,
        "answerList":["A","B","B",null,"C","C","B","A",null,"A"]
     }
   =>ResponseBody Sample:
     {
    "user": {
        "userId": 1,
        "name": "Rohit",
        "email": "rohit@gmail.com"
    },
    "summaryDetailsList": [
        {
            "question": "Explain the concept of process and thread in an operating system.",
            "selected": "A) A process is a program in execution; a thread is the smallest unit of a process",
            "correct": "A) A process is a program in execution; a thread is the smallest unit of a process"
        },
        {
            "question": "Explain the concept of deadlock in operating systems.",
            "selected": "B) A situation where memory is full",
            "correct": "A) A situation where processes are stuck in a loop waiting for resources"
        },
        {
            "question": "What is the purpose of an interrupt in an operating system?",
            "selected": "B) To allocate memory",
            "correct": "A) To handle events that need immediate attention"
        },
        {
            "question": "What is the difference between multitasking and multiprocessing?",
            "selected": "none",
            "correct": "A) Multitasking allows running multiple applications; multiprocessing uses multiple CPUs"
        },
        {
            "question": "What are the different types of operating systems?",
            "selected": "C) Embedded, Real-time",
            "correct": "D) All of the above"
        },
        {
            "question": "What is the purpose of a semaphore in an operating system?",
            "selected": "C) To speed up the CPU",
            "correct": "A) To manage access to shared resources"
        },
        {
            "question": "Explain the concept of paging and segmentation.",
            "selected": "B) Segmentation divides memory into fixed-size pages; paging divides memory into variable-sized segments",
            "correct": "A) Paging divides memory into fixed-size pages; segmentation divides memory into variable-sized segments"
        },
        {
            "question": "What is the role of a device driver in an operating system?",
            "selected": "A) To control hardware devices",
            "correct": "A) To control hardware devices"
        },
        {
            "question": "How does a shell work in an operating system?",
            "selected": "none",
            "correct": "A) Provides an interface for users to interact with the OS"
        },
        {
            "question": "Explain the concept of process synchronization.",
            "selected": "A) To coordinate access to shared resources",
            "correct": "A) To coordinate access to shared resources"
        }
    ],
    "category": "Os",
    "numberOfQuestions": 10,
    "numberOfCorrectAnswer": 3,
    "numberOfInCorrectAnswer": 5,
    "numberOfNotAttemptedQuestions": 2,
    "score": 3
    }

4) Get Quiz Summary:
   =>by using this endpoint user can get summary for there quiz at any time.
   =>endpoint : http://localhost:8080/quiz/session/result  (http-verb : get)
   =>RequestHeader : sessionId-> specify session id for that quiz is finished
   =>ResponseBody :{
    "user": {
        "userId": 1,
        "name": "Rohit",
        "email": "rohit@gmail.com"
    },
    "summaryDetailsList": [
        {
            "question": "Explain the concept of process and thread in an operating system.",
            "selected": "A) A process is a program in execution; a thread is the smallest unit of a process",
            "correct": "A) A process is a program in execution; a thread is the smallest unit of a process"
        },
        {
            "question": "Explain the concept of deadlock in operating systems.",
            "selected": "B) A situation where memory is full",
            "correct": "A) A situation where processes are stuck in a loop waiting for resources"
        },
        {
            "question": "What is the purpose of an interrupt in an operating system?",
            "selected": "B) To allocate memory",
            "correct": "A) To handle events that need immediate attention"
        },
        {
            "question": "What is the difference between multitasking and multiprocessing?",
            "selected": "none",
            "correct": "A) Multitasking allows running multiple applications; multiprocessing uses multiple CPUs"
        },
        {
            "question": "What are the different types of operating systems?",
            "selected": "C) Embedded, Real-time",
            "correct": "D) All of the above"
        },
        {
            "question": "What is the purpose of a semaphore in an operating system?",
            "selected": "C) To speed up the CPU",
            "correct": "A) To manage access to shared resources"
        },
        {
            "question": "Explain the concept of paging and segmentation.",
            "selected": "B) Segmentation divides memory into fixed-size pages; paging divides memory into variable-sized segments",
            "correct": "A) Paging divides memory into fixed-size pages; segmentation divides memory into variable-sized segments"
        },
        {
            "question": "What is the role of a device driver in an operating system?",
            "selected": "A) To control hardware devices",
            "correct": "A) To control hardware devices"
        },
        {
            "question": "How does a shell work in an operating system?",
            "selected": "none",
            "correct": "A) Provides an interface for users to interact with the OS"
        },
        {
            "question": "Explain the concept of process synchronization.",
            "selected": "A) To coordinate access to shared resources",
            "correct": "A) To coordinate access to shared resources"
        }
    ],
    "category": "Os",
    "numberOfQuestions": 10,
    "numberOfCorrectAnswer": 3,
    "numberOfInCorrectAnswer": 5,
    "numberOfNotAttemptedQuestions": 2,
    "score": 3
}

5)Performance:
==> Also That all the endpoints are totaly responsible and also secure as we manage to handle exceptions.
