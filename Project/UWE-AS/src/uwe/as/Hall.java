package uwe.as;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class Hall {

    private int UID;
    private int wardenUID;
    private String name;
    private String number;
    private String address;
    private String telephoneNumber;
    private List<Integer> roomUIDs;
    public static Data_Cache data_cache;

    public Hall(String name, String number, String address, String telephone) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
        if (number != null) {
            this.number = number;
        } else {
            this.number = "";
        }
        if (address != null) {
            this.address = address;
        } else {
            this.address = "";
        }
        if (telephone != null) {
            this.telephoneNumber = telephone;
        } else {
            this.telephoneNumber = "";
        }
        data_cache.createHall(this);
    }

    public Hall(int UID, int wardenUID, String name, String number, String address, String telephoneNumber) {
        this.UID = UID;
        this.wardenUID = wardenUID;
        this.name = name;
        this.number = number;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.roomUIDs = new ArrayList<Integer>();
        data_cache.addHall(this);
    }

    public int getUID() {
        return this.UID;
    }

    public int getWardenUID() {
        return this.wardenUID;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public String getAddress() {
        return this.address;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public List<Integer> getRooms() {
        return roomUIDs;
    }

    public void addRoom(int room) {
        if (roomUIDs.contains(room) == false) {
            roomUIDs.add(room);
        }
    }

    public void removeRoom(int room) {
        if (roomUIDs.contains(room)) {
            roomUIDs.remove(room);
        }
    }

    public void modifyWarden(int warden) {
        this.wardenUID = warden;
        data_cache.updateHall(this);
    }

    public void modifyAddress(String address) {
        if (address != null) {
            if (!address.equals("")) {
                this.address = address;
            }
        }
        data_cache.updateHall(this);
    }

    public void modifyTelephone(String newNumber) {
        if (telephoneNumber != null) {
            if (!telephoneNumber.equals("")) {
                this.telephoneNumber = newNumber;
            }
        }
        data_cache.updateHall(this);
    }

    public void modifyName(String newName) {
        if (newName != null) {
            if (!newName.equals("")) {
                this.name = newName;
            }
        }
        data_cache.updateHall(this);
    }

    public void modifyNumber(String newNumber) {
        if (newNumber != null) {
            if (!newNumber.equals("")) {
                this.number = newNumber;
            }
        }
        data_cache.updateHall(this);
    }
}
