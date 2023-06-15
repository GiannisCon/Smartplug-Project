package MyCoursework;
import java.util.Scanner;
public class ConsoleHelper {
    public int GetInt(String prompt) {
        Scanner input = new Scanner(System.in);
        PrintString(prompt);
        return input.nextInt();
    }

    public String GetLine(String Prompt){
        Scanner input = new Scanner(System.in);
        PrintString(Prompt);
        return input.nextLine();

    }

    public String GetString(String prompt) {
        Scanner input = new Scanner(System.in);
        PrintString(prompt);
        return input.next();

    }

    public void PrintString(String Prompt) {
        System.out.println(Prompt);
    }

    public void PopulateRooms(SmartHome SH, int RoomSize){//This function gets the user input and sends the data to the AddRooms function
        int RoomID=1;                                     //within the SmartHome class
        for(int i = 0; i<RoomSize; i++) {
            SH.AddRooms(GetLine("Please provide a name for room number"+" "+(i+1)+": "),RoomID);
            RoomID++;
        }
    }

    public void RequestPlugInfo(){
        PrintString("ENTER PLUG INFORMATION BELOW \n"+"ROOMS AVAILABLE: ");
    }

    public void MenuOptions(SmartHome SH){                                                //this functions controls everything,it asks the user for an option from the menu options
        PrintString("                         --------MENU OPTIONS--------");     //and based on the input it receives calls the appropriate functions
        PrintString("                      --------please select option--------");
        PrintString("1 - house level  options");
        PrintString("2 - room level options");
        PrintString("3 - plug level options");
        PrintString("4 - system options");
        int OptionLevel= GetInt("");
        switch(OptionLevel){
            case 1:
                SH.HouseLevelOptions(GetInt("HOUSE LEVEL OPTIONS\n 1 - Switch all plugs off\n 2 - Switch all plugs on\n Select an option: "));
                break;
            case 2:
                PrintString("ROOMS AVAILABLE: ");
                PrintString(SH.DisplayRooms());
                SH.RoomLevelOptions(GetInt("Please select room (integer only)"));
                break;
            case 3:
                SH.SmartPlugLevelOptions();
                break;
            case 4:
                SH.SystemLevelOptions();
                break;
            default:
                PrintString("please enter a valid option");
        }
    }

    public void Dashboard(SmartHome SH){//this functions displays the dashboard's context by calling the DisplayDashboard within the SmartHome Class
        PrintString("                       --------DASHBOARD--------");
        PrintString(SH.DisplayDashboard());

    }
}
