package MyCoursework;

public class SmartHome {
    private SmartPlug[] SmartPlugs;

    private String[] RoomList;

    ConsoleHelper CH =new ConsoleHelper();

    String [] DeviceList=new String[5];// this function creates an array for the devices to be attached with a size 5

    public void Devices(){             //this function populates the array with the default device options
        DeviceList[0]="Lamp";
        DeviceList[1]="TV";
        DeviceList[2]="Computer";
        DeviceList[3]="Phone Recharger";
        DeviceList[4]="Heater";
    }

    public SmartHome(int PlugSize,int RoomSize){//sets the size of SmartPlugs and RoomList array
        SmartPlugs =new SmartPlug[PlugSize];
        RoomList=new String[RoomSize];
    }

    public void AddRooms(String RoomName,int RoomID){//This function gets the data from PopulateRooms function within ConsoleHelper and creates a String array of the rooms
        RoomList[RoomID-1]=RoomName;
    }

    public void AddNewDevices(String Device){               //First, they copy the elements of the DeviceList, RoomList, SmartPlugs array accordingly to a new temporary array of the same type but with a size that is bigger
        String[] TempArray=new String[DeviceList.length+1];//than the size of the original array by one. After the copying is done the Temporary array’s last index is filled with the input of the user.
        for(int i=0;i<DeviceList.length;i++){              //And after that I make the original array equal to the temporary array so that it gets updated every time the user wants to add new rooms, devices or plugs.
            TempArray[i]=DeviceList[i];
        }
        TempArray[TempArray.length-1]=Device;
        DeviceList=TempArray;
    }

    public void AddNewRooms(String RoomName){
        String[] TempArray=new String[RoomList.length+1];
        for(int i=0;i<RoomList.length;i++){
            TempArray[i]=RoomList[i];
        }
        TempArray[TempArray.length-1]=RoomName;
        RoomList=TempArray;
    }

    public void AddNewPlugs(){
        SmartPlug[] TempArray = new SmartPlug[SmartPlugs.length+1];
        for(int i=0;i<SmartPlugs.length;i++){
            TempArray[i]=SmartPlugs[i];
        }
        CH.RequestPlugInfo();
        CH.PrintString(DisplayRooms());
        int RoomId= CH.GetInt("Using the above list, please select the room for this plug (integer only)");
        CH.PrintString("AVAILABLE DEVICE LIST OPTIONS\n" + "These are standard devices that can be attached to the smart plug: ");
        for(int i=0;i<DeviceList.length;i++){
            CH.PrintString((i+1)+" - "+DeviceList[i]);
        }
        int DeviceListOption = CH.GetInt("Using the above list, please select the device to attach to the smart plug (integer only)");
        Devices();
        SmartPlug Object = new SmartPlug(false,RoomId,DeviceList[DeviceListOption-1], TempArray.length, RoomList[RoomId-1]);
        TempArray[TempArray.length-1]=Object;
        SmartPlugs=TempArray;
    }

    public void PopulatePlugs(int PlugSize){  //this function populates the SmartPlugs array with the input it receives from the console
        for (int i = 0; i < PlugSize; i++) {
            CH.RequestPlugInfo();
            CH.PrintString(DisplayRooms());
            int RoomId= CH.GetInt("Using the above list, please select the room for this plug (integer only)");
            CH.PrintString("AVAILABLE DEVICE LIST OPTIONS\n" + "These are standard devices that can be attached to the smart plug: ");
            CH.PrintString("1-Lamp\n" + "2-TV\n" + "3-Computer\n" + "4-Phone Recharger\n" + "5-Heater\n");
            int DeviceListOption = CH.GetInt("Using the above list, please select the device to attach to the smart plug (integer only)");
            Devices();
            SmartPlug Object = new SmartPlug(false,RoomId,DeviceList[DeviceListOption-1],(i+1),RoomList[RoomId-1]);
            SmartPlugs[i]=Object;
        }
    }

    public String DisplayRooms(){// This function gets all of the room names from the RoomList array and returns the as a string to the PopulatePlugs function
        String s= "";
        for(int i=0;i<RoomList.length;i++){
            s+=i+1+" - "+RoomList[i]+" | ";
        }
        return s;
    }

    public String DisplayDashboard(){//This function gets all the Room information from the RoomList array and all the plugs information from the PlugInfo function within the
        String s= "";                //SmartPlug class and returns it all as a string to the Dashboard function within Console helper.
        for(int i=0;i<RoomList.length;i++){
            s+="ROOM: "+(i+1)+"\n";
            for(SmartPlug Object : SmartPlugs){
                if(Object.getRoomId()==i+1){
                    s+=Object.PlugInfo();
                }
            }
        }
        return s;
    }

