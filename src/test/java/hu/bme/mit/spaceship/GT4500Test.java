package hu.bme.mit.spaceship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;

  @BeforeEach
  public void init(){
    this.mockTS1 = mock(TorpedoStore.class, withSettings().useConstructor(10));
    this.mockTS2 = mock(TorpedoStore.class, withSettings().useConstructor(10));
    this.ship = new GT4500(this.mockTS1, this.mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(1)).fire(anyInt());
    verify(mockTS2, never()).fire(anyInt());
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTS1, times(1)).fire(anyInt());
    verify(mockTS2, times(1)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_Single_Alternate_Second() {
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Egyet lovunk, es azt tudjuk, hogy az elso loves az elsobol jon
    ship.fireTorpedo(FiringMode.SINGLE);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS2, times(1)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_Single_Alternate_First() {
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Egyet lovunk, es azt tudjuk, hogy az elso loves az elsobol jon
    ship.fireTorpedo(FiringMode.SINGLE);
    // Lovunk meg egyet, es azt tudjuk, hogy a masodik loves a masodikbol jon
    ship.fireTorpedo(FiringMode.SINGLE);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(2)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_Single_Empty() {
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS2, times(1)).fire(anyInt());
  }

  @Test
  public void fireTorpedo_Single_Fail() {
    // Arrange
    when(mockTS1.fire(1)).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS2, never()).fire(anyInt());
  }

  @Test
  public void fireTorpedo_Single_BothEmpty() {
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, never()).fire(anyInt());
    verify(mockTS2, never()).fire(anyInt());
  }

}
