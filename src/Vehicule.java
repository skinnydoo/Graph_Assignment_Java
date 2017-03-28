/**
 * Created by Simel on 3/27/2017.
 */

public class Vehicule {

    private String vehiculeType_;
    private int maxMileage_;
    private int currentMileage_;

    /**
     * Contructor
     * @param vehiculeType type of the vehicule (fuel, electric, hybrid)
     * @param currentMileage current mileage of the vehicule
     * @param maxMileage maximum allowed mileage to run with
     */
    public Vehicule(String vehiculeType, int currentMileage, int maxMileage) {

        vehiculeType_ = vehiculeType;
        currentMileage_ = currentMileage;
        maxMileage_ = maxMileage;
    }

    /**
     * Return the vehicule type
     * @return the vehicule type
     */
    public String getVehiculeType () {

        return vehiculeType_;
    }

    /**
     * set the current mileage of the vehicule
     * @param currentMileage current mileage of the Vehicule
     */
    public void setCurrentMileage (int currentMileage) {

        currentMileage_ = currentMileage;
    }

    /**
     * return the current mileage
     * @return the current mileage
     */
    public int getCurrentMileage() {

        return currentMileage_;
    }


    /**
     * return the max allowed mileage to run with
     * @return the maximum mileage
     */
    public int getMaxMileage_() {

        return maxMileage_;
    }
}
