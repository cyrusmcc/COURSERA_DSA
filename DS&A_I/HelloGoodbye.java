/******************************************************************************
 *  Compilation:  javac HelloWorld.java
 *  Execution:    java HelloWorld
 *
 *  Prints "Hello, World". By tradition, this is everyone's first program.
 *
 *  % java HelloWorld
 *  Hello, World
 *
 *  These 17 lines of text are comments. They are not part of the program;
 *  they serve to remind us about its properties. The first two lines tell
 *  us what to type to compile and test the program. The next line describes
 *  the purpose of the program. The next few lines give a sample execution
 *  of the program and the resulting output. We will always include such
 *  lines in our programs and encourage you to do the same.
 *
 ******************************************************************************/

public class HelloGoodbye {

    public static void main(String[] args) {

        try {
            if((args[0] != null) && (args[1]) != null) {
                System.out.println("Hello " + args[0] + " and " + args[1] + ".");
                System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Provide two names as CLA.");
        }
    }


}