    public void SwitchOffEverything(){//This function with the help of a for loop goes through all the plugs status and turns them off
        for(SmartPlug Object: SmartPlugs){
            Object.setStatus(false);
            }
        }

    public void SwitchOnEverything(){//This function with the help of a for loop goes through all the plugs status and turns them on
        for(SmartPlug object: SmartPlugs){
                object.setStatus(true);
        }
    }

    public void HouseLevelOptions(int Option){//it gets called from MenuOptions function within ConsoleHelper,
        switch(Option){                       // gets input from console and according to the input calls the  desired function to complete the process
            case 1:
                SwitchOffEverything();
                break;
            case 2:
                SwitchOnEverything();
                break;
            default:
                CH.PrintString("please enter a valid option");

        }
    }

    public void RoomLevelOptions(int RoomID){//it gets called from the MenuOptions function within ConsoleHelper class,asks the user for input and then
        for(SmartPlug Object: SmartPlugs){//according to the input inserted by the user completes the desired process using a switch
            if(Object.getRoomId()==RoomID){CH.PrintString(Object.PlugInfo());}
        }
        CH.PrintString("1 - Switch all plugs off in room\n2 - Switch all plugs on in room\n3 - Select a plug in the room and toggle it's on/off status");
        int Option=CH.GetInt("Select an option");
        switch(Option){
            case 1:
                for(SmartPlug Object: SmartPlugs){
                    if(Object.getRoomId()==RoomID){
                        Object.setStatus(false);
                    }
                }
                break;
            case 2:
                for(SmartPlug Object: SmartPlugs){
                    if(Object.getRoomId()==RoomID){
                        Object.setStatus(true);
                    }
                }
                break;
            case 3:
                int PlugId=CH.GetInt("Insert the ID of the plug you want to toggle: ");
                for(SmartPlug Object: SmartPlugs){
                    if(Object.getRoomId()==RoomID && Object.getPlugId()==PlugId){
                        Object.toggle();
                    }
                }
                break;
            default: CH.PrintString("please enter a valid option");
        }
    }

    public void SmartPlugLevelOptions(){//it gets called from the MenuOptions function within the ConsoleHelper class,asks the user for input
        for(SmartPlug Object: SmartPlugs){//and according to the input calls the appropriate function
            CH.PrintString(Object.PlugInfo());
        }
        int PlugId= CH.GetInt("Please select plug(integer only)");
        CH.PrintString("PLUG LEVEL OPTIONS\n1 - Switch plug off\n2 - Switch plug on\n3 - Change attached device\n4 - Move plug to different room\n");
        int Option=CH.GetInt("Select an option");
        switch(Option){
            case 1:
                for(SmartPlug Object: SmartPlugs){
                        if(Object.getPlugId()==PlugId){Object.setStatus(false);}
                }
                break;
            case 2:
                for(SmartPlug Object: SmartPlugs){
                        if(Object.getPlugId()==PlugId){Object.setStatus(true);}
                }
                break;
            case 3:
                CH.PrintString("AVAILABLE DEVICE LIST OPTIONS\n" + "These are standard devices attached to the smart plug, unless otherwise stated ");
                for(int i=0;i<DeviceList.length;i++){
                CH.PrintString((i+1)+" - "+DeviceList[i]);
                }
                int DeviceOption = CH.GetInt("Enter device to attach to smart plug(integer only)");
                for(SmartPlug Object: SmartPlugs){
                        if(Object.getPlugId()==PlugId){
                            Devices();
                            Object.setDeviceAttached(DeviceList[DeviceOption-1]);
                        }
                }
                break;
            case 4:
                CH.PrintString("ROOMS AVAILABLE:");
                CH.PrintString(DisplayRooms());
                int RoomId=CH.GetInt("Please select room for device from list(integer only)");
                for(SmartPlug Object: SmartPlugs){
                    if(Object.getPlugId()==PlugId){
                        Object.setRoomId(RoomId);
                        Object.setRoomName(RoomList[RoomId-1]);
                    }
                }
                break;
            default: CH.PrintString("please enter a valid option");
        }
    }

    public void SystemLevelOptions(){//gets called from the MenuOptions function within the console helper,asks the user for input and according
        CH.PrintString("SYSTEM LEVEL OPTIONS\n1 - Add more plugs\n2 - Add more devices\n3 - Add more rooms");//to the input calls the function to complete the desired process
        int Option=CH.GetInt("Select an option");
        switch(Option){
            case 1:
                AddNewPlugs();
                break;
            case 2:
                String Device=CH.GetLine("What device do you want to add to the device list");
                AddNewDevices(Device);
                break;
            case 3:
                String RoomName=CH.GetLine("Insert the name of the room you want to insert: ");
                AddNewRooms(RoomName);
                break;
            default:CH.PrintString("please enter a valid option");
        }
    }
}

