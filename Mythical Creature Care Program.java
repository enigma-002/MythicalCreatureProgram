/*
 AUTHOR: Tama Guisado Okumura
 DATE: 28 November 2024
 VERSION 4
*/


import java.io.IOException;
//Creates a new record of Mythical Creatures and their fields 
class MythicalCreatures
{
    String name;
    
    int HP;
    String element;
}//END Mythical Creatures

class MythicalCreatureCareProgram
{
    public static void main(String [] a) throws IOException
    {
        ownerName();
        int number = numberOfCreatures();
        MythicalCreatures [] creatures = new MythicalCreatures [number];
        loadPreviousGame(creatures);
        createCreatureData(creatures,number);
        feed(creatures,number);
        save(number,creatures);
        
    }
    
    // Introduction into the creature care and outlines what the program does 
     public static String ownerName() 
    {
        Scanner scanner = new Scanner(System.in);
        String ownerName;

        System.out.println("Hello!\nWelcome to the Mythical Creature Care Program.\nHere we will take care of your mythical creatures.");
        System.out.println("Before we take care of your creature I need to grab a couple details from you.\nWhat is your name?");
        ownerName = scanner.nextLine();

        return ownerName;
    }//END introduction

    //Counts how many creatures have been already placed in creatures
    public static int countCreatures(MythicalCreatures [] creatures)
    {
        int count = 0;
        for(int i=0; i<creatures.length; i++)
            {
                if(creatures[i] !=null){count++;}
            }
        return count;
    }
    
    
    //Gets the amount of creatures which the user will input
    public static int numberOfCreatures()
    {
        Scanner scanner = new Scanner(System.in);
        int number;
        
        System.out.println("How many creatures will you be entering today?");
        number = Integer.parseInt(scanner.nextLine());

        return number;
    }//END numberOfCreatures

    //Creates a new instance of monster
    public static MythicalCreatures createCreature(String name, int HP, String element)
    {
        MythicalCreatures monster = new MythicalCreatures();
        monster.name = name;
        monster.HP = HP;
        monster.element = element;
        return monster;
    }// END createCreature

    //Asks the user for the information about their creature
    public static MythicalCreatures [] createCreatureData(MythicalCreatures[] creatures, int number)
    {
        Scanner scanner = new Scanner(System.in);

        int existingCreatures = countCreatures(creatures);
            
        for (int i=existingCreatures; i<number; i++)
        {

            System.out.println("What is the name of your creature?");
            String creatureName = scanner.nextLine();
            
    
            System.out.println("How much HP does " + creatureName + " have?");
            int creatureHP = Integer.parseInt(scanner.nextLine());
                   
    
            System.out.println("What element is  " + creatureName );
            String creatureElement =scanner.nextLine();
            
    
            MythicalCreatures monster = createCreature(creatureName,creatureHP,creatureElement);
            Statement(monster); 
            creatures[i]  = monster;
            
        
        }
        return creatures;
    }    //END createCreatureData

    //Prints a statement based off of the element of the creature
    public static void Statement(MythicalCreatures monster)
    {
       String creatureName = getName(monster);
       String creatureElement = getElement(monster);
       if (creatureElement.equals ("Fire")|| creatureElement.equals("fire")) {System.out.println(creatureName + " has fire resistance");}
       else if (creatureElement.equals("Ice")|| creatureElement.equals("ice")){System.out.println(creatureName + " has ice resistance");}
       else if (creatureElement.equals("Psychic")|| creatureElement.equals("psychic")){System.out.println(creatureName + " has telekinesis");}
       else if (creatureElement.equals("Water")|| creatureElement.equals("water")){System.out.println(creatureName + " has water resistance");}
       else if (creatureElement.equals("Electric")|| creatureElement.equals("electric")){System.out.println(creatureName + " has electricity resistance");}
       else if (creatureElement.equals("Ghost")|| creatureElement.equals("ghost")){System.out.println(creatureName + " has a 30% chance of dodging an attack");}
       else {System.out.println("Your creature has no extra resistance");}

        return;
    }//END Statement 

