package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore ts1;
  private TorpedoStore ts2;

  @BeforeEach
  public void init(){
    this.ts1 = mock(TorpedoStore.class, withSettings().useConstructor(10));
    this.ts2 = mock(TorpedoStore.class, withSettings().useConstructor(10));
    this.ship = new GT4500(this.ts1, this.ts2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

}
