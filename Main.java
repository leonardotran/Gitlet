package gitlet;


import java.io.File;
import java.io.IOException;

public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        } else if (args[0].equals("init")) {
            if (new File(".gitlet").exists()) {
                System.out.println("A Gitlet version-control system "
                        + "already exists in the current directory.");
                return;
            } else {
                Repository.init();
            }
        } else if (!new File(".gitlet").exists()) {
            System.out.println("Not in an initialized Gitlet directory.");
            return;
        } else if (args[0].equals("add")) {
            Repository.add(args[1]);
        } else if (args[0].equals("commit")) {
            Repository.makeCommit(args[1], null);
        } else if (args[0].equals("log")) {
            Repository.log();
        } else if (args[0].equals("checkout")) {
            if (args[1].equals("--")) {
                Repository.checkoutFile(args[2]);
            } else if (args.length > 2 && args[2].equals("--")) {
                Repository.checkoutCommit(args[1], args[3]);
            } else if (args.length == 2) {
                Repository.checkoutBranch(args[1]);
            } else {
                System.out.println("Incorrect operands.");
                return;
            }
        } else if (args[0].equals("branch")) {
            Repository.branch(args[1]);
        } else if (args[0].equals("status")) {
            Repository.status();
        } else if (args[0].equals("rm")) {
            Repository.remove(args[1]);
        } else if (args[0].equals("global-log")) {
            Repository.globalLog();
        } else if (args[0].equals("find")) {
            Repository.find(args[1]);
        } else if (args[0].equals("rm-branch")) {
            Repository.removeBranch(args[1]);
        } else if (args[0].equals("reset")) {
            Repository.reset(args[1]);
        } else if (args[0].equals("merge")) {
            Repository.merge(args[1]);
        } else {
            System.out.println("No command with that name exists.");
            return;
        }
    }

}