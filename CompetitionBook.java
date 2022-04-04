package edu.psu.ist311;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompetitionBook {

    private String fileLogName;
    private List<Competition> competitions;

    public CompetitionBook(String fileName){
        this.fileLogName = fileName;
        this.competitions = new ArrayList<>();


        try(Scanner scan = new Scanner(new File(fileLogName))) {

            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Competition comp = new Competition(line, true);
                competitions.add(comp);

            }
        }catch (FileNotFoundException e){
            System.out.print("Unable to find the file specified" + fileName + ". ");
        }catch(Exception e){
            System.out.println("Something went wrong!");
        }

    }

    public String getFileLogName(){
        return fileLogName;
    }



    public void add(Competition c){
        if(competitions.contains(c)){
            System.out.println("Competition is duplicated.");
        }else {
            competitions.add(c);
        }
    }

    public List<Competition> getCompetitions(){
        return competitions;
    }

    public Year year(){
        return year();
    }


    public void remove(Competition c){
        if(competitions.contains(c)){
            System.out.println(("Competition is not found."));
        }else {
            competitions.remove(c);
        }
    }



    public boolean isInBook(Competition c){
        if(competitions.contains(c)){
            return false;
        }else {
            return false;
        }
    }


    public boolean competitions(List<Competition> competition){

        return false;
    }


    private int lookUpIndex(Competition c){

        return 0;
    }




}
