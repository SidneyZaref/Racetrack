package edu.psu.ist311;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.PrintStream;

public class RacetrackCli {

    private Scanner in;
    private PrintStream out;
    private CompetitionBook book;

    public RacetrackCli(Scanner i, PrintStream o, CompetitionBook book) {
        this.out = o;
        this.in = i;
        this.book = book;
    }

    public void doShowCmdLine() {
        System.out.println("Welcome to the running competition record system!");
        System.out.println("-----------------------------------------------------");

        while(true) {
            out.println("Enter A)add, R)remove, LY)List By Year, S)save, L)List All, or Q)Quit");
            String cmd = in.nextLine().trim().toUpperCase();
            try {

                if (cmd.equals("A")) {
                    doAdd();
                } else if (cmd.equals("R")) {
                    doRemove();
                } else if (cmd.equals("LY")) {
                    doListByYear();
                } else if (cmd.equals("S")) {
                    out.println("Do you want to save?");
                    String userSave = in.nextLine();
                    if(userSave.equals("Yes")) {
                        out.println("Saved!");
                        doSave();
                    }
                } else if (cmd.equals("L")) {
                    doListAll();
                } else if (cmd.equals("Q")) {
                    out.println("Do you want to Quit (Yes/No");
                    String userinput = in.nextLine();
                    if(userinput.equals("Yes")){
                        out.println("Quitting!");
                    }else if(userinput.equals("No")){
                        out.println("You chose to quit .Goodbye!");
                    }
                } else {
                    out.println("Invalid Command");
                }
            }catch(Exception e){
                out.println("An error occured... " + e.getMessage() + " ... try again.");
            }
        }
    }

    private void doAdd(){
        out.println("Enter: [comp. name] [5k|10k|15k] ([year]) [number-of-runners] [top 3 finishers (initials:minutes)]");
        String addString = in.nextLine();
        Competition addcomp = new Competition(addString, true); //true because info inclused top finishers
        book.add(addcomp);
    }

    private void doRemove(){
        out.println("Enter the competition name you would like to remove: " + "[comp name] [5k|10k|15k] ([year])");
        String removeString = in.nextLine();
        Competition removeCompetition = new Competition(removeString, false); //false because information does not include top finishers
        out.println(removeString);
        book.remove(removeCompetition);
    }

    private void doListAll(){
        out.println("Here are all the competitions: ");
        for(Competition c : book.getCompetitions()){
            out.println(c);
        }
    }


    private void doListByYear(){
        out.println("What year would you like to see? Enter here: ");
        Year userLY = Year.parse(in.nextLine());
        out.println(userLY);
        for(Competition c : book.getCompetitions()){
            if(c.getYear().equals(userLY))
                out.println(c);
        }

    }


    private void doSave() {
        out.println("Saving!");
        try(PrintWriter writer = new PrintWriter(book.getFileLogName())){
            for(Competition c : book.getCompetitions()){
                writer.println(c.renderInPresentationFormat());
            }
            out.println("Saved! Goodbye!");
        }catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Error " + e.getMessage());
        }
    }
}
