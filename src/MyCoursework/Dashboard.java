package MyCoursework;

public class Dashboard {

    public static void main(String[] args) {
        ConsoleHelper CH= new ConsoleHelper();
        int RoomSize= CH.GetInt("How many rooms are there in this property? ");
        int PlugSize=CH.GetInt("How many plugs do you want to place in this property? ");
        SmartHome SH=new SmartHome(PlugSize,RoomSize);
        CH.PopulateRooms(SH,RoomSize);
        SH.PopulatePlugs(PlugSize);
        while(true){
            CH.Dashboard(SH);
            CH.MenuOptions(SH);
        }
    }
}
