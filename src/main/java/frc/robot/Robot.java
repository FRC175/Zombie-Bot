package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Jaguar;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.*;



public class Robot extends TimedRobot {
  private final Victor left = new Victor(0);
  private final Victor right = new Victor(1);
  private final Jaguar pickupA = new Jaguar(2);
  private final Jaguar pickupB = new Jaguar(3);
  private final Jaguar adjuster = new Jaguar(4);
  private final Jaguar shooter = new Jaguar(5);
  private final DoubleSolenoid shooterStand = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 4, 5);
  private final DoubleSolenoid intake = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 6, 7);
  // private final AdvancedXboxController driverController = new AdvancedXboxController(0, 0.15);
  private final Joystick driver = new Joystick(0);
  private final JoystickButton eight = new JoystickButton(driver, 8);
  private final JoystickButton seven = new JoystickButton(driver, 7);
  private final JoystickButton nine = new JoystickButton(driver, 9);
  private double throttle;
  private double turn;
  private double shootP;
  private int inverse = 1;

  @Override
  public void robotInit() {
    right.setInverted(true);
    pickupA.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    throttle = -driver.getY();
    turn = driver.getX();
    shootP = 0.6;

    right.set((throttle - turn) * inverse);
    left.set((throttle + turn) * inverse);
    
    // if (driverController.getAButtonPressed()) {
    //   pickupA.set(1);
    //   pickupB.set(1);
    //   // intake.set(DoubleSolenoid.Value.kReverse);
    // } else if (driverController.getBButtonPressed()) {
    //   pickupA.set(-1);
    //   pickupB.set(-1);
    // } else if (driverController.getAButtonReleased() || driverController.getBButtonReleased()) {
    //   pickupA.set(0);
    //   pickupB.set(0);
    //   // intake.set(DoubleSolenoid.Value.kForward);
    // }
    
    if (driver.getTrigger()) shooter.set(shootP);
    else  shooter.set(0);
    



    if (seven.getAsBoolean()) {
      adjuster.set(-0.75);
    } else if (eight.getAsBoolean()) {
      adjuster.set(0.75);
    } else {
      adjuster.set(0);
    }

    if (nine.getAsBoolean()) {
      shooterStand.set(DoubleSolenoid.Value.kForward);
    } else {
      shooterStand.set(DoubleSolenoid.Value.kReverse);
    }

    // if (driverController.getYButtonPressed()) {
    //   inverse = inverse == 1 ? -1 : 1;
    // }

    
    
  }
}
