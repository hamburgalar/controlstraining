// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    motor1.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor1.setIdleMode(IdleMode.kCoast);
    motor2.setIdleMode(IdleMode.kCoast);
  }

  public double getSpeed(double time){
    if(time < 4) return time / 4;
    if(time < 14) return 1.0;
    if(time < 18) return (1.0-(time-14)/4);
    return 0;
  }

  //@Override
  boolean runmotor;
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(distanceSensor.get());
    motor1.set(Double.valueOf(1-distanceSensor.get()));




    /*
    if(controller.getAButtonPressed()){
      timer.reset();
      timer.start();
      runmotor = true;
    }
    if(runmotor){
      motor1.set(getSpeed(timer.get()));
    }
    */






    //motor1.set(controller.getLeftY());
    //motor2.follow(motor1);
    //motor2.set(controller.getLeftY());

  }

  private final Timer timer = new Timer();
  private final CANSparkMax motor1 = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax motor2 = new CANSparkMax(17, MotorType.kBrushless);
  private final AnalogPotentiometer distanceSensor = new AnalogPotentiometer(0);


  private final XboxController controller = new XboxController(0);
  //private final JoystickButton button = new JoystickButton(controller, XboxController.Button.kA.value);

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
