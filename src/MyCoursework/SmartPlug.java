package MyCoursework;

public class SmartPlug {
    private boolean Status;
    private int RoomId;
    private String DeviceAttached;
    private int PlugId;
    private String RoomName;

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public String getDeviceAttached() {
        return DeviceAttached;
    }

    public void setDeviceAttached(String deviceAttached) {
        DeviceAttached = deviceAttached;
    }

    public int getPlugId() {
        return PlugId;
    }

    public void setPlugId(int plugId) {
        PlugId = plugId;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public SmartPlug(boolean Status, int RoomID, String DeviceAttached, int PlugId, String RoomName) {
        this.RoomId = RoomID;
        this.Status =Status;
        this.DeviceAttached=DeviceAttached;
        this.PlugId=PlugId;
        this.RoomName = RoomName;
    }

    public String toString() {
        return "status: " + Status +
                "| location: " + RoomName +
                "| ID: " + RoomId +
                "|";
    }

    public String PlugInfo(){
        String s= "";
                s+="SmartPlug |attached to: "+DeviceAttached +"        "+ "|room: "+RoomName+ " | ID: "+PlugId+"|status: " + booleanString(Status)+"|\n";
        return s;
    }

    public void toggle(){
        setStatus(!getStatus());
    }

    private String booleanString(boolean status){// this function according to the value of the status
        if(status){return"on";}                  //turns it into an on or off string value
        return "off";
    }
}
