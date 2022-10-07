package session

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Session {
    // create a user
    private var user: User? = null
    private var name: String? = null

    /**
     * If the user is not null, print a message, else start a new session
     *
     * @param name String - the name of the user
     */
    fun login(name: String) {
        user?.let {
            println("session in progress, kindly logout to start a new process")
        } ?: kotlin.run {
            // start a new session
            val startSessionTime = LocalDateTime.now()
            val formattedStartSessionTime = DateTimeFormatter.ofPattern("DD MMM, YYYY h:mm a").format(startSessionTime)

            // Creating a new User and assigning it to the user variable
            user = User(name, formattedStartSessionTime)
            println("Session started")
        }
    }

    /**
     * If user is not null,
     * assign user back to null and print "User logged out".
     * If user is null, print "No session found"
     */
    fun logout() {
        user?.let {
            // assign user back to null
            user = null
            println("User logged out")
        } ?: kotlin.run {
            println("No session found")
        }
    }


    /**
     * It checks if the user is logged in or not.
     * If a user is logged in it prints the user details and returns true otherwise if there is no user it
     * returns false
     * @return A boolean value
     */
    fun checkSession(): Boolean {
        user?.let {
            println("-----------------------------")
            println("Current session:\n Name:${it.name}\n Time:${it.time}")
            println("-----------------------------")
            return true
        } ?: kotlin.run {
            return false
        }

    }

    /**
     *The Builder class is a class that builds a Session object."
     */
    class Builder {
        //Creating a new instance of the Session class
        var session = Session()

        /**
         * It sets the user's name.
         * @param name The name of the user.
         * @return The Builder object
         */
        fun setUser(name: String): Builder {
            session.login(name)
            return this;
        }

        /**
         * It returns a session
         */
        fun build(): Session = session
    }
}