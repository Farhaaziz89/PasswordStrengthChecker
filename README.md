# PasswordStrengthChecker
Password Strength Checker App .

#Password Strength Checker

The Enhanced Password Strength Checker is a simple yet effective tool built using Java Swing. It provides a user-friendly interface for users to input a password and instantly receive feedback on the strength of their password. Additionally, it offers the functionality to copy the password to the clipboard for easy use.

## Features

- **Password Strength Evaluation**: Dynamically evaluates the strength of the entered password based on various criteria including length, the inclusion of numbers, uppercase and lowercase letters, and special characters.
- **Password Length Display**: Shows the current length of the entered password.
- **Copy to Clipboard**: Allows the user to copy the entered password to the system clipboard with a single click.

 ## ScreenShots


 ![Screenshot (830)](https://github.com/Farhaaziz89/PasswordStrengthChecker/assets/154342582/ed04fa89-49e7-4bc9-8884-ad54470f76e2)


## How It Works

The application evaluates the strength of the entered password by assigning points based on the following criteria:

- Length of the password
- Inclusion of numeric characters
- Inclusion of lowercase letters
- Inclusion of uppercase letters
- Inclusion of special characters

The strength of the password is categorized into four levels:

- **Weak**: The password does not meet the basic criteria for strength.
- **Moderate**: The password meets the minimum requirements for strength.
- **Strong**: The password is robust and meets a higher standard for security.
- **Very Strong**: The password is highly secure and meets all the criteria for strength.

## Requirements

- Java Development Kit (JDK) 21 or above.

## Running the Application

To run the Enhanced Password Strength Checker, follow these steps:

1. Ensure Java is installed and properly configured on your system.
2. Compile the application using a Java compiler. For example, you can use the following command:

    ```shell
    javac PasswordStrengthChecker.java
    ```

3. Run the compiled application using the Java interpreter:

    ```shell
    java PasswordStrengthChecker
    ```

The application window should open, and you can start testing the strength of your passwords immediately.

## Contributions

Contributions are welcome! If you have suggestions for improving this application, feel free to create a pull request or open an issue on the project's repository.
