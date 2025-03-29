package com.example.startquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuestionActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var questionAdapter: QuestionAdapter
    private var questionsList: List<QuestionModel> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        recyclerView = findViewById(R.id.recyclerQuestions)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get category from Intent
        val categoryName = intent.getStringExtra("CATEGORY_NAME")?.uppercase()

        // Debugging Log
        println("Received CATEGORY_NAME: $categoryName")

        // Load questions based on category
        questionsList = getQuestionsForCategory(categoryName)

        // Set up adapter
        questionAdapter = QuestionAdapter(questionsList)
        recyclerView.adapter = questionAdapter
    }

    private fun getQuestionsForCategory(categoryName: String?): List<QuestionModel> {
        return when (categoryName) {
            "FUNDAMENTALS" -> listOf(
                QuestionModel("1. What is the correct way to declare a variable in Kotlin?", listOf("var name: String", "variable name: String", "name: var String", "string name"), "var name: String"),
                QuestionModel("2. What is the primary advantage of using val instead of var in Kotlin?", listOf("val variables are immutable", "val variables consume less memory", "val variables are faster", "There is no difference"), "val variables are immutable"),
                QuestionModel("3. How do you declare a function in Kotlin?", listOf("def functionName() {}", "fun functionName() {}", "function functionName() {}", "void functionName() {}"), "fun functionName() {}"),
                QuestionModel("4. What is the use of the Elvis operator (?:) in Kotlin?", listOf("It is used for nullable types", "It handles exceptions", "It is used to create loops", "It converts types"), "It is used for nullable types"),
                QuestionModel("5. What is the output of 5 == 5 in Kotlin?", listOf("true", "false", "null", "error"), "true"),
                QuestionModel("6. How do you check for null safety in Kotlin?", listOf("Using '?' operator", "Using '!!' operator", "Using try-catch", "Using 'nullCheck()' function"), "Using '?' operator"),
                QuestionModel("7. What is the correct syntax for a when expression in Kotlin?", listOf("when x { 1 -> 'One' }", "switch(x) { case 1: 'One' }", "if (x == 1) then 'One'", "select x { case 1: 'One' }"), "when x { 1 -> 'One' }"),
                QuestionModel("8. Which of the following is NOT a Kotlin feature?", listOf("Null safety", "Extension functions", "Checked exceptions", "Coroutines"), "Checked exceptions"),
                QuestionModel("9. What is the default visibility modifier in Kotlin?", listOf("private", "protected", "public", "internal"), "public"),
                QuestionModel("10. What is the purpose of the companion object in Kotlin?", listOf("To create static members", "To store instance variables", "To manage memory", "To define a singleton class"), "To create static members"),
                QuestionModel("11. What is Kotlin's primary programming paradigm?", listOf("Functional", "Object-oriented", "Procedural", "Declarative"), "Object-oriented"),
                QuestionModel("12. Which keyword is used to inherit a class in Kotlin?", listOf("extend", "inherit", "open", "override"), "open"),
                QuestionModel("13. How do you declare a nullable variable in Kotlin?", listOf("var name: String?", "var name: ?String", "var name = null", "var? name: String"), "var name: String?"),
                QuestionModel("14. What function is used to iterate over a list in Kotlin?", listOf("forEach", "map", "reduce", "filter"), "forEach"),
                QuestionModel("15. How do you define a sealed class in Kotlin?", listOf("sealed class Example", "class Example sealed", "sealed Example class", "Example sealed class"), "sealed class Example"),
                QuestionModel("16. What is the default return type of a function in Kotlin?", listOf("Unit", "Void", "null", "Any"), "Unit"),
                QuestionModel("17. What is a higher-order function in Kotlin?", listOf("A function that takes another function as a parameter", "A function that runs first", "A function that returns an integer", "A function that has a higher priority"), "A function that takes another function as a parameter"),
                QuestionModel("18. Which operator is used for type checking in Kotlin?", listOf("is", "as", "in", "of"), "is"),
                QuestionModel("19. What is the purpose of the let function in Kotlin?", listOf("To execute a block of code on a nullable object", "To transform a list", "To filter data", "To create an instance of a class"), "To execute a block of code on a nullable object"),
                QuestionModel("20. How do you create an immutable list in Kotlin?", listOf("val list = listOf(1, 2, 3)", "val list = mutableListOf(1, 2, 3)", "val list = arrayListOf(1, 2, 3)", "val list = linkedListOf(1, 2, 3)"), "val list = listOf(1, 2, 3)"),
                QuestionModel("21. What keyword is used to declare an interface in Kotlin?", listOf("interface", "implements", "extends", "protocol"), "interface"),
                QuestionModel("22. How do you handle exceptions in Kotlin?", listOf("try-catch", "throws", "handle", "except"), "try-catch"),
                QuestionModel("23. What is a lambda function in Kotlin?", listOf("An anonymous function", "A loop function", "A return function", "A parameter function"), "An anonymous function"),
                QuestionModel("24. Which function is used to launch a coroutine?", listOf("launch", "startCoroutine", "runBlocking", "beginCoroutine"), "launch"),
                QuestionModel("25. What does the suspend keyword do?", listOf("Marks a function as asynchronous", "Stops a function", "Runs a function in the main thread", "Creates a new thread"), "Marks a function as asynchronous"),
                QuestionModel("26. How do you define a singleton in Kotlin?", listOf("object Singleton", "class Singleton private constructor()", "static Singleton instance", "sealed class Singleton"), "object Singleton"),
                QuestionModel("27. What is the purpose of the apply function?", listOf("To configure an object", "To iterate over a collection", "To map values", "To filter a list"), "To configure an object")
            )
            "SQL" -> listOf(
                QuestionModel("1. What is a primary key?", listOf("Unique Key", "Foreign Key", "Candidate Key", "Composite Key"), "Unique Key"),
                QuestionModel("2. Difference between INNER JOIN and LEFT JOIN?", listOf("INNER JOIN returns only matching rows", "LEFT JOIN returns all left table rows and matching right table rows", "INNER JOIN returns all rows", "LEFT JOIN returns only left table rows"), "LEFT JOIN returns all left table rows and matching right table rows"),
                QuestionModel("3. What is normalization?", listOf("Process of organizing data", "A type of database", "A SQL command", "A table constraint"), "Process of organizing data"),
                QuestionModel("4. What are ACID properties?", listOf("Atomicity, Consistency, Isolation, Durability", "Authorization, Consistency, Integrity, Distribution", "Allocation, Computation, Integrity, Data", "Atomicity, Connection, Isolation, Data"), "Atomicity, Consistency, Isolation, Durability"),
                QuestionModel("5. What is an index in SQL?", listOf("A performance tuning method", "A column constraint", "A type of table", "A primary key"), "A performance tuning method"),
                QuestionModel("6. What is a foreign key?", listOf("A reference to another table's primary key", "A unique constraint", "A primary key", "A type of join"), "A reference to another table's primary key"),
                QuestionModel("7. What does the GROUP BY clause do?", listOf("Aggregates data", "Filters data", "Sorts data", "Joins tables"), "Aggregates data"),
                QuestionModel("8. What is a stored procedure?", listOf("A precompiled SQL command", "A temporary table", "A database view", "A column index"), "A precompiled SQL command"),
                QuestionModel("9. How do you retrieve distinct records in SQL?", listOf("Using DISTINCT keyword", "Using UNIQUE keyword", "Using FILTER keyword", "Using SEPARATE keyword"), "Using DISTINCT keyword"),
                QuestionModel("10. What is the purpose of the HAVING clause?", listOf("Filters aggregated data", "Filters individual rows", "Joins tables", "Sorts data"), "Filters aggregated data"),
                QuestionModel("11. What is the difference between UNION and UNION ALL?", listOf("UNION removes duplicates", "UNION ALL removes duplicates", "They are identical", "UNION is faster"), "UNION removes duplicates"),
                QuestionModel("12. What is a trigger in SQL?", listOf("A special stored procedure", "A unique constraint", "A type of join", "An indexing technique"), "A special stored procedure"),
                QuestionModel("13. How do you optimize SQL queries?", listOf("Using indexes", "Using loops", "Using more tables", "Using complex joins"), "Using indexes"),
                QuestionModel("14. What is a cursor in SQL?", listOf("A database object to retrieve rows", "A join operation", "A database constraint", "A sorting method"), "A database object to retrieve rows"),
                QuestionModel("15. What are SQL transactions?", listOf("A sequence of operations", "A database view", "A type of table", "A performance metric"), "A sequence of operations"),
                QuestionModel("16. What is a composite key?", listOf("A key with multiple columns", "A foreign key", "A unique constraint", "A type of index"), "A key with multiple columns"),
                QuestionModel("17. What is a self-join?", listOf("Joining a table with itself", "Joining two different tables", "A type of cross join", "A database trigger"), "Joining a table with itself"),
                QuestionModel("18. What is denormalization?", listOf("Adding redundancy for performance", "Reducing data size", "Using more indexes", "Avoiding SQL joins"), "Adding redundancy for performance"),
                QuestionModel("19. What are different types of SQL joins?", listOf("INNER, LEFT, RIGHT, FULL", "UNION, CROSS, INNER", "INDEX, VIEW, INNER", "FOREIGN, PRIMARY, CHECK"), "INNER, LEFT, RIGHT, FULL"),
                QuestionModel("20. How do you delete duplicate rows in SQL?", listOf("Using DELETE with ROW_NUMBER()", "Using SELECT DISTINCT", "Using GROUP BY", "Using HAVING"), "Using DELETE with ROW_NUMBER()"),
                QuestionModel("21. What is the difference between DELETE and TRUNCATE?", listOf("DELETE removes specific rows, TRUNCATE removes all rows", "DELETE is faster than TRUNCATE", "TRUNCATE can be rolled back", "DELETE cannot have a WHERE clause"), "DELETE removes specific rows, TRUNCATE removes all rows"),
                QuestionModel("22. What is a clustered index in SQL?", listOf("An index that determines the physical order of data", "An index that is created on multiple tables", "An index that speeds up queries", "An index that is used for foreign keys"), "An index that determines the physical order of data"),
                QuestionModel("23. What is a non-clustered index in SQL?", listOf("An index that does not determine the physical order of data", "An index used only for foreign keys", "An index that forces data sorting", "An index that is created by default"), "An index that does not determine the physical order of data"),
                QuestionModel("24. What is a common table expression (CTE) in SQL?", listOf("A temporary result set", "A permanent table", "A stored procedure", "A trigger"), "A temporary result set"),
                QuestionModel("25. What is the purpose of the COALESCE function in SQL?", listOf("Returns the first non-null value", "Joins two tables", "Sorts data in ascending order", "Creates a primary key"), "Returns the first non-null value"),
                QuestionModel("26. What is the difference between CHAR and VARCHAR in SQL?", listOf("CHAR has a fixed length, VARCHAR has a variable length", "CHAR is always faster", "VARCHAR stores numbers only", "CHAR cannot store special characters"), "CHAR has a fixed length, VARCHAR has a variable length"),
                QuestionModel("27. What is a view in SQL?", listOf("A virtual table based on a query", "A type of index", "A stored procedure", "A temporary table"), "A virtual table based on a query"),
                QuestionModel("28. What is the difference between HAVING and WHERE?", listOf("HAVING filters aggregated results, WHERE filters individual rows", "HAVING is used only with SELECT", "WHERE is faster than HAVING", "HAVING can only be used with ORDER BY"), "HAVING filters aggregated results, WHERE filters individual rows"),
                QuestionModel("29. What is the purpose of the CASE statement in SQL?", listOf("To perform conditional logic", "To join tables", "To optimize performance", "To create new tables"), "To perform conditional logic"),
                QuestionModel("30. How do you find the second highest salary in SQL?", listOf("Using LIMIT and OFFSET", "Using GROUP BY", "Using DISTINCT", "Using COUNT"), "Using LIMIT and OFFSET")

            )
            "HR QUESTIONS" -> listOf(
                QuestionModel("1. What is the best way to introduce yourself in an interview?", listOf("A. Talk about hobbies", "B. Highlight skills and experience", "C. Discuss personal life", "D. Stay silent"), "B. Highlight skills and experience"),
                QuestionModel("2. How should you handle a conflict with a team member?", listOf("A. Ignore it", "B. Argue until they agree", "C. Discuss the issue professionally", "D. Report immediately to HR"), "C. Discuss the issue professionally"),
                QuestionModel("3. What is the best response to 'What are your weaknesses?'", listOf("A. I have no weaknesses", "B. I struggle with time management but improving", "C. I am a perfectionist", "D. I don't like teamwork"), "B. I struggle with time management but improving"),
                QuestionModel("4. How can you demonstrate good communication skills?", listOf("A. Listen actively", "B. Speak loudly", "C. Use complex words", "D. Avoid eye contact"), "A. Listen actively"),
                QuestionModel("5. What should you do if you don’t know the answer to an interview question?", listOf("A. Guess randomly", "B. Admit and show willingness to learn", "C. Ignore the question", "D. Change the topic"), "B. Admit and show willingness to learn"),
                QuestionModel("6. What is a good way to discuss salary expectations?", listOf("A. Demand a high salary", "B. Be open to negotiation", "C. Avoid the topic", "D. Say you will work for free"), "B. Be open to negotiation"),
                QuestionModel("7. How do you show interest in the company during an interview?", listOf("A. Ask relevant questions", "B. Stay quiet", "C. Criticize the company", "D. Change the topic"), "A. Ask relevant questions"),
                QuestionModel("8. What is the best way to end an interview?", listOf("A. Leave immediately", "B. Say thank you and shake hands", "C. Ask for feedback", "D. Both B and C"), "D. Both B and C"),
                QuestionModel("9. How do you handle feedback from a manager?", listOf("A. Ignore it", "B. Argue against it", "C. Accept and improve", "D. Quit the job"), "C. Accept and improve"),
                QuestionModel("10. Why is teamwork important for an Android developer?", listOf("A. To collaborate effectively", "B. To avoid coding", "C. To reduce accountability", "D. To work independently"), "A. To collaborate effectively"),
                QuestionModel("11. How should you answer 'Why do you want this job?'", listOf("A. I need money", "B. I like the company and role", "C. I have no other offers", "D. I was forced to apply"), "B. I like the company and role"),
                QuestionModel("12. What is the best way to handle work pressure?", listOf("A. Complain", "B. Stay organized and prioritize tasks", "C. Procrastinate", "D. Leave the job"), "B. Stay organized and prioritize tasks"),
                QuestionModel("13. How do you show leadership in a project?", listOf("A. Encourage teamwork", "B. Control everything", "C. Ignore team members", "D. Avoid responsibilities"), "A. Encourage teamwork"),
                QuestionModel("14. What is the most important soft skill for a developer?", listOf("A. Communication", "B. Coding speed", "C. Personal interests", "D. Silence"), "A. Communication"),
                QuestionModel("15. How should you respond to 'Tell me about a time you failed'?", listOf("A. Blame others", "B. Explain the lesson learned", "C. Say you never failed", "D. Change the topic"), "B. Explain the lesson learned"),
                QuestionModel("16. What is a good approach to learning new technologies?", listOf("A. Ignore them", "B. Explore and practice", "C. Stick to old knowledge", "D. Wait for instructions"), "B. Explore and practice"),
                QuestionModel("17. How do you ensure good work-life balance?", listOf("A. Work all day", "B. Set boundaries", "C. Ignore deadlines", "D. Avoid communication"), "B. Set boundaries"),
                QuestionModel("18. Why is problem-solving important in Android development?", listOf("A. To build efficient apps", "B. To impress others", "C. To avoid mistakes", "D. To reduce coding time"), "A. To build efficient apps"),
                QuestionModel("19. What makes an Android developer stand out?", listOf("A. Strong problem-solving skills", "B. Fast coding", "C. Avoiding documentation", "D. Working alone"), "A. Strong problem-solving skills"),
                QuestionModel("20. How do you handle disagreements with a manager?", listOf("A. Discuss respectfully", "B. Quit the job", "C. Ignore the manager", "D. Start an argument"), "A. Discuss respectfully"),
                QuestionModel("21. Why is continuous learning important in Android development?", listOf("A. To stay updated with technology", "B. To show off", "C. To avoid work", "D. To impress interviewers"), "A. To stay updated with technology"),
                QuestionModel("22. What is the importance of time management in a project?", listOf("A. To meet deadlines", "B. To relax more", "C. To delay tasks", "D. To impress managers"), "A. To meet deadlines"),
                QuestionModel("23. How should you approach a difficult task?", listOf("A. Break it down", "B. Avoid it", "C. Delegate without thinking", "D. Guess the solution"), "A. Break it down"),
                QuestionModel("24. What should you do if you make a mistake at work?", listOf("A. Admit and fix it", "B. Hide it", "C. Blame others", "D. Ignore it"), "A. Admit and fix it"),
                QuestionModel("25. Why is adaptability important in software development?", listOf("A. To keep up with changes", "B. To resist change", "C. To avoid learning", "D. To impress interviewers"), "A. To keep up with changes")
            )
            else -> listOf(QuestionModel("No questions available for this category.", listOf("", "", "", ""), ""))
        }
    }

}
