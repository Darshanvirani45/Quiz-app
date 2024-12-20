package com.quiz.Quiz;

import com.quiz.Quiz.dao.*;
import com.quiz.Quiz.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QuizApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private QuizCategoryRepository quizCategoryRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}


	//this method perform task of storing some dummy record in db
	@Override
	public void run(String... args) throws Exception {
		User user=new User("Rohit","rohit@gmail.com","rohit@45");
		userRepository.save(user);

		List<QuizCategory> quizCategories=List.of(new QuizCategory("Java"),new QuizCategory("Python"),new QuizCategory("Os"));
		quizCategoryRepository.saveAll(quizCategories);

		String[] category1=new String[]{"What is the size of an int in Java?","What does JVM stand for?",
							"Which method is used to start a thread in Java?","Which keyword is used to inherit a class in Java?",
							"What is the default value of a boolean variable in Java?","Which method can be used to find the length of a string in Java?",
							"What is the purpose of the final keyword in Java?","Which package is automatically imported in every Java program?",
							"How can you make a method synchronized in Java?","Which of the following is not a primitive data type in Java?",
							"Which class is the superclass of all classes in Java?","What is used to compile Java source code?",
							"How do you declare a constant in Java?","Which exception is thrown when an array is accessed with an invalid index?",
							"Which collection class does not allow duplicate elements?","Which of the following is a marker interface?",
							"What is the output of 10 + 20 + \"30\" in Java?","Which keyword is used for explicit garbage collection in Java?",
							"Which of following occupy more bytes in memory?",
							"Which of the following methods is used to compare two strings in Java?"};
		String[][] answer1=new String[][]{{"4 bytes","2 bytes","8 bytes","1 byte"},
							{"Java Virtual Machine","Java Verified Machine","Java Visual Machine","Java Virtual Model"},
							{"run()","start()","init()","execute()"},
							{"this","extends","implements","inherits"},
							{"true","false","0","null"},
							{"size()","length()","getSize()","getLength()"},
							{"To indicate a constant","To prevent inheritance","To prevent method overriding","All of the above"},
							{"java.until","java.lang","java.io","java.net"},
							{"Using synchronized keyword","Using volatile keyword","Using transient keyword","Using native keyword"},
							{"int","byte","String","char"},
							{"Object","Class","System","Runtime"},
							{"JDK","JRE","JVM","JIT"},
							{"final","const","static","immutable"},
							{"ArrayIndexOutOfBoundsException","NullPointerException","ArithmeticException","ClassNotFoundException"},
							{"List","Set","Map","Queue"},
							{"Serializable","Runnable","Cloneable","Both a and c"},
							{"30","3030","102030","30 + 30"},
							{"collect()","gc()","System.gc()","Runtime.gc()"},
							{"int","long","double","float"},
							{"equals()","==","compareTo()","Both a and c"}};

		char[] correct1 =new char[]{'A','A','B','B','B','B','D','B','A','C','A','A','A','A','B','D','B','C','A','D'};
		for(int i=0;i<category1.length;i++){
			String questionText =category1[i];
			String[] possibleAnswer=answer1[i];
			List<Answer> answers=new ArrayList<>();
			Question question=new Question(questionText,quizCategories.get(0),answers);
			for(int j=0;j< possibleAnswer.length;j++){
				Answer answer = new Answer(possibleAnswer[j], (j + 65) == correct1[i],(char)(65+j),question);
				answers.add(answer);
			}
			question.setAnswers(answers);
			questionRepository.save(question);
		}

		String[] category2 = new String[]{
				"What is the output of print(type([])) in Python?",
				"How do you create a virtual environment in Python?",
				"What is the output of print(2 ** 3) in Python?",
				"Which of the following is not a valid Python data type?",
				"How do you start the Python interpreter in interactive mode?",
				"Which of the following is used to define a block of code in Python?",
				"What is the use of the 'self' keyword in Python?",
				"Which of the following is a built-in function in Python?",
				"How do you create a list in Python?",
				"Which of the following statements is used to handle exceptions in Python?",
				"What does the 'pass' keyword do in Python?",
				"Which of the following is used to import a module in Python?",
				"How do you access a member of a list in Python?",
				"Which of the following is not a keyword in Python?",
				"What is the output of print('Hello ' + 'World') in Python?",
				"Which of the following is used to define a function in Python?",
				"How do you create a tuple in Python?",
				"Which of the following is used to iterate over a sequence in Python?",
				"Which of the following is used to create an anonymous function in Python?",
				"What is the use of the 'del' keyword in Python?",
		};

		String[][] answer2 = new String[][]{
				{"<type 'list'>", "<type 'tuple'>", "<type 'dict'>", "<type 'set'>"},
				{"virtualenv env", "python -m venv env", "mkvirtualenv env", "createvirtualenv env"},
				{"6", "8", "9", "4"},
				{"list", "tuple", "set", "array"},
				{"python -i", "python", "python -interactive", "py"},
				{"{}", "[]", "()", "Indentation"},
				{"To refer to the instance of the class", "To refer to the parent class", "To refer to the class itself", "To refer to the super class"},
				{"len()", "sum()", "range()", "all of the above"},
				{"list()", "[]", "{}", "()"},
				{"try", "catch", "except", "finally"},
				{"Passes control to the next line", "Raises an exception", "Ends the program", "Does nothing"},
				{"include", "import", "require", "use"},
				{"list[0]", "list.0", "list@0", "list<0>"},
				{"try", "with", "global", "return"},
				{"Hello World", "HelloWorld", "Hello + World", "None of the above"},
				{"def", "func", "lambda", "function"},
				{"()", "[]", "{}", "<>"},
				{"for", "while", "both a and b", "do-while"},
				{"def", "lambda", "anonymous", "func"},
				{"Deletes a variable", "Creates a variable", "Replaces a variable", "None of the above"},
		};

		char[] correct2 = new char[]{'A','B','B','D','B','D','A','D','B','A','D','B','A','C','A','A','A','C','B','A'};

		for(int i=0;i<category2.length;i++){
			String questionText =category2[i];
			String[] possibleAnswer=answer2[i];
			List<Answer> answers=new ArrayList<>();
			Question question=new Question(questionText,quizCategories.get(1),answers);
			for(int j=0;j< possibleAnswer.length;j++){
				Answer answer = new Answer(possibleAnswer[j], (j + 65) == correct2[i],(char)(65+j),question);
				answers.add(answer);
			}
			question.setAnswers(answers);
			questionRepository.save(question);
		}

		String[] category3 = new String[]{
				"What are the main functions of an operating system?",
				"Explain the concept of process and thread in an operating system.",
				"What is the purpose of process scheduling?",
				"How does virtual memory work?",
				"What is the difference between preemptive and non-preemptive scheduling?",
				"Explain the concept of deadlock in operating systems.",
				"How do you prevent deadlock in an operating system?",
				"What is the purpose of an interrupt in an operating system?",
				"Explain the concept of context switching.",
				"What is the difference between multitasking and multiprocessing?",
				"What are the different types of operating systems?",
				"Explain the concept of a file system.",
				"How does memory management work in an operating system?",
				"What is the purpose of a semaphore in an operating system?",
				"Explain the concept of paging and segmentation.",
				"What is the role of a device driver in an operating system?",
				"How does a shell work in an operating system?",
				"What is the difference between monolithic and microkernel operating systems?",
				"Explain the concept of process synchronization.",
				"What is the purpose of the bootloader in an operating system?"
		};

		String[][] answer3 = new String[][]{
				{"Managing hardware resources", "Providing a user interface", "Executing and providing services for application software", "All of the above"},
				{"A process is a program in execution; a thread is the smallest unit of a process", "A thread is a program in execution; a process is the smallest unit of a thread", "Both are the same", "None of the above"},
				{"To manage the execution of processes", "To allocate memory", "To manage files", "None of the above"},
				{"Uses disk space as an extension of RAM", "Allocates more RAM to processes", "Increases CPU speed", "None of the above"},
				{"Preemptive allows interruption; non-preemptive does not", "Non-preemptive allows interruption; preemptive does not", "Both are the same", "None of the above"},
				{"A situation where processes are stuck in a loop waiting for resources", "A situation where memory is full", "A situation where CPU is fully utilized", "None of the above"},
				{"By avoiding circular wait", "By allocating more memory", "By increasing CPU speed", "None of the above"},
				{"To handle events that need immediate attention", "To allocate memory", "To manage files", "None of the above"},
				{"Switching from one process to another", "Changing the operating system", "Upgrading the CPU", "None of the above"},
				{"Multitasking allows running multiple applications; multiprocessing uses multiple CPUs", "Multiprocessing allows running multiple applications; multitasking uses multiple CPUs", "Both are the same", "None of the above"},
				{"Batch, Time-sharing, Distributed", "Single-user, Multi-user", "Embedded, Real-time", "All of the above"},
				{"Organizes and manages files on a storage device", "Increases CPU speed", "Allocates memory to processes", "None of the above"},
				{"Allocates and deallocates memory to processes", "Increases CPU speed", "Manages files", "None of the above"},
				{"To manage access to shared resources", "To increase memory", "To speed up the CPU", "None of the above"},
				{"Paging divides memory into fixed-size pages; segmentation divides memory into variable-sized segments", "Segmentation divides memory into fixed-size pages; paging divides memory into variable-sized segments", "Both are the same", "None of the above"},
				{"To control hardware devices", "To manage memory", "To increase CPU speed", "None of the above"},
				{"Provides an interface for users to interact with the OS", "Manages hardware devices", "Allocates memory", "None of the above"},
				{"Monolithic has one large kernel; microkernel has a smaller kernel with user services", "Microkernel has one large kernel; monolithic has a smaller kernel with user services", "Both are the same", "None of the above"},
				{"To coordinate access to shared resources", "To increase memory", "To speed up the CPU", "None of the above"},
				{"To initialize the operating system", "To manage files", "To increase memory", "None of the above"}
		};

		char[] correct3 = new char[]{'D','A','A','A','A','A','A','A','A','A','D','A','A','A','A','A','A','A','A','A'};

		for(int i=0;i<category3.length;i++){
			String questionText =category3[i];
			String[] possibleAnswer=answer3[i];
			List<Answer> answers=new ArrayList<>();
			Question question=new Question(questionText,quizCategories.get(2),answers);
			for(int j=0;j< possibleAnswer.length;j++){
				Answer answer = new Answer(possibleAnswer[j], (j + 65) == correct3[i],(char)(65+j),question);
				answers.add(answer);
			}
			question.setAnswers(answers);
			questionRepository.save(question);
		}

	}
}
