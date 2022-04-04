package edu.psu.ist311;



import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Competition {

    public String competitionName;
    public String distance; //5k, 10k, or 15k
    public int numOfRunners;
    public Year year;


    // here are the runners for this competition
    private List<Runner> runners = new ArrayList<>();

    public Competition(String raceInfoStr, boolean infoIncludesFinishers){
        //0                                 1   2     3      4        5         6
        //Rambler Classic at Frontier Park 5k (2006) 65 (m.d.b:26) (d.w:30) (n.w.t:34)
        // tokenizer means taken this and chunks it up into pieces
        StringTokenizer t = new StringTokenizer(raceInfoStr);

        //tile parsing
        int numOfWordsInTitle = 0;

        if(infoIncludesFinishers){
            numOfWordsInTitle = t.countTokens() - 6;
        }else{
            numOfWordsInTitle = t.countTokens() - 2;
        }
        String tempTitle = "";

        for(int i = 0; i < numOfWordsInTitle; i++){
            tempTitle = tempTitle + t.nextToken() + " ";
        }

        this.competitionName = tempTitle.trim();

        //distance parsing
        String tempDistance = t.nextToken();
        if(tempDistance.equals("5k") || tempDistance.equals("10k") || tempDistance.equals("15k")){
            this.distance = tempDistance;
        }else{
            throw new IllegalArgumentException("Invalid Distance. Should be 5k, 10, or 15k");
        }

        //year parsing
        String strYear = t.nextToken();
        strYear = strYear .substring(1, 5);
        this.year = Year.parse(strYear);


        if(infoIncludesFinishers) {
            //parse number of runners
            String StrNumofRunners = t.nextToken();
            this.numOfRunners = Integer.parseInt(StrNumofRunners);


            //Runner r = new Runner(initial, finishTime);
            while (t.hasMoreTokens()) {
                String RunnerString = t.nextToken();
                //need to use substring to seperate initials from time
                int colonIndex = RunnerString.indexOf(":");
                int finishTime = Integer.parseInt(RunnerString.substring(colonIndex + 1, RunnerString.length() - 1));
                Runner r = new Runner(finishTime, RunnerString.substring(0, colonIndex));
                runners.add(r);
            }
            Collections.sort(runners);


        }


    }


    public String renderInPresentationFormat(){
        String returnWholeString =  competitionName + " " + distance + " " +  "(" + year + ")" + numOfRunners;
        for(Runner r : runners) {
            returnWholeString = returnWholeString + r;
        }
        return returnWholeString;
    }



    public boolean equals(Object o){
        boolean result = o instanceof Competition;
        if(result){
            result = competitionName.equals(((Competition) o).competitionName) && year.equals(((Competition) o).year)
                    && distance.equals(((Competition) o).distance);
        }
        return result;
    }



    @Override
    public String toString() {
        String result = competitionName + " " + distance + " (" + year + ")";

        if(!runners.isEmpty()){
            result = result + "runners: " + numOfRunners + "top time: " + runners.get(0).getTimeInMinutes() + "mins";
       }
        return result;
    }

    public Year getYear() {
        return this.year;
    }
}
