package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  @BeforeEach
  public void init(){
    this.primaryTorpedoStore = mock(TorpedoStore.class);
    this.secondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryTorpedoStore, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Primary_Empty() {
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primaryTorpedoStore, times(1)).isEmpty();
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Primary_Fail() {
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primaryTorpedoStore, times(1)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Secondary_Fail() {
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn()
  }

}