    //Asks user if they want to feed their creature and updates their health accordingly
    public static void feed (MythicalCreatures [] creatures ,int number)
    {
        Scanner scanner = new Scanner(System.in);
        int foodChoice;
        int currentHP = 0;
        int newHP = 0;
        final int TREATS = 50;
        final int WATER = 100;
        final int FOOD = 150;
        
        System.out.println("Would you like to feed your creatures. (y/n)?");
        String answer = scanner.nextLine();
        
        while (!(answer.equals("n")|| answer.equals("no")||answer.equals("NO")||answer.equals("N")))
        {
            for (int i=0; i<number; i++)
            {
                MythicalCreatures monster = creatures[i]; 
                System.out.println("What would you like to feed " +getName(monster)+ "?" );
                System.out.println("1.) Treats \n2.) Food \n3.) Water \n Please enter the number");
                foodChoice = Integer.parseInt(scanner.nextLine());

                while (!((foodChoice == 1)|| (foodChoice == 2)||(foodChoice == 3)))
                {
                    System.out.println("Please enter a choice between 1 and 3");
                    foodChoice = Integer.parseInt(scanner.nextLine());
                }
                    
                if (foodChoice == 1)
                {System.out.println("You fed " + getName(monster) +  " treats!" );
                 newHP = getHP(monster) + TREATS;
                 setHP(monster,newHP);
                 System.out.println(getName(monster) + "'s HP increased by 50!");
                }

                else if (foodChoice == 2)
                {System.out.println("You fed " +getName(monster)+" food !" );

                 newHP = getHP(monster)+ FOOD;
                 setHP(monster,newHP);
                 System.out.println(getName(monster) + "'s HP increased by 150!");
                }   
                
                else if (foodChoice == 3)
                {System.out.println("You fed " +getName(monster)+  " water !" );
                 newHP = getHP(monster) + WATER;
                 setHP(monster,newHP);
                 System.out.println(getName(monster) + "'s HP increased by 100!");
                }   

                else{}
            }
            System.out.println("Would you like to feed your creatures again?");
            answer = scanner.nextLine();
        }
        System.out.println("Your creatures seem really happy!");
        return;
    }// END feed

    //It saves all the current creatures made into an external file
    public static void save(int number,MythicalCreatures [] creatures) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Would you like to save your current game?");
        String save = scanner.nextLine();

        if ((save.equalsIgnoreCase("yes"))||(save.equalsIgnoreCase("y")))
            {
               PrintWriter writer = new PrintWriter(new FileWriter("MythicalCreatureFile.txt"));

                for (int i=0; i<number; i++)
                {
                    MythicalCreatures monster = creatures[i]; 
                    String monsterName = getName(monster);
                    int monsterHP = getHP(monster);
                    String monsterElement= getElement(monster);

                    writer.println(monsterName + "\n" + monsterHP + "\n" + monsterElement);
                    
                }
            writer.close();
                System.out.println("Game saved");
            }

        

        return ; 
    }//END save

    //It reads a file and initialise the creatures array with old creatures
    public static void loadPreviousGame(MythicalCreatures [] creatures) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to load in data from a previous game?");
        String answer = scanner.nextLine();
        
        if((answer.equalsIgnoreCase("yes"))||(answer.equalsIgnoreCase("y")))
            {
                System.out.println("How many creatures would you like to load?");
                int number = Integer.parseInt(scanner.nextLine());
                BufferedReader read = new BufferedReader(new FileReader("MythicalCreatureFile.txt"));
                
                
                for(int i=0; i<number; i++)
                    {
                        MythicalCreatures monster = new MythicalCreatures();
                        
                        String name = read.readLine();
                        int HP = Integer.parseInt(read.readLine());
                        String element = read.readLine();

                        
                        MythicalCreatures newmonster = createCreature(name,HP,element);
                        creatures[i] = newmonster;

                        
                        System.out.println(name+ "\t" + HP+ "\t" + element);
                        
                    }
                System.out.println("Previous creatures were loaded");
                read.close();

            }   
   
        return ;
        
    }//END loadPreviousGame

    

    /////////GETTERS
    public static String getName(MythicalCreatures monster)
    {
        return monster.name;
    }
    
    public static int getHP(MythicalCreatures monster)
    {
        return monster.HP;
    }
    
    public static String getElement(MythicalCreatures monster)
    {
        return monster.element;
    }
        
    //////////SETTERS
    
    public static void setName(MythicalCreatures monster, String name)
    {
        monster.name = name;
        return;
    }
    
    public static void setHP(MythicalCreatures monster, int HP)
    {
        monster.HP = HP;
        return;
    }
    
    
    public static void setElement(MythicalCreatures monster, String element)
    {
        monster.element = element;
        return;
    }
    
} 
MythicalCreatureCareProgram.main(null)