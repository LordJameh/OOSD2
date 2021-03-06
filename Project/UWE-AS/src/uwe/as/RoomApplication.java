package uwe.as;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class RoomApplication {

    private int UID;
    private int roomUID;
    private Date date;
    private int duration;
    private int studentUID;
    public static Data_Cache data_cache;

    public RoomApplication(int roomUID, Date date, int duration, int studentUID) {
        this.roomUID = roomUID;
        this.date = date;
        this.duration = duration;
        this.studentUID = studentUID;
        data_cache.createApplication(this);
    }

    public RoomApplication(int UID, int roomUID, Date date, int duration, int studentUID) {
        this.UID = UID;
        this.roomUID = roomUID;
        this.date = date;
        this.duration = duration;
        this.studentUID = studentUID;
        data_cache.addApplication(this);
    }

    public void approveApplication() {
        List<Lease> leases = data_cache.getLeases();
        if (leases != null)
        {
            if (!leases.isEmpty())
            {
                int lastNumber = leases.get(leases.size()-1).getLeaseNumber();
                new Lease(lastNumber+1, this.studentUID, this.roomUID, this.duration, this.date);
                data_cache.removeApplication(this);
            }
        }
        
    }

    public void refuseApplication() {
        data_cache.removeApplication(this);
    }

    public int getUID() {
        return this.UID;
    }

    public int getRoomUID() {
        return this.roomUID;
    }

    public Date getDate() {
        return this.date;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getStudentUID() {
        return this.studentUID;
    }

    public Date getEndDate() {
        // TODO
        return this.date;
    }
}
