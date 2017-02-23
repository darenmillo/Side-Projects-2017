import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by daren.millo on 2/22/2017.
 */
public class Efficiency {



   public static int remnantCount = 0;
   public static int vortexCount = 0;
   public static int ballLightningCount = 0;
   public static int overloadProcCount = 0;

    public static void main(String[] args){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Please locate the combat log.");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, ("You cancelled the file chooser."));
            System.exit(0);
        }

        try{
            File inputFile = fileChooser.getSelectedFile();
            Scanner in = new Scanner(inputFile);





            while (in.hasNext()){
                String currentWord = in.next();
                if(currentWord.equals("type:")){
                   int type = getType(in, currentWord);
                   if (type == 5 || type == 3){
                       if (isStorm(in, currentWord)){
                           getAbility(in, currentWord);

                       }
                   }

                }
            }

            double efficiencyScore = ((double) overloadProcCount/((double)(remnantCount +  vortexCount +  ballLightningCount))) * 100;
            int totalCount = remnantCount +  vortexCount +  ballLightningCount;

            System.out.println("Remnant count: " + remnantCount);
            System.out.println("Vortex count: " + vortexCount);
            System.out.println("Ball Lightning count: " + ballLightningCount);
            System.out.println("Overload procs: " + overloadProcCount);
            System.out.println("You were " + (int) efficiencyScore + "% efficient with your abilities this game.");


            JOptionPane.showMessageDialog(null, "Remnant count: " + remnantCount + "\n" +
                    "Vortex Count: " + vortexCount + "\n" + "Ball Lightning Count: " + ballLightningCount + "\n" +
                    "The total amount of abilities used is: " + totalCount + "\n\n" + "However, you proced overload " +
                    overloadProcCount + " unique times." + "\n\n" +"This means you were " + (int) efficiencyScore +
                    "% efficient with your abilities this game." );



        }

        catch (IOException e) { // If file is corrupt, not valid etc. Lets user
            // know it cannot be read.
            JOptionPane.showMessageDialog(null, ("The file cannot be read."));
            System.exit(0);
        }

    }
    public static int getType (Scanner in, String s){
        int type = in.nextInt();
        return type;
    }
    public static Boolean isStorm (Scanner in, String s){
        while (in.hasNext()) {
            s = in.next();
            if (s.equals("attacker_name:")){
                break;
            }
        }
        s = in.next();
        if (s.equals("npc_dota_hero_storm_spirit")){
              return true;
          }
          else{
            return false;
        }



    }
    public static void getAbility (Scanner in, String s){
      while (in.hasNext()){
          s = in.next();
          if (s.equals("inflictor:")){
              break;
          }
      }
     s = in.next();
      if (s.equals ("storm_spirit_electric_vortex")){
        vortexCount ++;
      }
      if (s.equals ("storm_spirit_static_remnant")){
            remnantCount ++;
      }
      if (s.equals ("storm_spirit_ball_lightning")){
            ballLightningCount ++;
      }
      if(s.equals("modifier_storm_spirit_overload")){
          overloadProcCount ++;
      }
    }
}

