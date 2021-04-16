package hu.bme.mit.spaceship;

/**
* A simple spaceship with two proton torpedo stores and four lasers
*/
public class GT4500 implements hu.bme.mit.spaceship.SpaceShip {

  private hu.bme.mit.spaceship.TorpedoStore primaryTorpedoStore;
  private hu.bme.mit.spaceship.TorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;

  public GT4500() {
    this.primaryTorpedoStore = new hu.bme.mit.spaceship.TorpedoStore(10);
    this.secondaryTorpedoStore = new hu.bme.mit.spaceship.TorpedoStore(10);
  }

  public boolean fireLaser(hu.bme.mit.spaceship.FiringMode firingMode) {
    // TODO not implemented yet
    return false;
  }

  /**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty, the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
  */
  @Override
  public boolean fireTorpedo(hu.bme.mit.spaceship.FiringMode firingMode) {

    boolean firingSuccess = false;

    switch (firingMode) {
      case SINGLE:
        if (wasPrimaryFiredLast) {
          // try to fire the secondary first - this is a cooler comment yes thats true
          if (! secondaryTorpedoStore.isEmpty()) {
            firingSuccess = secondaryTorpedoStore.fire(1);
            wasPrimaryFiredLast = false;
          }
          else {
            // although primary was fired last time, but the secondary is empty
            // thus try to fire primary again
            if (! primaryTorpedoStore.isEmpty()) {
              firingSuccess = primaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = true;
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
        }
        else {
          // try to fire the primary first
          if (! primaryTorpedoStore.isEmpty()) {
            firingSuccess = primaryTorpedoStore.fire(1);
            wasPrimaryFiredLast = true;
          }
          else {
            // although secondary was fired last time, but primary is empty
            // thus try to fire secondary again
            if (! secondaryTorpedoStore.isEmpty()) {
              firingSuccess = secondaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = false;
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
        }
        break;

      case ALL:
        // try to fire both of the torpedo stores
        boolean firingSuccessPrimary = false;
        boolean firingSuccessSecondary = false;
        if (! primaryTorpedoStore.isEmpty()) {
          firingSuccessPrimary = primaryTorpedoStore.fire(1);
        }
        if (! secondaryTorpedoStore.isEmpty()) {
          firingSuccessSecondary = secondaryTorpedoStore.fire(1);
        }
        // success when at least one torpedo was successful
        firingSuccess = firingSuccessPrimary || firingSuccessSecondary;
        break;
    }

    return firingSuccess;
  }

}
